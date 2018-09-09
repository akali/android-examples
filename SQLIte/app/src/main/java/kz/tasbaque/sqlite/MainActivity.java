package kz.tasbaque.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AuthDao dao = new AuthDao(new DBHelper(this));

    dao.insert(new User("Aisultan", "Kali"));
    dao.insert(new User("Hello", "World"));
    dao.insert(new User("Abacaba", "FAWRSVD"));
    dao.insert(new User("QWERT", "Hello"));
    dao.insert(new User("GAGHERS", "Dickens"));

    for (User user : dao.select()) {
      Log.d("TAG", user.toString());
    }
  }
}
