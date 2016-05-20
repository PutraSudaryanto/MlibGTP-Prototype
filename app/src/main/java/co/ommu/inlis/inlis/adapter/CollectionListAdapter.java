package co.ommu.inlis.inlis.adapter;

import java.util.ArrayList;

import co.ommu.inlis.R;
import co.ommu.inlis.inlis.model.CollectionListModel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.MyViewHolder> {

    private List<CollectionListModel> collList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView location, year, genre;

        public MyViewHolder(View view) {
            super(view);
            location = (TextView) view.findViewById(R.id.tvtitle);
           // genre = (TextView) view.findViewById(R.id.genre);
           // year = (TextView) view.findViewById(R.id.year);
        }
    }


    public CollectionListAdapter(List<CollectionListModel> moviesList) {
        this.collList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_inlis_collection_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CollectionListModel item = collList.get(position);
        holder.location.setText(item.location);

    }

    @Override
    public int getItemCount() {
        return collList.size();
    }
}
