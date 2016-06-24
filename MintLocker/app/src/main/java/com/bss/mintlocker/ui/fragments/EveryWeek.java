package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bss.mintlocker.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class EveryWeek extends Fragment implements View.OnClickListener {
    ImageView mPrevious, mNext;
    TextView weekdaysToPrint;
    String dayOfTheWeek = "";

    public static final String[] weekDaysName = new String[]{
            "Sunday",
            "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};
    int valuetoPrintFromArray = 0;
    public EveryWeek() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_every_week, container, false);
        mPrevious = (ImageView) v.findViewById(R.id.every_week_previous);
        mNext = (ImageView) v.findViewById(R.id.every_week_next);
        weekdaysToPrint = (TextView) v.findViewById(R.id.every_week_weekdays);

        mPrevious.setOnClickListener(this);
        mNext.setOnClickListener(this);


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);


        for (int i = 0; i < weekDaysName.length; i++) {
//            System.out.println("value are >>"+weekDaysName[i]);

            if (dayOfTheWeek.equalsIgnoreCase(weekDaysName[i])) {

                valuetoPrintFromArray = i;

                break;

            }


        }

        weekdaysToPrint.setText(weekDaysName[valuetoPrintFromArray]);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.every_week_previous:

                if (valuetoPrintFromArray != 0) {
                    valuetoPrintFromArray--;
                    weekdaysToPrint.setText(weekDaysName[valuetoPrintFromArray]);


                }


                break;

            case R.id.every_week_next:

                if (valuetoPrintFromArray < weekDaysName.length-1) {
                    valuetoPrintFromArray++;
                    weekdaysToPrint.setText(weekDaysName[valuetoPrintFromArray]);


                }

                break;

        }
    }
}