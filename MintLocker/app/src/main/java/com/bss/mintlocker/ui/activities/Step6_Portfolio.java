package com.bss.mintlocker.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;

public class Step6_Portfolio extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {
    Toolbar toolbar;

    View circleLowRisk, circleHighRisk, circleMediumRisk;
    RelativeLayout lowRisk, highRisk, mediumRisk;
    Button questionnaire;

    boolean isRiskDialog = true;
    String riskType = "";
    Step6_Portfolio mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_step6_portfolio);

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
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("6. Portfolio");
    }

    // function to initialise the view
    public void initialiseView() {
        mContext=Step6_Portfolio.this;
        circleHighRisk = findViewById(R.id.circleHighRisk);
        circleLowRisk = findViewById(R.id.circleLowRisk);
        circleMediumRisk = findViewById(R.id.circleMediumRisk);

        lowRisk = (RelativeLayout) findViewById(R.id.lowRisk);
        mediumRisk = (RelativeLayout) findViewById(R.id.mediumRisk);
        highRisk = (RelativeLayout) findViewById(R.id.highRisk);

        questionnaire = (Button) findViewById(R.id.questionnaire);

        lowRisk.setOnClickListener(this);
        mediumRisk.setOnClickListener(this);
        highRisk.setOnClickListener(this);

        questionnaire.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lowRisk:
                isRiskDialog = true;
                riskType = "low";
                circleLowRisk.setBackgroundResource(R.drawable.cirlce_selected);
                circleHighRisk.setBackgroundResource(R.drawable.circle_normal);
                circleMediumRisk.setBackgroundResource(R.drawable.circle_normal);
//                Constants.showAlertDialog("Low Risk", getResources().getString(R.string.textLowRisk), Step6_Portfolio.this, getResources().getString(R.string.cancel), getResources().getString(R.string.selectRisk), false, R.layout.popup_alertdialog);
                Constants.str_register_risktype="Low Risk";
                SuggestionScreen.riskFactor = "low";
                startActivity(new Intent(getApplicationContext(), SuggestionScreen.class));
                finish();


                break;
            case R.id.mediumRisk:
                isRiskDialog = true;
                riskType = "medium";
                circleLowRisk.setBackgroundResource(R.drawable.circle_normal);
                circleHighRisk.setBackgroundResource(R.drawable.circle_normal);
                circleMediumRisk.setBackgroundResource(R.drawable.cirlce_selected);
//                Constants.showAlertDialog("Medium Risk", getResources().getString(R.string.textLowRisk), Step6_Portfolio.this, getResources().getString(R.string.cancel), getResources().getString(R.string.selectRisk), false, R.layout.popup_alertdialog);

               Constants.str_register_risktype="Medium Risk";

                SuggestionScreen.riskFactor = "medium";
                startActivity(new Intent(getApplicationContext(), SuggestionScreen.class));
                finish();

                break;
            case R.id.highRisk:
                isRiskDialog = true;
                riskType = "high";
                circleLowRisk.setBackgroundResource(R.drawable.circle_normal);
                circleHighRisk.setBackgroundResource(R.drawable.cirlce_selected);
                circleMediumRisk.setBackgroundResource(R.drawable.circle_normal);
//                Constants.showAlertDialog("High Risk", getResources().getString(R.string.textLowRisk), Step6_Portfolio.this, getResources().getString(R.string.cancel), getResources().getString(R.string.selectRisk), false, R.layout.popup_alertdialog);
                Constants.str_register_risktype="High Risk";
                SuggestionScreen.riskFactor = "high";
                startActivity(new Intent(getApplicationContext(), SuggestionScreen.class));
                finish();



                break;
            case R.id.questionnaire:

                Constants.str_register_risktype=riskType;

                startActivity(new Intent(getApplicationContext(), Step6_QuestionnaireNew.class));
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isRiskDialog = false;
            riskType = "";
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step6_Portfolio.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {
        if (isRiskDialog) {
            circleLowRisk.setBackgroundResource(R.drawable.circle_normal);
            circleHighRisk.setBackgroundResource(R.drawable.circle_normal);
            circleMediumRisk.setBackgroundResource(R.drawable.circle_normal);
        }
    }

    @Override
    public void okButton() {
        if (isRiskDialog) {
//            hitWebServiceForRegister();
        } else {


            Constants.clearRegisterStrings(Step6_Portfolio.this);

            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }
    }

//    private void hitWebServiceForRegister() {
//
//        if (Constants.str_register_questioniar.endsWith(",")) {
//            Constants.str_register_questioniar = Constants.str_register_questioniar.substring(0, Constants.str_register_questioniar.length() - 1);
//        }
//
//        Constants.str_register_token = "1234567890123456789012345678901234567890";
////        Constants.str_register_risktype="Moderate Risk";
//
//        Map<String, String> data = new HashMap<String, String>();
//        data.put(ConstantKeys.TOKEN, Constants.str_register_token);
//        data.put(ConstantKeys.REGISTERUSER_NAME, Constants.str_register_name);
//        data.put(ConstantKeys.REGISTERUSER_EMAIL, Constants.str_register_email);
//        data.put(ConstantKeys.REGISTERUSER_PASSWORD, Constants.str_register_password);
//        data.put(ConstantKeys.REGISTERUSER_DOB, Constants.str_register_dob);
//        data.put(ConstantKeys.REGISTERUSER_GENDER, Constants.str_register_gender);
//        data.put(ConstantKeys.REGISTERUSER_ADDRESS, Constants.str_register_address);
//        data.put(ConstantKeys.REGISTERUSER_CITY, Constants.str_register_city);
//        data.put(ConstantKeys.REGISTERUSER_STATE, Constants.str_register_state);
//        data.put(ConstantKeys.REGISTERUSER_ZIP, Constants.str_register_zip);
//        data.put(ConstantKeys.REGISTERUSER_PHONENUMBER, Constants.str_register_phonenumber);
//        data.put(ConstantKeys.REGISTERUSER_SSNNUMBER, Constants.str_register_ssn_number);
//        data.put(ConstantKeys.REGISTERUSER_KNOWABOUTUS, Constants.str_register_knowaboutus);
//        data.put(ConstantKeys.REGISTERUSER_EMPLOYMENT_STATUS, Constants.str_register_employment_status);
//        data.put(ConstantKeys.REGISTERUSER_INCOME, Constants.str_register_income);
//        data.put(ConstantKeys.REGISTERUSER_NETWORTH, Constants.str_register_networth);
//        data.put(ConstantKeys.REGISTERUSER_RISKTYPE, Constants.str_register_risktype);
//        data.put(ConstantKeys.REGISTERUSER_REGULATION, Constants.str_register_regulation);
//        data.put(ConstantKeys.REGISTERUSER_SECURITY, Constants.str_register_security);
//        data.put(ConstantKeys.REGISTERUSER_QUESTIONNAIRE, Constants.str_register_questioniar);
//
//        Constants.sysoMessage(mContext, "case register result", data.toString());
//
//
//        HitServerForRegister(data);
//
//
//    }



//    /*METHOD FOR REGISTER WEBSERVICE*/
//
//    private void HitServerForRegister(Map<String, String> data) {
//        final ProgressDialog pDialog = new ProgressDialog(mContext);
//        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pDialog.setMessage("Loading...");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//
//        WebService.Register(mContext, data, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject json) {
//// TODO Auto-generated method stub
//                if (json != null) {
//                    try {
//                        pDialog.dismiss();
//
//
//                        String status = json.getString("status");
//
//
//                        if (status.equalsIgnoreCase("1")) {
//                            String Messagefromserver = json.getString("message");
//                            String user_id = json.getString("user_id");
//                            System.out.println("message: " + Messagefromserver);
//
//                            startActivity(new Intent(getApplicationContext(), Home.class));
//                            finish();
//
//                        } else if (status.equalsIgnoreCase("0")) {
//                            String Messagefromserver = json.getString("message");
//
//
//                        }
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//// TODO Auto-generated method stub
//                String json = null;
//                pDialog.dismiss();
////                    Intent dashboard = new Intent(mContext, HomeActivity.class);
////                    dashboard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                    startActivity(dashboard);
//                String message = error.getMessage();
//
//                Constants.showToast(message, 3000);
//                NetworkResponse response = error.networkResponse;
//                if (response != null && response.data != null) {
//
//                    json = new String(response.data);
//                    System.out.println(json);
//                }
//            }
//        });
//
//
//    }

}
