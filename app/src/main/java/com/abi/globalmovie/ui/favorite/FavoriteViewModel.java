package com.abi.globalmovie.ui.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.data.repository.MovieRepository;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public FavoriteViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<MovieDB>> getFavorites(){
        return movieRepository.getFavorite();
    }
    public void deleteFavorite(MovieDB movieDB){
        movieRepository.deleteFavorite(movieDB);
    }
}
