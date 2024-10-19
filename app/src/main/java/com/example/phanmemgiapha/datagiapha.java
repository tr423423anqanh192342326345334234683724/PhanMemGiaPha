package com.example.phanmemgiapha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class datagiapha extends SQLiteOpenHelper {
    private static final String data_name = "giapha.db";
    private static final int version = 1;
    private static final String table_giapha = "giapha";
    private static final String cot_id = "id";
    private static final String cot_ten = "ten";
    private static final String cot_tuoi = "tuoi";
    private static final String cot_moi_quan_he = "moi_quan_he";
    private static final String cot_the_he = "the_he";
    private static final String table_tk = "dangnhap";

    private static final String cot_1 = "taikhoan";
    private static final String cot_2 = "matkhau";

    private static final String hoan_thanh_bang_taikhoan =
            "CREATE TABLE " + table_tk + " (" +
                    cot_1 + " TEXT PRIMARY KEY, " + // Thêm dấu cách
                    cot_2 + " TEXT NOT NULL);";

    private static final String hoan_thanh_bang_giapha =
            "CREATE TABLE " + table_giapha + " (" +
                    cot_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    cot_ten + " TEXT NOT NULL, " +
                    cot_tuoi + " INTEGER, " +
                    cot_moi_quan_he + " TEXT, " +
                    cot_the_he + " INTEGER);";

    public datagiapha(Context context) {
        super(context, data_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(hoan_thanh_bang_taikhoan);
        sqLiteDatabase.execSQL(hoan_thanh_bang_giapha);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_tk);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_giapha); // Thêm bảng giapha
        onCreate(sqLiteDatabase);
    }

    public boolean kiemtradangnhap(String tk, String mk) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String kt = "SELECT * FROM " + table_tk + " WHERE " +
                    cot_1 + " = ? AND " + cot_2 + " = ?";
            cursor = sqLiteDatabase.rawQuery(kt, new String[]{tk, mk});
            return cursor != null && cursor.getCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            sqLiteDatabase.close();
        }
        return false;
    }

    public boolean kiemtrataikhoan(String tk) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String kt = "SELECT * FROM " + table_tk + " WHERE " +
                    cot_1 + " = ?";
            cursor = sqLiteDatabase.rawQuery(kt, new String[]{tk});
            return cursor != null && cursor.getCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            sqLiteDatabase.close();
        }
        return false;
    }

    public long dangky(String tk, String mk) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_1, tk);
        contentValues.put(cot_2, mk);
        return sqLiteDatabase.insert(table_tk, null, contentValues);
    }

    public boolean themthanhvien(String ten, int tuoi, String mqh, int thehe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", ten);
        contentValues.put("tuoi", tuoi);
        contentValues.put("mqh", mqh);
        contentValues.put("thehe", thehe);

        long result = db.insert("table_giapha", null, contentValues);
        return result != -1;
    }
}
