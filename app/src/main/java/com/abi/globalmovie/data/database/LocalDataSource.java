package com.abi.globalmovie.data.database;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.abi.globalmovie.data.remote.RemoteDataSource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalDataSource {

    private volatile static LocalDataSource INSTANCE = null;
    private MovieDao mMoviesDao;
    private ExecutorService executorService;

    private LocalDataSource(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMoviesDao = db.movieDao();
    }

    public static LocalDataSource getInstance(Application application){
        if(INSTANCE == null){
            INSTANCE = new LocalDataSource(application);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieDB>> getAllMovies() {
        return mMoviesDao.getAllMovies();
    }
    public void insert(final MovieDB movie) {
        executorService.execute(() -> mMoviesDao.insert(movie));
    }
    public void delete(final MovieDB movieDB){
        executorService.execute(() -> mMoviesDao.delete(movieDB));
    }
    public void update(final MovieDB movieDB){
        executorService.execute(() -> mMoviesDao.update(movieDB));
    }

}
