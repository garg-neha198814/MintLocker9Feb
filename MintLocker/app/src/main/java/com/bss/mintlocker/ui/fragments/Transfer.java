package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bss.mintlocker.R;
import com.bss.mintlocker.landing.LandingScreenActivity;


/**
 * Created by bhawanisingh on 18/11/15.
 */
public class Transfer extends Fragment implements View.OnClickListener {

    public Transfer() {

    }

    LinearLayout mWidthraw,mdeposit,pendingTransfer,mAutoDepost;

    public  static  String clickedWhichButton="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transfer, container, false);
        LandingScreenActivity.mToolbarTitle.setText("Transfer");
        mdeposit=(LinearLayout)v.findViewById(R.id.transfer_deposit);
        mWidthraw=(LinearLayout)v.findViewById(R.id.transfer_withdraw);
        pendingTransfer=(LinearLayout)v.findViewById(R.id.transfer_pendingtransfer);
        mAutoDepost=(LinearLayout)v.findViewById(R.id.transfer_autodepost);
        mdeposit.setOnClickListener(this);
        mWidthraw.setOnClickListener(this);
        pendingTransfer.setOnClickListener(this);
        mAutoDepost.setOnClickListener(this);


        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.transfer_deposit:
                clickedWhichButton="d";

                FragmentManager fragmentManager = ((LandingScreenActivity) getActivity()).getSupportFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Fragment duedateFrag = new com.bss.mintlocker.ui.fragments.DepositeProcess();
                transaction.replace(R.id.frame_container, duedateFrag );
                transaction.addToBackStack(null);
                transaction.commit();


                break;
            case R.id.transfer_withdraw:
                clickedWhichButton="w";
                FragmentManager fragmentManager1 = ((LandingScreenActivity) getActivity()).getSupportFragmentManager();

                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Fragment duedateFrag1 = new com.bss.mintlocker.ui.fragments.DepositeProcess();
                transaction1.replace(R.id.frame_container, duedateFrag1 );
                transaction1.addToBackStack(null);
                transaction1.commit();
                break;
            case R.id.transfer_pendingtransfer:


                FragmentManager fragmentManager2 = ((LandingScreenActivity) getActivity()).getSupportFragmentManager();

                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
                transaction2.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Fragment duedateFrag2 = new com.bss.mintlocker.ui.fragments.PendingDepostWithdraw();
                transaction2.replace(R.id.frame_container, duedateFrag2 );
                transaction2.addToBackStack(null);
                transaction2.commit();




                break;
            case R.id.transfer_autodepost:

                FragmentManager fragmentManager4 = ((LandingScreenActivity) getActivity()).getSupportFragmentManager();

                FragmentTransaction transaction4 = fragmentManager4.beginTransaction();
                transaction4.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Fragment duedateFrag4 = new com.bss.mintlocker.ui.fragments.AutoDeposit();
                transaction4.replace(R.id.frame_container, duedateFrag4 );
                transaction4.addToBackStack(null);
                transaction4.commit();

                break;
        }
    }
}
