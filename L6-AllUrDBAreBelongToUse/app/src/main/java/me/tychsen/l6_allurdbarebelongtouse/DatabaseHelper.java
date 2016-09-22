package me.tychsen.l6_allurdbarebelongtouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "L6DB1";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Tasks";
    private static final String DB_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_NAME + " (" +
            Task.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Task.COLUMN_NAME + " text," +
            Task.COLUMN_PLACE + " text" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    public long addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(Task.COLUMN_NAME, task.getName());
        content.put(Task.COLUMN_PLACE, task.getPlace());

        return db.insert(TABLE_NAME, null, content);
    }

    public Task getTask(long taskId) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Task.COLUMN_ID,
                Task.COLUMN_NAME,
                Task.COLUMN_PLACE
        };

        String selection = Task.COLUMN_ID + " = ?";
        String[] selectionArgs = {Long.toString(taskId)};

        Cursor c = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        Task task;
        if (c.moveToFirst()) {
            task = new Task(c.getLong(0), c.getString(1), c.getString(2));
        } else {
            task = null;
        }

        c.close();
        return task;
    }

    void deleteTask(long taskId) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = Task.COLUMN_ID + " = ?";
        String[] args = {Long.toString(taskId)};

        db.delete(TABLE_NAME, selection, args);
    }

    List<Task> getAllTasks() {
        Log.d(getClass().getCanonicalName(), "GetAllTasks started!");
        SQLiteDatabase db = getReadableDatabase();
        List<Task> tasks = new ArrayList<>();

        String[] columns = {
                Task.COLUMN_ID,
                Task.COLUMN_NAME,
                Task.COLUMN_PLACE
        };

        String orderBy = Task.COLUMN_ID;

        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, orderBy);

        Log.d(getClass().getCanonicalName(), "Getting tasks!");
        if (c.moveToFirst()) {
            do {
                Log.d(getClass().getCanonicalName(), "Getting task!");
                Task task = new Task(c.getLong(0), c.getString(1), c.getString(2));
                tasks.add(task);
            } while (c.moveToNext());
        }
        c.close();

        return tasks;
    }
}
