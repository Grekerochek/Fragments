package com.alexander.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    private static final String DATA = "Data";

    private ViewCallback mViewCallback;

    public CustomBroadcastReceiver(ViewCallback viewCallback){
        this.mViewCallback = viewCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mViewCallback.onChanged(intent.getIntExtra(DATA, 0));
    }
}
