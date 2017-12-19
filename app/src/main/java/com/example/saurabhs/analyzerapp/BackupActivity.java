package com.example.saurabhs.analyzerapp;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saurabhs.analyzerapp.data.AirContract;
import com.example.saurabhs.analyzerapp.network.RestClientAWS;
import com.example.saurabhs.analyzerapp.network.TCPClientAWS;
import com.example.saurabhs.analyzerapp.network.TcpClient;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.saurabhs.analyzerapp.network.RestClientAWS.makeHttpPostRequest;

public class BackupActivity extends AppCompatActivity {

    private static final String LOG_TAG = BackupActivity.class.getSimpleName();
//    private TCPClientAWS AWSClient;
//    private RestClientAWS AWSClient;
    private ProgressBar progressBar;
    private TextView backupText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        backupText = (TextView) findViewById(R.id.backup_complete);
        backupText.setVisibility(View.GONE);

//        new BackupActivity.BackupTask().execute("");

        String [] projection = {AirContract.AirEntry.COLUMN_ID,
                AirContract.AirEntry.COLUMN_PARTICULATES10,
                AirContract.AirEntry.COLUMN_CO,
                AirContract.AirEntry.COLUMN_SMOKE,
                AirContract.AirEntry.COLUMN_LPG,
                AirContract.AirEntry.COLUMN_PARTICULATES25
        };

        Cursor cursor = getContentResolver().query(AirContract.AirEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        int PM10 = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_PARTICULATES10);
        int PM25 = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_PARTICULATES25);
        int CO = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_CO);
        int LPG = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_LPG);
        int SMOKE = cursor.getColumnIndex(AirContract.AirEntry.COLUMN_SMOKE);


        while (cursor.moveToNext()){
            JSONObject root = new JSONObject();
            String valPM10 = cursor.getString(PM10);
            String valPM25 = cursor.getString(PM25);
            String valSMOKE = cursor.getString(SMOKE);
            String valCO = cursor.getString(CO);
            String valLPG = cursor.getString(LPG);

            try {
                root.put(AirContract.AirEntry.COLUMN_PARTICULATES10, valPM10);
                root.put(AirContract.AirEntry.COLUMN_PARTICULATES25, valPM25);
                root.put(AirContract.AirEntry.COLUMN_LPG, valLPG);
                root.put(AirContract.AirEntry.COLUMN_CO, valCO);
                root.put(AirContract.AirEntry.COLUMN_SMOKE, valSMOKE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String message = root.toString();

            new BackupActivity.sendDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message);

        }

        progressBar.setVisibility(View.GONE);
        backupText.setVisibility(View.VISIBLE);

    }

    public class sendDataTask extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                // Nothing needs to be done
            }

            makeHttpPostRequest(strings[0]);


//            if (AWSClient != null) {
//                AWSClient.sendMessage(strings[0]);
//                Log.i(LOG_TAG, strings[0]);
//            } else {
//                Log.e(LOG_TAG, "AWS client is null.");
//            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

//    public class BackupTask extends AsyncTask<String, String, TCPClientAWS> {
//
//        @Override
//        protected TCPClientAWS doInBackground(String... strings) {
//            AWSClient = new TCPClientAWS(new TCPClientAWS.OnMessageReceived() {
//                @Override
//                public void messageReceived(String message) {
//                    publishProgress(message);
//                }
//            });
//            AWSClient.run();
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
//        AWSClient.stopClient();
    }
}
