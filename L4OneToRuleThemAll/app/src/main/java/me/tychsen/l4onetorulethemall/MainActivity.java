package me.tychsen.l4onetorulethemall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int RESULT_PICKER = 100;
    public static final int RESULT_EDITTEXT = 101;
    public static final int RESULT_SLIDER = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
    }

    private void setupButtons() {
        Button btnPicker = (Button) findViewById(R.id.btnPicker);
        Button btnEditText = (Button) findViewById(R.id.btnEditText);
        Button btnSlider = (Button) findViewById(R.id.bttnSlider);

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PickerActivity.class);
                startActivityForResult(intent, RESULT_PICKER);
            }
        });

        btnEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditTextActivity.class);
                startActivityForResult(intent, RESULT_EDITTEXT);
            }
        });

        btnSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SliderActivity.class);
                startActivityForResult(intent, RESULT_SLIDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_PICKER: {
                String msg = "You picked the value: " + data.getIntExtra("value", 0);
                Toast t = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                t.show();
                break;
            }
            case RESULT_EDITTEXT: {
                String msg = "You picked a lot of values :P (this is not yet implemented)";
                Toast t = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                t.show();
                break;
            }
            case RESULT_CANCELED: {
                String msg = "You cancelled the activity";
                Toast t = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                t.show();
                break;
            }
        }
    }
}
