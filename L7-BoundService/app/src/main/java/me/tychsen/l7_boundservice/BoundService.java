package me.tychsen.l7_boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BoundService extends Service {
    public static final String LOG_TAG = "BoundService";
    private IBinder binder = new LocalBinder();
    private int counter;
    private boolean running = false;

    public BoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(LOG_TAG, "Creating bound service!");

        counter = 0;
        running = true;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (running) {
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
