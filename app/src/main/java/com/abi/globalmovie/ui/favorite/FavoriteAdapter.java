package com.abi.globalmovie.ui.favorite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abi.globalmovie.ItemClickListener;
import com.abi.globalmovie.R;
import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.databinding.FavoritesItemsBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private final List<MovieDB> listFavorites = new ArrayList<>();
    private ItemClickListener listener;
    private FavoriteViewModel mFavoriteViewModel;
    private Context mContext;
    FavoriteAdapter(Context context, FavoriteViewModel favoriteViewModel){
        this.mContext = context;
        this.mFavoriteViewModel = favoriteViewModel;
    }

    void setFavorites(List<MovieDB> listFavorites, ItemClickListener listener) {
        if (listFavorites == null) return;
        this.listFavorites.clear();
        this.listFavorites.addAll(listFavorites);
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoritesItemsBinding binding = FavoritesItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        MovieDB movieDB = listFavorites.get(position);
        holder.bind(movieDB);
    }

    @Override
    public int getItemCount() {
        return listFavorites.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FavoritesItemsBinding binding;
        public ViewHolder(FavoritesItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MovieDB movieDB){
            Log.d("Local Data", movieDB.toString());
            binding.imgPoster.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
            binding.itemContainer.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
            binding.tvItemTitle.setText(movieDB.getTitle());
            binding.tvItemYear.setText(movieDB.getReleaseDate());
            binding.ivDelete.setOnClickListener(v -> {
                mFavoriteViewModel.deleteFavorite(movieDB);
                Toast.makeText(mContext, "Delete Success!", Toast.LENGTH_LONG).show();
            });

            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/original"+movieDB.getPosterPath())
                    .centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }
}
