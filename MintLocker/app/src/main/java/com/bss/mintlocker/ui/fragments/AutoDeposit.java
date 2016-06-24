package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.bss.mintlocker.R;
import com.bss.mintlocker.landing.LandingScreenActivity;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class AutoDeposit extends Fragment implements View.OnClickListener {
    de.hdodenhof.circleimageview.CircleImageView mOnceperMonth,mWeekonly;
    Switch mAutoDeposit;
    public AutoDeposit() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_auto_deposite, container, false);

        LandingScreenActivity.mToolbarTitle.setText("Auto Deposit");
        mOnceperMonth=(de.hdodenhof.circleimageview.CircleImageView)v.findViewById(R.id.oncepermonth);
        mWeekonly=(de.hdodenhof.circleimageview.CircleImageView)v.findViewById(R.id.everyweek);
        mAutoDeposit=(Switch)v.findViewById(R.id.auto_deposit_switch);

        mOnceperMonth.setOnClickListener(this);
        mWeekonly.setOnClickListener(this);

        mAutoDeposit.setChecked(false);
        mOnceperMonth.setAlpha(0.3f);
        mWeekonly.setAlpha(0.3f);
        mOnceperMonth.setEnabled(false);
        mWeekonly.setEnabled(false);

        mAutoDeposit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    mOnceperMonth.setAlpha(0.9f);
                    mWeekonly.setAlpha(0.9f);


                    mOnceperMonth.setEnabled(true);
                    mWeekonly.setEnabled(true);

                } else {
                    mOnceperMonth.setAlpha(0.3f);
                    mWeekonly.setAlpha(0.3f);
                    mOnceperMonth.setEnabled(false);
                    mWeekonly.setEnabled(false);

                }

            }
        });

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oncepermonth:

                FragmentManager fragmentManager3 = ((LandingScreenActivity) getActivity()).getSupportFragmentManager();

                FragmentTransaction transaction3 = fragmentManager3.beginTransaction();
                transaction3.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Fragment duedateFrag4 = new com.bss.mintlocker.ui.fragments.AutoDepositeNew();
                transaction3.replace(R.id.frame_container, duedateFrag4);
      transaction3.addToBackStack(null);
//                transaction3.remove(duedateFrag4);
                transaction3.commit();
//                fragmentManager4.popBackStack();



                break;

            case R.id.everyweek:






                break;
        }
    }
}