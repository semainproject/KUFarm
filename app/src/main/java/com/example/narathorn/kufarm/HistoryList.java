package com.example.narathorn.kufarm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by narathorn on 15/3/2018 AD.
 */

public class HistoryList extends ArrayAdapter<InformationGetter> {
    private Activity context;
    private List<InformationGetter> list;

    public HistoryList(Activity context, List<InformationGetter> list) {
        super(context, R.layout.layout_list, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.layout_list, null, true);
        TextView time = (TextView) listView.findViewById(R.id.time);
        InformationGetter informationGetter = list.get(position);
        if(informationGetter != null) {
            time.setText(informationGetter.getTime() + " น.");
        }
        return listView;
    }
}
