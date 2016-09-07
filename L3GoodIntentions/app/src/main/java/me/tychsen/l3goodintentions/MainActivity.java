package me.tychsen.l3goodintentions;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
    }

    private void setupButtons() {
        Button btnEmail = (Button) findViewById(R.id.buttonEmail);
        Button btnFb = (Button) findViewById(R.id.buttonFB);
        Button btnMsg = (Button) findViewById(R.id.buttonMsg);
        Button btnAlarm = (Button) findViewById(R.id.buttonAlarm);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "This is an email from the Good Intentions app!");
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test email!");

                startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });

        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://feed"));

                startActivity(fbIntent);
            }
        });

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent();
                msgIntent.setAction(Intent.ACTION_SEND);
                msgIntent.putExtra(Intent.EXTRA_TEXT, "Hello from Good Intentions app!");
                msgIntent.setType("text/plain");
                msgIntent.setPackage("com.facebook.orca");

                try {
                    startActivity(msgIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(v.getContext(), "Messenger was not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Good Alarm");
                alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, 8);

                startActivity(alarmIntent);
            }
        });
    }
}
