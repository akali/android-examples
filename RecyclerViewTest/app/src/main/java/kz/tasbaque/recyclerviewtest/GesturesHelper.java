package kz.tasbaque.recyclerviewtest;

import android.support.v7.widget.RecyclerView;

interface GesturesHelper {
  boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target);
  void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
}
