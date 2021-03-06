package com.abi.globalmovie.ui.favorite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abi.globalmovie.ItemClickListener;
import com.abi.globalmovie.data.ViewModelFactory;
import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.databinding.FavoritesItemsBinding;
import com.abi.globalmovie.databinding.FragmentFavoritesBinding;
import com.abi.globalmovie.databinding.FragmentMoviesBinding;
import com.abi.globalmovie.ui.movies.MoviesAdapter;
import com.abi.globalmovie.ui.movies.MoviesFragment;
import com.abi.globalmovie.ui.movies.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FavoriteFragment extends Fragment implements ItemClickListener {
    private FragmentFavoritesBinding fragmentFavoritesBinding;

    public FavoriteFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return  fragmentFavoritesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewModelFactory factory =  ViewModelFactory.getInstance(getActivity(), getActivity().getApplication());
        FavoriteViewModel viewModel =  new ViewModelProvider(this, factory).get(FavoriteViewModel.class);
        FavoriteAdapter adapter = new FavoriteAdapter(getContext(), viewModel);
        if(getActivity() != null){
            Log.d("Adapter Count", String.valueOf(adapter.getItemCount()));

            viewModel.getFavorites().observe(this, movies -> {
                adapter.setFavorites(movies, FavoriteFragment.this);
                adapter.notifyDataSetChanged();
                Log.d("Movies DB", movies.toString());

            });
            fragmentFavoritesBinding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentFavoritesBinding.rvMovie.setHasFixedSize(true);
            fragmentFavoritesBinding.rvMovie.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClickCallback(Movie movie) {

    }

    @Override
    public void onItemFavoriteClickCallback(MovieDB movieDB) {

    }
}
