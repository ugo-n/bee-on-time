package com.example.checklisttest.list_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.checklisttest.list_items.ChecklistItem;
import com.example.checklisttest.R;

import java.util.ArrayList;

public class ChecklistItemAdapter extends ArrayAdapter<ChecklistItem> {

    private ArrayList<ChecklistItem> checklistItems;
    private Context context;

    public ChecklistItemAdapter(Context context, ArrayList<ChecklistItem> checklistItems){
        super(context, 0, checklistItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ChecklistItem checklistItem = getItem(position);

        if(row ==null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.simple_checklist_item, parent, false);
        }

        //final ImageView checklistIcon = row.findViewById(R.id.checklistIcon);
        final TextView checklistName = row.findViewById(R.id.checklistName);

        assert checklistItem != null;
        //checklistIcon.setImageBitmap(checklistItem.checklistType);
        checklistName.setText(checklistItem.getChecklistName());

        return row;


    }
}
