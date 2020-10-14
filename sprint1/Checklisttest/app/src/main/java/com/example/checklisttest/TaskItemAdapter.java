package com.example.checklisttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class TaskItemAdapter extends ArrayAdapter<TaskItem> {
    public TaskItemAdapter(Context context, ArrayList<TaskItem> taskItems){

        super(context, 0, taskItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskItem taskItem = getItem(position);

        if(convertView ==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item, parent, false);
        }

        ToggleButton toggleButton = convertView.findViewById(R.id.toggleButton);
        EditText task_input = (EditText) convertView.findViewById(R.id.task_text);

        assert taskItem != null;
        toggleButton.setChecked(taskItem.check);
        task_input.setText(taskItem.task);

        return convertView;
    }
}
