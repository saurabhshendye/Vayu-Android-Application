package com.example.saurabhs.analyzerapp.network;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.saurabhs.analyzerapp.network.NetworkHelper.AWS_REQUEST_URL;

/**
 * Created by saurabhs on 12/19/17.
 */

public class RestClientAWS {

    private static final String LOG_TAG = RestClientAWS.class.getSimpleName();


    private static URL createURL(String strUrl) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem with url" + e);
        }

        return url;
    }

    public static void makeHttpPostRequest(String data) {
        String JSONResponse = "";

        URL url = createURL(AWS_REQUEST_URL);
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(15000);

            out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            writer.write(data);
            writer.flush();
            writer.close();

            urlConnection.connect();

        } catch (IOException e) {

        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (out != null) {
                // function must handle java.io.IOException here
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}