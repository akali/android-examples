package kz.tasbaque.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private EditText editText;
  private NamesAdapter adapter;
  private Button button;

  private List<String> data;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    init();

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.filter(editText.getText().toString());
      }
    });

    ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
    helper.attachToRecyclerView(recyclerView);
  }

  private void init() {
    recyclerView = findViewById(R.id.recyclerView);
    editText = findViewById(R.id.editText);
    button = findViewById(R.id.searchButton);

    data = new ArrayList<String>() {
      {
        add("Abylay");
        add("Aruzan");
        add("Aidos");
        add("Shyngys");
        add("Zhandos");
      }
    };

    adapter = new NamesAdapter(data);
  }
}
