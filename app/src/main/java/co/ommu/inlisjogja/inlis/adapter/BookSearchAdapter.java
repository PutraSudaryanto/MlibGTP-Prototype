package co.ommu.inlisjogja.inlis.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import com.bumptech.glide.Glide;


import java.util.ArrayList;


import co.ommu.inlisjogja.BookDetailActivity;
import co.ommu.inlisjogja.inlis.model.CatalogSearchModel;
import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.components.OnLoadMoreListener;

/**
 * Created by KurniawanD on 4/27/2016.
 */
public class BookSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<CatalogSearchModel> listItem;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    ProgressBar pb;
    Context context;


    public BookSearchAdapter(Context context, ArrayList<CatalogSearchModel> item, RecyclerView mRecyclerView) {
        this.listItem = item;
        this.context = context;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }

        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, category, date;
        public ImageView img;
        public final View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.tvTitle);
            category = (TextView) view.findViewById(R.id.tvCategory);
            date = (TextView) view.findViewById(R.id.tvDate);
           // img = (ImageView) view.findViewById(R.id.imgView);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar pbKit;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            pbKit = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return listItem.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_book, parent, false);
            return new MyViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {

           final CatalogSearchModel item = listItem.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.title.setText(item.title);
            myViewHolder.date.setText(item.publish_year);
            myViewHolder.category.setText(item.author);

//            myViewHolder.img;
//            Glide.with(context).load(item.imgUrl.replace(" ", "%20")).centerCrop().into(myViewHolder.img);
            myViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    context.startActivity(new Intent(context, BookDetailActivity.class)
                            .putExtra("id",item.id)
                    );


                }
            });

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.pbKit= pb;
        }


    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return listItem == null ? 0 : listItem.size();
    }
}
