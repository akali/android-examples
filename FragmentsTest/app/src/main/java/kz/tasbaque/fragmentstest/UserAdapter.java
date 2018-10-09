package kz.tasbaque.fragmentstest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kz.tasbaque.fragmentstest.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
  private final static String TAG = UserAdapter.class.getSimpleName();

  private final List<User> users;

  public UserAdapter(List<User> users) {
    this.users = users;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.d(TAG, "Creating view holder");
    View view =
      LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, null, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Log.d(TAG, "Binding view holder:" + users);
    Glide
      .with(holder.getProfileImageView().getContext())
      .load(users.get(position).getAvatar_url())
      .into(holder.getProfileImageView());

    holder.getUsernameTextView().setText(users.get(position).getLogin());
  }

  @Override
  public int getItemCount() {
    Log.d(TAG, "users size: " + users.size());
    return users.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView profileImageView;
    private TextView usernameTextView;
    public ViewHolder(View itemView) {
      super(itemView);
      profileImageView = itemView.findViewById(R.id.profileImageView);
      usernameTextView = itemView.findViewById(R.id.usernameTextView);
    }

    public ImageView getProfileImageView() {
      return profileImageView;
    }

    public TextView getUsernameTextView() {
      return usernameTextView;
    }
  }
}
