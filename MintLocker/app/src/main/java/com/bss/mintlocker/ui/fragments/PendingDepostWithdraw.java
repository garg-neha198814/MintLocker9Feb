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
public class PendingDepostWithdraw extends Fragment implements View.OnClickListener {

    public PendingDepostWithdraw() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pending_deposit_withdraw, container, false);
        LandingScreenActivity.mToolbarTitle.setText("Deposit/Withdraw");

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}