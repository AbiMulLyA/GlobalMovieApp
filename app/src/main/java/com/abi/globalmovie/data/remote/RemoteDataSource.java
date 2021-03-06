package com.abi.globalmovie.data.remote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.abi.globalmovie.BuildConfig;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.data.remote.response.MovieResponse;
import com.abi.globalmovie.service.ApiClient;
import com.abi.globalmovie.service.ApiService;
import com.abi.globalmovie.utils.EspressoIdlingResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    @SuppressLint("StticFieldLeak")
    private static  RemoteDataSource INSTANCE;

    private Context context;
    private RemoteDataSource (Context context){this.context = context;}

    public static  RemoteDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }

    public void getAllMovies(LoadResultCallback callback){
        EspressoIdlingResource.increment();
        ApiClient.client(ApiService.class).getMovie(BuildConfig.API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        EspressoIdlingResource.decrement();
                        if (response.body() != null) {
                            Log.d("Response : ", response.body().toString());
                            callback.onResultReceived(response.body().getResults());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        EspressoIdlingResource.decrement();
                        Toast.makeText(context, "Failed "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getAllTVShow(LoadResultCallback callback) {
        EspressoIdlingResource.increment();
        ApiClient.client(ApiService.class).getTvShow(BuildConfig.API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        EspressoIdlingResource.decrement();
                        if (response.body() != null) {
                            callback.onResultReceived(response.body().getResults());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        EspressoIdlingResource.decrement();
                        Toast.makeText(context, "Failed "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    public interface LoadResultCallback {
        void onResultReceived(List<Movie> response);
    }
}
