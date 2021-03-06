package com.abi.globalmovie.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abi.globalmovie.data.database.LocalDataSource;
import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.RemoteDataSource;
import com.abi.globalmovie.data.remote.response.Movie;

import java.util.List;

public class MovieRepository implements IMovieRepository {

    private volatile static MovieRepository INSTANCE = null;
    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;

    private MovieRepository(@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static MovieRepository getInstance(RemoteDataSource remoteData, LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteData, localDataSource);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public LiveData<List<Movie>> getMoviesShow() {
        MutableLiveData<List<Movie>> movieShow = new MutableLiveData<>();
        remoteDataSource.getAllMovies(movieShow::postValue);
        return movieShow;
    }

    @Override
    public LiveData<List<Movie>> getTVShow() {
        MutableLiveData<List<Movie>> tvShow = new MutableLiveData<>();
        remoteDataSource.getAllTVShow(tvShow::postValue);
        return tvShow;
    }

    @Override
    public LiveData<Movie> getMovie(Movie movie) {
        MutableLiveData<Movie> movieDetail = new MutableLiveData<>();
        movieDetail.setValue(movie);
        return movieDetail;
    }

    @Override
    public void addToFavorite(MovieDB movieDB) {
        localDataSource.insert(movieDB);
    }

    @Override
    public void deleteFavorite(MovieDB movieDB) {
        localDataSource.delete(movieDB);
    }

    @Override
    public LiveData<List<MovieDB>> getFavorite() {
       return localDataSource.getAllMovies();
    }

}
