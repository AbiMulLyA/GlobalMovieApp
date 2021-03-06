package com.abi.globalmovie;

import com.abi.globalmovie.data.database.MovieDB;
import com.abi.globalmovie.data.remote.response.Movie;

public interface ItemClickListener {

    void onItemClickCallback(Movie movie);

    void onItemFavoriteClickCallback(MovieDB movieDB);
}
