package tdc.edu.vn.quanlynhansu_ver0.database_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import tdc.edu.vn.quanlynhansu_ver0.NhanSu;

public class MyDatabase extends SQLiteOpenHelper {
    private static String DB_NAME = "database";
    private static int DB_VERSION = 1;
    //khai báo bảng dữ liệu gồm tên bảng / cột ID / cột degree / cột hoppies.
    private static String TABLE_NAME = "nembers";
    private static String ID = "_id";
    private static String NAME = "name";
    private static String DEGREE = "degree";
    private static String HOPPIES = "hoppies";

    //xóa String name,SQLiteDatabase.CursorFactory factory, int version trong MyDatabase đi
    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //tạo ra bảng gồm tên bảng / cột ID / cột degree / cột hoppies.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DEGREE + " TEXT, " +
                HOPPIES + " TEXT)";
        db.execSQL(sql);
    }

    //khai bao 1 cái SQLiteDatabase database = getWritableDatabase(); và database.close(); làm gì ở giữa tụi nó thì làm
    public void saveMembers(ArrayList<NhanSu> members) {
        SQLiteDatabase database = getWritableDatabase();
        //for để duyệt mãng từ đầu tới cuối//lấy giá trị ra bằng ContentValues
        for (NhanSu nhanSu : members) {
            ContentValues values = new ContentValues();
            values.put(NAME, nhanSu.getName());
            values.put(DEGREE, nhanSu.getDegree());
            values.put(HOPPIES, nhanSu.getHoppies());
            //insert it to the database
            database.insert(TABLE_NAME, null, values);
        }
        database.close();
    }

    //lấy các phần tử trong SQL ra.
    public void getMembers(ArrayList<NhanSu> members) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{NAME, DEGREE, HOPPIES}, null, null, null, null, NAME);
        if (cursor.moveToFirst()) {
            do {
                NhanSu nhanSu = new NhanSu();
                nhanSu.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                nhanSu.setDegree(cursor.getString(cursor.getColumnIndex(DEGREE)));
                nhanSu.setHoppies(cursor.getString(cursor.getColumnIndex(HOPPIES)));
            } while (cursor.moveToNext());
        }
        db.close();
    }
    //xóa nhân sự trong data
    public void removeMember(NhanSu nhanSu)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, NAME+"= ? AND "+ DEGREE + "= ? AND "+ HOPPIES+"=?",
                new String[]{nhanSu.getName(), nhanSu.getDegree(), nhanSu.getHoppies()});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
