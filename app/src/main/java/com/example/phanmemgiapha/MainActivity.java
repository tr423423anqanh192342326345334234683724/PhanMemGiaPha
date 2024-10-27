package com.example.phanmemgiapha;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.phanmemgiapha.datagiapha;

public class MainActivity extends AppCompatActivity {
    EditText taikhoan, matkhau;
    Button dangnhap, dangki;
    datagiapha datagiapha;
    SharedPreferences chichsuat;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        taikhoan = findViewById(R.id.taikhoan);
        matkhau = findViewById(R.id.matkhau);
        dangnhap = findViewById(R.id.dangnhap);
        dangki = findViewById(R.id.dangki);

        datagiapha = new datagiapha(this);
        chichsuat = getSharedPreferences("Login", MODE_PRIVATE);
        if(datagiapha.kiemtradangnhap(chichsuat.getString("tk", ""), chichsuat.getString("mk", ""))){
            Intent intent = new Intent(MainActivity.this, HienthiActivity.class);
            startActivity(intent);
            finish();
        }

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = taikhoan.getText().toString().trim();
                String mk = matkhau.getText().toString().trim();
                if (tk.isEmpty() || mk.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui Lòng Nhập Thông Tin Cho Tài Khoản Và Mật Khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (datagiapha.kiemtradangnhap(tk, mk)) {
                    luutaikhoan(tk);
                    Intent intent = new Intent(MainActivity.this, SodoiActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "Tài Khoản Mật Khẩu Không Đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Xác Nhận")
                        .setMessage("Bạn Có Chắc Chắn Muốn Đăng Kí Không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MainActivity.this, DangkyActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Bạn Đã Hủy Đăng Kí", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

            }
        });
    }

    

    

    public void luutaikhoan(String tk) {
        SharedPreferences.Editor editor = chichsuat.edit();
        editor.putString("tk", tk);
        editor.apply();
    }
}