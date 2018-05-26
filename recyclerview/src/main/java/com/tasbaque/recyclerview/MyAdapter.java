package com.tasbaque.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Film> films;

    public MyAdapter(ArrayList<Film> films) {
        this.films = films;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setFilm(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size(); // Number of elements
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView, subtitleTextView;
        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
            button = itemView.findViewById(R.id.button);
        }

        public void setFilm(final Film film) {
            Picasso.get().load(film.getUrl()).into(imageView);
            titleTextView.setText(Utils.resize(film.getTitle()));
            subtitleTextView.setText(Utils.resize(film.getSubtitle()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int row = getAdapterPosition();
                    films.remove(row);
                    notifyItemRemoved(row);

                    // Toast.makeText(itemView.getContext(), row + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
