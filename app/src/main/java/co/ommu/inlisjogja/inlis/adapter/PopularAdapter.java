package co.ommu.inlisjogja.inlis.adapter;

import java.util.ArrayList;
import java.util.List;

import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.inlis.model.PopularModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private ArrayList<PopularModel> array;
    private Context context;

    public PopularAdapter(Context context, ArrayList<PopularModel> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_inlis_tracks, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PopularModel model = array.get(position);

        holder.tvTitle.setText(model.title);
        if(!model.author.equals("-"))
            holder.tvAuthor.setText(model.author);
        if(!model.subject.equals("-"))
            holder.tvSubject.setText(model.subject);
        if(!model.publisher.equals("-") && !model.publish_year.equals("-"))
            holder.tvPublish.setText(model.publisher +" / "+ model.publish_year);
        else {
            if(!model.publisher.equals("-"))
                holder.tvPublish.setText(model.publisher);
            else
                holder.tvPublish.setText(model.publish_year);
        }

        /*
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return (null != array ? array.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public TextView tvTitle;
        public TextView tvAuthor;
        public TextView tvSubject;
        public TextView tvPublish;

        public ViewHolder(View view) {
            super(view);
            //mView = view;
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
            tvSubject = (TextView) view.findViewById(R.id.tvSubject);
            tvPublish = (TextView) view.findViewById(R.id.tvPublish);
        }
    }

}
