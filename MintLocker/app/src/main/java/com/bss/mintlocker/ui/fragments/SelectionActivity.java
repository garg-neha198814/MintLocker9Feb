package com.bss.mintlocker.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.landing.LandingScreenActivity;


public class SelectionActivity extends Fragment implements View.OnClickListener{
    de.hdodenhof.circleimageview.CircleImageView mHiew,mGet_hired;
    public static com.bss.mintlocker.util.CustomViewpager mViewfliper;
    private float lastX;
    public SelectionActivity() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_after_bank_account, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        Constants.scrFragmentTag = "activity_main_one";
        LandingScreenActivity.mToolbarTitle.setText("Hi,John");
        mViewfliper=(com.bss.mintlocker.util.CustomViewpager)v.findViewById(R.id.after_bank_account_viewflipper);

//        mViewfliper.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
////                        Drawer.requestDisallowInterceptTouchEvent(true);
//                        lastX = event.getX();
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//
//
//                        float currentX = event.getX();
//
//                        // Handling left to right screen swap.
//                        if (lastX < currentX) {
//
//                            // If there aren't any other children, just break.
//                            if (mViewfliper.getDisplayedChild() == 0)
//                                break;
//
//                            // Next screen comes in from left.
//                            mViewfliper.setInAnimation(getActivity(), R.anim.slide_in_from_left);
//                            // Current screen goes out from right.
//                            mViewfliper.setOutAnimation(getActivity(), R.anim.slide_out_to_right);
//
//                            // Display next screen.
//                            System.out.println("movenext");
//                            mViewfliper.showNext();
//                        }
//
//                        // Handling right to left screen swap.
//                        if (lastX > currentX) {
//
//                            // If there is a child (to the left), kust break.
//                            if (mViewfliper.getDisplayedChild() == 1)
//                                break;
//
//                            // Next screen comes in from right.
//                            mViewfliper.setInAnimation(getActivity(), R.anim.slide_in_from_right);
//                            // Current screen goes out from left.
//                            mViewfliper.setOutAnimation(getActivity(), R.anim.slide_out_to_left);
//
//                            // Display previous screen.
//                            System.out.println("moveprevious");
//                            mViewfliper.showPrevious();
//                        }
//                        break;
//                }
//                return true;
//            }
//        });



        return v;

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }

//    // Using the following method, we will handle all screen swaps.
//    public boolean onTouchEvent(MotionEvent touchevent) {
//        switch (touchevent.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                lastX = touchevent.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                float currentX = touchevent.getX();
//
//                // Handling left to right screen swap.
//                if (lastX < currentX) {
//
//                    // If there aren't any other children, just break.
//                    if (mViewfliper.getDisplayedChild() == 0)
//                        break;
//
//                    // Next screen comes in from left.
//                    mViewfliper.setInAnimation(getActivity(), R.anim.slide_in_from_left);
//                    // Current screen goes out from right.
//                    mViewfliper.setOutAnimation(getActivity(), R.anim.slide_out_to_right);
//
//                    // Display next screen.
//                    mViewfliper.showNext();
//                }
//
//                // Handling right to left screen swap.
//                if (lastX > currentX) {
//
//                    // If there is a child (to the left), kust break.
//                    if (mViewfliper.getDisplayedChild() == 1)
//                        break;
//
//                    // Next screen comes in from right.
//                    mViewfliper.setInAnimation(getActivity(), R.anim.slide_in_from_right);
//                    // Current screen goes out from left.
//                    mViewfliper.setOutAnimation(getActivity(), R.anim.slide_out_to_left);
//
//                    // Display previous screen.
//                    mViewfliper.showPrevious();
//                }
//                break;
//        }
//        return false;
//    }

}
