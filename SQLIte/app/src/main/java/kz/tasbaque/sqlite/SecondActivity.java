package kz.tasbaque.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    AuthDao dao = AuthDao.getInstance(new DBHelper(this));

    TextView tv = findViewById(R.id.textView);

    List<User> select = dao.select("0");

    StringBuilder result = new StringBuilder();

    for (User user : select) {
      result.append(user.id).append(" | ").append(user.name).append("\n");
    }

    tv.setText(result.toString());
  }
}
