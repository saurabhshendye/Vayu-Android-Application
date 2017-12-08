package com.example.saurabhs.analyzerapp;

import android.database.Cursor;
import android.graphics.Color;
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
    private Cursor cursor;

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        plotSpinner = (Spinner) findViewById(R.id.Graph_Selector);

        setupSpinner();

        lineChart = (LineChart) findViewById(R.id.graph);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(false);

        String [] projection = {AirContract.AirEntry.COLUMN_ID,
                AirContract.AirEntry.COLUMN_PARTICULATES10,
                AirContract.AirEntry.COLUMN_CO,
                AirContract.AirEntry.COLUMN_SMOKE,
                AirContract.AirEntry.COLUMN_LPG,
                AirContract.AirEntry.COLUMN_PARTICULATES25
        };

        cursor = getContentResolver().query(AirContract.AirEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter plotSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.Plot_Options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        plotSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        plotSpinner.setAdapter(plotSpinnerAdapter);

        // Set the integer mSelected to the constant values
        plotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.PM10))) {
                        plotPM10();
                    } else if (selection.equals(getString(R.string.PM25))) {
                        plotM25();
                    } else if (selection.equals(getString(R.string.CO))) {
                        plotCO();
                    } else if (selection.equals(getString(R.string.LPG))) {
                        plotLPG();
                    } else if (selection.equals(getString(R.string.Smoke))) {
                        plotSmoke();
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                mGender = PetsContract.PetEntry.GENDER_UNKNOWN; // Unknown
            }
        });
    }

    private void plotPM10(){
        cursor.moveToFirst();
        int PM25_Column = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_PARTICULATES10);

        ArrayList<Entry> List = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            List.add(new Entry(i, Float.parseFloat(cursor.getString(PM25_Column))));
            Log.i(LOG_TAG, cursor.getString(PM25_Column));
            i++;
        }
        LineDataSet lineDataSet = new LineDataSet(List, "PM10");
//        lineDataSet.setFillAlpha(100);


        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setCircleHoleRadius(1.5f);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setHighLightColor(Color.BLACK);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
    }

    private void plotM25(){
        int PM25_Column = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_PARTICULATES25);

        ArrayList<Entry> List = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            List.add(new Entry(i, Float.parseFloat(cursor.getString(PM25_Column))));
            Log.i(LOG_TAG, cursor.getString(PM25_Column));
            i++;
        }
        LineDataSet lineDataSet = new LineDataSet(List, "PM2.5");
//        lineDataSet.setFillAlpha(100);


        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setCircleHoleRadius(1.5f);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setHighLightColor(Color.BLACK);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
    }

    private void plotLPG() {
        int PM25_Column = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_LPG);

        ArrayList<Entry> List = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            List.add(new Entry(i, Float.parseFloat(cursor.getString(PM25_Column))));
            Log.i(LOG_TAG, cursor.getString(PM25_Column));
            i++;
        }
        LineDataSet lineDataSet = new LineDataSet(List, "PM2.5");
//        lineDataSet.setFillAlpha(100);


        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setCircleHoleRadius(1.5f);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setHighLightColor(Color.BLACK);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
    }

    private void plotSmoke() {
        int PM25_Column = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_SMOKE);

        ArrayList<Entry> List = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            List.add(new Entry(i, Float.parseFloat(cursor.getString(PM25_Column))));
            Log.i(LOG_TAG, cursor.getString(PM25_Column));
            i++;
        }
        LineDataSet lineDataSet = new LineDataSet(List, "Smoke");
//        lineDataSet.setFillAlpha(100);


        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setCircleHoleRadius(1.5f);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setHighLightColor(Color.BLACK);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
    }

    private void plotCO() {
        int PM25_Column = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_CO);

        ArrayList<Entry> List = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            List.add(new Entry(i, Float.parseFloat(cursor.getString(PM25_Column))));
            Log.i(LOG_TAG, cursor.getString(PM25_Column));
            i++;
        }
        LineDataSet lineDataSet = new LineDataSet(List, "CO");
//        lineDataSet.setFillAlpha(100);


        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setCircleHoleRadius(1.5f);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setHighLightColor(Color.BLACK);
        lineDataSet.setDrawValues(false);


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
