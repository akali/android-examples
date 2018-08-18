package kz.tasbaque.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import kz.tasbaque.lists.model.ItemProfile;

public class MyListActivity extends AppCompatActivity {

  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_list);

    listView = findViewById(R.id.listView);

    List<ItemProfile> names =
      Arrays.asList(
        new ItemProfile("Abylay", "https://flb.ru/images/album/normal/normal_0.jpg"),
        new ItemProfile("Ayan", "https://pbs.twimg.com/profile_images/897508478763257856/am-7k0Ob_400x400.jpg"),
        new ItemProfile("Yelaman", "https://www.dj-store.ru/data/product/img/32568_shuyskaya-garmon-bayan-shuya-47kh80-ii-siniy-photo.jpg"),
        new ItemProfile("Zhandos", "https://previews.123rf.com/images/korionov/korionov1601/korionov160100193/50933501-portrait-of-stupid-meme-on-black-background.jpg"),
        new ItemProfile("Arystan", "https://image.shutterstock.com/z/stock-photo-lion-sick-lion-laying-under-a-tree-85919686.jpg"),
        new ItemProfile("Shyngis", "https://memepedia.ru/wp-content/uploads/2017/12/%D1%88%D1%8B%D0%BD%D0%B3%D1%8B%D1%81-%D0%BE%D1%85%D1%83%D0%B5%D0%B2%D1%88%D0%B8%D0%B9-5.png"),
        new ItemProfile("1chik", "https://scontent-otp1-1.cdninstagram.com/vp/727148b82484992f3e6b82053ce7362d/5BB3F0AD/t51.2885-19/s320x320/33060577_1268167703315087_5517174641795268608_n.jpg"));

    MyAdapter.MyOnClickListener myOnClickListener = new MyAdapter.MyOnClickListener() {
      @Override
      public void onElementClicked(String s) {
        Intent intent = new Intent();
        intent.putExtra("name", s);
        setResult(RESULT_OK, intent);
        finish();
      }
    };
    MyAdapter adapter = new MyAdapter(this, names, myOnClickListener);
    listView.setAdapter(adapter);
  }
}
