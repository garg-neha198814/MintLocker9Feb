package com.bss.mintlocker.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.landing.LandingScreenActivity;
import com.bss.mintlocker.util.SharedPreferencesHandler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int i = 0;
    SharedPreferences sp;
    Timer timer;
    TimerTask timertask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        sp = getSharedPreferences(Constants.PREFERENCE_STRING, MODE_PRIVATE);
        timer = new Timer();
        timertask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        boolean b = isConnectingToInternet();
                        if (b) {
                            String user_id="";
                            try {
                                user_id  = SharedPreferencesHandler.getStringValues(MainActivity.this, "login");
                            }catch (Exception e){
                                e.printStackTrace();
                            }



                            if(user_id.equalsIgnoreCase("")||user_id.equalsIgnoreCase("null")||user_id==null){
                                user_id="";
                            }

                            if(user_id.equalsIgnoreCase("")){
                                Intent loginactivity = new Intent(MainActivity.this,Home.class);

                                startActivity(loginactivity);
                                finish();
                            }else{
                                Intent dashboard = new Intent(MainActivity.this, LandingScreenActivity.class);
                                dashboard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(dashboard);
                                finish();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "No internet connectivity.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
            }
        };
        timer.schedule(timertask, 3000);
    }

    // function to check if internet connection is active or not.
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (timertask != null) {
                timertask.cancel();
                timertask = null;
            }
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}