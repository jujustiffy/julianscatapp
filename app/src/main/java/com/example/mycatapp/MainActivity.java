package com.example.mycatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.zip.Inflater;
//          f2fedff8-00ee-4416-a82f-dcf0f6e6d90d API key
//          https://api.thecatapi.com/v1/breeds?api_key=f2fedff8-00ee-4416-a82f-dcf0f6e6d90d
//          https://api.thecatapi.com/v1/images/search?api_key=f2fedff8-00ee-4416-a82f-dcf0f6e6d90d

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btmNavigationView;
    CatAdapter catAdapter;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new CatRecyclerFragment();
        swapFragment(fragment);

        btmNavigationView = findViewById(R.id.bottom_navigation);
        btmNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // menuItem = the item on the bottom nav view that was selected
                // The id's here belong to the items in the menu of the BottomnNavigationView
                // The menu is chunked out as bottom_nav_menu.xml in the res > menu folder
                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment = new CatRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_favourite) {
                    Fragment fragment = new FavouritesRecyclerFragment();
                    // Here's just an example of passing information to the Fragment via Bundle
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                    // End bundle part

                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }


    }




