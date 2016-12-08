package com.example.aqali.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<String> list;

	public MyAdapter(Context context, ArrayList<String> list) {
		this.context = context;
		this.list = list;
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
			viewHolder = new ViewHolder(view);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.textView.setText(list.get(i));
		return view;
	}

	private class ViewHolder {
		private TextView textView;

		public ViewHolder(View view) {
			textView = (TextView) view.findViewById(R.id.textView);
		}
	}
}