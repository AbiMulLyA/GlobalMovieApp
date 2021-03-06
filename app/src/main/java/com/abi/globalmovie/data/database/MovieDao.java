package com.abi.globalmovie.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieDB movieDB);
    @Update()
    void update(MovieDB movieDB);
    @Delete()
    void delete(MovieDB movieDB);
    @Query("SELECT * from moviedb ORDER BY id ASC")
    LiveData<List<MovieDB>> getAllMovies();
}
