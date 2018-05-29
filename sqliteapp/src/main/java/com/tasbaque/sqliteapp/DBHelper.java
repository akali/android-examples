package com.tasbaque.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(" +
                "id integer primary key autoincrement," +
                "username varchar(32) not null unique," +
                "name varchar(32) not null," +
                "surname varchar(32) not null);");

//        db.execSQL("CREATE TABLE rounds(" +
//                "id integer primary key autoincrement," +
//                "score integer not null unique);");
//
//        db.execSQL("CREATE TABLE user_rounds(" +
//                "id integer primary key autoincrement," +
//                "user_id references users," +
//                "rounds_id references rounds);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
