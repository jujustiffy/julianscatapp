package com.example.mycatapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FavouritesRecyclerFragment extends Fragment {

    private RecyclerView.LayoutManager favLayoutManager;
    private ArrayList<Cat> favList;
    private RecyclerView favRecyclerView;
    private FavAdapter favAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_recycler, container, false);
        favList = load("favourites", favList);
        view = buildRecyclerView(view);
        return view;
    }

    public void removeItem(int position) {
        favList.remove(position);
        favAdapter.notifyItemRemoved(position);
        delete("favourites", favList);
    }

    private ArrayList<Cat> load(String key, ArrayList<Cat> list) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type menuJson = new TypeToken<ArrayList<Cat>>() {
        }.getType();
        list = gson.fromJson(json, menuJson);

        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }

    private void delete(String key, ArrayList<Cat> list) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("favourites", gson.toJson(list));
        editor.apply();
    }

    public View buildRecyclerView(View view) {
        favRecyclerView = view.findViewById(R.id.rv_fav);
        favRecyclerView.setHasFixedSize(true);
        favLayoutManager = new LinearLayoutManager(view.getContext());
        favAdapter = new FavAdapter(view.getContext(), favList);

        favRecyclerView.setLayoutManager(favLayoutManager);
        favRecyclerView.setAdapter(favAdapter);

        favAdapter.setOnItemClickListener(new FavAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
        return view;
    }
}
