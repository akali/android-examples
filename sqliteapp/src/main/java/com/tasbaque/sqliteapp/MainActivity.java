package com.tasbaque.sqliteapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ListView listView;
    private TextView usernameTextView, nameTextView, surnameTextView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        listView = findViewById(R.id.listView);
        usernameTextView = findViewById(R.id.usernameEditText);
        nameTextView = findViewById(R.id.nameEditText);
        surnameTextView = findViewById(R.id.surnameEditText);

        DBHelper helper = new DBHelper(this, "users.db", null, 1);

        final SQLiteDatabase db = helper.getWritableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(db, String.valueOf(usernameTextView.getText()),
                        String.valueOf(nameTextView.getText()),
                        String.valueOf(surnameTextView.getText()));
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new MyAdapter(this, db);

        listView.setAdapter(adapter);
    }

    private long insert(SQLiteDatabase db, String username, String name, String surname) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("name", name);
        cv.put("surname", surname);
        return db.insert("users", null, cv);
    }
}

