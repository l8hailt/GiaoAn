package vn.poly.hailt.giaoan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.poly.hailt.giaoan.common.Constant;

public class DbHelper extends SQLiteOpenHelper implements Constant {

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //SDK cũ hơn: sqLiteDatabase <=> db
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Tạo bảng Subject
        sqLiteDatabase.execSQL(CREATE_SUBJECT_TABLE);
    }

    //Chạy khi nâng version
    //SDK cũ hơn: i <=> oldVersion, i1 <=> newVersion)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Xóa bảng Subject
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE);
    }
}
