package com.bss.mintlocker.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by bhawanisingh on 26/11/15.
 */
public class SuggestionScreen extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {
    LinearLayout sBarOne, sBarTwo, sBarThree;
    LinearLayout sBarFour, sBarFive, sBarSix;
    TextView tvOne, tvTwo, tvThree, tv_Four, tvFive, tvSix,riskTypeTitle;

    float values[] = new float[5];
    int max = 100;
    float viewWidth;

    Button mAccept, mReject;
    Toolbar toolbar;

    SuggestionScreen mContext;
   public static String  riskFactor = "";

    private float valueCash, valueUSStock, valueNonUSStock, valueBonds, valueOthers;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_suggestion_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        inflateToolbar();

        init();

        Constants.screenDensity(SuggestionScreen.this);

        riskTypeTitle.setText(Constants.str_register_risktype);


        if (riskFactor.equalsIgnoreCase("low")) {

            valueCash = (float) 3.09;
            valueUSStock = (float) 30.60;
            valueNonUSStock = (float) 5.94;
            valueBonds = (float) 60.10;
            valueOthers = (float) 0.27;
            values[0] = valueCash;
            values[1] = valueUSStock;
            values[2] = valueNonUSStock;
            values[3] = valueBonds;
            values[4] = valueOthers;

        } else if (riskFactor.equalsIgnoreCase("medium")) {
            valueCash = (float) 10.9;
            valueUSStock = (float) 57.34;
            valueNonUSStock = (float) 4.57;
            valueBonds = (float) 24.18;
            valueOthers = (float) 3.01;

            values[0] = valueCash;
            values[1] = valueUSStock;
            values[2] = valueNonUSStock;
            values[3] = valueBonds;
            values[4] = valueOthers;



        } else if (riskFactor.equalsIgnoreCase("high")) {
            valueCash = (float) 11.82;
            valueUSStock = (float) 0.00;
            valueNonUSStock= (float) 0.00;
            valueBonds = (float) 88.11;
            valueOthers = (float) 0.07;

            values[0] = valueCash;
            values[1] = valueUSStock;
            values[2] = valueNonUSStock;
            values[3] = valueBonds;
            values[4] = valueOthers;

        }
        LinearLayout lv1 = (LinearLayout) findViewById(R.id.linear);

        values = calculateData(values);
        MyGraphview graphview = new MyGraphview(this, values);
        lv1.addView(graphview);


        tvOne.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = tvOne.getMeasuredWidth();
        viewWidth = tvOne.getMeasuredWidth();

        seekbarProgress();

    }

    private void init() {
        mContext = SuggestionScreen.this;
        riskTypeTitle= (TextView) findViewById(R.id.suggest_riskType);
        sBarOne = (LinearLayout) findViewById(R.id.suggest_seekbar_one);
        sBarTwo = (LinearLayout) findViewById(R.id.suggest_seekbar_two);
        sBarThree = (LinearLayout) findViewById(R.id.suggest_seekbar_three);
        sBarFour = (LinearLayout) findViewById(R.id.suggest_seekbar_four);
        sBarFive = (LinearLayout) findViewById(R.id.suggest_seekbar_five);
//        sBarSix = (LinearLayout) findViewById(R.id.suggest_seekbar_six);
        mAccept = (Button) findViewById(R.id.suggest_btn_accept);
        mReject = (Button) findViewById(R.id.suggest_btn_reject);

        tvOne = (TextView) findViewById(R.id.suggest_tv_one);
        tvTwo = (TextView) findViewById(R.id.suggest_tv_two);
        tvThree = (TextView) findViewById(R.id.suggest_tv_three);
        tv_Four = (TextView) findViewById(R.id.suggest_tv_four);
        tvFive = (TextView) findViewById(R.id.suggest_tv_five);
//        tvSix = (TextView) findViewById(R.id.suggest_tv_six);
        mAccept.setOnClickListener(this);
        mReject.setOnClickListener(this);

    }

    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("7. Portfolio");
    }

    private void seekbarProgress() {

//       seekbar one


        tvOne.setText(valueCash + "%");
        int color1 =Color.argb(255, 1, 56,
                101);
        tvOne.setTextColor(color1);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float dpWidth = dm.widthPixels;
        System.out.println("val00000:" + dpWidth);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen = dpWidth - viewWidth - 90;

        int totalFinalwidthOFScreen = (int) (totalwidthOFScreen * (valueCash / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen + "");
        sBarOne.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen, 20));


        //       seekbar two


        tvTwo.setText(valueUSStock + "%");
        int color2 =Color.argb(255, 254, 177,
                60);
        tvTwo.setTextColor(color2);

        DisplayMetrics dm1 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm1);
        float dpWidth1 = dm1.widthPixels;
        System.out.println("val00000:" + dpWidth1);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen1 = dpWidth1 - viewWidth - 90;

        int totalFinalwidthOFScreen1 = (int) (totalwidthOFScreen1 * (valueUSStock / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen1);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen1 + "");
        sBarTwo.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen1, 20));

        //       seekbar three


        tvThree.setText(valueNonUSStock + "%");

        int color3 = Color.argb(255, 182, 74,
                39);
        tvThree.setTextColor(color3);

        DisplayMetrics dm2 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm2);
        float dpWidth2 = dm2.widthPixels;
        System.out.println("val00000:" + dpWidth2);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen2 = dpWidth2 - viewWidth - 90;

        int totalFinalwidthOFScreen2 = (int) (totalwidthOFScreen2 * (valueNonUSStock / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen2);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen2 + "");
        sBarThree.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen2, 20));


        //       seekbar four


        tv_Four.setText(valueBonds + "%");

        int color4 = Color.argb(255, 140, 41,
                -0);
        tv_Four.setTextColor(color4);
        DisplayMetrics dm3 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm3);
        float dpWidth3 = dm3.widthPixels;
        System.out.println("val00000:" + dpWidth3);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen3 = dpWidth3 - viewWidth - 90;

        int totalFinalwidthOFScreen3 = (int) (totalwidthOFScreen3 * (valueBonds / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen3);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen3 + "");
        sBarFour.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen3, 20));


        //       seekbar five


        tvFive.setText(valueOthers + "%");

        int color5 = Color.argb(255, 37, 181,
                163);
        tvFive.setTextColor(color5);
        DisplayMetrics dm4 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm4);
        float dpWidth4 = dm4.widthPixels;
        System.out.println("val00000:" + dpWidth4);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen4 = dpWidth4 - viewWidth - 90;

        int totalFinalwidthOFScreen4 = (int) (totalwidthOFScreen4 * (valueOthers / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen4);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen4 + "");
        sBarFive.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen4, 20));


//        //       seekbar five
//
//
//        tvSix.setText(33 + "%");
//        DisplayMetrics dm5 = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm5);
//        float dpWidth5 = dm5.widthPixels;
//        System.out.println("val00000:"+ dpWidth5);
//        System.out.println("val000001111:"+ viewWidth);
//        float totalwidthOFScreen5 = dpWidth5-viewWidth-30;
//
//        int totalFinalwidthOFScreen5 = (int)(totalwidthOFScreen5*(33/100.0f));
//        System.out.println("vallll11111:"+ totalwidthOFScreen5);
//
//        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen5 + "");
//        sBarSix.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen5, 20));

    }

    private float[] calculateData(float[] data) {
        float total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i];
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = 360 * (data[i] / total);
        }
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.suggest_btn_accept:
                hitWebServiceForRegister();
                break;

            case R.id.suggest_btn_reject:
                startActivity(new Intent(getApplicationContext(), Step6_Portfolio.class));
                finish();
                break;
        }
    }

    public class MyGraphview extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] value_degree;
        RectF rectf ;
        float temp = 0;

        public MyGraphview(Context context, float[] values) {
            super(context);
            value_degree = new float[values.length];
            for (int i = 0; i < values.length; i++) {
                value_degree[i] = values[i];
            }

            if(Constants.scrDensityValue.equalsIgnoreCase("hdpi")){

                System.out.println("case: A");
                rectf = new RectF(40, 40, 240, 240);
            }else if(Constants.scrDensityValue.equalsIgnoreCase("xhdpi")){
                System.out.println("case: B");
                rectf = new RectF(40, 40, 240, 240);
            }else if(Constants.scrDensityValue.equalsIgnoreCase("xxhdpi")){
                System.out.println("case: C");
                rectf = new RectF(135, 135, 360, 360);
            }else{
                System.out.println("case: D");
                rectf = new RectF(40, 40, 240, 240);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Random r;
            for (int i = 0; i < value_degree.length; i++) {

                if (i == 0) {

                    r = new Random();
                    int color =  Color.argb(255, 1, 56,
                         101);
                    paint.setColor(color);
                    canvas.drawArc(rectf, 0, value_degree[i], true, paint);
                } else if (i == 1) {
                    temp += value_degree[i - 1];
                    r = new Random();
                    int color = Color.argb(255, 254, 177,
                            60);
                    paint.setColor(color);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                } else if (i == 2) {
                    temp += value_degree[i - 1];
                    r = new Random();
                    int color = Color.argb(255, 182, 74,
                            39);
                    paint.setColor(color);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                } else if (i == 3) {
                    temp += value_degree[i - 1];
                    r = new Random();
                    int color = Color.argb(255, 140, 41,
                            -0);
                    paint.setColor(color);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                } else if (i == 4) {
                    temp += value_degree[i - 1];
                    r = new Random();
                    int color = Color.argb(255, 37, 181,
                            163);
                    paint.setColor(color);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                }
//                else if (i == 5) {
//                    temp += value_degree[i - 1];
//                    r = new Random();
//                    int color = Color.argb(255, 1, 56,
//                            101);
//                    paint.setColor(color);
//                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
//                }
             /*   if (i == 0) {
                    for (int i1 = 0; i1 < 33; i1++) {
                        int color = Color.argb(100, 1, 51,
                                101);
                        paint.setColor(color);
                        canvas.drawArc(rectf, 0, value_degree[i], true, paint);


                    }
                } else if (i == 33) {

                    for (int i11 = 33; i11 < 52; i11++) {
                        int color1 = Color.argb(100, 254, 240,
                                160);
                        paint.setColor(color1);
                        canvas.drawArc(rectf, 33, value_degree[i], true, paint);

                    }*/


            }


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), SuggestionScreen.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {

    }

    @Override
    public void okButton() {

        Constants.clearRegisterStrings(SuggestionScreen.this);


        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }


    private void hitWebServiceForRegister() {

        if (Constants.str_register_questioniar.endsWith(",")) {
            Constants.str_register_questioniar = Constants.str_register_questioniar.substring(0, Constants.str_register_questioniar.length() - 1);
        }

        Constants.str_register_token = "1234567890123456789012345678901234567890";
//        Constants.str_register_risktype = "Moderate Risk";

        Map<String, String> data = new HashMap<String, String>();
        data.put(ConstantKeys.TOKEN, Constants.str_register_token);
        data.put(ConstantKeys.REGISTERUSER_NAME, Constants.str_register_name);
        data.put(ConstantKeys.REGISTERUSER_EMAIL, Constants.str_register_email);
        data.put(ConstantKeys.REGISTERUSER_PASSWORD, Constants.str_register_password);
        data.put(ConstantKeys.REGISTERUSER_DOB, Constants.str_register_dob);
        data.put(ConstantKeys.REGISTERUSER_GENDER, Constants.str_register_gender);
        data.put(ConstantKeys.REGISTERUSER_ADDRESS, Constants.str_register_address);
        data.put(ConstantKeys.REGISTERUSER_CITY, Constants.str_register_city);
        data.put(ConstantKeys.REGISTERUSER_STATE, Constants.str_register_state);
        data.put(ConstantKeys.REGISTERUSER_ZIP, Constants.str_register_zip);
        data.put(ConstantKeys.REGISTERUSER_PHONENUMBER, Constants.str_register_phonenumber);
        data.put(ConstantKeys.REGISTERUSER_SSNNUMBER, Constants.str_register_ssn_number);
        data.put(ConstantKeys.REGISTERUSER_KNOWABOUTUS, Constants.str_register_knowaboutus);
        data.put(ConstantKeys.REGISTERUSER_EMPLOYMENT_STATUS, Constants.str_register_employment_status);
        data.put(ConstantKeys.REGISTERUSER_INCOME, Constants.str_register_income);
        data.put(ConstantKeys.REGISTERUSER_NETWORTH, Constants.str_register_networth);
        data.put(ConstantKeys.REGISTERUSER_RISKTYPE, Constants.str_register_risktype);
        data.put(ConstantKeys.REGISTERUSER_REGULATION, Constants.str_register_regulation);
        data.put(ConstantKeys.REGISTERUSER_SECURITY, Constants.str_register_security);
        data.put(ConstantKeys.REGISTERUSER_QUESTIONNAIRE, Constants.str_register_questioniar);

        Constants.sysoMessage(mContext, "case register result", data.toString());


        HitServerForRegister(data);


    }



    /*METHOD FOR REGISTER WEBSERVICE*/

    private void HitServerForRegister(Map<String, String> data) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();


        WebService.Register(mContext, data, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject json) {
// TODO Auto-generated method stub
                if (json != null) {
                    try {
                        pDialog.dismiss();


                        String status = json.getString("status");


                        if (status.equalsIgnoreCase("1")) {
                            String Messagefromserver = json.getString("message");
                            String user_id = json.getString("user_id");
                            System.out.println("message: " + Messagefromserver);
                          Constants.showToast("Registration successful", 3000);
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            finish();

                        } else if (status.equalsIgnoreCase("0")) {
                            String Messagefromserver = json.getString("message");
                           Constants.showToast(Messagefromserver,3000);

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