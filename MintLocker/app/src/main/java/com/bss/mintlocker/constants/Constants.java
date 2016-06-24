/*
 * Copyright (C) 2015 360 IT PROFESSIONALS INC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bss.mintlocker.constants;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bss.mintlocker.R;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;

/*
 * class which contains the constants .
 */
public class Constants extends Application {
    public static final String PREFERENCE_STRING = "mint.locker.prefs";
    private static Context context;

    public static String scrDensityValue = "";
    public static String scrFragmentTag = "";
    /*Register process strings*/

    public static String str_register_token = "";
    public static String str_register_name = "";
    public static String str_register_email = "";
    public static String str_register_password = "";

    public static String str_register_dob = "";
    public static String str_register_gender = "";
    public static String str_register_address = "";
    public static String str_register_city = "";

    public static String str_register_state = "";
    public static String str_register_zip = "";
    public static String str_register_phonenumber = "";
    public static String str_register_ssn_number = "";

    public static String str_register_knowaboutus = "";
    public static String str_register_employment_status = "";
    public static String str_register_income = "";
    public static String str_register_networth = "";

    public static String str_register_risktype = "";
    public static String str_register_regulation = "";
    public static String str_register_security = "";
    public static String str_register_questioniar = "";


    //    /*Login process strings*/
//
//    public static String str_login_email = "";
//    public static String str_login_password = "";
    public static String str_pickdate = "";

    @Override
    public void onCreate() {
        super.onCreate();
        Constants.context = getApplicationContext();
    }

    // function to display the short message in toast.
    public static void showToast(String message, int duration) {
        if (duration == 0) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

    }

    public static void showAlertDialog(String title, String message, Context c, String cancelButtonTitle, String okButtonTitle, boolean showCancelButton, int layout) {
        final AlertDialogClickListener listeners = (AlertDialogClickListener) c;
        AlertDialog.Builder ab = new AlertDialog.Builder(c);
        AlertDialog ad = null;
        View v = ((LayoutInflater) c.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(layout, null);

        ab.setView(v);
        ab.setCancelable(false);

        ad = ab.create();

        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        TextView textView = (TextView) v.findViewById(R.id.text);

        titleTextView.setText(title);
        textView.setText(message);

        Button cancel = (Button) v.findViewById(R.id.cancel);
        Button ok = (Button) v.findViewById(R.id.ok);

        cancel.setText(cancelButtonTitle);
        ok.setText(okButtonTitle);

        if (showCancelButton) {
            cancel.setVisibility(View.GONE);
        } else {
            cancel.setVisibility(View.VISIBLE);
        }

        final AlertDialog finalAd = ad;
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finalAd.dismiss();
                listeners.cancelButton();
            }
        });

        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finalAd.dismiss();
                listeners.okButton();
            }
        });

        ad.show();
    }

    public static Drawable homeButtonDrawable() {
        Drawable upArrow = ContextCompat.getDrawable(context, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    public static void clearRegisterStrings(Context ctx) {

        str_register_token = "";
        /*Screen 1*/
        str_register_name = "";
        str_register_email = "";
        str_register_password = "";

        str_register_dob = "";
        str_register_gender = "";
        str_register_address = "";
        str_register_city = "";

        str_register_state = "";
        str_register_zip = "";
        str_register_phonenumber = "";

        /*Screen 2*/
        str_register_ssn_number = "";


        str_register_knowaboutus = "";
        str_register_employment_status = "";
        str_register_income = "";
        str_register_networth = "";

        str_register_risktype = "";
        str_register_regulation = "";
        str_register_security = "";
        str_register_questioniar = "";


    }


    //email validation

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    //snackbar
    public static void showSnackbar(Context ctx, View view, String str, int cv) {
        Snackbar snack = Snackbar.make(view, str, Snackbar.LENGTH_LONG);
        view = snack.getView();
        view.setBackgroundColor(cv);
        TextView tv = (TextView)
                view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        view.setLayoutParams(params);
        snack.show();
    }


    //syso method
    public static void sysoMessage(Context tourMapTest, String caseonwhich, String string) {
        System.out.println("value is here: " + caseonwhich + ":" + string);

    }

    //log message
    public static void logMessage(Context tourMapTest, String caseonwhich, String string) {
        Log.e(caseonwhich, "value is here: " + caseonwhich + ":" + string);

    }


    public static void screenDensity(Context ctx) {
        switch (ctx.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                // ...

                scrDensityValue = "ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                // ...

                scrDensityValue = "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                // ...

                scrDensityValue = "hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                // ...

                scrDensityValue = "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                // ...
                scrDensityValue = "xxhdpi";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                // ...
                scrDensityValue = "xxxhdpi";
                break;


        }
    }


}
