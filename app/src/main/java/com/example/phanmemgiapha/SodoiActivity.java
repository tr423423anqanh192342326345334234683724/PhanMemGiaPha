package com.example.phanmemgiapha;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SodoiActivity extends AppCompatActivity {
    EditText ten, tuoi, mqh, thehe;
    Button tt, xacnhan, quaylai;
    RadioGroup radioGroupNoiNgoai;
    datagiapha datagiapha;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sodoi);
        ten = findViewById(R.id.ten);
        tuoi = findViewById(R.id.tuoi);
        thehe = findViewById(R.id.thehe);
        mqh = findViewById(R.id.moiquanhe);
        radioGroupNoiNgoai = findViewById(R.id.radioGroupNoiNgoai);
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
                String mqht = mqh.getText().toString();
                int noiNgoai = radioGroupNoiNgoai.getCheckedRadioButtonId();
                int tuoitt ;
                int thehett ;
                if (tent.isEmpty() || tuoit.isEmpty() || thehet.isEmpty() || mqht.isEmpty() || noiNgoai == -1) {
                    Toast.makeText(SodoiActivity.this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                        tuoitt = Integer.parseInt(tuoit);
                        thehett = Integer.parseInt(thehet);

                    }catch(NumberFormatException e){

                        return;
                    }

               
                if(datagiapha.themthanhvien(tent, tuoitt, thehett)){
                    Toast.makeText(SodoiActivity.this, "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                    ten.setText("");
                    tuoi.setText("");
                    thehe.setText("");
                    mqh.setText("");
                } else {
                    Toast.makeText(SodoiActivity.this, "Thêm thành viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SodoiActivity.this, HienthiActivity.class);
                startActivity(intent);
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
