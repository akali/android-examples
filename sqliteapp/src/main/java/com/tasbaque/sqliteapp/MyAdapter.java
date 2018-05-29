package com.tasbaque.sqliteapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private SQLiteDatabase db;

    public MyAdapter(Context context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }

    @Override
    public int getCount() {
        return DBUtils.getCount(db, "users");
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Log.d("TAG", "pos = " + position);
        Cursor c = db.query("users",
                null,
                "id = " + (position + 1),
                null,
                null,
                null,
                null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int usernameColIndex = c.getColumnIndex("username");
            int surnameColIndex = c.getColumnIndex("surname");

            int id = c.getInt(idColIndex);
            String username = c.getString(nameColIndex);
            String name = c.getString(usernameColIndex);
            String surname = c.getString(surnameColIndex);

            c.close();

            @SuppressLint({"ViewHolder", "InflateParams"})
            View view = LayoutInflater.from(context).inflate(R.layout.row_item, null);

            TextView tv = view.findViewById(R.id.idTextView);
            tv.setText(String.valueOf(id));
            tv = view.findViewById(R.id.usernameTextView);
            tv.setText(username);
            tv = view.findViewById(R.id.nameTextView);
            tv.setText(name);
            tv = view.findViewById(R.id.surnameTextView);
            tv.setText(surname);

            return view;
        } else return null;
    }
}
