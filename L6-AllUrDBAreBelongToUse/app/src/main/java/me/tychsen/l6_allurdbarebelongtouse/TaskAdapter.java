package me.tychsen.l6_allurdbarebelongtouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_task, null);
        }

        Task task = getItem(position);

        if (task != null) {
            TextView txtName = (TextView) convertView.findViewById(R.id.taskName);
            TextView txtPlace = (TextView) convertView.findViewById(R.id.taskPlace);

            txtName.setText(task.getName());
            txtPlace.setText(task.getPlace());
        }

        return convertView;
    }
}
