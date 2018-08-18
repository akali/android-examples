package kz.tasbaque.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kz.tasbaque.lists.model.ItemProfile;

class MyAdapter extends BaseAdapter {
  private Context context;
  private List<ItemProfile> names;
  private LayoutInflater inflater;
  private MyOnClickListener onClick;

  public MyAdapter(Context myListActivity, List<ItemProfile> names, MyOnClickListener onClick) {
    this.context = myListActivity;
    this.names = names;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.onClick = onClick;
  }

  @Override
  public int getCount() {
    return names.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    View view = inflater.inflate(R.layout.list_view_item, null, false);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onClick.onElementClicked(names.get(position).getName());
      }
    });
    TextView nameTextView = view.findViewById(R.id.nameTextView);
    ImageView imageView = view.findViewById(R.id.imageView);
    nameTextView.setText(names.get(position).getName());

    Picasso.get().load(names.get(position).getUrl()).resize(120, 120).into(imageView);

    return view;
  }

  public interface MyOnClickListener {
    void onElementClicked(String s);
  }
}
