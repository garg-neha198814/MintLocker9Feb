package com.bss.mintlocker.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bss.mintlocker.ui.activities.Home;
import com.bss.mintlocker.R;
import com.bss.mintlocker.util.SharedPreferencesHandler;

public class Settings extends  Fragment implements View.OnClickListener {
    Toolbar toolbar;

    RelativeLayout updateRelativeLayout, feedbackRelativeLayout, rateRelativeLayout, pinRelativeLayout, helpRelativeLayout, financeRelativeLayout,mLogout;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_settings);
//
//        //inflate toolbar
//        inflateToolbar();
//
//        // initialize view
//        initialiseView();
//    }

//


    public Settings() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        updateRelativeLayout = (RelativeLayout) v.findViewById(R.id.update);
        feedbackRelativeLayout = (RelativeLayout) v.findViewById(R.id.feedback);
        rateRelativeLayout = (RelativeLayout) v.findViewById(R.id.rate);
        pinRelativeLayout = (RelativeLayout) v.findViewById(R.id.pin);
        helpRelativeLayout = (RelativeLayout) v.findViewById(R.id.help);
        financeRelativeLayout = (RelativeLayout) v.findViewById(R.id.finance);
        mLogout = (RelativeLayout) v.findViewById(R.id.logout);

        updateRelativeLayout.setOnClickListener(this);
        feedbackRelativeLayout.setOnClickListener(this);
        rateRelativeLayout.setOnClickListener(this);
        pinRelativeLayout.setOnClickListener(this);
        helpRelativeLayout.setOnClickListener(this);
        financeRelativeLayout.setOnClickListener(this);
        mLogout.setOnClickListener(this);

        return v;

    }




    // function to initialise the view
    public void initialiseView() {

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:

                break;
            case R.id.feedback:

                break;
            case R.id.rate:

                break;
            case R.id.pin:

                break;
            case R.id.help:

                break;
            case R.id.finance:

                break;
            case R.id.logout:
                SharedPreferencesHandler.setStringValues(getActivity(), "login", "");
                startActivity(new Intent(getActivity(), Home.class));
                getActivity().finish();

                break;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
////            finish();
//            return super.onKeyDown(keyCode, event);
//        }
//        return false;
//    }
}