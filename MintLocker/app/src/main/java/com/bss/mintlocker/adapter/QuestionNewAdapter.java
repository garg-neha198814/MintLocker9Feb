package com.bss.mintlocker.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bss.mintlocker.R;
import com.bss.mintlocker.ui.activities.Step6_QuestionnaireNew;
import com.bss.mintlocker.model.QuestionModal;

import java.util.List;

/**
 * Created by bhawanisingh on 24/11/15.
 */
public class QuestionNewAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<QuestionModal> pagerItems;
    int positioncurrent;
    public radioButton_listener mListener;

    public interface radioButton_listener {
        void onradioButton_listenerItemClicked(QuestionModal s, int position,int idclicked);
        void onradioButtonpreviousClicked(QuestionModal s, int position);
        void onradioButtonNextClicked(QuestionModal s, int position,int idclicked);
    }

    public QuestionNewAdapter(Context context, List<QuestionModal> items) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pagerItems = items;
        mListener = (radioButton_listener) context;

    }

    @Override
    public int getCount() {
        return pagerItems.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_question_layout_new, container, false);

        TextView title = (TextView) itemView.findViewById(R.id.quesMain);
        final RadioGroup rg = (RadioGroup) itemView.findViewById(R.id.radio_group);
        RadioButton ansOne = (RadioButton) itemView.findViewById(R.id.ans1);

        RadioButton anstwo = (RadioButton) itemView.findViewById(R.id.ans2);
        RadioButton ansthree = (RadioButton) itemView.findViewById(R.id.ans3);
        RadioButton ansfour = (RadioButton) itemView.findViewById(R.id.ans4);
        RadioButton ansfive = (RadioButton) itemView.findViewById(R.id.ans5);

        RadioButton anssix = (RadioButton) itemView.findViewById(R.id.ans6);
        RadioButton ansSeven = (RadioButton) itemView.findViewById(R.id.ans7);
        RadioButton ansEight = (RadioButton) itemView.findViewById(R.id.ans8);
        RadioButton ansNine  = (RadioButton) itemView.findViewById(R.id.ans9);
        RadioButton ansTen = (RadioButton) itemView.findViewById(R.id.ans10);

        Button previous = (Button) itemView.findViewById(R.id.previous);
        Button next = (Button) itemView.findViewById(R.id.next);

        String strQuestion =pagerItems.get(position).getQuestion();
        String ansOneFromBackend =pagerItems.get(position).getAnswerOne();
        String ansTwoFromBackend =pagerItems.get(position).getAnswerTwo();
        String ansThreeFromBackend =pagerItems.get(position).getAnswerThree();
        String ansFourFromBackend =pagerItems.get(position).getAnsweFour();
        String ansFiveFromBackend =pagerItems.get(position).getAnswerFive();

        String ansSixFromBackend =pagerItems.get(position).getAnswerSix();
        String ansSevenFromBackend =pagerItems.get(position).getAnswerSeven();
        String ansEightFromBackend =pagerItems.get(position).getAnsweEight();
        String ansNineFromBackend =pagerItems.get(position).getAnswerNine();
        String ansTenFromBackend =pagerItems.get(position).getAnsweTen();


        if(strQuestion.equalsIgnoreCase("")){


        }else{
            Step6_QuestionnaireNew.customCheckpointForQuestionFinish++;
            title.setText(strQuestion);
        }





        /*radio button ans 1*/
        if(ansOneFromBackend.equalsIgnoreCase("")){
            ansOne.setVisibility(View.GONE);
        }else{
            ansOne.setVisibility(View.VISIBLE);
            ansOne.setText(ansOneFromBackend);
        }

 /*radio button ans 2*/
        if(ansTwoFromBackend.equalsIgnoreCase("")){
            anstwo.setVisibility(View.GONE);
        }else{
            anstwo.setVisibility(View.VISIBLE);
            anstwo.setText(ansTwoFromBackend);
        }

         /*radio button ans 3*/
        if(ansThreeFromBackend.equalsIgnoreCase("")){
            ansthree.setVisibility(View.GONE);
        }else{
            ansthree.setVisibility(View.VISIBLE);
            ansthree.setText(ansThreeFromBackend);
        }

         /*radio button ans 4*/
        if(ansFourFromBackend.equalsIgnoreCase("")){
            ansfour.setVisibility(View.GONE);
        }else{
            ansfour.setVisibility(View.VISIBLE);
            ansfour.setText(ansFourFromBackend);
        }

         /*radio button ans 5*/
        if(ansFiveFromBackend.equalsIgnoreCase("")){
            ansfive.setVisibility(View.GONE);
        }else{
            ansfive.setVisibility(View.VISIBLE);
            ansfive.setText(ansFiveFromBackend);
        }

         /*radio button ans 6*/
        if(ansSixFromBackend.equalsIgnoreCase("")){
            anssix.setVisibility(View.GONE);
        }else{
            anssix.setVisibility(View.VISIBLE);
            anssix.setText(ansSixFromBackend);
        }

         /*radio button ans 7*/
        if(ansSevenFromBackend.equalsIgnoreCase("")){
            ansSeven.setVisibility(View.GONE);
        }else{
            ansSeven.setVisibility(View.VISIBLE);
            ansSeven.setText(ansSevenFromBackend);
        }

         /*radio button ans 8*/
        if(ansEightFromBackend.equalsIgnoreCase("")){
            ansEight.setVisibility(View.GONE);
        }else{
            ansEight.setVisibility(View.VISIBLE);
            ansEight.setText(ansEightFromBackend);
        }

         /*radio button ans 9*/
        if(ansNineFromBackend.equalsIgnoreCase("")){
            ansNine.setVisibility(View.GONE);
        }else{
            ansNine.setVisibility(View.VISIBLE);
            ansNine.setText(ansNineFromBackend);
        }

         /*radio button ans 10*/
        if(ansTenFromBackend.equalsIgnoreCase("")){
            ansTen.setVisibility(View.GONE);
        }else{
            ansTen.setVisibility(View.VISIBLE);
            ansTen.setText(ansTenFromBackend);
        }




        final QuestionModal rowItem = (QuestionModal) getItem(position);


//        view_detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onradioButton_listenerItemClicked(rowItem, position);
//            }
//        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onradioButtonpreviousClicked(rowItem, position);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=0;
              selectedId = rg.getCheckedRadioButtonId();
if(selectedId!=-1) {

//    Toast.makeText(mContext, "chrcked id . "+selectedId,
//            Toast.LENGTH_SHORT).show();
    mListener.onradioButtonNextClicked(rowItem, position, selectedId);
}else{
                Toast.makeText(mContext, "Choose answer first.",
                        Toast.LENGTH_SHORT).show();
}

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected

                if(checkedId!=-1) {
//                    Toast.makeText(mContext, "chrcked id . "+checkedId,
//                            Toast.LENGTH_SHORT).show();


                mListener.onradioButton_listenerItemClicked(rowItem, position,checkedId);
                }else{
                    Toast.makeText(mContext, "Choose answer first.",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });


        container.addView(itemView);

        return itemView;
    }

    public QuestionModal getItem(int position) {
        return pagerItems.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}
