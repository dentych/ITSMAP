package me.tychsen.l7_boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BoundService boundService;
    boolean isBound = false;

    CheckBox checkBox;

    // TODO
    // 2: Bound Service -  <bonus: make your own pun>

    // create another Service and add a few state variables, e.g. int count = 42;
    // make this a bound Service, so that your activity can bind and unbind to it (add two new buttons for this)
    // Create a third button that calls the service directly (if bound) and retrieves the state; then prints it out

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        log("Activity created!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
        startService(intent);
        boolean result = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        log("Activity onStart! Result: " + result);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            log("OnServiceConnected!");
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            boundService = binder.getService();
            isBound = true;
            checkBox.setChecked(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            log("OnServiceDisconnected!");
            isBound = false;
            checkBox.setChecked(false);
        }
    };

    public void log(String msg) {
        Log.d("MainActivity", msg);
    }

    public void onButtonClick(View view) {
        if (isBound) {
            int number = boundService.getCounter();
            Toast.makeText(this, "Current number: " + number, Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(this, "Service is not bound!", Toast.LENGTH_SHORT).show();
        }
    }
}
