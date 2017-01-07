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
public class CharactersAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<KCharacter> list;

	public CharactersAdapter(Context context, ArrayList<KCharacter> list) {
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
			view = inflater.inflate(R.layout.item_character, viewGroup, false);
			view.setTag(viewHolder = new ViewHolder(view));
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.nameTextView.setText(list.get(i).getName());
		if (list.get(i).getGender() != "") viewHolder.genderTextView.setText("Gender: " + list.get(i).getGender());
		if (list.get(i).getBorn() != "") viewHolder.bornTextView.setText("Born: " + list.get(i).getBorn());
		if (list.get(i).getDied() != "") viewHolder.diedTextView.setText("Died: " + list.get(i).getDied());
		return view;
	}

	public ArrayList<KCharacter> getList() {
		return list;
	}

	public void setList(ArrayList<KCharacter> list) {

		this.list = list;
	}

	private class ViewHolder {
		public TextView nameTextView, genderTextView, bornTextView, diedTextView;

		public ViewHolder(View view) {
			nameTextView = (TextView) view.findViewById(R.id.nameTextView);
			genderTextView= (TextView) view.findViewById(R.id.genderTextView);
			bornTextView = (TextView) view.findViewById(R.id.bornTextView);
			diedTextView = (TextView) view.findViewById(R.id.diedTextView);
		}
	}
}
