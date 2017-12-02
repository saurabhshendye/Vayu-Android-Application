package com.example.saurabhs.analyzerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import com.example.saurabhs.analyzerapp.R;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener{

    private static final String LOG_TAG = StatsActivity.class.getSimpleName();

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        lineChart = (LineChart) findViewById(R.id.graph);

//        lineChart.setOnChartGestureListener(StatsActivity.this);
//        lineChart.setOnChartValueSelectedListener(StatsActivity.this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Entry> List = new ArrayList<>();
        List.add(new Entry(0, 10));
        List.add(new Entry(1, 20));
        List.add(new Entry(2, 30));
        List.add(new Entry(3, 5));
        List.add(new Entry(4, 7));
        List.add(new Entry(5, 65));
        List.add(new Entry(6, 80));

        LineDataSet lineDataSet = new LineDataSet(List, "Set 1");
        lineDataSet.setFillAlpha(100);

//        ArrayList<LineDataSet> dataset = new ArrayList<>();
//        dataset.add(lineDataSet);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
