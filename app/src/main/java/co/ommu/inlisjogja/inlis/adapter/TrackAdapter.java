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


import java.util.ArrayList;


import co.ommu.inlisjogja.BookDetailActivity;
import co.ommu.inlisjogja.inlis.model.TrackMemberModel;
import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.components.OnLoadMoreListener;

/**
 * Created by KurniawanD on 4/27/2016.
 */
public class TrackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<TrackMemberModel> listItem;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    ProgressBar pb;
    Context context;
    Boolean statusTrack = true;


    public TrackAdapter(Context context, ArrayList<TrackMemberModel> item, RecyclerView mRecyclerView, Boolean status) {
        this.listItem = item;
        this.context = context;
        statusTrack = status;
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


        public TextView tvTitle;
        public TextView tvAuthor;
        public TextView tvSubject;
        public TextView tvPublish;
        public final View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
            tvSubject = (TextView) view.findViewById(R.id.tvSubject);
            tvPublish = (TextView) view.findViewById(R.id.tvPublish);
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
            View itemView = null;
            if(statusTrack)
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_inlis_track_member, parent, false);
            else
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_inlis_tracks, parent, false);


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


            TrackMemberModel model = listItem.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            myViewHolder.tvTitle.setText(model.title);
            if(!model.author.equals("-"))
                myViewHolder.tvAuthor.setText(model.author);
            if(!model.subject.equals("-"))
                myViewHolder.tvSubject.setText(model.subject);
            if(!model.publisher.equals("-") && !model.publish_year.equals("-"))
                myViewHolder.tvPublish.setText(model.publisher +" / "+ model.publish_year);
            else {
                if(!model.publisher.equals("-"))
                    myViewHolder.tvPublish.setText(model.publisher);
                else
                    myViewHolder.tvPublish.setText(model.publish_year);
            }


            myViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Context context = v.getContext();
//                    context.startActivity(new Intent(context, BookDetailActivity.class)
//                            .putExtra("id",item.id)
//                    );


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
