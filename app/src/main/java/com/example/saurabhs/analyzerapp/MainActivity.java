package com.example.saurabhs.analyzerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saurabhs.analyzerapp.data.AirContract;
import com.example.saurabhs.analyzerapp.network.TcpClient;
//import com.example.saurabhs.analyzerapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nav = (TextView) findViewById(R.id.Navigation_Activity);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numIntent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(numIntent);
            }
        });

        TextView stats = (TextView) findViewById(R.id.Statistics);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colIntent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(colIntent);
            }
        });

        TextView backup = (TextView) findViewById(R.id.Backup);
        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colIntent = new Intent(MainActivity.this, BackupActivity.class);
                startActivity(colIntent);
            }
        });

    }
}
