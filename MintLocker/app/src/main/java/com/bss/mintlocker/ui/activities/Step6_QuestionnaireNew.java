package com.bss.mintlocker.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.adapter.QuestionNewAdapter;
import com.bss.mintlocker.interfaces.AlertDialogClickListener;
import com.bss.mintlocker.model.QuestionModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhawanisingh on 24/11/15.
 */
public class Step6_QuestionnaireNew extends AppCompatActivity implements QuestionNewAdapter.radioButton_listener, View.OnClickListener, AlertDialogClickListener {

    public static final String[] question = new String[]{"Which one of the following best describes your primary Investment objective?",
            "Approximately how many years do you expect to continue to invest your assests in the stock market?",
            "Assume yoy are investion $100,00 ans can choose  from the five hypothetical portfolios shown in the table below. The dollar values for each fragment_portfolio represent two possible returns - low and high - after one year. /n Assuming there is an equal chance of achieving either result (low and high), indicate which fragment_portfolio represents the maximum risk/return trade-off you would be willing to accept.",
            "Please indicate the level of risk with which you are most comfortable (select one response).",
            "How long are you prepared to wait for your account to return to its original value after a down market?",
            "What percentage of your total investment assests will be invested with CLS?",

            "How able are you to handle financial emergencies with assests outside of your CLS account(s).", "", "", ""};
    public static final String[] ansOne = new String[]{"Wealth Accumulation Emphasis on continued capital appreceiation in accordance with your overall risk tolerance.", "3 - 5 years", "(Possible High Value)$148 352 (Possible Low Value)$58 863", "1", "Less than 18 months", "Less than 20%", "Very able", "", "", ""};
    public static final String[] anstwo = new String[]{"Wealth Maintenance Emphasis on maintaining a desire lifestyle or level of financial security.", "6 - 10 years", "(Possible High Value)$139 263 (Possible Low Value)$67 529", "2", "18 months - 2 years", "21-50%", "Able", "", "", ""};
    public static final String[] ansthree = new String[]{"Wealth Distribution Emphasis on using wealth for living expenses.", "11 - 15 years", "(Possible High Value)$130 146 (Possible Low Value)$77 073", "3", "2 - 3 years", "51-75%", "SomeWhat able", "", "", ""};
    public static final String[] ansFour = new String[]{"", "Greater than 15 years", "(Possible High Value)$121 834 (Possible Low Value)$87 514", "4", "More than 3 years", "76-100%", "Not able", "", "", ""};
    public static final String[] ansFive = new String[]{"", "", "(Possible High Value)$114 051 (Possible Low Value)$98 860", "5", "", "", "", "", "", ""};
    public static final String[] ansSix = new String[]{"", "", "", "6", "", "", "", "", "", ""};
    public static final String[] ansSeven = new String[]{"", "", "", "7", "", "", "", "", "", ""};
    public static final String[] ansEight = new String[]{"", "", "", "8", "", "", "", "", "", ""};
    public static final String[] ansNine = new String[]{"", "", "", "9", "", "", "", "", "", ""};
    public static final String[] ansTen = new String[]{"", "", "", "10", "", "", "", "", "", ""};


    int countUnique = 1;

    ViewPager mViewpage;
    QuestionNewAdapter mCustomPagerAdapter;
    List<QuestionModal> rowItems;

    String lastvalue = "";


     public static int customCheckpointForQuestionFinish = 1;

    Toolbar toolbar;
    boolean isDialogForConfirmation = true;
    Step6_QuestionnaireNew mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_step6_questionnaire);

        // dialog to get the confirmation for questionnaire. if yes then load the content view else finish;
        isDialogForConfirmation = true;
        Constants.showAlertDialog(getResources().getString(R.string.titleQues), getResources().getString(R.string.messageQues), Step6_QuestionnaireNew.this, getResources().getString(R.string.cancelQues), getResources().getString(R.string.okQues), false, R.layout.popup_alertdialog);


//        mViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//        });


    }


    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("6. Investment Questionnaire");
    }


    @Override
    public void onradioButton_listenerItemClicked(QuestionModal s, int position, int idclicked) {
//        if (idclicked == R.id.ans1) {
////            Toast.makeText(Step6_QuestionnaireNew.this, "choice: one",
////                    Toast.LENGTH_SHORT).show();
//            Constants.str_register_questioniar=s.getUniqueValue()+":"+s.getAnswerOne();
//
//        } else if (idclicked == R.id.ans2) {
////            Toast.makeText(Step6_QuestionnaireNew.this, "choice: two",
////                    Toast.LENGTH_SHORT).show();
//            Constants.str_register_questioniar=s.getUniqueValue()+":"+s.getAnswerTwo();
//        } else if (idclicked == R.id.ans3) {
////            Toast.makeText(Step6_QuestionnaireNew.this, "choice: three",
////                    Toast.LENGTH_SHORT).show();
//
//            Constants.str_register_questioniar=s.getUniqueValue()+":"+s.getAnswerThree();
//        } else if (idclicked == R.id.ans4) {
////            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
////                    Toast.LENGTH_SHORT).show();
//
//            Constants.str_register_questioniar=s.getUniqueValue()+":"+s.getAnsweFour();
//        } else {
////                    Toast.makeText(mContext, "choice: three",
////                            Toast.LENGTH_SHORT).show();
//            Constants.str_register_questioniar=s.getUniqueValue()+":"+s.getAnswerOne();
//        }
    }

    @Override
    public void onradioButtonpreviousClicked(QuestionModal s, int position) {
        MovePrevious();
    }

    @Override
    public void onradioButtonNextClicked(QuestionModal s, int position, int idclicked) {
        if (idclicked == R.id.ans1) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: one",
//                    Toast.LENGTH_SHORT).show();
            lastvalue = s.getUniqueValue() + ":" + s.getAnswerOne();

        } else if (idclicked == R.id.ans2) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: two",
//                    Toast.LENGTH_SHORT).show();
            lastvalue = s.getUniqueValue() + ":" + s.getAnswerTwo();
        } else if (idclicked == R.id.ans3) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: three",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnswerThree();
        } else if (idclicked == R.id.ans4) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnsweFour();
        } else if (idclicked == R.id.ans5) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnswerFive();
        } else if (idclicked == R.id.ans6) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnswerSix();
        } else if (idclicked == R.id.ans7) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnswerSeven();
        } else if (idclicked == R.id.ans8) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnsweEight();
        } else if (idclicked == R.id.ans9) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnswerNine();
        } else if (idclicked == R.id.ans10) {
//            Toast.makeText(Step6_QuestionnaireNew.this, "choice: four",
//                    Toast.LENGTH_SHORT).show();

            lastvalue = s.getUniqueValue() + ":" + s.getAnsweTen();
        }
//        MoveNext();
        Constants.str_register_questioniar = Constants.str_register_questioniar + lastvalue + ",";


        System.out.println("final questionar string are :  " + Constants.str_register_questioniar);
        System.out.println("current position :  " + mViewpage.getCurrentItem() + "," + (question.length - 4));
//        Toast.makeText(Step6_QuestionnaireNew.this, "value: " + Constants.str_register_questioniar,
//                Toast.LENGTH_SHORT).show();


        if (mViewpage.getCurrentItem() == question.length - 4) {
            SuggestionScreen.riskFactor = "medium";
            Constants.str_register_risktype ="Medium Risk";
            startActivity(new Intent(getApplicationContext(), SuggestionScreen.class));
            finish();
        } else {
            mViewpage.setCurrentItem(mViewpage.getCurrentItem() + 1);
        }


    }




    public void MoveNext() {
        //it doesn't matter if you're already in the last item


    }

    public void MovePrevious() {
        //it doesn't matter if you're already in the first item
        mViewpage.setCurrentItem(mViewpage.getCurrentItem() - 1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isDialogForConfirmation = false;
            Constants.showAlertDialog(getResources().getString(R.string.title), getResources().getString(R.string.message), Step6_QuestionnaireNew.this, getResources().getString(R.string.cancel), getResources().getString(R.string.ok), false, R.layout.popup_alertdialog);
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

//            questions = new ArrayList();
//            adapterQuestion = new Adapter();
//            questionList.setAdapter(adapterQuestion);
            rowItems = new ArrayList<QuestionModal>();
            for (int i = 0; i < question.length; i++) {
                QuestionModal item = new QuestionModal(countUnique++, question[i], ansOne[i], anstwo[i], ansthree[i], ansFour[i], ansFive[i], ansSix[i], ansSeven[i], ansEight[i], ansNine[i], ansTen[i]);
                rowItems.add(item);
            }
            mCustomPagerAdapter = new QuestionNewAdapter(Step6_QuestionnaireNew.this, rowItems);
            mViewpage.setAdapter(mCustomPagerAdapter);

            mViewpage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });


        } else {
            Constants.clearRegisterStrings(Step6_QuestionnaireNew.this);
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }
    }

    private void initialiseView() {

        mViewpage = (ViewPager) findViewById(R.id.question_viewpager);
        mContext = Step6_QuestionnaireNew.this;
    }

    @Override
    public void onClick(View v) {

    }
}
