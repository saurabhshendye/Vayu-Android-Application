package com.example.saurabhs.analyzerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton UpButton = (ImageButton) findViewById(R.id.Up);
        ImageButton DownButton = (ImageButton) findViewById(R.id.Down);
        ImageButton LeftButton = (ImageButton) findViewById(R.id.Left);
        ImageButton RigtButton = (ImageButton) findViewById(R.id.Right);
        Button summaryButton = (Button) findViewById(R.id.summary_button);

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Get Summary",
                        Toast.LENGTH_SHORT).show();
            }
        });

        UpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move forward",
                        Toast.LENGTH_SHORT).show();
            }
        });

        DownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move Backward",
                        Toast.LENGTH_SHORT).show();

            }
        });

        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move Left",
                        Toast.LENGTH_SHORT).show();
            }
        });

        RigtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move Right",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
