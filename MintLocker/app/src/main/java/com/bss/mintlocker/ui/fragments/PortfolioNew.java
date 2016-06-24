package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.adapter.PortfolioAdapter;
import com.bss.mintlocker.model.PortfolioModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhawanisingh on 03/12/15.
 */
public class PortfolioNew extends Fragment implements View.OnClickListener {
    ViewFlipper mViewfliper;
    private float lastX;
    public PortfolioNew() {

    }

    public static final String[] titles = new String[] { "Low Risk",
            "Medium Risk", "High Risk" };
    public static final String[] desc = new String[] { "Low Risk gbhfgbfgbfgb",
            "Medium Risk fghgfhfghgfh", "High Risk gfhgfhgfhgfhgfh" };
    public static final String[] CurrentView = new String[] { "Low Risk gbhfgbfgbfgb",
            "Medium Risk fghgfhfghgfh", "High Risk gfhgfhgfhgfhgfh" };

    public static final double[] valueCash = { 3.09,
            10.9, 11.82};

    public static final double[] valueUSStock = { 30.60,
            57.34, 0.00};

    public static final double[] valueUSNonStock = { 5.94,
            4.57, 0.00};

    public static final double[] valueBond = { 60.10,
            24.18, 88.11};

    public static final double[] valueOthers = { 0.27,
            3.01, 0.07};


    public static ViewPager mViewpage;

    PortfolioAdapter mCustomPagerAdapter;

    List<PortfolioModel> rowItems;

    public static RadioButton rb1;
    public static RadioButton rb2;
    public static RadioButton rb3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_portfolio, container, false);

        Constants.scrFragmentTag="fragment_portfolio";
        Constants.screenDensity(getActivity());

        rowItems = new ArrayList<PortfolioModel>();
        for (int i = 0; i < titles.length; i++) {
            PortfolioModel item = new PortfolioModel(titles[i],desc[i],(float) valueCash[i], (float) valueUSStock[i],(float) valueUSNonStock[i], (float) valueBond[i],(float) valueOthers[i]);
            rowItems.add(item);
        }


        mCustomPagerAdapter = new PortfolioAdapter(getActivity(),rowItems);

        mViewpage = (ViewPager) v.findViewById(R.id.portfolio_viewPager);
        RadioGroup rg =(RadioGroup)v.findViewById(R.id.RadioGroupViewFliper);
        rb1=(RadioButton)v.findViewById(R.id.rb_One);
      rb2 =(RadioButton)v.findViewById(R.id.rb_two);
      rb3 =(RadioButton)v.findViewById(R.id.rb_three);


        mViewpage.setAdapter(mCustomPagerAdapter);

//        mViewpage.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

//        mViewpage.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                mViewpage.getParent().requestDisallowInterceptTouchEvent(true);
//
//                if (position == 0) {
//                    rb1.setChecked(true);
//                    rb2.setChecked(false);
//                    rb3.setChecked(false);
//                } else if (position == 1) {
//                    rb1.setChecked(false);
//                    rb2.setChecked(true);
//                    rb3.setChecked(false);
//                } else if (position == 2) {
//                    rb1.setChecked(false);
//                    rb2.setChecked(false);
//                    rb3.setChecked(true);
//                }
//
//            }
//        });


//        mViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int newState) {
//                if (newState == ViewPager.SCROLL_STATE_DRAGGING) {
//                    // Prevent the ScrollView from intercepting this event now that the page is changing.
//                    // When this drag ends, the ScrollView will start accepting touch events again.
////                    sv.requestDisallowInterceptTouchEvent(true);
//                }
//                if (newState == 0) {
//                    rb1.setChecked(true);
//                    rb2.setChecked(false);
//                    rb3.setChecked(false);
//                } else if (newState == 1) {
//                    rb1.setChecked(false);
//                    rb2.setChecked(true);
//                    rb3.setChecked(false);
//                } else if (newState == 2) {
//                    rb1.setChecked(false);
//                    rb2.setChecked(false);
//                    rb3.setChecked(true);
//                }
//
//
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });

//        mViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            public void onPageScrollStateChanged(int state) {}
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
//
//            public void onPageSelected(int position) {
//                // Check if this is the page you want.
//                if (position == 0) {
//                    rb1.setChecked(true);
//                    rb2.setChecked(false);
//                    rb3.setChecked(false);
//                } else if (position == 1) {
//                    rb1.setChecked(false);
//                    rb2.setChecked(true);
//                    rb3.setChecked(false);
//                } else if (position == 2) {
//                    rb1.setChecked(false);
//                    rb2.setChecked(false);
//                    rb3.setChecked(true);
//                }
//            }
//        });

        mViewpage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            // optional
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            // optional
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rb1.setChecked(true);
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                } else if (position == 1) {
                    rb1.setChecked(false);
                    rb2.setChecked(true);
                    rb3.setChecked(false);
                } else if (position == 2) {
                    rb1.setChecked(false);
                    rb2.setChecked(false);
                    rb3.setChecked(true);
                }
            }
            // optional
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
