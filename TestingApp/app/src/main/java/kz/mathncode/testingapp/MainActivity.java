package kz.mathncode.testingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private EditText editText;
  private Button button;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editText = findViewById(R.id.edit_text);
    button = findViewById(R.id.button);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String text = editText.getText().toString();

        Toast.makeText(
          MainActivity.this,
          String.format("Hello, %s!", text),
          Toast.LENGTH_SHORT
        ).show();
      }
    });
  }
}
