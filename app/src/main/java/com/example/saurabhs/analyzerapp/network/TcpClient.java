package com.example.saurabhs.analyzerapp.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Saurabh on 11/14/2017.
 */

public class TcpClient {

    // Log tag used for Logging
    private static final String LOG_TAG = TcpClient.class.getSimpleName();

    private static final String SERVER_IP = "10.0.0.178"; //server IP address
//    private static final String SERVER_IP = "10.1.224.95"; //server IP address
    private static final int SERVER_PORT = 1234;
    // message to send to the server
    private String mServerMessage;
    // sends message received notifications
    private OnMessageReceived mMessageListener = null;
    // while this is true, the server will continue running
    private boolean mRun = false;
    // used to send messages
//    private PrintWriter mBufferOut;
    private DataOutputStream dout;
    // used to read messages from the server
//    private BufferedReader mBufferIn;
    private DataInputStream din;


    /**
     * Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public TcpClient(OnMessageReceived listener) {
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
        Log.i(LOG_TAG, "Message Length: " + String.valueOf(messageBytes.length));
        if (dout != null){
            try {
                dout.write(messageBytes, 0, messageBytes.length);
                dout.flush();
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getLocalizedMessage());
            }
        }
    }

    /**
     * Close the connection and release the members
     */
    public void stopClient() {

        mRun = false;
//
//        if (mBufferOut != null) {
//            mBufferOut.flush();
//            mBufferOut.close();
//        }

        mMessageListener = null;
//        mBufferIn = null;
        din = null;
//        mBufferOut = null;
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

            try {

                //sends the message to the server
//                mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                dout = new DataOutputStream(socket.getOutputStream());

                //receives the message which the server sends back
//                mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                din = new DataInputStream(socket.getInputStream());


                //in this while the client listens for the messages sent by the server
                while (mRun) {

                    byte [] bytes = new byte[119];
//                    mServerMessage = mBufferIn.readLine();
                    din.readFully(bytes);
                    StringBuilder stringBuilder = new StringBuilder();
                    char ch;
                    for (byte b : bytes){
                        ch = (char) b;
                        stringBuilder.append(ch);
                    }
                    mServerMessage = stringBuilder.toString();

                    if (mServerMessage != null && mMessageListener != null) {
                        //call the method messageReceived from MyActivity class
                        mMessageListener.messageReceived(mServerMessage);
                    }
                }

                Log.e(LOG_TAG, "S: Received Message from server: '" + mServerMessage + "'");

            } catch (Exception e) {

                Log.e(LOG_TAG, "S: Error", e);

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
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
