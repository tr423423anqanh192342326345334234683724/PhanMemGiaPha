package com.example.phanmemgiapha;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SodoiActivity extends AppCompatActivity {
    EditText ten, tuoi, mqh, thehe;
    Button tt, xacnhan, quaylai;
    datagiapha datagiapha;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sodoi);
        ten = findViewById(R.id.ten);
        tuoi = findViewById(R.id.tuoi);
        thehe = findViewById(R.id.thehe);
        tt = findViewById(R.id.tieptuc);
        xacnhan = findViewById(R.id.xacnhan);
        quaylai = findViewById(R.id.quaylai);
        datagiapha = new datagiapha(this);
        
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tent = ten.getText().toString();
                String tuoit = tuoi.getText().toString();
                String thehet = thehe.getText().toString();
                
               
                if(datagiapha.themthanhvien(tent, Integer.parseInt(tuoit), Integer.parseInt(thehet))){
                    Toast.makeText(SodoiActivity.this, "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                    ten.setText("");
                    tuoi.setText("");
                    thehe.setText("");
                } else {
                    Toast.makeText(SodoiActivity.this, "Thêm thành viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SodoiActivity.this, HienthiActivity.class));
            }
        });
        
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
