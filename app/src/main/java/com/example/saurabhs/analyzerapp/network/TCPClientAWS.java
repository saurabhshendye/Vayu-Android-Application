package com.example.saurabhs.analyzerapp.network;

import android.database.Cursor;
import android.util.Log;

import com.example.saurabhs.analyzerapp.data.AirContract;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static com.example.saurabhs.analyzerapp.network.NetworkHelper.AWSIP;
import static com.example.saurabhs.analyzerapp.network.NetworkHelper.AWS_PORT;
import static com.example.saurabhs.analyzerapp.network.NetworkHelper.RPI_PORT;
import static com.example.saurabhs.analyzerapp.network.NetworkHelper.RaspBerryIP;

/**
 * Created by Saurabh on 12/7/2017.
 */

public class TCPClientAWS {
    // Log tag used for Logging
    private static final String LOG_TAG = TCPClientAWS.class.getSimpleName();

    private static final String SERVER_IP = AWSIP; //server IP address
    private static final int SERVER_PORT = AWS_PORT; // Server port
    // message to send to the server
    private String mServerMessage;
    // sends message received notifications
    private TCPClientAWS.OnMessageReceived mMessageListener = null;
    // while this is true, the server will continue running
    private boolean mRun = false;
    // used to send messages
    private DataOutputStream dout;
    // used to read messages from the server
    private DataInputStream din;


    /**
     * Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public TCPClientAWS(TCPClientAWS.OnMessageReceived listener) {
        mMessageListener = listener;
    }

    /**
     * Sends the message entered by client to the server
     *
     * @param message text entered by client
     */
    public void sendMessage(String message) {
//        if (mBufferOut != null && !mBufferOut.checkError()) {
//            mBufferOut.println(message);
//            mBufferOut.flush();
//        }
        byte[] messageBytes = message.getBytes();

        if (dout != null){
            try {
                dout.write(messageBytes, 0, messageBytes.length);
                dout.flush();
                Log.i(LOG_TAG, "Message Length: " + String.valueOf(messageBytes.length));
            } catch (IOException e) {
                Log.e(LOG_TAG, "SendMessage: " + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Close the connection and release the members
     */
    public void stopClient() {

        try {
            dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRun = false;
        mMessageListener = null;
        din = null;
        dout = null;
        mServerMessage = null;
    }

    public void run() {

        mRun = true;

        try {
            //here you must put your computer's IP address.
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            Log.e(LOG_TAG, "Connecting...");

            //create a socket to make the connection with the server
            Socket socket = new Socket(serverAddr, SERVER_PORT);
            if (!socket.isConnected()){
                Log.e(LOG_TAG, "Socket not Connected");
            }
            try {
                dout = new DataOutputStream(socket.getOutputStream());

                while (mRun) {

                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "S: Error", e);
            } finally {
                socket.close();
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "C: Error", e);
        }

    }

    //Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
    //class at on asynckTask doInBackground
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}
