package com.tasbaque.sqliteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class DBUtils {
    public static int getCount(SQLiteDatabase db, String table) {
        Cursor c = db.rawQuery("select * from " + table, null);
        int result = c.getCount();
        c.close();
        return result;
    }
}
