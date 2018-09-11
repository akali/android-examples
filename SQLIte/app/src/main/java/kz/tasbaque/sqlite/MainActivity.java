package kz.tasbaque.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final AuthDao dao = AuthDao.getInstance(new DBHelper(this));

    for (User user : dao.select("5")) {
      Log.d("TAG", user.toString());
    }

    Button b = findViewById(R.id.button);
    final EditText e = findViewById(R.id.editText);

    b.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = String.valueOf(e.getText());

        dao.insert(new User(name));
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
      }
    });

    findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dao.truncate();
      }
    });
  }
}
