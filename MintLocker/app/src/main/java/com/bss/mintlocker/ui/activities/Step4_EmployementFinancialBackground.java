package com.bss.mintlocker.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;

import java.util.ArrayList;

public class Step4_EmployementFinancialBackground extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {
    Toolbar toolbar;
    RelativeLayout nextLayout,spinnerLayout1;
    EditText mIncome,mInWorth;
    Spinner employStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_step4_employementfinancialbackground);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        //inflate toolbar
        inflateToolbar();

        // initialize view
        initialiseView();

        sspinnerExample();
    }

    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("4. Employement & Financial Background");
    }

    // function to initialise the view
    public void initialiseView() {
        spinnerLayout1 = (RelativeLayout) findViewById(R.id.SpinnerLayout1);
        nextLayout = (RelativeLayout) findViewById(R.id.next);
        mIncome = (EditText) findViewById(R.id.incomeValue);
        mInWorth = (EditText) findViewById(R.id.netWorthValue);
        employStatus = (Spinner) findViewById(R.id.employment_status);
        nextLayout.setOnClickListener(this);
        spinnerLayout1.setOnClickListener(this);

    }


    private void sspinnerExample() {




        ArrayAdapter<String> spinnerCountstatusArrayAdapterB = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.employment_status));
        employStatus.setAdapter(spinnerCountstatusArrayAdapterB);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:

                nextButtonClickOnEmploymentBackgroundFinaiancial();

                break;

            case R.id.SpinnerLayout1:
                employStatus.performClick();
                break;
        }
    }

    private void nextButtonClickOnEmploymentBackgroundFinaiancial() {

        Constants.str_register_employment_status=employStatus.getSelectedItem().toString();
        Constants.str_register_income= mIncome.getText().toString().trim();
        Constants.str_register_networth= mInWorth.getText().toString().trim();


        //edit text field is empty
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        allEds.add(mIncome);
        allEds.add(mInWorth);
        for (EditText edit : allEds) {
            if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                // EditText was empty
                // Do something fancy
                edit.requestFocus();

                edit.setError("Field Required.");
                return;
            }

        }



        startActivity(new Intent(getApplicationContext(), Step5_Regulation.class));
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step4_EmployementFinancialBackground.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {

    }

    @Override
    public void okButton() {

        Constants.clearRegisterStrings(Step4_EmployementFinancialBackground.this);
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }
}
