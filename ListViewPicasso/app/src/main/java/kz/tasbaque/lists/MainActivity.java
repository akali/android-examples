package kz.tasbaque.lists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private TextView selectTextView;
  private TextView nameTextView;
  private static final int LIST_ACTIVITY_REQUEST_CODE = 101;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  
    selectTextView = findViewById(R.id.selectTextView);
    nameTextView = findViewById(R.id.nameTextView);
    
    selectTextView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onSelectTVClicked();
      }
    });
  }

  private void onSelectTVClicked() {
    Intent intent = new Intent(MainActivity.this, MyListActivity.class);

    startActivityForResult(intent, LIST_ACTIVITY_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == LIST_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        String name = data.getStringExtra("name");
        nameTextView.setText(String.format("Привет, %s!", name));
      }
    }
  }
}
