package me.tychsen.l6_allurdbarebelongtouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TaskAdapter taskAdapter;

    Button btnTask;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        taskAdapter = new TaskAdapter(this, android.R.layout.simple_list_item_1);
        btnTask = (Button) findViewById(R.id.btnTask);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(taskAdapter);

        taskAdapter.addAll(databaseHelper.getAllTasks());

        setupButtonClickListener();
        setupItemLongClickListener();
    }

    private void setupButtonClickListener() {
        btnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtName = (EditText) findViewById(R.id.txtTask);
                EditText txtPlace = (EditText) findViewById(R.id.txtPlace);
                String name = txtName.getText().toString();
                String place = txtPlace.getText().toString();

                Task task = new Task(name, place);

                long id = databaseHelper.addTask(task);

                task.setId(id);

                taskAdapter.add(task);

                Toast.makeText(v.getContext(), "Task with ID: " + id + " added to list.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupItemLongClickListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = taskAdapter.getItem(position);

                databaseHelper.deleteTask(task.getId());

                taskAdapter.remove(task);

                return true;
            }
        });
    }
}
