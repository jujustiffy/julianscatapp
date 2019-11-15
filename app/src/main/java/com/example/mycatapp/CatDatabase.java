package com.example.mycatapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cat.class, CatImage.class}, version = 9, exportSchema = false)
public abstract class CatDatabase extends RoomDatabase {
    public abstract CatDao catDao();
    public abstract CatImageDao catImgDao();
    private static CatDatabase instance;
    public static CatDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, CatDatabase.class, "cat")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}