package com.example.qq.dawd_assignment11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText etName, etPhone, etAddress, etEmail;
    ArrayList<Contact> arrContact = new ArrayList<>();
    ArrayAdapter<Contact> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etEmail = (EditText) findViewById(R.id.etEmail);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                //add
                addContact();
                break;
            case R.id.menuDisplay:
                passData();
                //display
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void setNull() {
        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etEmail.setText("");
        etName.requestFocus();
    }

    public void addContact(){
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        Contact contact = new Contact(0, name, phone, address, email);

        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        handler.add(contact);
        handler.close();
        Toast.makeText(this, "Thêm danh bạ thành công", Toast.LENGTH_SHORT).show();
    }

    public void passData(){
        Intent passIntent = new Intent(AddActivity.this, DisplayActivity.class);
        Bundle bundle = new Bundle();
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        arrContact = (ArrayList<Contact>) handler.display();
        bundle.putSerializable("arrContact", arrContact);
        passIntent.putExtras(bundle);
        startActivity(passIntent);
    }
}
