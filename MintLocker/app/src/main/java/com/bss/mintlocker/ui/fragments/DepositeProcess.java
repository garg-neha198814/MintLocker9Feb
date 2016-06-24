package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bss.mintlocker.R;
import com.bss.mintlocker.landing.LandingScreenActivity;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class DepositeProcess extends Fragment implements View.OnClickListener {

    public DepositeProcess() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_deposite_process, container, false);

        if(Transfer.clickedWhichButton.equalsIgnoreCase("d")){
            LandingScreenActivity.mToolbarTitle.setText("Deposit");
        }else if(Transfer.clickedWhichButton.equalsIgnoreCase("w")){
            LandingScreenActivity.mToolbarTitle.setText("Withdraw");
        }



        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}