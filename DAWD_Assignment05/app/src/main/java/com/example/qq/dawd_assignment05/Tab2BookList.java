package com.example.qq.dawd_assignment05;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by QQ on 3/29/2017.
 */

public class Tab2BookList extends Fragment {

    private static ListView lvBook;
    // Khai báo Data Source lưu trữ danh sách book
    public ArrayList<Book> arrBook = null;
    // Khai báo ArrayAdapter cho ListView
//    public ArrayAdapter<Book> adapter = null;
    private static List<HashMap<String, String>> aList = null;
    private static SimpleAdapter adapter = null;
    private static String[] keys = {"lvCode", "lvTitle", "lvImg"};
    private static int[] ids = {R.id.lvCode, R.id.lvTitle, R.id.lvImg};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        BookManager.list.add(new Book("1", "Java", "Khoa học", 111, "Oracle", "h1.png"));

        lvBook = (ListView) rootView.findViewById(R.id.lvBook);

        ArrayList<String> itemcode = new ArrayList<>();
        ArrayList<String> itemtitle = new ArrayList<>();
        ArrayList<Integer> imgid = new ArrayList<>();

        for (Book b : BookManager.list) {
            int dotPosition = b.getImg().indexOf(".");
            String resName = b.getImg().substring(0, dotPosition);
            int resId = getResources().getIdentifier(resName, "drawable", rootView.getContext().getPackageName());
            itemcode.add(b.getCode());
            itemtitle.add(b.getTitle());
            imgid.add(resId);
        }

        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < itemcode.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("lvCode", "Code : " + itemcode.get(i));
            hm.put("lvTitle", "Title : " + itemtitle.get(i));
            hm.put("lvImg", imgid.get(i).toString());
            aList.add(hm);
        }

        // Keys used in Hashmap
//        String[] keys = {"lvCode", "lvTitle", "lvImg"};

        // Ids of views in listview_layout
//        int[] ids = {R.id.lvCode, R.id.lvTitle, R.id.lvImg};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
//        SimpleAdapter adapter = new SimpleAdapter(rootView.getContext(), aList, R.layout.listview_layout, keys, ids);
        adapter = new SimpleAdapter(getContext(), aList, R.layout.listview_layout, keys, ids);
        lvBook.setAdapter(adapter);


////         Gắn DataSource vào ArrayAdapter
//        adapter = new ArrayAdapter<Book>(rootView.getContext(), R.layout.support_simple_spinner_dropdown_item, arrBook);
//
//        BookManager.setAdapter(adapter);
////         gắn adapter vào listview
//        lvBook.setAdapter(adapter);

        lvBook.setOnItemClickListener(new MyListViewEvents());
//        lvBook.setOnItemLongClickListener(new MyListViewEvents());


        return rootView;
    }


    public static void updateList() {
        Context context = MainActivity.t2.getContext();
        aList = new ArrayList<>();
        ArrayList<String> itemcode = new ArrayList<>();
        ArrayList<String> itemtitle = new ArrayList<>();
        ArrayList<Integer> imgid = new ArrayList<>();

        for (Book b : BookManager.list) {
            int dotPosition = b.getImg().indexOf(".");
            String resName = b.getImg().substring(0, dotPosition);
            int resId = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
            itemcode.add(b.getCode());
            itemtitle.add(b.getTitle());
            imgid.add(resId);
        }

        // Each row in the list stores country name, currency and flag
        aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < itemcode.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("lvCode", "Code : " + itemcode.get(i));
            hm.put("lvTitle", "Title : " + itemtitle.get(i));
            hm.put("lvImg", imgid.get(i).toString());
            aList.add(hm);
        }
        adapter = null;
        adapter = new SimpleAdapter(context, aList, R.layout.listview_layout, keys, ids);
        lvBook.setAdapter(adapter);
    }

    private class MyListViewEvents implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Khi click chuột 1 item trong list view sẽ hiển thị thêm thông tin về giá và tác giả
            Book book = null;
            for (Book b : BookManager.getList()) {
                if (b.getCode().equals(BookManager.getList().get(position).getCode())) {
                    book = b;
                }
            }

            Toast.makeText(getActivity(), "Giá " + book.getPrice() +
                    " - Tác giả: " + book.getAuthor(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // Xóa vị trí position
//            BookManager.removeBook(position);
//            adapter.notifyDataSetChanged();
            return false;
        }
    }

}
