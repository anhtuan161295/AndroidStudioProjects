package com.example.qq.dawd_assignment11;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by QQ on 4/10/2017.
 */

public class MyListViewEvents implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
