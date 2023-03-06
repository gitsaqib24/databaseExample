package com.example.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class mydb_Halper extends SQLiteOpenHelper {

    public static final String db_name      ="contacts";
    public static final String table_name   = "students";
    public static final String col_1        = "id";
    public static final String col_2        = "name";
    public static final String col_3        = "phone";

    //Constructor
    public mydb_Halper(Context context) {
        super(context, db_name, null, 1);
    }

    //On Create
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT)");
    }

    //On Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name );
    }




    //-------------------------------------------------------------------------------------

    // Insert
    public boolean insert_data(String name,String phone)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues   = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,phone);
        long result = sqLiteDatabase.insert(table_name,null,contentValues);
        sqLiteDatabase.close();

        // Check if data was inserted Successfully
        return result != -1;
    }
    //-------------------------------------------------------------------------------------

    // Read
    public Cursor getAllInfo()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from " + table_name , null);
    }

    // Update
    public boolean update_data(String id,String name, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues   = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,phone);
        int result = db.update(table_name,contentValues,"ID =?", new String[]{id});
        return result  >  0 ;
    }
    //-------------------------------------------------------------------------------------
    // Delete
    public int delete_data(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table_name,"ID=?", new String[]{id});
    }
}

