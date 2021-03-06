package com.abi.globalmovie.data;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.abi.globalmovie.data.di.Injection;
import com.abi.globalmovie.data.repository.MovieRepository;
import com.abi.globalmovie.ui.detail.DetailViewModel;
import com.abi.globalmovie.ui.favorite.FavoriteViewModel;
import com.abi.globalmovie.ui.movies.MoviesViewModel;
import com.abi.globalmovie.ui.tvshow.TVShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static  volatile  ViewModelFactory INSTANCE;
    private final MovieRepository movieRepository;

    private ViewModelFactory(MovieRepository testRepository) {
        movieRepository = testRepository;
    }

    public static ViewModelFactory getInstance(Context context, Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context, application));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(movieRepository);
        }
        else if (modelClass.isAssignableFrom(TVShowViewModel.class)) {
            return (T) new TVShowViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(movieRepository);
        }else if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(movieRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
