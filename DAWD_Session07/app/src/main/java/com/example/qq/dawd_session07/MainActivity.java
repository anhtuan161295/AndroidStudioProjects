package com.example.qq.dawd_session07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAddress;
    private Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        btnWrite = (Button) findViewById(R.id.btnWrite);

    }

    public void writeToFile(View v) {
        try {
            FileOutputStream fos = openFileOutput("myFile.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            String info = "";
            info += "Your name: " + etName.getText().toString();
            info += "\nYour address: " + etAddress.getText().toString();
            writer.write(info);
            writer.close();
            Toast.makeText(this, "Save file success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
