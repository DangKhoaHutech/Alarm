package com.example.alarm;

import java.util.concurrent.BrokenBarrierException;

import android.content.BroadcastReceiver;
import  android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {

        Log.e("Báo thức", "!");
        String string_intent= intent.getExtras().getString("extra");
        Log.e("Ban truyen",string_intent);


        Intent myIntent= new Intent(context,Music.class);
        myIntent.putExtra("extra",string_intent);
        context.startService(myIntent);


    }
}
