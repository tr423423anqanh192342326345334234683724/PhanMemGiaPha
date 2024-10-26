package com.example.phanmemgiapha;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.phanmemgiapha.LineView;
import com.otaliastudios.zoom.ZoomLayout;

public class HienthiActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout giaPhaContainer;
    private List<ThanhVien> thanhVienList;
    private datagiapha datagiapha;
    private ZoomLayout zoomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hienthi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                
                if (id == R.id.nav_item1) {
                    themNguoi();
                } else if (id == R.id.nav_item2) {
                    dangXuat();
                } else if (id == R.id.nav_item3) {
                    xoaNguoi();
                }
                
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        giaPhaContainer = findViewById(R.id.giaPhaContainer);
        zoomLayout = findViewById(R.id.zoom_layout);
        
        datagiapha = new datagiapha(this);
        thanhVienList = new ArrayList<>();
        
        loadThanhVien();
        hienThiGiaPha();

        // Tùy chỉnh ZoomLayout (nếu cần)
        zoomLayout.setMaxZoom(4f); // Đặt mức zoom tối đa
        zoomLayout.setMinZoom(0.5f); // Đặt mức zoom tối thiểu
    }

    private void loadThanhVien() {
        Cursor cursor = datagiapha.layTatCaThanhVien();
        thanhVienList.clear();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String ten = cursor.getString(cursor.getColumnIndex("ten"));
                @SuppressLint("Range") int tuoi = cursor.getInt(cursor.getColumnIndex("tuoi"));
                @SuppressLint("Range") int theHe = cursor.getInt(cursor.getColumnIndex("the_he"));
                
                thanhVienList.add(new ThanhVien(id, ten, tuoi, theHe));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Sắp xếp danh sách theo thế hệ (từ nhỏ đến lớn)
        Collections.sort(thanhVienList, new Comparator<ThanhVien>() {
            @Override
            public int compare(ThanhVien tv1, ThanhVien tv2) {
                return Integer.compare(tv1.getTheHe(), tv2.getTheHe());
            }
        });
    }

    private void hienThiGiaPha() {
        giaPhaContainer.removeAllViews();

        Map<Integer, List<ThanhVien>> thanhVienTheoTheHe = new TreeMap<>();

        for (ThanhVien tv : thanhVienList) {
            thanhVienTheoTheHe.computeIfAbsent(tv.getTheHe(), k -> new ArrayList<>()).add(tv);
        }

        List<LinearLayout> hangTheHeList = new ArrayList<>();

        for (Map.Entry<Integer, List<ThanhVien>> entry : thanhVienTheoTheHe.entrySet()) {
            List<ThanhVien> thanhVienTrongTheHe = entry.getValue();

            LinearLayout hangTheHe = new LinearLayout(this);
            hangTheHe.setOrientation(LinearLayout.HORIZONTAL);
            hangTheHe.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            hangTheHe.setPadding(0, 50, 0, 50);

            for (int i = 0; i < thanhVienTrongTheHe.size(); i++) {
                ThanhVien tv = thanhVienTrongTheHe.get(i);

                TextView tvThanhVien = new TextView(this);
                tvThanhVien.setText(tv.getTen());
                tvThanhVien.setBackgroundResource(R.drawable.thanh_vien_background);
                tvThanhVien.setPadding(20, 20, 20, 20);
                tvThanhVien.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(20, 0, 20, 0);
                tvThanhVien.setLayoutParams(params);

                hangTheHe.addView(tvThanhVien);

                if (i < thanhVienTrongTheHe.size() - 1) {
                    View lineHorizontal = new View(this);
                    lineHorizontal.setBackgroundColor(Color.BLACK);
                    LinearLayout.LayoutParams lineParamsH = new LinearLayout.LayoutParams(50, 2);
                    lineParamsH.gravity = Gravity.CENTER_VERTICAL;
                    lineHorizontal.setLayoutParams(lineParamsH);
                    hangTheHe.addView(lineHorizontal);
                }
            }

            giaPhaContainer.addView(hangTheHe);
            hangTheHeList.add(hangTheHe);
        }

        // Thêm đường nối chéo
        for (int i = 0; i < hangTheHeList.size() - 1; i++) {
            LinearLayout currentRow = hangTheHeList.get(i);
            LinearLayout nextRow = hangTheHeList.get(i + 1);

            FrameLayout connectionContainer = new FrameLayout(this);
            connectionContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 100));

            for (int j = 0; j < currentRow.getChildCount(); j += 2) {
                View currentView = currentRow.getChildAt(j);
                float startX = currentView.getX() + currentView.getWidth() / 2;

                for (int k = 0; k < nextRow.getChildCount(); k += 2) {
                    View nextView = nextRow.getChildAt(k);
                    float endX = nextView.getX() + nextView.getWidth() / 2;

                    LineView lineView = new LineView(this);
                    lineView.setLayoutParams(new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    lineView.setLine(startX, 0, endX, 100);
                    connectionContainer.addView(lineView);
                }
            }

            giaPhaContainer.addView(connectionContainer);
        }
    }


    // Các phương thức khác giữ nguyên

    private void themNguoi() {
        Intent intent = new Intent(this, SodoiActivity.class);
        startActivity(intent);
    }

    private void dangXuat() {
        // Thực hiện các bước đăng xut ở đây
        // Ví dụ: xóa thông tin đăng nhập, chuyển về màn hình đăng nhập
        Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void xoaNguoi() {
        // Xóa hết dữ liệu của bảng giapgha
        datagiapha.xoaTatCaThanhVien();
        thanhVienList.clear();
        hienThiGiaPha(); // Cập nhật lại giao diện
        Toast.makeText(this, "Đã xóa hết dữ liệu", Toast.LENGTH_SHORT).show();
    }
}
