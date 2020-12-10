package com.example.checklist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.checklist.ListNameItem;
import com.example.checklist.R;
import com.example.checklist.Updatelist;
import com.example.checklist.activities.CreateList;

import java.util.List;

public class ListNameAdapter extends ArrayAdapter<ListNameItem> {

    private List<ListNameItem> checklistItems;
    private Context context;

    public ListNameAdapter(Context context, List<ListNameItem> checklistItems){
        super(context, 0, checklistItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ListNameItem listNameItem = getItem(position);

        if(row ==null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.listname_item, parent, false);
        }

        final TextView checklistName = row.findViewById(R.id.txt_name);
        final Button delete_button = row.findViewById(R.id.delete_btn);
        final Button edit_button = row.findViewById(R.id.edit_btn);

        delete_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ListNameItem rListName = getItem(position);
                remove(rListName);
                CreateList.taskDatabase.taskDao().nukeTable(rListName.getId());
                CreateList.listNameDatabase.listNameDao().delete(rListName);
                notifyDataSetChanged();
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ListNameItem eListName = getItem(position);
                Intent i = new Intent(v.getContext().getApplicationContext(), Updatelist.class);
                i.putExtra("id", eListName.getId());
                v.getContext().startActivity(i);
            }
        });

        assert listNameItem != null;
        checklistName.setText(listNameItem.getName());

        return row;
    }
}

