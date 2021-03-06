package com.abi.globalmovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.data.repository.MovieRepository;

public class DetailViewModel extends ViewModel {

    private Movie movie;
    private MovieRepository movieRepository;

    public DetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setDetailView(Movie movie){
        this.movie = movie;
    }

    public LiveData<Movie> getDetailMovie(){
        return movieRepository.getMovie(this.movie);
    }

    public void addToFavorite(MovieDB movieDB){
        movieRepository.addToFavorite(movieDB);
    }
}
