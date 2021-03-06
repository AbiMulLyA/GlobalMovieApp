//package com.abi.globalmovie.data;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.abi.globalmovie.ui.detail.DetailViewModel;
//
//public class FavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory{
//    private static volatile ViewModelFactory INSTANCE;
//    private final Application mApplication;
//    private FavoriteViewModelFactory(Application application) {
//        mApplication = application;
//    }
//    public static ViewModelFactory getInstance(Application application) {
//        if (INSTANCE == null) {
//            synchronized (ViewModelFactory.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new FavoriteViewModelFactory(application);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(DetailViewModel.class)) {
//            return (T) new DetailViewModel(mApplication);
//        } else if (modelClass.isAssignableFrom(NoteAddUpdateViewModel.class)) {
//            return (T) new NoteAddUpdateViewModel(mApplication);
//        }
//        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
//    }
//}
