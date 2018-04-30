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

public class PredictList extends ArrayAdapter<TreeGetter> {
    private Activity context;
    private List<TreeGetter> list;

    public PredictList(Activity context, List<TreeGetter> list) {
        super(context, R.layout.layout_plist, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.layout_plist, null, true);
        TextView type = (TextView) listView.findViewById(R.id.type);
        TreeGetter treeGetter = list.get(position);
        type.setText(treeGetter.getName());
        return listView;
    }
}
