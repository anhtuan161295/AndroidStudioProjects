package com.example.qq.dawd_assignment09;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    ListView lvEmployee;
    ArrayList<Employee> arrEmployee = new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lvEmployee = (ListView) findViewById(R.id.lvEmployee);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        arrEmployee = (ArrayList<Employee>) bundle.getSerializable("arrEmployee");
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrEmployee);
        lvEmployee.setAdapter(adapter);

        lvEmployee.setOnItemClickListener(new MyListViewEvents());

    }

    public void back(View view) {
        finish();
    }


    private class MyListViewEvents implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Tác vụ");
            builder.setMessage("Chọn 1 tác vụ");
            builder.setPositiveButton(R.string.lblUpdate, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Employee emp = (Employee) lvEmployee.getAdapter().getItem(position);
                    Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
//                    Toast.makeText(DisplayActivity.this, emp.toString(), Toast.LENGTH_SHORT).show();
                    intent.putExtra("id", String.valueOf(emp.getId()));
                    startActivity(intent);
                    dialog.cancel();
                    finish();
                }
            });
            builder.setNegativeButton(R.string.lblDelete, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Xóa nhân viên");
                    builder.setMessage("Xác nhận xóa ?");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            MyDBHandler handler = new MyDBHandler(view.getContext(), null, null, 1);
                            Employee emp = (Employee) lvEmployee.getAdapter().getItem(position);
                            arrEmployee.remove(emp);
                            adapter.notifyDataSetChanged();
                            handler.delete(String.valueOf(emp.getId()));
                            handler.close();
                            Toast.makeText(DisplayActivity.this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            dialog.cancel();
                        }
                    });
                    builder.create().show();

                    dialog.cancel();
                }
            });
            builder.create().show();
        }
    }
}
