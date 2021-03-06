package com.abi.globalmovie.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abi.globalmovie.ItemClickListener;
import com.abi.globalmovie.R;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.databinding.MoviesItemsBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final List<Movie> listMovies = new ArrayList<>();
    private ItemClickListener listener;
    private Context mContext;

    MoviesAdapter(Context context){
        this.mContext = context;
    }

    void setMovies(List<Movie> listMovies, ItemClickListener listener) {
        if (listMovies == null) return;
        this.listMovies.clear();
        this.listMovies.addAll(listMovies);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesItemsBinding binding = MoviesItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = listMovies.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MoviesItemsBinding binding;

        public ViewHolder(MoviesItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie movie){

            binding.imgPoster.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
            binding.itemContainer.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

            binding.tvItemTitle.setText(movie.getTitle());
            binding.tvItemYear.setText(movie.getReleaseDate());
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onItemClickCallback(movie);
            });
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/original"+movie.getPosterPath())
                    .centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }

}
