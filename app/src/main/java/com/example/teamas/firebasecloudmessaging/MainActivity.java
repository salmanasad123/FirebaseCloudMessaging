package com.example.teamas.firebasecloudmessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MTAG";
    TextView displayToken;
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayToken = (TextView) findViewById(R.id.tv_show_token);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                displayToken.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            }
        };

        if (SharedPrefManager.getInstance(this).getToken() != null) {
            displayToken.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            Log.d(TAG, "onCreate: " + SharedPrefManager.getInstance(this).getToken());
        }

        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));


        String data = getIntent().getStringExtra("name");
        if (data != null) {
            displayToken.setText(data);
        }
    }
}
