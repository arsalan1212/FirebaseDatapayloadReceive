package com.example.arsalankhan.firebasedatapayloadreceive;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Arsalan khan on 6/10/2017.
 */

public class FcmMessagingServices extends FirebaseMessagingService {

    public static final String intent_filter="FcmMessageBroadcast";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent=new Intent(intent_filter);

        if(remoteMessage.getData().size()>0){

            String title=remoteMessage.getData().get("title");
            String message=remoteMessage.getData().get("message");

            intent.putExtra("title",title);
            intent.putExtra("message",message);


            //Broadcasting the title and message when received
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }
}
