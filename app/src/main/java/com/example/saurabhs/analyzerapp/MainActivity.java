package com.example.saurabhs.analyzerapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.saurabhs.analyzerapp.network.TcpClient;

public class MainActivity extends AppCompatActivity {

    private TcpClient mTcpClient;
    private static final String LOG_TAG = "MainActivity";
    private static final String MOVE_FORWARD = "Move Forward";
    private static final String MOVE_BACKWARD = "Move Backward";
    private static final String MOVE_RIGHT = "Move Right";
    private static final String MOVE_LEFT = "Move Left";
    private static final String GET_SUMMARY = "Get Summary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton UpButton = (ImageButton) findViewById(R.id.Up);
        ImageButton DownButton = (ImageButton) findViewById(R.id.Down);
        ImageButton LeftButton = (ImageButton) findViewById(R.id.Left);
        ImageButton RightButton = (ImageButton) findViewById(R.id.Right);
        Button summaryButton = (Button) findViewById(R.id.summary_button);
        Button disConnectButton = (Button) findViewById(R.id.Disconnect);

        new ConnectionTask().execute("");
//        new SendTask().execute("Move Right");
//        Log.v(LOG_TAG,"Sent Message");



        disConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Disconnect",
                        Toast.LENGTH_SHORT).show();

            }
        });

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Get Summary",
                        Toast.LENGTH_SHORT).show();
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, GET_SUMMARY);
            }
        });

        UpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move forward",
                        Toast.LENGTH_SHORT).show();
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_FORWARD);
            }
        });

        DownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move Backward",
                        Toast.LENGTH_SHORT).show();
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_BACKWARD);

            }
        });

        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move Left",
                        Toast.LENGTH_SHORT).show();
                new SendTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MOVE_LEFT);
            }
        });

        RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Move Right",
                        Toast.LENGTH_SHORT).show();
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
            //process server response here....
        }
    }
}
