package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bss.mintlocker.R;
import com.bss.mintlocker.landing.LandingScreenActivity;
import com.bss.mintlocker.util.SharedPreferencesHandler;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class AutoDepositeNew extends Fragment implements View.OnClickListener {

    public AutoDepositeNew(String value) {

    }
    public AutoDepositeNew() {

    }
     private static EditText mPickDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_auto_deposit, container, false);

        LandingScreenActivity.mToolbarTitle.setText("Auto Deposit");


        mPickDate=(EditText)v.findViewById(R.id.auto_deposit_calender);
        mPickDate.setOnClickListener(this);






        return v;

    }

    @Override
    public void onResume() {
        super.onResume();

        String valueToSet="";
        try {
            valueToSet  = SharedPreferencesHandler.getStringValues(getActivity(), "vall");
        }catch (Exception e){
            e.printStackTrace();
        }



        if(valueToSet.equalsIgnoreCase("")||valueToSet.equalsIgnoreCase("null")||valueToSet==null){
            valueToSet="";
        }

//        Constants.showToast("value in activity_main_one screen click: " + valueToSet, 3000);

        mPickDate.setText(valueToSet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto_deposit_calender:

                FragmentManager fragmentManager4 = ((LandingScreenActivity) getActivity()).getSupportFragmentManager();

                FragmentTransaction transaction4 = fragmentManager4.beginTransaction();
                transaction4.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Fragment duedateFrag4 = new com.bss.mintlocker.ui.fragments.OncePerMonth();
                transaction4.replace(R.id.frame_container, duedateFrag4);
                transaction4.addToBackStack(null);
//                transaction4.remove(duedateFrag4);
                transaction4.commit();
//                fragmentManager4.popBackStack();


                break;
        }
    }
}
