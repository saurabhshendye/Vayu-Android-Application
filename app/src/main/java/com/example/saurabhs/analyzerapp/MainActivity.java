package com.example.saurabhs.analyzerapp;

import android.content.ContentValues;
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

public class MainActivity extends AppCompatActivity {

    // Log tag used for Logging
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Southbound TCP client to be used to communicate with pi
    private TcpClient mTcpClient;

    // Constants (commands) to be used to navigate the bot
//    private static final String MOVE_FORWARD = "Move Forward";
//    private static final String MOVE_BACKWARD = "Move Backward";
//    private static final String MOVE_RIGHT = "Move Right";
//    private static final String MOVE_LEFT = "Move Left";
//    private static final String GET_SUMMARY = "Get Summary";
//    private static final String DISCONNECT = "Disconnect";

    private static final String MOVE_FORWARD = "FRWD";
    private static final String MOVE_BACKWARD = "BACK";
    private static final String MOVE_RIGHT = "RIGH";
    private static final String MOVE_LEFT = "LEFT";
    private static final String GET_SUMMARY = "SUMM";
    private static final String DISCONNECT = "DISC";

//    private static final int MOVE_FORWARD = 0;
//    private static final int MOVE_BACKWARD = 1;
//    private static final int MOVE_RIGHT = 2;
//    private static final int MOVE_LEFT = 3;
//    private static final int GET_SUMMARY = 4;
//    private static final int DISCONNECT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference to all the buttons on this activity
        ImageButton UpButton = (ImageButton) findViewById(R.id.Up);
        ImageButton DownButton = (ImageButton) findViewById(R.id.Down);
        ImageButton LeftButton = (ImageButton) findViewById(R.id.Left);
        ImageButton RightButton = (ImageButton) findViewById(R.id.Right);
        Button summaryButton = (Button) findViewById(R.id.summary_button);
        Button disConnectButton = (Button) findViewById(R.id.Disconnect);

        // Starting a thread for Network Connection
        new ConnectionTask().execute("");


        disConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), DISCONNECT,
                        Toast.LENGTH_SHORT).show();

            }
        });

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), GET_SUMMARY,
                        Toast.LENGTH_SHORT).show();
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, GET_SUMMARY);
            }
        });

        UpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), MOVE_FORWARD,
                        Toast.LENGTH_SHORT).show();
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_FORWARD);
            }
        });

        DownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), MOVE_BACKWARD,
                        Toast.LENGTH_SHORT).show();
                // Sending "Move Back" command to pi
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_BACKWARD);

            }
        });

        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), MOVE_LEFT,
                        Toast.LENGTH_SHORT).show();
                // Sending "Move Left" command to pi
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_LEFT);
            }
        });

        RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), MOVE_RIGHT,
                        Toast.LENGTH_SHORT).show();
                // Sending "Move Right" command to pi
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_RIGHT);
            }
        });

    }

    public class SendTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
//            Log.v(LOG_TAG,"In the do In BackGround");
            if (mTcpClient != null) {
                mTcpClient.sendMessage(strings[0]);
                Log.v(LOG_TAG, "Command: "+ strings[0]);
            }
            return null;
        }
    }

    public class ConnectionTask extends AsyncTask<String, String, TcpClient> {
        @Override
        protected TcpClient doInBackground(String... strings) {
            //we create a TCPClient object
            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //response received from server
            Log.e("test", "response " + values[0]);
//
//            TextView screen = (TextView) findViewById(R.id.screen);
//
//
//            //process server response here....
//            String[] parts = values[0].split(":");
//
//            screen.setText(parts[0] + "\n" + parts[1] + "\n" +
//                            parts[2] + "\n" + parts[3] + "\n" + parts[4]);
//
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(AirContract.AirEntry.COLUMN_TEMPERATURE, parts[0]);
//            contentValues.put(AirContract.AirEntry.COLUMN_HUMIDITY, parts[1]);
//            contentValues.put(AirContract.AirEntry.COLUMN_PARTICULATES, parts[2]);
//            contentValues.put(AirContract.AirEntry.COLUMN_CO, parts[3]);
//            contentValues.put(AirContract.AirEntry.COLUMN_MB, parts[4]);
//
//            Uri rowUri = getContentResolver().insert(AirContract.AirEntry.CONTENT_URI,contentValues);
//
//            if (rowUri!=null){
//                Log.v(LOG_TAG, "Record Successfully inserted");
//            } else {
//                Log.e(LOG_TAG, "Unable to insert record into a database");
//            }
        }
    }
}
