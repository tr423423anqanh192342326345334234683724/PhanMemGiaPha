package com.example.phanmemgiapha;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DangkyActivity extends AppCompatActivity {
        EditText tkdk,mk,xtmk;
        Button dk,back;
        datagiapha datagiapha;
        SharedPreferences chinhxuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dangky);
       tkdk = findViewById(R.id.tkdk);
        mk = findViewById(R.id.mk);
        xtmk = findViewById(R.id.xtmk);
        dk = findViewById(R.id.dk);
        back = findViewById(R.id.back);

        datagiapha = new datagiapha(this);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = tkdk.getText().toString();
                String mkt = mk.getText().toString();
                String xtmkt = xtmk.getText().toString();
                if(tk.isEmpty()||mkt.isEmpty()||xtmkt.isEmpty()){
                    Toast.makeText(DangkyActivity.this,"Vui Lòng Nhập Đầy Đủ Thông Tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(datagiapha.kiemtrataikhoan(tk)){
                    Toast.makeText(DangkyActivity.this,"Tài Khoản Đã Tồn Tại",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!mkt.equals(xtmkt)){
                    Toast.makeText(DangkyActivity.this,"Mật Khẩu Không Trùng Khớp Hãy Kiểm Tra Lại",Toast.LENGTH_SHORT).show();
                    return;
                }
                long ketqua = datagiapha.dangky(tk,mkt);
                    if(ketqua>0){
                        Toast.makeText(DangkyActivity.this,"Đăng Kí Thành Công",Toast.LENGTH_SHORT).show();
                        finish();
                    }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DangkyActivity.this).setTitle("Thoát").setMessage("Quạy Lại Màn Hình Đăng Nhâp Không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(DangkyActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }).setPositiveButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(DangkyActivity.this,"Bạn Đã Hủy",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
}