package me.tychsen.l3post_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    public static final String TAG = "ViewActivity";
    public static final int RESULT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Button editButton = (Button) findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(v.getContext(), EditActivity.class);
                TextView currentText = (TextView) findViewById(R.id.textView);
                editIntent.putExtra("currentText", currentText.getText().toString());
                startActivityForResult(editIntent, RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT) {
            return;
        }

        TextView editText = (TextView) findViewById(R.id.textView);
        String editedText = data.getStringExtra("editedText");
        editText.setText(editedText);
    }
}
