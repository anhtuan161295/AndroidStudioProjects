package com.example.qq.dawd_lab07;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText etHoten, etNgaySinh, etCMND, etBoSung;
    RadioButton rdTrungCap, rdCaoDang, rdDaiHoc;
    RadioGroup rdGroup;
    CheckBox chkDocBao, chkDocSach, chkDocCode;
    Button btnSend, btnSave, btnRead;

    String information = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etHoten = (EditText) findViewById(R.id.etHoTen);
        etNgaySinh = (EditText) findViewById(R.id.etNgaySinh);
        etCMND = (EditText) findViewById(R.id.etCMND);
        etBoSung = (EditText) findViewById(R.id.etBoSung);
        rdTrungCap = (RadioButton) findViewById(R.id.rdTrungCap);
        rdCaoDang = (RadioButton) findViewById(R.id.rdCaoDang);
        rdDaiHoc = (RadioButton) findViewById(R.id.rdDaiHoc);
        rdGroup = (RadioGroup) findViewById(R.id.radioGroup);
        chkDocBao = (CheckBox) findViewById(R.id.chkDocBao);
        chkDocSach = (CheckBox) findViewById(R.id.chkDocSach);
        chkDocCode = (CheckBox) findViewById(R.id.chkDocCode);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnRead = (Button) findViewById(R.id.btnRead);

    }

    public void doShowInformation(View v) {
        // Kiểm tra tên hợp lệ
        String ten = etHoten.getText().toString().trim();
        if (ten.length() < 3) {
            etHoten.requestFocus();
            etHoten.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra CMND hợp lệ
        String cmnd = etCMND.getText().toString().trim();
        if (cmnd.length() != 9) {
            etCMND.requestFocus();
            etCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra bằng cấp
        String bang = "";
        int id = rdGroup.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rad = (RadioButton) findViewById(id);
        bang = rad.getText().toString();
        // Kiểm tra sở thích
        String sothich = "";
        if (chkDocBao.isChecked()) {
            sothich += chkDocBao.getText() + "\n";
        }
        if (chkDocSach.isChecked()) {
            sothich += chkDocSach.getText() + "\n";
        }
        if (chkDocCode.isChecked()) {
            sothich += chkDocCode.getText() + "\n";
        }

        String bosung = etBoSung.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Tạo nội dung
        information = ten + "\n";
        information += cmnd + "\n";
        information += bang + "\n";
        information += sothich;
        information += "-------------------------\n";
        information += "Thông tin bổ sung:\n";
        information += bosung + "\n";
        information += "-------------------------";
        builder.setMessage(information); // thiết lập nội dung
        builder.create().show(); // hiển thị Dialog

    }

    public void writeData(View v) {
        try {
            FileOutputStream out = openFileOutput("myfile.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(information);
            writer.close();
            Toast.makeText(this, "Lưu file thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readData(View v) {
        try {
            FileInputStream in = openFileInput("myfile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data = reader.readLine()) != null) {
                builder.append(data);
                builder.append("\n");
            }
            in.close();

            // Xử lý hiển thị dữ liệu trong 1 Activity có tên là DisplayActivity
            // Tạo Intent để mở DisplayActivity
            Intent myIntent = new Intent(MainActivity.this, DisplayActivity.class);
            // Khai báo Bundle
            Bundle bundle = new Bundle();
            // Đưa dữ liệu kiểu string vào bundle
            bundle.putString("informations", builder.toString());
            // Đưa bundle vào Intent
            myIntent.putExtra("MyPackage", bundle);
            // Mở Activity DisplayActivity
            startActivity(myIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
