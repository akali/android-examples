package com.example.aqali.asongoficeandfire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aqali on 1/8/17.
 */
public class BooksAdapter extends BaseAdapter {
	private ArrayList<Book> list;
	private Context context;
	private LayoutInflater inflater;

	public BooksAdapter(Context context, ArrayList<Book> list) {
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
			view = inflater.inflate(R.layout.item_book, viewGroup, false);
			viewHolder = new ViewHolder(view);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.nameTextView.setText(list.get(i).getName());
		viewHolder.pagesTextView.setText(String.valueOf(list.get(i).getNumberOfPages()));
		viewHolder.publisherTextView.setText(list.get(i).getPublisher());
		return view;
	}

	private class ViewHolder {
		public TextView nameTextView, pagesTextView, publisherTextView;

		public ViewHolder(View view) {
			nameTextView = (TextView) view.findViewById(R.id.nameTextView);
			pagesTextView = (TextView) view.findViewById(R.id.pagesTextView);
			publisherTextView = (TextView) view.findViewById(R.id.publisherTextView);
		}
	}
}
