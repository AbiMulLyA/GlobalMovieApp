package com.abi.globalmovie.data.di;

import android.app.Application;
import android.content.Context;

import com.abi.globalmovie.data.database.LocalDataSource;
import com.abi.globalmovie.data.remote.RemoteDataSource;
import com.abi.globalmovie.data.repository.MovieRepository;

public class Injection {


    public static MovieRepository provideRepository(Context context, Application application) {

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(context);
        LocalDataSource localDataSource = LocalDataSource.getInstance(application);
        return MovieRepository.getInstance(remoteDataSource, localDataSource);
    }


}
