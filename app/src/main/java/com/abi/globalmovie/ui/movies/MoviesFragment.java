package com.abi.globalmovie.ui.movies;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abi.globalmovie.ItemClickListener;
import com.abi.globalmovie.data.ViewModelFactory;
import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.databinding.FragmentMoviesBinding;
import com.abi.globalmovie.ui.detail.DetailActivity;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MoviesFragment extends Fragment implements ItemClickListener {
    private FragmentMoviesBinding fragmentMoviesBinding;
    private Application application;
    public MoviesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false);
        return  fragmentMoviesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            ViewModelFactory factory =  ViewModelFactory.getInstance(getActivity(), getActivity().getApplication());
            MoviesViewModel viewModel =  new ViewModelProvider(this, factory).get(MoviesViewModel.class);
            MoviesAdapter adapter = new MoviesAdapter(getContext());
            fragmentMoviesBinding.progressBar.setVisibility(VISIBLE);

            viewModel.getMovies().observe(this, movies -> {
                fragmentMoviesBinding.progressBar.setVisibility(GONE);
                adapter.setMovies(movies, MoviesFragment.this);
                adapter.notifyDataSetChanged();
            });

            fragmentMoviesBinding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentMoviesBinding.rvMovie.setHasFixedSize(true);
            fragmentMoviesBinding.rvMovie.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClickCallback(Movie movie) {
        startActivity(new Intent(getContext(), DetailActivity.class)
                .putExtra(DetailActivity.EXTRA_MOVIE, movie));
    }

    @Override
    public void onItemFavoriteClickCallback(MovieDB movieDB) {

    }
}
