package com.example.mycatapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;



    @Dao
    public interface CatImageDao {

        @Query("SELECT * FROM catImage")
        List<CatImage> getAll();

        @Query("SELECT * FROM catImage WHERE id = :id LIMIT 1")
        CatImage findCatImageById(String id);

        @Query("SELECT url FROM catImage WHERE breed_id LIKE :id ")
        CatImage findCatUrl (String id);






        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(List<CatImage> catImage);

        @Insert
        void insertAll(CatImage... catImages);

        @Delete
        void delete(CatImage catImage);


        @Delete
        void deleteAll(CatImage... catImages);
    }


