package com.bss.mintlocker.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bss.mintlocker.R;

/**
 * Created by bhawanisingh on 18/11/15.
 */
public class Summary extends Fragment implements View.OnClickListener {

    public Summary() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_summary, container, false);


        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
