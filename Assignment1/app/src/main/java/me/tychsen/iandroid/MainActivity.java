package me.tychsen.iandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView profilePic;
    TextView txtName;
    TextView txtId;
    CheckBox androidDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilePic = (ImageView) findViewById(R.id.imageView);
        txtName = (TextView) findViewById(R.id.txtName);
        txtId = (TextView) findViewById(R.id.txtId);
        androidDev = (CheckBox) findViewById(R.id.cbAndroid);

        // Set unknown profile pic
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.unknown);
        profilePic.setImageDrawable(drawable);

        // Set info if available
        if (savedInstanceState != null) {
            txtName.setText(savedInstanceState.getString("name"));
            txtId.setText(savedInstanceState.getString("id"));
            androidDev.setChecked(savedInstanceState.getBoolean("devStatus"));
            Bitmap bitmap = savedInstanceState.getParcelable("profilePic");
            profilePic.setImageBitmap(bitmap);
        }

        // Set onclick method on FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setupFabListener(fab);

        // Set onclick for image
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void setupFabListener(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);
                String name = txtName.getText().toString();
                String id =  txtId.getText().toString();
                boolean devStatus = androidDev.isChecked();

                if (!name.equalsIgnoreCase(getString(R.string.label_no_name_given))) {
                    intent.putExtra("name", name);
                }
                if (!id.equalsIgnoreCase(getString(R.string.label_no_id_given))) {
                    intent.putExtra("id", id);
                }
                intent.putExtra("devStatus", devStatus);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", txtName.getText().toString());
        outState.putString("id", txtId.getText().toString());
        outState.putBoolean("devStatus", androidDev.isChecked());
        Bitmap bitmap = ((BitmapDrawable) profilePic.getDrawable()).getBitmap();
        outState.putParcelable("profilePic", bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 100) {
            String name = data.getStringExtra("name");
            String id = data.getStringExtra("id");
            boolean devStatus = data.getBooleanExtra("devStatus", false);

            if (!name.isEmpty()) {
                txtName.setText(name);
            } else {
                txtName.setText(getString(R.string.label_no_name_given));
            }

            if (!id.isEmpty()) {
                txtId.setText(id);
            } else {
                txtId.setText(getString(R.string.label_no_id_given));
            }

            if (devStatus) {
                androidDev.setChecked(true);
            } else {
                androidDev.setChecked(false);
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profilePic.setImageBitmap(imageBitmap);
        }
    }
}
