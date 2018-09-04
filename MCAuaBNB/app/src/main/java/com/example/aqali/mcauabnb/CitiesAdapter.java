package com.example.aqali.mcauabnb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aqali on 1/21/17.
 */
public class CitiesAdapter extends BaseAdapter {
  private Context context;
  private LayoutInflater inflater;
  private List<City> list;

  interface OnClickListener {
    void onClick(City city);
  }

  private OnClickListener onClickListener;

  public CitiesAdapter(Context context, List<City> list, OnClickListener onClickListener) {
    this.context = context;
    this.list = list;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.onClickListener = onClickListener;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int i) {
    return null;
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    ViewHolder viewHolder;

    if (view == null) {
      view = inflater.inflate(R.layout.item_city, viewGroup, false);
      viewHolder = new ViewHolder(i, view);
      view.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) view.getTag();
    }

    viewHolder.textView.setText(list.get(i).getTitle());

    Glide.with(context).load(list.get(i).getImage()).centerCrop().into(viewHolder.imageView);
    return view;
  }

  private class ViewHolder {
    private TextView textView;
    private ImageView imageView;
    private int position;

    public ViewHolder(final int position, View view) {
      this.position = position;
      textView = view.findViewById(R.id.textView);
      imageView = view.findViewById(R.id.imageView);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onClickListener.onClick(list.get(position));
        }
      });
    }
  }
}
