package me.tychsen.l3post_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText editedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editedText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        if (intent.hasExtra("currentText")) {
            editedText.setText(intent.getStringExtra("currentText"));
        }

        editedText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void saveEditedText(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        Intent intent = new Intent();
        EditText editedText = (EditText) findViewById(R.id.editText);
        intent.putExtra("editedText", editedText.getText().toString());
        setResult(ViewActivity.RESULT, intent);
        finish();
    }
}
