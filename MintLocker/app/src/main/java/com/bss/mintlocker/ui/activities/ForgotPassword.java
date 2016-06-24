package com.bss.mintlocker.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.constants.ConstantKeys;
import com.bss.mintlocker.webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhawanisingh on 02/12/15.
 */
public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;

    Button mSubmit;
    EditText mEmail;
    ForgotPassword mContext;
    String mStrEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_forgot_password);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        inflateToolbar();

        init();

    }

    private void init() {
        mContext = ForgotPassword.this;
        mSubmit = (Button) findViewById(R.id.fp_btn_submit);
        mEmail = (EditText) findViewById(R.id.fp_et_email);
        mSubmit.setOnClickListener(this);
    }


    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("Forgot Your Password?");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), NativeLoginActivity.class));
        finish();


    }







    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.fp_btn_submit:

                ForgotPasswordMethod();


                break;


        }
    }

    private void ForgotPasswordMethod() {
        mStrEmail = mEmail.getText().toString().trim();
        //edit text field is empty
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        allEds.add(mEmail);


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

        if (!Constants.isValidEmail(mStrEmail) == true) {

            String s = "Please enter valid email.";
            Constants.showToast(s, 3000);


            return;

        }


        hitWebServiceForForgotPassword();


    }


    private void hitWebServiceForForgotPassword() {


        Constants.str_register_token = "1234567890123456789012345678901234567890";


        Map<String, String> data = new HashMap<String, String>();
        data.put(ConstantKeys.TOKEN, Constants.str_register_token);
        data.put(ConstantKeys.FORGOTPASSWORD_EMAIL, mStrEmail);


        Constants.sysoMessage(mContext, "case ForgotPassword result", data.toString());


        HitServerForFORGOTPASSWORD(data);


    }



    /*METHOD FOR REGISTER WEBSERVICE*/

    private void HitServerForFORGOTPASSWORD(Map<String, String> data) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();


        WebService.ForgotPassword(mContext, data, new Response.Listener<JSONObject>() {

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
                            Constants.showToast(Messagefromserver, 3000);
                            startActivity(new Intent(getApplicationContext(), NativeLoginActivity.class));
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
