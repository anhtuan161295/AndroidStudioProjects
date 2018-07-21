package com.example.qq.dawd_assignment11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayList<Contact> arrContact = new ArrayList<>();
    ArrayAdapter<Contact> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lvContact = (ListView) findViewById(R.id.lvContact);
        Bundle bundle = getIntent().getExtras();
        arrContact = (ArrayList<Contact>) bundle.getSerializable("arrContact");
        adapter = new ArrayAdapter<Contact>(this, R.layout.support_simple_spinner_dropdown_item, arrContact);
        lvContact.setAdapter(adapter);
        registerForContextMenu(lvContact);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.my_context_menu, menu);

        // Hiển thị context menu khi long click trên list view
        if (v.getId()== R.id.lvContact){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(arrContact.get(info.position).toString());
            String[] menuItems = getResources().getStringArray(R.array.context_menu);
            for (int i = 0 ; i < menuItems.length; i++ ){
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menuDelete:
//                //add
//                break;
//            case R.id.menuBack:
//                //back to add
//                back();
//                break;
//            default:
//                break;
//        }
//        return super.onContextItemSelected(item);




        // Xử lí khi click trên 1 chức năng trong context menu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId(); // lấy id context dc chọn
        String[] menuItems = getResources().getStringArray(R.array.context_menu); // lấy danh sách context menu
        String menuItemName = menuItems[menuItemIndex]; // lấy tên của context dc chọn
        String listItemName = arrContact.get(info.position).toString(); // lấy item dc chọn trong listview

        if (menuItemIndex == 0) { // Nếu chọn xóa
            Contact contact = arrContact.get(info.position);

            MyDBHandler handler = new MyDBHandler(this, null, null, 1);
            handler.delete(String.valueOf(contact.getId()));
            arrContact.remove(info.position); // xóa row trong listview tại vị trí click chuột
            adapter.notifyDataSetChanged();
//            Toast.makeText(this, contact.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Xóa danh bạ thành công", Toast.LENGTH_SHORT).show();

        } else if (menuItemIndex == 1) { // Nếu chọn sửa
            // sửa
            finish();
        }

        return true;
    }


    public void back() {
//        Intent intent =new Intent(DisplayActivity.this, AddActivity.class);
//        startActivity(intent);
        finish();
    }
}
