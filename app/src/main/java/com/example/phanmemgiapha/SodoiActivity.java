package com.example.phanmemgiapha;
import android.annotation.SuppressLint;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SodoiActivity extends AppCompatActivity {
        EditText ten,tuoi,mqh,thehe;
        Button tt,xacnhan;
        datagiapha datagiapha;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ten = findViewById(R.id.ten);
        tuoi = findViewById(R.id.tuoi);
        mqh = findViewById(R.id.mqh);
        thehe = findViewById(R.id.thehe);
        tt = findViewById(R.id.tieptuc);
        xacnhan = findViewById(R.id.xacnhan);
        datagiapha = new datagiapha(this);
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tent = ten.getText().toString();
                String tuoit = tuoi.getText().toString();
                String mqht = mqh.getText().toString();
                String thehet = thehe.getText().toString();
                if(tent.isEmpty() ||tuoit.isEmpty()||mqht.isEmpty()||thehet.isEmpty() ){
                    Toast.makeText(SodoiActivity.this,"Vui Lòng Nhập Đầy Đủ Thông Tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                int a ,b;
                try {
                    a = Integer.parseInt(tuoit);
                    b = Integer.parseInt(thehet);

                }catch (NumberFormatException e){
                    Toast.makeText(SodoiActivity.this,"Vui Lòng Nhập Tuổi và Đời là Số",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean themdulieu = datagiapha.themthanhvien(tent, a, mqht, b);
                if(themdulieu){
                    Toast.makeText(SodoiActivity.this,"Thêm Dữ Lie",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });
    }
}
