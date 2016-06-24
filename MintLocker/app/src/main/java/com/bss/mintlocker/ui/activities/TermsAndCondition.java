package com.bss.mintlocker.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.bss.mintlocker.R;

/**
 * Created by bhawanisingh on 23/09/15.
 */
public class TermsAndCondition extends Activity implements View.OnClickListener{
    private WebView webView;
    Button mButtonClose;

    TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.80);
        int screenheight = (int) (metrics.heightPixels * 0.80);
        setContentView(R.layout.activity_termsandcondition);

try{






//        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        getWindow().setLayout(screenWidth, screenheight); //set below the setContentview
init();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new AppWebViewClients());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());


            mTitle.setText("Terms and Conditions");
            webView.loadUrl("file:///android_asset/cine_pp.html");


}catch (Exception e){
    e.printStackTrace();
}






    }

    private void init() {
        webView = (WebView) findViewById(R.id.tandc_webview);
        mTitle = (TextView) findViewById(R.id.tandc_title);
        mButtonClose = (Button) findViewById(R.id.tandc_close);
        mButtonClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tandc_close:

                finish();

                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    public class AppWebViewClients extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

        }
    }
}
