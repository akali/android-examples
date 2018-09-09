package kz.tasbaque.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AuthDao {
  private DBHelper helper;

  public AuthDao(DBHelper helper) {
    this.helper = helper;
  }

  public long insert(User user) {
    ContentValues cv = new ContentValues();
    cv.put("name", user.name);
    cv.put("surname", user.surname);
    return helper.getWritableDatabase().insert("test_table", null, cv);
  }

  public List<User> select() {
    List<User> result = new ArrayList<>();

    Cursor c =
      helper
        .getReadableDatabase()
        .query(
          "test_table",
          null,
          null,
          null,
          null,
          null,
          null
        );
    if (c.moveToFirst()) {
      while (c.moveToNext()) {
        result.add(new User(
          c.getInt(c.getColumnIndex("id")),
          c.getString(c.getColumnIndex("name")),
          c.getString(c.getColumnIndex("surname"))));
      }
    }
    return result;
  }
}
