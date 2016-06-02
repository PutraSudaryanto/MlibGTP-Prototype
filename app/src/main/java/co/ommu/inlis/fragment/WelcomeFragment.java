package co.ommu.inlis.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.ommu.inlis.R;
import co.ommu.inlis.inlis.adapter.RecyclerViewBookAdapter;
import co.ommu.inlis.inlis.model.SectionBookModel;
import co.ommu.inlis.inlis.model.SingleBookItemModel;


public class WelcomeFragment extends Fragment {


    ArrayList<SectionBookModel> allSampleData;
    RecyclerView recyclerView;
    RecyclerViewBookAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);


        allSampleData = new ArrayList<SectionBookModel>();

        createDummyData();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewBookAdapter(getActivity(), allSampleData);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);


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
