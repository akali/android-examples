package kz.tasbaque.recyclerviewtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> implements GesturesHelper {

  private List<String> data;
  private List<String> filtered;
  private String filter;

  public NamesAdapter(List<String> data) {
    this.data = data;
    this.filtered = this.data;
    this.filter = "";
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    View view = inflater.inflate(R.layout.row_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.setText(filtered.get(position));
  }

  @Override
  public int getItemCount() {
    return filtered.size();
  }

  public void filter(String filter) {
    this.filter = filter;
    this.filtered = new ArrayList<>();
    for (String element : this.data) {
      if (element.toLowerCase().contains(filter.toLowerCase()))
        this.filtered.add(element);
    }
    notifyDataSetChanged();
  }

  @Override
  public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

    int fromPosition = viewHolder.getAdapterPosition(),
      toPosition = target.getAdapterPosition();

    if (fromPosition < toPosition) {
      for (int i = fromPosition; i < toPosition; i++) {
        Collections.swap(data, i, i + 1);
      }
    } else {
      for (int i = fromPosition; i > toPosition; i--) {
        Collections.swap(data, i, i - 1);
      }
    }

    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    data.remove(viewHolder.getAdapterPosition());
    notifyItemRemoved(viewHolder.getAdapterPosition());
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.textView);
    }

    public void setText(String s) {
      textView.setText(s);
    }
  }
}
