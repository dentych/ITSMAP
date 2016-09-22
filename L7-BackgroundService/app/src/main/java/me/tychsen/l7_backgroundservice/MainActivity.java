package me.tychsen.l7_backgroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup broadcast receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra(BgService.BG_SERVICE_RESULT);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(BgService.BROADCAST_ACTION));
        Intent intent = new Intent(this, BgService.class);
        startService(intent);

        Toast.makeText(this, "Started service!", Toast.LENGTH_SHORT).show();
    }
}
