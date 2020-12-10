package com.example.checklisttest.list_adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.checklisttest.R;
import com.example.checklisttest.list_items.TaskItem;

import java.util.ArrayList;

public class TaskItemAdapter extends ArrayAdapter<TaskItem> {

    private ArrayList<TaskItem> taskItems;
    private Context context;
    public TaskItemAdapter(Context context, ArrayList<TaskItem> taskItems){

        super(context, 0, taskItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final TaskItem taskItem = getItem(position);

        if(row ==null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item, parent, false);
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
                if (current.getCheck() == false ) {
                    current.setCheck(true);
                    //crosses text out if task is complete
                    task_input.setPaintFlags(task_input.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    current.setCheck(false);
                    //clears strikethrough from text
                    task_input.setPaintFlags(task_input.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               TaskItem rTask = getItem(position);
               remove(rTask); //deletes task at current position in listview

           }
        });

        assert taskItem != null;
        toggleButton.setChecked(taskItem.getCheck());
        task_input.setText(taskItem.getTask());

        return row;
    }

}
