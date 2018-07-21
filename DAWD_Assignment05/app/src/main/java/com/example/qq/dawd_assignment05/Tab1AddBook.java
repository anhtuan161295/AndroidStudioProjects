package com.example.qq.dawd_assignment05;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by QQ on 3/29/2017.
 */

public class Tab1AddBook extends Fragment {
    private EditText etCode, etTitle, etPrice;
    private Spinner spType, spAuthor, spImg;
    private Button btnAddBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1, container, false);

        etCode = (EditText) rootView.findViewById(R.id.edCode);
        etTitle = (EditText) rootView.findViewById(R.id.edTitle);
        spType = (Spinner) rootView.findViewById(R.id.spinnerType);
        etPrice = (EditText) rootView.findViewById(R.id.edPrice);
        spAuthor = (Spinner) rootView.findViewById(R.id.spinnerAuthor);
        spImg = (Spinner) rootView.findViewById(R.id.spinnerImg);
        btnAddBook = (Button) rootView.findViewById(R.id.btnAddBook);


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        return rootView;
    }


    public void setNull() {
        etCode.setText("");
        etTitle.setText("");
        spType.setSelection(0);
        etPrice.setText("");
        spAuthor.setSelection(0);
        etCode.requestFocus();
        spImg.setSelection(0);
    }

    public void validate() {

        if (Validation.checkNull(etCode)) {
            Toast.makeText(getContext(), R.string.errCodeRequired, Toast.LENGTH_SHORT).show();
        } else if (Validation.checkDuplicate(etCode)) {
            Toast.makeText(getContext(), R.string.errCodeDuplicate, Toast.LENGTH_SHORT).show();
        } else if (Validation.checkNull(etTitle)) {
            Toast.makeText(getContext(), R.string.errTitleRequired, Toast.LENGTH_SHORT).show();
        } else if (Validation.checkNull(etPrice)) {
            Toast.makeText(getContext(), R.string.errPriceRequired, Toast.LENGTH_SHORT).show();
        } else {
            Book b = new Book();
            b.setCode(etCode.getText().toString());
            b.setTitle(etTitle.getText().toString());
            b.setType(spType.getSelectedItem().toString());
            b.setPrice(Double.parseDouble(etPrice.getText().toString()));
            b.setAuthor(spAuthor.getSelectedItem().toString());
            b.setImg(spImg.getSelectedItem().toString());
            BookManager.list.add(b);
            Tab2BookList.updateList();
            Toast.makeText(getContext(), "Đã thêm sách thành công", Toast.LENGTH_SHORT).show();
        }


    }
}

