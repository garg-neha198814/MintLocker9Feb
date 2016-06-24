package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bss.mintlocker.R;
import com.bss.mintlocker.adapter.OncePerMonthAdapter;
import com.bss.mintlocker.landing.LandingScreenActivity;
import com.bss.mintlocker.model.OncePerMonthModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class OncePerMonth extends Fragment implements View.OnClickListener {

    public OncePerMonth() {

    }

    int monthMaxDays = 0;

    GridView gridView;
    List<OncePerMonthModel> rowItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_once_per_month, container, false);

        LandingScreenActivity.mToolbarTitle.setText("Deposit");
        gridView = (GridView) v.findViewById(R.id.once_per_month_gridview);

        Calendar c = Calendar.getInstance();
        monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        int[] arrayVal = new int[monthMaxDays];

        for (int j = 1; j <= monthMaxDays; j++) {
            arrayVal[j - 1] = j;
        }


        System.out.println("Size>>" + arrayVal.length);

        //setting dummy list
        rowItems = new ArrayList<OncePerMonthModel>();
        for (int i = 0; i < arrayVal.length; i++) {
            OncePerMonthModel item = new OncePerMonthModel(arrayVal[i]);
            rowItems.add(item);
        }

        System.out.println("Size11>>" + rowItems.size());


        for (int i = 0; i < arrayVal.length; i++) {
            System.out.println("value are >>" + arrayVal[i]);
        }
//setting dummy adapter
        OncePerMonthAdapter adapter = new OncePerMonthAdapter(getActivity(), rowItems);
        gridView.setAdapter(adapter);
        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
