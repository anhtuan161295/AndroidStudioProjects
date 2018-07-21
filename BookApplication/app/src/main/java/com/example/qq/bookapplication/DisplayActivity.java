package com.example.qq.bookapplication;

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

import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    ListView lvBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        loadData();

        lvBook.setOnItemClickListener(new MyListViewEvents());
        lvBook.setOnItemLongClickListener(new MyListViewEvents());

    }

    public void loadData() {
        lvBook = (ListView) findViewById(R.id.lvBook);
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, R.layout.support_simple_spinner_dropdown_item, dbHandler.display());
        lvBook.setAdapter(adapter);

        dbHandler.close();
    }

    public void back(View view) {
        finish();
    }

    private class MyListViewEvents implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Action");
            builder.setMessage("Please choose an action");
            builder.setPositiveButton(R.string.lblUpdate, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Book book = (Book) lvBook.getAdapter().getItem(position);
                    Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                    intent.putExtra("code", String.valueOf(book.getCode()));
                    startActivity(intent);
                    dialog.cancel();
                }
            });
            builder.setNegativeButton(R.string.lblDelete, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Delete book");
                    builder.setMessage("Are you sure to delete this book ?");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            MyDBHandler handler = new MyDBHandler(view.getContext(), null, null, 1);
                            List<Book> list = handler.display();
                            Book book = (Book) lvBook.getAdapter().getItem(position);
                            handler.delete(String.valueOf(book.getCode()));
                            handler.close();
                            Toast.makeText(DisplayActivity.this, "Delete book successfully", Toast.LENGTH_SHORT).show();
                            loadData();
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

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

            return false;
        }
    }


}
