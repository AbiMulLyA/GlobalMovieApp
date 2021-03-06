package com.abi.globalmovie.ui.tvshow;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
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
import com.abi.globalmovie.databinding.FragmentTvShowBinding;
import com.abi.globalmovie.ui.detail.DetailActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class TVShowFragment extends Fragment implements ItemClickListener {

    private FragmentTvShowBinding fragmentTvShowBinding;
    private Application application;
    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return fragmentTvShowBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity(), application);
            TVShowViewModel viewModel =
                    new ViewModelProvider(this, factory).get(TVShowViewModel.class);
            TVShowAdapter adapter = new TVShowAdapter(getContext());
            fragmentTvShowBinding.progressBar.setVisibility(VISIBLE);

            viewModel.getTvShowMovies().observe(this, movies -> {
                fragmentTvShowBinding.progressBar.setVisibility(GONE);
                adapter.setMovies(movies, TVShowFragment.this);
                adapter.notifyDataSetChanged();
            });

            fragmentTvShowBinding.rvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentTvShowBinding.rvTvshow.setHasFixedSize(true);
            fragmentTvShowBinding.rvTvshow.setAdapter(adapter);

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