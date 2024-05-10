package com.example.mycompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    String n;
    String p;


    public DBHelper(Context context) {
        super(context,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Boolean insertData(String username,String password,String name,String number)
    {
        n = username;
        p = password;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("name",name);
        contentValues.put("number",number);
        long result = MyDB.insert("users",null,contentValues);
        return result != -1;
    }

    public Boolean checkusername(String username)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?",new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        return cursor.getCount() > 0;
    }
    public Cursor readalldata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry="select * from users";
        return db.rawQuery(qry,null);

    }
}
