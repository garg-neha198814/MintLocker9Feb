package com.bss.mintlocker.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.constants.ConstantKeys;
import com.bss.mintlocker.interfaces.DateSelectedInterface;
import com.bss.mintlocker.ui.fragments.DatePickerFragment;
import com.bss.mintlocker.webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Step1_TellUsAboutYourself extends AppCompatActivity implements View.OnClickListener, DateSelectedInterface {

    Toolbar toolbar;

    EditText nameEditText, emailEditText, passwordEditText, birthDateEditText, addressEditText, cityEditText, stateEditText, zipEditText, phoneEditText;
    RelativeLayout birthDateSelector, nextLayout;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton, femaleRadioButton;

    Step1_TellUsAboutYourself mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_step1_tellusaboutyourself);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        //inflate toolbar
        inflateToolbar();

        // initialize view
        initialiseView();
    }

    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("1. Tell us about yourself");
    }

    // function to initialise the view
    public void initialiseView() {
        mContext = Step1_TellUsAboutYourself.this;
        nameEditText = (EditText) findViewById(R.id.name);
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        birthDateEditText = (EditText) findViewById(R.id.birthDate);
        addressEditText = (EditText) findViewById(R.id.address);
        cityEditText = (EditText) findViewById(R.id.city);
        stateEditText = (EditText) findViewById(R.id.state);
        zipEditText = (EditText) findViewById(R.id.zip);
        phoneEditText = (EditText) findViewById(R.id.phone);

        birthDateSelector = (RelativeLayout) findViewById(R.id.birthDateSeletor);
        nextLayout = (RelativeLayout) findViewById(R.id.next);

        genderRadioGroup = (RadioGroup) findViewById(R.id.gender);
        maleRadioButton = (RadioButton) findViewById(R.id.male);
        femaleRadioButton = (RadioButton) findViewById(R.id.female);

        birthDateSelector.setOnClickListener(this);
        nextLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthDateSeletor:
                new DatePickerFragment().show(getSupportFragmentManager(), "datepicker");
                break;
            case R.id.next:

                saveScreenAndJumpToNext(v);


                break;
        }
    }

    private void saveScreenAndJumpToNext(View v) {

        Constants.str_register_name = nameEditText.getText().toString().trim();
        Constants.str_register_email = emailEditText.getText().toString().trim();
        Constants.str_register_password = passwordEditText.getText().toString().trim();
        Constants.str_register_dob = birthDateEditText.getText().toString().trim();
/*get the selected radio button value*/
        // get selected radio button from radioGroup
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) genderRadioGroup.findViewById(selectedId);

        Constants.str_register_gender = radioButton.getText().toString().trim();


        Constants.str_register_address = addressEditText.getText().toString().trim();
        Constants.str_register_city = cityEditText.getText().toString().trim();
        Constants.str_register_state = stateEditText.getText().toString().trim();
        Constants.str_register_zip = zipEditText.getText().toString().trim();
        Constants.str_register_phonenumber = phoneEditText.getText().toString().trim();


//edit text field is empty
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        allEds.add(nameEditText);
        allEds.add(emailEditText);
        allEds.add(passwordEditText);
        allEds.add(birthDateEditText);
        allEds.add(addressEditText);
        allEds.add(cityEditText);
        allEds.add(stateEditText);
        allEds.add(zipEditText);
        allEds.add(phoneEditText);


        for (EditText edit : allEds) {
            if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                // EditText was empty
                // Do something fancy
                edit.requestFocus();

                edit.setError("Field Required.");
                return;
            }

        }

        //email pattern validation

        if (!Constants.isValidEmail(Constants.str_register_email) == true) {

            String s = "Please enter valid email.";
//                Constants.showToast(s,3000);

            Constants.showSnackbar(Step1_TellUsAboutYourself.this, v, s, getResources().getColor(R.color.colorPrimaryDark));
            return;

        }


/*for selection of gender*/

        if (Constants.str_register_gender.equalsIgnoreCase("")) {

            Constants.showToast("Please Select Gender.", 3000);


            return;
        }

/*for Phone number validation*/

        if (Constants.str_register_phonenumber.length() <= 6) {

            Constants.showToast("Please enter more than 6 digit phone number.", 3000);


            return;
        }
        /*for dob*/

        if (Constants.str_register_dob.equalsIgnoreCase("") || Constants.str_register_dob.equalsIgnoreCase("Date of birth")) {

            Constants.showToast("Please Select Date of Birth.", 3000);
            return;
        }

        hitWebServiceForEmailValidation();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }
        return false;
    }

    @Override
    public void onDateSet(String date) {
        birthDateEditText.setText(date);
    }


    private void hitWebServiceForEmailValidation() {


        Constants.str_register_token = "1234567890123456789012345678901234567890";


        Map<String, String> data = new HashMap<String, String>();
        data.put(ConstantKeys.TOKEN, Constants.str_register_token);
        data.put(ConstantKeys.EMAILVALIDATION, Constants.str_register_email);


        Constants.sysoMessage(mContext, "case ssn valication result", data.toString());


        HitServerForEMAILVALIDATION(data);


    }



    /*METHOD FOR SSN Validation WEBSERVICE*/

    private void HitServerForEMAILVALIDATION(Map<String, String> data) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();


        WebService.EmailValidation(mContext, data, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject json) {
// TODO Auto-generated method stub
                if (json != null) {
                    try {
                        pDialog.dismiss();


                        String status = json.getString("status");


                        if (status.equalsIgnoreCase("1")) {
                            String Messagefromserver = json.getString("message");
//                            String user_id = json.getString("user_id");
                            System.out.println("message: " + Messagefromserver);
//                            Constants.showToast(Messagefromserver, 3000);
                            startActivity(new Intent(getApplicationContext(), Step2_IdentityVerification.class));
                            finish();

                        } else if (status.equalsIgnoreCase("0")) {
                            String Messagefromserver = json.getString("message");
                            Constants.showToast(Messagefromserver, 3000);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
// TODO Auto-generated method stub
                String json = null;
                pDialog.dismiss();
//                    Intent dashboard = new Intent(mContext, HomeActivity.class);
//                    dashboard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(dashboard);
                String message = error.getMessage();

                Constants.showToast(message, 3000);
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {

                    json = new String(response.data);
                    System.out.println(json);
                }
            }
        });


    }


}
