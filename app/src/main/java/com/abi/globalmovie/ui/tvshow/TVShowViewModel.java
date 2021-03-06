package com.abi.globalmovie.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.data.repository.MovieRepository;

import java.util.List;

public class TVShowViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public TVShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<Movie>> getTvShowMovies(){
        return movieRepository.getTVShow();
    }

}
