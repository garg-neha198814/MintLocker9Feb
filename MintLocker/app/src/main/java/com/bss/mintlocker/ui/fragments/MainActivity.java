package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bss.mintlocker.R;
import com.bss.mintlocker.adapter.ActivityAdapter;
import com.bss.mintlocker.model.ActivityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class MainActivity extends Fragment implements View.OnClickListener {

    public MainActivity() {

    }

    public static final String[] showdate = new String[]{
            "1",
            "0", "0",
            "1", "0",
            "1", "0",
            "0"};


    public static final String[] showMonth = new String[]{
            "August 2015",
            "August 2015", "August 2015",
            "September 2015", "September 2015",
            "October 2015", "October 2015",
            "October 2015"};


    public static final String[] showdownload = new String[]{
            "1",
            "0", "0",
            "1", "0",
            "1", "0",
            "0"};



    public static final String[] title = new String[]{
            "Dividend Reinvestment",
            "Dividend Reinvestment", "Dividend Reinvestment",
            "Market Changes", "Market Changes",
            "Tax forms", "Tax forms",
            "Intial Investment Document"};


    public static final String[] largeamount = new String[]{
            "0.17",
            "0.17", "0.17",
            "0.16", "0.16",
            "42.17", "42.17",
            "0.19"};


    public static final String[] smallamount = new String[]{
            "444.45",
            "456.44", "111.66",
            "444.66", "77.87",
            "33.88", "456.44",
            "11.11"};

    public static final String[] smallDate = new String[]{
            "Aug 15",
            "Aug 20", "Aug 25",
            "Sept 18", "Sept 22",
            "Oct 16", "Oct 24",
            "Oct29"};
    ListView listView;
    List<ActivityModel> rowItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) v.findViewById(R.id.activity_list);

        rowItems = new ArrayList<ActivityModel>();
        for (int i = 0; i < title.length; i++) {
            ActivityModel item = new ActivityModel(showdate[i], showMonth[i], title[i], smallDate[i],largeamount[i],smallamount[i],showdownload[i]);
            rowItems.add(item);
        }

//setting dummy adapter


        ActivityAdapter adapter = new ActivityAdapter(getActivity(), rowItems);
        listView.setAdapter(adapter);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}

