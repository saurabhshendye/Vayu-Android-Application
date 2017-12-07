package com.example.saurabhs.analyzerapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.saurabhs.analyzerapp.data.AirContract;
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
    private Spinner plotSpinner;

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        plotSpinner = (Spinner) findViewById(R.id.Graph_Selector);

        setupSpinner();

        lineChart = (LineChart) findViewById(R.id.graph);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
//        lineChart.setHighlightPerTapEnabled(true);
        lineChart.setPinchZoom(true);

        ArrayList<Entry> List = new ArrayList<>();



        String [] projection = {AirContract.AirEntry.COLUMN_ID,
                AirContract.AirEntry.COLUMN_PARTICULATES25
        };

        Cursor cursor = getContentResolver().query(AirContract.AirEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        int PM25_Column = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_PARTICULATES25);

//        lineChart.setOnChartGestureListener(StatsActivity.this);
//        lineChart.setOnChartValueSelectedListener(StatsActivity.this);
        int i = 0;
        while (cursor.moveToNext()) {
            List.add(new Entry(i, Float.parseFloat(cursor.getString(PM25_Column))));
            Log.i(LOG_TAG, cursor.getString(PM25_Column));
            i++;
        }





//        List.add(new Entry(0, 10));
//        List.add(new Entry(1, 20));
//        List.add(new Entry(2, 30));
//        List.add(new Entry(3, 5));
//        List.add(new Entry(4, 7));
//        List.add(new Entry(5, 65));
//        List.add(new Entry(6, 80));
//        List.add(new Entry(7, 10));
//        List.add(new Entry(8, 20));
//        List.add(new Entry(9, 30));
//        List.add(new Entry(10, 5));
//        List.add(new Entry(11, 7));
//        List.add(new Entry(12, 65));
//        List.add(new Entry(13, 80));

        LineDataSet lineDataSet = new LineDataSet(List, "Set 1");
        lineDataSet.setFillAlpha(100);

//        ArrayList<LineDataSet> dataset = new ArrayList<>();
//        dataset.add(lineDataSet);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);

    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {

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
