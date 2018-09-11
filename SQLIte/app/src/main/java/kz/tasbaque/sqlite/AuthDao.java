package kz.tasbaque.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AuthDao {
  private DBHelper helper;
  private static AuthDao instance;

  private AuthDao(DBHelper helper) {
    this.helper = helper;
  }

  public long insert(User user) {
    ContentValues cv = new ContentValues();
    cv.put("name", user.name);

    return helper.getWritableDatabase().insert("test_table", null, cv);
  }

  public void update(User user) {

  }

  public List<User> select(String minId) {
    List<User> result = new ArrayList<>();

    Cursor c =
      helper
        .getReadableDatabase()
        .query(
          "test_table",
          null,
          "id > ?",
          new String[]{minId},
          null,
          null,
          null,
          "500"
        );
    if (c.moveToFirst()) {
      do {
        result.add(new User(
          c.getInt(c.getColumnIndex("id")),
          c.getString(c.getColumnIndex("name"))));
      } while (c.moveToNext());
    }
    c.close();
    return result;
  }

  public static AuthDao getInstance(DBHelper dbHelper) {
    if (instance == null)
      instance = new AuthDao(dbHelper);
    return instance;
  }

  public void truncate() {
    helper.getWritableDatabase().execSQL("delete from test_table;");
  }
}
