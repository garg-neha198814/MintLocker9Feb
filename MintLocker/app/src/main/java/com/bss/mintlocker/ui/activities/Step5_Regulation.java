package com.bss.mintlocker.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;

public class Step5_Regulation extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {
    Toolbar toolbar;
    RelativeLayout nextLayout,spinnerLayout1;

    String str_wuestionOne, str_wuestionTwo, str_wuestionThree, str_wuestionFour,str_Agree_or_not="0";
    RadioGroup mRg_questionOne, mRg_questiontwo, mRg_questionThree;
    Spinner mHearAboutUs;
    CheckBox mAccept;
    TextView mTermsAndCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_step5_regulation);
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
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("5. Regulation");
    }

    // function to initialise the view
    public void initialiseView() {
        spinnerLayout1 = (RelativeLayout) findViewById(R.id.SpinnerLayout1);
        nextLayout = (RelativeLayout) findViewById(R.id.next);
        mRg_questionOne = (RadioGroup) findViewById(R.id.question1);
        mRg_questiontwo = (RadioGroup) findViewById(R.id.question2);
        mRg_questionThree = (RadioGroup) findViewById(R.id.question3);
        mHearAboutUs=(Spinner)findViewById(R.id.knowaboutus);
        mAccept=(CheckBox)findViewById(R.id.accept);
        mTermsAndCondition=(TextView)findViewById(R.id.termsandcondition);

        spinnerLayout1.setOnClickListener(this);
        nextLayout.setOnClickListener(this);
        mAccept.setOnClickListener(this);
        mTermsAndCondition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:

                nextButtonRegulation();


                break;
            case R.id.SpinnerLayout1:
                mHearAboutUs.performClick();
                break;


            case R.id.accept:
                if (((CheckBox) v).isChecked()) {
                    str_Agree_or_not = "1";
                } else {
                    str_Agree_or_not = "0";
                }
                break;

            case R.id.termsandcondition:


                startActivity(new Intent(getApplicationContext(), TermsAndCondition.class));

                break;



        }
    }

    private void sspinnerExample() {

        ArrayAdapter<String> spinnerCountHearAboutUsArrayAdapterA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.hearAboutUs));
        mHearAboutUs.setAdapter(spinnerCountHearAboutUsArrayAdapterA);




    }

    private void nextButtonRegulation() {

/*question One*/
/*get the selected radio button value*/
        // get selected radio button from radioGroup
        int selectedId = mRg_questionOne.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) mRg_questionOne.findViewById(selectedId);

        str_wuestionOne = radioButton.getText().toString().trim();

/*question two*/

        int selectedId1 = mRg_questiontwo.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton1 = (RadioButton) mRg_questiontwo.findViewById(selectedId);

        str_wuestionTwo = radioButton.getText().toString().trim();



        /*question three*/
        int selectedId2 = mRg_questionThree.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton2 = (RadioButton) mRg_questionThree.findViewById(selectedId);

        str_wuestionThree = radioButton.getText().toString().trim();

/*Spinner select hear about us*/
        str_wuestionFour=mHearAboutUs.getSelectedItem().toString();





        Constants.str_register_regulation="1:"+str_wuestionOne+","+"2:"+str_wuestionTwo+","+"3:"+str_wuestionThree;
           System.out.println("Regulation String: "+Constants.str_register_regulation);

        Constants.str_register_knowaboutus=str_wuestionFour;


        //I agree validation
        if (str_Agree_or_not.equalsIgnoreCase("0")) {
            String s = "Please Agree Terms and Conditions.";
            Constants.showToast(s, 3000);

            return;
        }

        str_wuestionOne="";
        str_wuestionTwo="";
        str_wuestionThree="";
        str_wuestionFour="";



        startActivity(new Intent(getApplicationContext(), Step6_QuestionnaireNew.class));
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step5_Regulation.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {

    }

    @Override
    public void okButton() {

        Constants.clearRegisterStrings(Step5_Regulation.this);


        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }
}
