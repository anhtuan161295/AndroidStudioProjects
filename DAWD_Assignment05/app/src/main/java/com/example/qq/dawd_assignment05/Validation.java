package com.example.qq.dawd_assignment05;

import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QQ on 3/30/2017.
 */

public class Validation {

    // error message
    private static final String err = "";

    public static boolean checkNull(EditText ed) {
        if (ed.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkDuplicate(EditText ed) {
        boolean ok = false;
        List<Book> list = BookManager.getList();
        if (!list.isEmpty()) {
            for (Book b : list) {
                if (b.getCode().equals(ed.getText().toString())){
                    ok = true;
                }
            }
        }else{
            ok = false;
        }

        return ok;
    }
}
