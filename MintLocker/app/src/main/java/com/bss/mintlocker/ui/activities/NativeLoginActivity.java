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
import com.bss.mintlocker.landing.LandingScreenActivity;
import com.bss.mintlocker.util.SharedPreferencesHandler;
import com.bss.mintlocker.webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhawanisingh on 02/12/15.
 */
public class NativeLoginActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    EditText mEmail,mPassword;
    Button mSubmit;
    TextView mForgotPassword;
    String mStrEmail,mStrPassword;
    NativeLoginActivity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        inflateToolbar();
        init();

    }

    private void init() {
        mContext=NativeLoginActivity.this;
        mSubmit = (Button)findViewById(R.id.login_submit);
        mEmail = (EditText)findViewById(R.id.login_emailid);
        mPassword = (EditText)findViewById(R.id.login_password);
        mForgotPassword = (TextView)findViewById(R.id.login_forgotpassword);
        mSubmit.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);



    }


    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("Login");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_forgotpassword:

                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                finish();


                break;

            case R.id.login_submit:

loginMethod();


                break;



        }
    }

    private void loginMethod() {

        mStrEmail=mEmail.getText().toString().trim();
                mStrPassword=mPassword.getText().toString().trim();

        //edit text field is empty
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        allEds.add(mEmail);
        allEds.add(mPassword);


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
       Constants.showToast(s,3000);


            return;

        }


        hitWebServiceForLogin();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();


    }



        private void hitWebServiceForLogin() {



        Constants.str_register_token = "1234567890123456789012345678901234567890";


        Map<String, String> data = new HashMap<String, String>();
        data.put(ConstantKeys.TOKEN, Constants.str_register_token);
        data.put(ConstantKeys.LOGIN_EMAIL, mStrEmail);
        data.put(ConstantKeys.LOGINPASSWORD, mStrPassword);


        Constants.sysoMessage(mContext, "case login result", data.toString());


        HitServerForLogin(data);


    }



    /*METHOD FOR REGISTER WEBSERVICE*/

    private void HitServerForLogin(Map<String, String> data) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();


        WebService.Login(mContext, data, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject json) {
// TODO Auto-generated method stub
                if (json != null) {
                    try {
                        pDialog.dismiss();


                        String status = json.getString("status");


                        if (status.equalsIgnoreCase("1")) {
//                            String Messagefromserver = json.getString("message");
//                            String user_id = json.getString("user_id");
//                            System.out.println("message: " + Messagefromserver);
                            SharedPreferencesHandler.setStringValues(mContext, "login", "yes");
                            startActivity(new Intent(getApplicationContext(), LandingScreenActivity.class));
                            finish();

                        } else if (status.equalsIgnoreCase("0")) {
                            String Messagefromserver = json.getString("message");


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