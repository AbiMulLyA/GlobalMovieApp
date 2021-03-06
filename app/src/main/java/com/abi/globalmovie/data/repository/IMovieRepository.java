package com.abi.globalmovie.data.repository;

import androidx.lifecycle.LiveData;

import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;
import java.util.List;

public interface IMovieRepository {

    LiveData<List<Movie>> getMoviesShow();
    LiveData<List<Movie>> getTVShow();
    LiveData<Movie> getMovie(Movie movie);
    void addToFavorite(MovieDB movieDB);
    void deleteFavorite(MovieDB movieDB);
    LiveData<List<MovieDB>> getFavorite();
}
