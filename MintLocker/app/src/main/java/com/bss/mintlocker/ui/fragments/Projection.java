package com.bss.mintlocker.ui.fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.SeekBar;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class Projection extends Fragment implements View.OnClickListener {
    public static final String TYPE = "type";
    public static com.bss.mintlocker.util.CustomViewpager mViewfliper;
    private float lastX;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

     View needle;
    public Projection() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.chart_layout, container, false);

        Constants.scrFragmentTag = "projection";

        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        mViewfliper = (com.bss.mintlocker.util.CustomViewpager) v.findViewById(R.id.chart_viewflipper);
        needle=v.findViewById(R.id.needle);
        // Fetching footer layout
        View ViewTwoTopLayout = v.findViewById(R.id.view2);
        SeekBar mSeek_slider = (SeekBar) ViewTwoTopLayout.findViewById(R.id.seekbarTime);
        mSeek_slider.setMax(90);

        int width = needle.getWidth();
        int height = needle.getHeight();
        RotateAnimation anim = new RotateAnimation(-90f, 60f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
        anim.setInterpolator(new AccelerateInterpolator(2));
        anim.setFillAfter(true);
        anim.setRepeatCount(0);
        anim.setDuration(2000);
        needle.startAnimation(anim);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(2, 2),
//                new DataPoint(4, 4),
//                new DataPoint(16, 16)
//        });
//        series.setBackgroundColor(Color.GREEN);
////        graph.getLegendRenderer().setVisible(true);
////        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
//        series.setColor(Color.GREEN);
//        //eries.setDrawDataPoints(true);
//       // series.setDataPointsRadius(10);
//        //Paint paint = new Paint();
//        //paint.setStyle(Paint.Style.FILL);
//        //paint.setStrokeWidth(10);
//       // paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
//       // series.setCustomPaint(paint);
//        graph.addSeries(series);
        DataPoint[] points = new DataPoint[30];
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
//        for (int i = 0; i < 30; i++) {
//            points[i] = new DataPoint(i, Math.sin(i * 0.5) * 20 * (Math.random() * 10 + 1));
//
//        }
        for (int i = 0; i < 30; i++) {
            points[i] = new DataPoint(i, Math.pow(i,2.2));

        }
        DataPoint[] points1 = new DataPoint[30];
        for (int i = 0; i < 30; i++) {
            points1[i] = new DataPoint(i, Math.pow(i, 2));

        }


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);

//        points = new DataPoint[30];
//        int start = 0;
//        for (int i = 0; i < 30; i++) {
//            points[i] = new DataPoint(year, start);
//            start += 2000;
//            year++;
//        }
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(points1);
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(points);
        // styling grid/labels
        //graph.getGridLabelRenderer().setVerticalLabelsAlign();
        graph.getGridLabelRenderer().setGridColor(Color.DKGRAY);
        graph.getGridLabelRenderer().setHighlightZeroLines(false);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);
        graph.getGridLabelRenderer().setVerticalLabelsAlign(Paint.Align.RIGHT);
        graph.getGridLabelRenderer().setLabelVerticalWidth(100);
        graph.getGridLabelRenderer().setTextSize(20);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        graph.getGridLabelRenderer().reloadStyles();

        // styling viewport
        //  graph.getViewport().setBackgroundColor(Color.argb(255, 222, 222, 222));

        // styling series
//        series.setTitle("Random Curve 1");
//        series.setColor(Color.GREEN);
//        series.setDrawDataPoints(true);
//        series.setDataPointsRadius(10);
//        series.setThickness(8);
        series3.setTitle("Random Curve 2");
        series3.setDrawBackground(true);
        series3.setColor(Color.argb(70, 134,180,86));
        series3.setBackgroundColor(Color.argb(70, 134, 180, 86));
        series2.setTitle("Random Curve 3");
        series2.setDrawBackground(true);
        series2.setColor(Color.parseColor("#86B4E5"));

        series2.setBackgroundColor(Color.parseColor("#86B4E5"));
        //Paint paint = new Paint();
        // paint.setStyle(Paint.Style.STROKE);
        // paint.setStrokeWidth(0);
        // paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        // series2.setCustomPaint(paint);

        // styling legend
        graph.getLegendRenderer().setVisible(false);
        graph.getLegendRenderer().setTextSize(25);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(150, 50, 0, 0));
        graph.getLegendRenderer().setTextColor(Color.WHITE);
        //graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        //graph.getLegendRenderer().setMargin(30);
        graph.getLegendRenderer().setFixedPosition(150, 0);

        //graph.addSeries(series);
        graph.addSeries(series2);
        graph.addSeries(series3);

//        mViewfliper.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
////                        Drawer.requestDisallowInterceptTouchEvent(true);
//                        lastX = event.getX();
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//
//                        float currentX = event.getX();
//
//                        // Handling left to right screen swap.
//                        if (lastX < currentX) {
//
//                            // If there aren't any other children, just break.
//                            if (mViewfliper.getDisplayedChild() == 0)
//                                break;
//
//                            // Next screen comes in from left.
//                            mViewfliper.setInAnimation(getActivity(), R.anim.slide_in_from_left);
//                            // Current screen goes out from right.
//                            mViewfliper.setOutAnimation(getActivity(), R.anim.slide_out_to_right);
//
//                            // Display next screen.
//                            System.out.println("movenext");
//                            mViewfliper.showNext();
//                        }
//
//                        // Handling right to left screen swap.
//                        if (lastX > currentX) {
//
//                            // If there is a child (to the left), kust break.
//                            if (mViewfliper.getDisplayedChild() == 1)
//                                break;
//
//                            // Next screen comes in from right.
//                            mViewfliper.setInAnimation(getActivity(), R.anim.slide_in_from_right);
//                            // Current screen goes out from left.
//                            mViewfliper.setOutAnimation(getActivity(), R.anim.slide_out_to_left);
//
//                            // Display previous screen.
//                            System.out.println("moveprevious");
//                            mViewfliper.showPrevious();
//                        }
//                        break;
//                }
//                return true;
//            }
//        });



        mSeek_slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (b){
                    String strprogress = String.valueOf(progress);
                RotateAnimation anim = new RotateAnimation(-90f, Float.parseFloat(strprogress), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
                anim.setInterpolator(new AccelerateInterpolator(2));
                anim.setFillAfter(true);
                anim.setRepeatCount(0);
                anim.setDuration(2000);
                needle.startAnimation(anim);
            }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}
