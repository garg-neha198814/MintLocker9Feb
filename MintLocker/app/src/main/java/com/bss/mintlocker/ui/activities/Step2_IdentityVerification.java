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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.constants.ConstantKeys;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;
import com.bss.mintlocker.webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Step2_IdentityVerification extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {

    Toolbar toolbar;
    EditText securitySSNEditText;
    RelativeLayout nextLayout;
    Step2_IdentityVerification mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_step2_identityverification);
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
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("2. Identity Verification");
    }

    // function to initialise the view
    public void initialiseView() {
        mContext=Step2_IdentityVerification.this;
        securitySSNEditText = (EditText) findViewById(R.id.securitySSN);

        nextLayout = (RelativeLayout) findViewById(R.id.next);
        nextLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
  nextScreen();

                break;
        }
    }

    private void nextScreen() {

    Constants.str_register_ssn_number = securitySSNEditText.getText().toString().trim();



        //edit text field is empty
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        allEds.add(securitySSNEditText);




        for (EditText edit : allEds) {
            if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                // EditText was empty
                // Do something fancy
                edit.requestFocus();

                edit.setError("Field Required.");
                return;
            }

        }



//          /*for selection of gender*/
//
//    if (Constants.str_register_ssn_number.equalsIgnoreCase("")) {
//
//        Constants.showToast("Please enter ssn number.", 3000);
//
//
//        return;
//    }

        hitWebServiceForSSNValidation();



}


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step2_IdentityVerification.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {

    }

    @Override
    public void okButton() {

        Constants.clearRegisterStrings(Step2_IdentityVerification.this);
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }


    private void hitWebServiceForSSNValidation() {


        Constants.str_register_token = "1234567890123456789012345678901234567890";


        Map<String, String> data = new HashMap<String, String>();
        data.put(ConstantKeys.TOKEN, Constants.str_register_token);
        data.put(ConstantKeys.SSNVALIDATION, Constants.str_register_ssn_number);


        Constants.sysoMessage(mContext, "case ssn valication result", data.toString());


        HitServerForSSNVALIDATION(data);


    }



    /*METHOD FOR SSN Validation WEBSERVICE*/

    private void HitServerForSSNVALIDATION(Map<String, String> data) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();


        WebService.SSNValidation(mContext, data, new Response.Listener<JSONObject>() {

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
                            startActivity(new Intent(getApplicationContext(), Step3_Security.class));
                            finish();

                        } else if (status.equalsIgnoreCase("0")) {
                            String Messagefromserver = json.getString("message");
                            Constants.showToast("SSN not valid.", 3000);

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
