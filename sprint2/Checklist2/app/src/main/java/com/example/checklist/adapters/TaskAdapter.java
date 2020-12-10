package com.example.checklist.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.checklist.R;
import com.example.checklist.TaskItem;
import com.example.checklist.activities.CreateList;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskItem> {

    private List<TaskItem> taskItems;
    private Context context;
    public TaskAdapter(Context context, List<TaskItem> taskItems){

        super(context, 0, taskItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final TaskItem taskItem = getItem(position);

        if(row ==null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        final ToggleButton toggleButton = row.findViewById(R.id.toggleButton);
        //final EditText task_input = (EditText) row.findViewById(R.id.task_text);
        //to be implemented later for editable tasks
        final TextView task_input = (TextView) row.findViewById(R.id.task_text);
        final Button delete = (Button) row.findViewById(R.id.delete);



        toggleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                TaskItem current = getItem(position);
                if (current.isChecked() == false ) {
                    current.setChecked(true);
                    //crosses text out if task is complete
                    task_input.setPaintFlags(task_input.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                } else {
                    current.setChecked(false);
                    //clears strikethrough from text
                    task_input.setPaintFlags(task_input.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
                CreateList.taskDatabase.taskDao().update(current);
                notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TaskItem rTask = getItem(position);
                remove(rTask);
                CreateList.taskDatabase.taskDao().delete(rTask);
                notifyDataSetChanged();

            }
        });

        assert taskItem != null;
        toggleButton.setChecked(taskItem.isChecked());
        task_input.setText(taskItem.getTasktext());
        if(taskItem.isChecked() == true){
            task_input.setPaintFlags(task_input.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        return row;
    }

}
