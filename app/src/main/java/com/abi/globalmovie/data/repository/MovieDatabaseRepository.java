package com.abi.globalmovie.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.database.MovieDao;
import com.abi.globalmovie.data.database.MovieRoomDatabase;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDatabaseRepository {
    private MovieDao mMoviesDao;
    private ExecutorService executorService;

    public MovieDatabaseRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMoviesDao = db.movieDao();
    }

    public LiveData<List<MovieDB>> getAllMovies() {
        return mMoviesDao.getAllMovies();
    }
    public void insert(final MovieDB movie) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mMoviesDao.insert(movie);
            }
        });
    }
    public void delete(final MovieDB movieDB){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mMoviesDao.delete(movieDB);
            }
        });
    }
    public void update(final MovieDB movieDB){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mMoviesDao.update(movieDB);
            }
        });
    }
}
