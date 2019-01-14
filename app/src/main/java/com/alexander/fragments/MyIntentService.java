package com.alexander.fragments;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyIntentService extends IntentService {

    private static final String DATA = "Data";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        for (int i=0; i<100; i++) {
                try {

                    TimeUnit.SECONDS.sleep(2);
                    sendBroadcast(i*3);

                } catch (InterruptedException e){
                    Log.v("InterruptedException", e.getMessage());
                }

        }
    }

    private void sendBroadcast(int data){
        Intent broadcastIntent = new Intent("com.alexander.SEND_MESSAGES_FILTER");
        broadcastIntent.putExtra(DATA, data);
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(broadcastIntent);
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        return intent;
    }
}
