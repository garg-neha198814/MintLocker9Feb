package com.bss.mintlocker;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;
import com.bss.mintlocker.ui.activities.Home;
import com.bss.mintlocker.ui.activities.Step6_Portfolio;

import java.util.ArrayList;

public class Step6_Questionnaire extends AppCompatActivity implements View.OnClickListener, AlertDialogClickListener {
    Toolbar toolbar;

    ListView questionList;
    Button skipQues;

    ArrayList questions;
    Adapter adapterQuestion;

    boolean isDialogForConfirmation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dialog to get the confirmation for questionnaire. if yes then load the content view else finish;
        isDialogForConfirmation = true;
        Constants.showAlertDialog(getResources().getString(R.string.titleQues), getResources().getString(R.string.messageQues), Step6_Questionnaire.this, getResources().getString(R.string.cancelQues), getResources().getString(R.string.okQues), false, R.layout.popup_alertdialog);
    }

    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("6. Investment Questionnaire");
    }

    // function to initialise the view
    public void initialiseView() {
//        questionList = (ListView) findViewById(R.id.list);
        skipQues = (Button) findViewById(R.id.skipQues);

        skipQues.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skipQues:
                startActivity(new Intent(getApplicationContext(), Step6_Portfolio.class));
                finish();
                break;
        }
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item_questions, parent, false);
                holder = new Holder();
                holder.question = (TextView) convertView.findViewById(R.id.question);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.question.setText("Lorem Ipsum Dollart Sit Amet.Lorem Ipsum Dollart Sit Amet.Lorem Ipsum Dollart Sit Amet.Lorem Ipsum Dollart Sit Amet.Lorem Ipsum Dollart Sit Amet.");
            if (position % 2 == 0) {
                convertView.setBackgroundResource(R.drawable.list_item_selector_even);
            } else {
                convertView.setBackgroundResource(R.drawable.list_item_selector_odd);
            }
            return convertView;
        }

        class Holder {
            TextView question;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isDialogForConfirmation = false;
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step6_Questionnaire.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
        }
        return false;
    }

    @Override
    public void cancelButton() {
        if (isDialogForConfirmation) {
            startActivity(new Intent(getApplicationContext(), Step6_Portfolio.class));
            finish();
        }
    }

    @Override
    public void okButton() {
        if (isDialogForConfirmation) {
            // load the layout
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            setContentView(R.layout.activity_step6_questionnaire);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            //inflate toolbar
            inflateToolbar();

            // initialize view
            initialiseView();

            questions = new ArrayList();
            adapterQuestion = new Adapter();
            questionList.setAdapter(adapterQuestion);
        } else {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }
    }
}
