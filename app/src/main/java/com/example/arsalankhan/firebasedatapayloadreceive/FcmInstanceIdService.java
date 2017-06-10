package com.example.arsalankhan.firebasedatapayloadreceive;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Arsalan khan on 6/10/2017.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String Token= FirebaseInstanceId.getInstance().getToken();

        // receiving the Token in the Log cat
        Log.d("Token","Token: "+Token);
    }
}
