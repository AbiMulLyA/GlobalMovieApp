package com.abi.globalmovie.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.data.repository.MovieRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MoviesViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<Movie>> getMovies(){
        return movieRepository.getMoviesShow();
    }
}
