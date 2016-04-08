package co.ommu.inlisjogja.inlis.adapter;

import java.util.ArrayList;

import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.inlis.model.CatalogSearchModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CatalogSearchAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<CatalogSearchModel> array;

    public CatalogSearchAdapter(ArrayList<CatalogSearchModel> array, Context context) {
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
            vi = inflater.inflate(R.layout.adapter_inlis_catalog_search, null);
            holder.tvTitle = (TextView) vi.findViewById(R.id.tvTitle);
            holder.tvAuthor = (TextView) vi.findViewById(R.id.tvAuthor);
            holder.tvPublisher = (TextView) vi.findViewById(R.id.tvPublisher);
            holder.tvPublisLocation = (TextView) vi.findViewById(R.id.tvPublisLocation);
            holder.tvPublisYear = (TextView) vi.findViewById(R.id.tvPublisYear);
            holder.tvSubject = (TextView) vi.findViewById(R.id.tvSubject);
            holder.tvIsbn = (TextView) vi.findViewById(R.id.tvIsbn);
            holder.tvCallNumber = (TextView) vi.findViewById(R.id.tvCallNumber);
            holder.tvWorksheet = (TextView) vi.findViewById(R.id.tvWorksheet);
            vi.setTag(holder);
        } else {
            holder = (Holder) vi.getTag();
        }

        holder.tvTitle.setText(array.get(position).title);
        holder.tvAuthor.setText(array.get(position).author);
        holder.tvPublisher.setText(array.get(position).publisher);
        holder.tvPublisLocation.setText(array.get(position).publish_location);
        holder.tvPublisYear.setText(array.get(position).publish_year);
        holder.tvSubject.setText(array.get(position).subject);
        holder.tvIsbn.setText(array.get(position).isbn);
        holder.tvCallNumber.setText(array.get(position).callnumber);
        holder.tvWorksheet.setText(array.get(position).worksheet);
		
        return vi;
    }

    static class Holder {
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvPublisher;
        TextView tvPublisLocation;
        TextView tvPublisYear;
        TextView tvSubject;
        TextView tvIsbn;
        TextView tvCallNumber;
        TextView tvWorksheet;
    }

}
