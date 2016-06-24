package com.bss.mintlocker.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Step3_Security extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {
    Toolbar toolbar;
    RelativeLayout nextLayout, spinnerLayout1, spinnerLayout2;

    Spinner mQuestionA, mQuestionB;

    EditText mAnswerOne, mAnswerTwo;


    String mStr_questionOne, mStr_QuestionTwo, mStr_counter1, mStr_counter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_step3_security);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        //inflate toolbar
        inflateToolbar();

        // initialize view
        initialiseView();

        sspinnerExample();


    }

    private void sspinnerExample() {

        ArrayAdapter<String> spinnerCountQuestionArrayAdapterA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.question_arrayA));
        mQuestionA.setAdapter(spinnerCountQuestionArrayAdapterA);


        ArrayAdapter<String> spinnerCountQuestionArrayAdapterB = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.question_arrayB));
        mQuestionB.setAdapter(spinnerCountQuestionArrayAdapterB);


    }

    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("3. Security");
    }

    // function to initialise the view
    public void initialiseView() {
        nextLayout = (RelativeLayout) findViewById(R.id.next);
        spinnerLayout1 = (RelativeLayout) findViewById(R.id.SpinnerLayout1);
        spinnerLayout2 = (RelativeLayout) findViewById(R.id.SpinnerLayout2);
        mQuestionA = (Spinner) findViewById(R.id.QuestionA);
        mQuestionB = (Spinner) findViewById(R.id.QuestionB);
        mAnswerOne = (EditText) findViewById(R.id.answer1);
        mAnswerTwo = (EditText) findViewById(R.id.answer2);

        nextLayout.setOnClickListener(this);
        spinnerLayout1.setOnClickListener(this);
        spinnerLayout2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                nextScreenEmployementFinancialBackground();
                break;
            case R.id.SpinnerLayout1:
                mQuestionA.performClick();
                break;
            case R.id.SpinnerLayout2:
                mQuestionB.performClick();
                break;


        }
    }

    private void nextScreenEmployementFinancialBackground() {


        mStr_questionOne = mAnswerOne.getText().toString().trim();

        mStr_QuestionTwo = mAnswerTwo.getText().toString().trim();

        mStr_counter1 = String.valueOf(mQuestionA.getSelectedItemPosition());
        mStr_counter2 = String.valueOf(mQuestionB.getSelectedItemPosition());
        //edit text field is empty
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        allEds.add(mAnswerOne);
        allEds.add(mAnswerTwo);
        for (EditText edit : allEds) {
            if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                // EditText was empty
                // Do something fancy
                edit.requestFocus();

                edit.setError("Field Required.");
                return;
            }

        }


        Constants.str_register_security = mStr_counter1 + ":" + mStr_questionOne + "," + mStr_counter2 + ":" + mStr_QuestionTwo;
        System.out.println("String Security: 3 screen  " + Constants.str_register_security);


        mStr_questionOne = "";
        mStr_QuestionTwo = "";


        startActivity(new Intent(getApplicationContext(), Step4_EmployementFinancialBackground.class));
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step3_Security.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {

    }

    @Override
    public void okButton() {

        Constants.clearRegisterStrings(Step3_Security.this);


        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }
}
