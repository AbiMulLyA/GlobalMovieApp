package com.abi.globalmovie.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.abi.globalmovie.R;
import com.abi.globalmovie.data.ViewModelFactory;
import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;
import com.abi.globalmovie.databinding.ActivityDetailBinding;
import com.abi.globalmovie.databinding.MoviesDetailBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity{

    public static final String EXTRA_MOVIE = "extra_movie";
    private final int ROUNDED_CORNER = 20;
    MoviesDetailBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(activityDetailBinding.getRoot());
        detailBinding = activityDetailBinding.detailContent;

        setSupportActionBar(activityDetailBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewModelFactory factory = ViewModelFactory.getInstance(this, getApplication());
        DetailViewModel viewModel = new ViewModelProvider(this,
                factory).get(DetailViewModel.class);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        if (movie != null) {
            viewModel.setDetailView(movie);
            viewModel.getDetailMovie().observe(this, this::setupDetail);
        }
        String year = movie.getReleaseDate() != null ? movie.getReleaseDate() : movie.getFirstAirDate();
        String title = movie.getTitle() != null ? movie.getTitle() : movie.getName();
        detailBinding.fabBtn.setOnClickListener(v -> {

            MovieDB movieDB = new MovieDB();
            movieDB.setId(movie.getId());
            movieDB.setTitle(title);
            movieDB.setOverview(movie.getOverview());
            movieDB.setposterPath(movie.getPosterPath());
            movieDB.setPopularity("");
            movieDB.setReleaseDate(year);

            Log.d("Data will be saved", movieDB.toString());
            viewModel.addToFavorite(movieDB);
            Toast.makeText(this, "Add to Favorite Success!", Toast.LENGTH_LONG).show();
        });
    }

    private void setupDetail(Movie movie) {
        String year = movie.getReleaseDate() != null ? movie.getReleaseDate() : movie.getFirstAirDate();
        String title = movie.getTitle() != null ? movie.getTitle() : movie.getName();
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + movie.getBackdropPath())
                .transform(new RoundedCorners(ROUNDED_CORNER))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailBinding.ivCover);
        detailBinding.tvTitle.setText(title);
        detailBinding.language.setText(movie.getOriginalLanguage().toUpperCase());
        detailBinding.year.setText(year);
        detailBinding.synopsis.setText(movie.getOverview());
    }


}