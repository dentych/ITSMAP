package me.tychsen.iandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditActivity extends AppCompatActivity {
    public static final int RESULT_SUCCESS = 100;

    EditText txtName;
    EditText txtId;
    RadioButton radioYes;
    RadioButton radioNo;
    Button btnSave;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtName = (EditText) findViewById(R.id.txtName);
        txtId = (EditText) findViewById(R.id.txtId);
        radioYes = (RadioButton) findViewById(R.id.radioYes);
        radioNo = (RadioButton) findViewById(R.id.radioNo);

        if (savedInstanceState != null) {
            txtName.setText(savedInstanceState.getString("name"));
            txtId.setText(savedInstanceState.getString("id"));
            if (savedInstanceState.getBoolean("devStatus")) {
                radioYes.setChecked(true);
            } else {
                radioNo.setChecked(true);
            }
        } else {
            txtName.setText(getIntent().getStringExtra("name"));
            txtId.setText(getIntent().getStringExtra("id"));
            if (getIntent().getBooleanExtra("devStatus", false)) {
                radioYes.setChecked(true);
            } else {
                radioNo.setChecked(true);
            }
        }

        setupOnClickForButtonSave();
        setupOnClickForButtonCancel();
    }

    private void setupOnClickForButtonCancel() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void setupOnClickForButtonSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", txtName.getText().toString());
                intent.putExtra("id", txtId.getText().toString());
                intent.putExtra("devStatus", radioYes.isChecked());
                setResult(RESULT_SUCCESS, intent);
                finish();
            }
        });
    }
}
