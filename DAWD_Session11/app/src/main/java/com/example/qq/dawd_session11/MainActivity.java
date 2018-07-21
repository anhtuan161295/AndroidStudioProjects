package com.example.qq.dawd_session11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                // gọi hàm xử lý add
                return true;
            case R.id.menuDisplay:
                // gọi hàm xử lý display
                return true;
            case R.id.menuExit:
                // gọi hàm xử lý exit
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
