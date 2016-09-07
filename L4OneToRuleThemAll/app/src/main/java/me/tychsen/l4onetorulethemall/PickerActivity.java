package me.tychsen.l4onetorulethemall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class PickerActivity extends AppCompatActivity {
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        String[] numbersToShow = getArrayWithSteps(1000, 0, 10);
        numberPicker.setMaxValue(100);
        numberPicker.setDisplayedValues(numbersToShow);

        setupButtons();
    }

    private void setupButtons() {
        Button btnOk = (Button) findViewById(R.id.btnPickerOk);
        Button btnCancel = (Button) findViewById(R.id.btnPickerCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("value", numberPicker.getValue()*10);
                setResult(MainActivity.RESULT_PICKER, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    public String[] getArrayWithSteps(int iMaxValue, int iMinValue, int iStep) {
        int iStepsArray = (iMaxValue - iMinValue) / iStep + 1; //get the lenght array that will return

        String[] arrayValues = new String[iStepsArray]; //Create array with length of iStepsArray

        for (int i = 0; i < iStepsArray; i++) {
            arrayValues[i] = String.valueOf(iMinValue + (i * iStep));
        }

        return arrayValues;
    }
}
