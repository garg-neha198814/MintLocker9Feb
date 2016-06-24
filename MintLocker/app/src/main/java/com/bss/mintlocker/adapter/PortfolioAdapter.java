package com.bss.mintlocker.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.model.PortfolioModel;

import java.util.List;
import java.util.Random;

/**
 * Created by bhawanisingh on 03/12/15.
 */
public class PortfolioAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<PortfolioModel> pagerItems;

    public Portfoliolistener mListener;

    float values[] = new float[5];

    float viewWidth;

    private float valueCash, valueUSStock, valueNonUSStock, valueBonds, valueOthers;
    public static int valuePositionofCurrentRisk=0;
    public interface Portfoliolistener {
        void onAcceptItemClicked(PortfolioModel s);
        void onRejectItemClicked(PortfolioModel s);


    }

    public PortfolioAdapter(Context context,List<PortfolioModel> items) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pagerItems = items;
        mListener = (Portfoliolistener) context;

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
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_portfolio_item, container, false);

        TextView riskTypeTitle= (TextView) itemView.findViewById(R.id.suggest_riskType);
        LinearLayout sBarOne = (LinearLayout) itemView.findViewById(R.id.suggest_seekbar_one);
        LinearLayout  sBarTwo = (LinearLayout) itemView.findViewById(R.id.suggest_seekbar_two);
        LinearLayout sBarThree = (LinearLayout) itemView.findViewById(R.id.suggest_seekbar_three);
        LinearLayout sBarFour = (LinearLayout) itemView.findViewById(R.id.suggest_seekbar_four);
        LinearLayout sBarFive = (LinearLayout) itemView.findViewById(R.id.suggest_seekbar_five);
        LinearLayout ll_accept_Reject = (LinearLayout) itemView.findViewById(R.id.ll_suggest_btn_accept_reject);
        LinearLayout lv1 = (LinearLayout) itemView.findViewById(R.id.linear);
//        sBarSix = (LinearLayout) findViewById(R.id.suggest_seekbar_six);
        Button  mAccept = (Button) itemView.findViewById(R.id.suggest_btn_accept);
        Button  mReject = (Button) itemView.findViewById(R.id.suggest_btn_reject);

        TextView   tvOne = (TextView) itemView.findViewById(R.id.suggest_tv_one);
        TextView tvTwo = (TextView) itemView.findViewById(R.id.suggest_tv_two);
        TextView tvThree = (TextView) itemView.findViewById(R.id.suggest_tv_three);
        TextView tv_Four = (TextView) itemView.findViewById(R.id.suggest_tv_four);
        TextView tvFive = (TextView) itemView.findViewById(R.id.suggest_tv_five);


        riskTypeTitle.setText(pagerItems.get(position).getTitle());




        tvOne.setText(pagerItems.get(position).getValueCash()+"%");
        tvTwo.setText(pagerItems.get(position).getValueUSStock()+"%");
        tvThree.setText(pagerItems.get(position).getValueNonUSStock()+"%");
        tv_Four.setText(pagerItems.get(position).getValueBonds()+"%");
        tvFive.setText(pagerItems.get(position).getValueOthers() + "%");

if(pagerItems.get(position).getTitle().equalsIgnoreCase("Medium Risk")){
    ll_accept_Reject.setVisibility(View.INVISIBLE);
}else{
    ll_accept_Reject.setVisibility(View.VISIBLE);
}


//       seekbar one



        int color1 = Color.argb(255, 1, 56,
                101);
        tvOne.setTextColor(color1);
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float dpWidth = mContext.getResources().getDisplayMetrics().widthPixels;

//        float dpWidth = dm.widthPixels;
        System.out.println("val00000:" + dpWidth);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen = dpWidth - viewWidth - 90;

        int totalFinalwidthOFScreen = (int) (totalwidthOFScreen * (pagerItems.get(position).getValueCash() / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen + "");
        sBarOne.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen, 20));


        //       seekbar two



        int color2 =Color.argb(255, 254, 177,
                60);
        tvTwo.setTextColor(color2);

//        DisplayMetrics dm1 = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm1);
//        float dpWidth1 = dm1.widthPixels;
        float dpWidth1 = mContext.getResources().getDisplayMetrics().widthPixels;
        System.out.println("val00000:" + dpWidth1);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen1 = dpWidth1 - viewWidth - 90;

        int totalFinalwidthOFScreen1 = (int) (totalwidthOFScreen1 * (pagerItems.get(position).getValueUSStock() / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen1);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen1 + "");
        sBarTwo.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen1, 20));

        //       seekbar three




        int color3 = Color.argb(255, 182, 74,
                39);
        tvThree.setTextColor(color3);

//        DisplayMetrics dm2 = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm2);
//        float dpWidth2 = dm2.widthPixels;
        float dpWidth2 = mContext.getResources().getDisplayMetrics().widthPixels;
        System.out.println("val00000:" + dpWidth2);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen2 = dpWidth2 - viewWidth - 90;

        int totalFinalwidthOFScreen2 = (int) (totalwidthOFScreen2 * (pagerItems.get(position).getValueNonUSStock() / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen2);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen2 + "");
        sBarThree.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen2, 20));


        //       seekbar four




        int color4 = Color.argb(255, 140, 41,
                -0);
        tv_Four.setTextColor(color4);
//        DisplayMetrics dm3 = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm3);
//        float dpWidth3 = dm3.widthPixels;

        float dpWidth3 = mContext.getResources().getDisplayMetrics().widthPixels;
        System.out.println("val00000:" + dpWidth3);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen3 = dpWidth3 - viewWidth - 150;

        int totalFinalwidthOFScreen3 = (int) (totalwidthOFScreen3 * (pagerItems.get(position).getValueBonds() / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen3);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen3 + "");
        sBarFour.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen3, 20));


        //       seekbar five




        int color5 = Color.argb(255, 37, 181,
                163);
        tvFive.setTextColor(color5);
//        DisplayMetrics dm4 = new DisplayMetrics();
//       getWindowManager().getDefaultDisplay().getMetrics(dm4);
//        float dpWidth4 = dm4.widthPixels;

        float dpWidth4 = mContext.getResources().getDisplayMetrics().widthPixels;
        System.out.println("val00000:" + dpWidth4);
        System.out.println("val000001111:" + viewWidth);
        float totalwidthOFScreen4 = dpWidth4 - viewWidth - 90;

        int totalFinalwidthOFScreen4 = (int) (totalwidthOFScreen4 * (pagerItems.get(position).getValueOthers() / 100.0f));
        System.out.println("vallll11111:" + totalwidthOFScreen4);

        System.out.println("vallll11111ssssss: " + totalFinalwidthOFScreen4 + "");
        sBarFive.setLayoutParams(new LinearLayout.LayoutParams((int) totalFinalwidthOFScreen4, 20));




if(pagerItems.get(position).getTitle().equalsIgnoreCase("Low Risk")){
//                    rb1.setChecked(true);
//                    rb2.setChecked(false);
//                    rb3.setChecked(false);
    valuePositionofCurrentRisk=0;
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

}else if(pagerItems.get(position).getTitle().equalsIgnoreCase("Medium Risk")){
//    rb1.setChecked(false);
//                    rb2.setChecked(true);
//                    rb3.setChecked(false);
    valuePositionofCurrentRisk=1;
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
}else if(pagerItems.get(position).getTitle().equalsIgnoreCase("High Risk")){

//    rb1.setChecked(false);
//                    rb2.setChecked(false);
//                    rb3.setChecked(true);
    valuePositionofCurrentRisk=2;
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



        values = calculateData(values);
        MyGraphview graphview = new MyGraphview(mContext, values);
        lv1.addView(graphview);



        final PortfolioModel rowItem = (PortfolioModel) getItem(position);

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAcceptItemClicked(rowItem);
            }
        });

        mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRejectItemClicked(rowItem);
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
    public PortfolioModel getItem(int position) {
        return pagerItems.get(position);
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

    /*Class create pie graph based on value*/

    public class MyGraphview extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] value_degree;




        RectF rectf;
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
                rectf = new RectF(100, 100, 340, 340);
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
                    int color = Color.argb(255, 1, 56,
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



            }


        }
    }



}
