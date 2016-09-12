package me.tychsen.l4onetorulethemall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        setupButtons();
    }

    private void setupButtons() {
        Button btnOk = (Button) findViewById(R.id.btnEditTextOk);
        Button btnCancel = (Button) findViewById(R.id.btnEditTextCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtName = (EditText) findViewById(R.id.txtName);
                EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
                EditText txtAge = (EditText) findViewById(R.id.txtAge);
                EditText txtPassword = (EditText) findViewById(R.id.txtPasswd);
                Intent response = new Intent();
                response.putExtra("name", txtName.getText().toString());
                response.putExtra("email", txtEmail.getText().toString());
                response.putExtra("age", txtAge.getText().toString());
                response.putExtra("password", txtPassword.getText().toString());
                setResult(MainActivity.RESULT_EDITTEXT, response);
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
}
