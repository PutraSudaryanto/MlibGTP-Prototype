package co.ommu.inlisjogja.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.inlis.model.SectionBookModel;
import  java.util.ArrayList;
import android.support.v7.widget.RecyclerView;
import co.ommu.inlisjogja.inlis.adapter.RecyclerViewBookAdapter;
import android.support.v7.widget.LinearLayoutManager;
import co.ommu.inlisjogja.inlis.model.SingleBookItemModel;

public class BookFragment extends Fragment {

    ArrayList<SectionBookModel> allSampleData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        allSampleData = new ArrayList<SectionBookModel>();

        createDummyData();


        RecyclerView my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewBookAdapter adapter = new RecyclerViewBookAdapter(getActivity(), allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);

        return view;
    }
    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionBookModel dm = new SectionBookModel();

            dm.setHeaderTitle("Section " + i);

            ArrayList<SingleBookItemModel> singleItem = new ArrayList<SingleBookItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleBookItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
}
