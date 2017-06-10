package com.example.arsalankhan.firebasedatapayloadreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    /**
     *  Two Case
     *  1) App in the Background
     *   when the app is in the background the notification is received on the system tray while
     *   data is received in the intent extras of launcher activity
     *
     *   2) App in the Foreground
     *   when the app is in the foreground both data and notification are received on the onMessageReceived method
     *   of FirebaseMessagingService
     *
     */

    private TextView textViewTitle,textViewMessage;
    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewTitle= (TextView) findViewById(R.id.textViewTitle);
        textViewMessage= (TextView) findViewById(R.id.textViewMessage);

        //Receiving payload data
        receivePayload();

         broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                textViewTitle.setText(intent.getStringExtra("title"));
                textViewMessage.setText(intent.getStringExtra("message"));
            }
        };

        // Registrating the Broadcast Receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter(FcmMessagingServices.intent_filter));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void receivePayload() {

        if(getIntent().getExtras()!=null){

            String title=getIntent().getExtras().getString("title");
            String message=getIntent().getExtras().getString("message");

            textViewTitle.setText(title);
            textViewMessage.setText(message);


            /**
             * OR
             * YOu can also do it in the following way
             *
             *      for(String key: getIntent().getExtras().keySet()){
             *
             *          if(key.equals("title")){
             *            textViewTitle.setText(key);
             *          }
             *          else if(key.equals("message")){
             *             textViewMessage.setText(key);
             *          }
             *
             *      }
             */
        }
    }

}
