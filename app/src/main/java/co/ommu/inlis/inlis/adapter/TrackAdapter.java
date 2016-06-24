package co.ommu.inlis.inlis.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.ommu.inlis.R;
import co.ommu.inlis.components.OnLoadMoreListener;
import co.ommu.inlis.inlis.model.TrackMemberModel;

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
        public RelativeLayout btnMoreMenu;


        public MyViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
            tvSubject = (TextView) view.findViewById(R.id.tvSubject);
            tvPublish = (TextView) view.findViewById(R.id.tvPublish);
            btnMoreMenu = (RelativeLayout) view.findViewById(R.id.rl_more_menu);
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {

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



            final PopupMenu popup = new PopupMenu(this.context, myViewHolder.btnMoreMenu);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.others, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_settings:
                            // do what you need.
                            Toast.makeText(context, "setting_" + position, Toast.LENGTH_LONG).show();
                            break;
                        case R.id.action_search:
                            // do what you need.
                            Toast.makeText(context, "search_" + position, Toast.LENGTH_LONG).show();
                            break;

                        default:
                            return false;
                    }
                    return false;
                }
            });

            myViewHolder.btnMoreMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    popup.show();

                }
            });



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
