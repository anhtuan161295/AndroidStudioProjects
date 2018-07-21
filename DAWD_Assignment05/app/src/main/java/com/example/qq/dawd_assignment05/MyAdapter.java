package com.example.qq.dawd_assignment05;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by QQ on 3/31/2017.
 */

public class MyAdapter extends SimpleAdapter{
    private final Context context;
    public LayoutInflater inflater = null;
    private List<HashMap<String, String>> data;

    public MyAdapter(Context context, List<HashMap<String, String>> data , int resourse,  String[] keys, int[] ids) {
        super(context, data, resourse, keys, ids);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data= data;
    }



    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        View row = view;
        if (row == null)
            row = inflater.inflate(R.layout.listview_layout, null);

        TextView lvCode = (TextView) row.findViewById(R.id.lvCode);
        ImageView imageView = (ImageView) row.findViewById(R.id.icon);
        TextView lvTitle = (TextView) row.findViewById(R.id.lvTitle);

        lvCode.setText(data.get(position).get("lvCode"));
        imageView.setImageResource(Integer.parseInt(data.get(position).get("lvImg")));
        lvTitle.setText("Description " + data.get(position).get("lvTitle"));
        return row;
    }
}


