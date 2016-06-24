package com.bss.mintlocker.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button loginButton, registerButton;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        //initialize views
        initializeViews();

    }

    private void initializeViews() {
        loginButton = (Button) findViewById(R.id.login);
        registerButton = (Button) findViewById(R.id.register);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce) {
                finish();
                return super.onKeyDown(keyCode, event);
            }

            doubleBackToExitPressedOnce = true;
            Constants.showToast(getString(R.string.toastMessage), 0);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(getApplicationContext(), NativeLoginActivity.class));
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(getApplicationContext(), Step1_TellUsAboutYourself.class));
                finish();
                break;
        }
    }
}
