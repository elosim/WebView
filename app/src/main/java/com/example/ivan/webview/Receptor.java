package com.example.ivan.webview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.example.ivan.webview.bd.GestorLlamadas;
import com.example.ivan.webview.pojo.Llamada;

/**
 * Created by ivan on 1/25/2016.
 */

public class Receptor extends BroadcastReceiver {
    private GestorLlamadas gl;
    @Override
    public void onReceive(Context context, Intent intent) {
        gl = new GestorLlamadas(context);



                try {
                    // TELEPHONY MANAGER class object to register one listner
                    TelephonyManager tmgr = (TelephonyManager) context
                            .getSystemService(Context.TELEPHONY_SERVICE);

                    //Create Listner
                    MyPhoneStateListener PhoneListener = new MyPhoneStateListener();

                    // Register listener for LISTEN_CALL_STATE
                    tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

                } catch (Exception e) {
                    Log.e("Phone Receive Error", " " + e);
                }

            }

            private class MyPhoneStateListener extends PhoneStateListener {
                private Llamada ll= new Llamada();
                public void onCallStateChanged(int state, String incomingNumber) {

                    Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

                    if (state == 1) {
                        gl.open();
                        Log.v("aa", "New Phone Call Event. Incomming Number : " + incomingNumber);
                        Time today = new Time(Time.getCurrentTimezone());
                        today.setToNow();

                        int a = today.monthDay;
                        int b = today.weekDay;
                        int c = today.month;
                        int d = today.year;

                        Log.v("aa","Ha llamado el dia : "+a+" "+b+ " del mes "+c+" del año "+d);

                        ll= new Llamada(Long.parseLong(incomingNumber),0,b,a,c,d);
                        gl.insert(ll);


                    }
                    if (state == TelephonyManager.CALL_STATE_IDLE){

                        gl.close();

                        Log.v("aa", "idle");
                    }
                }
            }

    }

