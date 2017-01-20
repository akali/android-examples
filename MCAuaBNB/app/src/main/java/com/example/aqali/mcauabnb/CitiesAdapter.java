package com.example.aqali.mcauabnb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by aqali on 1/21/17.
 */
public class CitiesAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<City> list;

	public CitiesAdapter(Context context, ArrayList<City> list) {
		this.context = context;
		this.list = list;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			viewHolder = new ViewHolder(view);
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

		public ViewHolder(View view) {
			textView = (TextView) view.findViewById(R.id.textView);
			imageView= (ImageView) view.findViewById(R.id.imageView);
		}
	}
}
