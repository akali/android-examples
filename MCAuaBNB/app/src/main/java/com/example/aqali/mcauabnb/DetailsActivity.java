package com.example.aqali.mcauabnb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

  private ImageView imageView;
  private TextView nameTextView, descTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);

    imageView = findViewById(R.id.imageView);
    nameTextView = findViewById(R.id.cityTextView);
    descTextView = findViewById(R.id.descriptionTextView);

    nameTextView.setText(getIntent().getStringExtra("name"));
    descTextView.setText(getIntent().getStringExtra("desc"));
    Glide.with(this).load(descTextView).centerCrop().into(imageView);
  }
}
