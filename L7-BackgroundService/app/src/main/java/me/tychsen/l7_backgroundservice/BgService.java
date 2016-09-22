package me.tychsen.l7_backgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class BgService extends Service {
    public static final String BROADCAST_ACTION =
            "me.tychsen.l7_backgroundservice.BG_SERVICE_RESULT";
    public static final int WAIT_TIME = 5000;
    public static final String LOG_TAG = "BG_SERVICE";
    public static final String BG_SERVICE_RESULT = "bg_service_result";
    private boolean active = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "Created service!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!active) {
            Log.d(LOG_TAG, "Starting background service!");
            active = true;
            doBackgroundJob(1000);
        }
        return START_STICKY;
    }

    private void doBackgroundJob(final int waitTime) {
        AsyncTask<Object, Object, String> task = new AsyncTask<Object, Object, String>() {
            @Override
            protected String doInBackground(Object... params) {
                String s;
                try {
                    Log.d(LOG_TAG, "Starting wait.");
                    Thread.sleep(waitTime);
                    Log.d(LOG_TAG, "Done waiting for ms: " + waitTime);
                } catch (InterruptedException e) {
                    Log.d(LOG_TAG, "An error occurred while waiting.");
                    s = "Background service failed to wait";
                }
                s = "Background service completed job after " + waitTime + " ms.";

                return s;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                postResult(result);

                if (active) {
                    doBackgroundJob(WAIT_TIME);
                }
            }

        };

        task.execute();
    }

    private void postResult(String result) {
        Intent broadcastIntent = new Intent(BROADCAST_ACTION)
                .putExtra(BG_SERVICE_RESULT, result);
        Log.d(LOG_TAG, "Broadcasting:" + result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }

    @Override
    public void onDestroy() {
        active = false;
    }
}
