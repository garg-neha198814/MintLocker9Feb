package com.bss.mintlocker.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.bss.mintlocker.interfaces.DateSelectedInterface;

import java.util.Calendar;

/**
 * Created by deepakgoyal on 19/11/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    DateSelectedInterface dateSelectedInterface;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dateSelectedInterface = (DateSelectedInterface) getActivity();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        dateSelectedInterface.onDateSet(day + "-" + (month + 1) + "-" + year);
    }
}