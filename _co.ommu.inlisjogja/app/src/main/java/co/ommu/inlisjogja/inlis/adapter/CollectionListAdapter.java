package co.ommu.inlisjogja.inlis.adapter;

import java.util.ArrayList;

import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.inlis.model.CollectionListModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CollectionListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<CollectionListModel> array;

    public CollectionListAdapter(ArrayList<CollectionListModel> array, Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.array = array;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return array.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        // TODO Auto-generated method stub
        View vi = view;
        Holder holder;
        if (vi == null) {
            holder = new Holder();
            vi = inflater.inflate(R.layout.adapter_inlis_collection_list, null);
            holder.tvTitle = (TextView) vi.findViewById(R.id.tvTitle);
            holder.tvLocation = (TextView) vi.findViewById(R.id.tvLocation);
            holder.tvStatus = (TextView) vi.findViewById(R.id.tvStatus);
            vi.setTag(holder);
        } else {
            holder = (Holder) vi.getTag();
        }

        holder.tvTitle.setText(array.get(position).title);
        holder.tvLocation.setText(array.get(position).location);
        holder.tvStatus.setText(array.get(position).status);
		
        return vi;
    }

    static class Holder {
        TextView tvTitle;
        TextView tvLocation;
        TextView tvStatus;
    }

}
