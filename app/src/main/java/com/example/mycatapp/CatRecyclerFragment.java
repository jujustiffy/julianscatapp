package com.example.mycatapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private CatDatabase catDB;
    private EditText text;

    public CatRecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cat_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        text = view.findViewById(R.id.search_bar);
        Button searchButton;
        searchButton = view.findViewById( R.id.search_button );
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSearchActivity();
            }
        } );
        final CatAdapter catAdapter = new CatAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        final String url = "https://api.thecatapi.com/v1/breeds?api_key=a03cb8ae-b77f-4bbe-9455-11a8ddbbba1b";
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type collectionType = new TypeToken<List<Cat>>(){}.getType();
                List<Cat> catList =  new Gson().fromJson(response, collectionType);
                catDB = CatDatabase.getInstance(getContext());
                catDB.catDao().insert(catList);
                catAdapter.CatAdapter(catDB.catDao().getAll(), getContext());
                recyclerView.setAdapter(catAdapter);
                requestQueue.stop();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);
        final String searchUrl = "https://api.thecatapi.com/v1/images/search?api_key=a03cb8ae-b77f-4bbe-9455-11a8ddbbba1b";
        Response.Listener<String> responseImageListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type collectionType = new TypeToken<List<CatImage>>(){}.getType();
                List<CatImage> catImageList =  new Gson().fromJson(response, collectionType);
                catDB = CatDatabase.getInstance(getContext());
                catDB.catImgDao().insert(catImageList);
                requestQueue.stop();
            }
        };
        Response.ErrorListener errorImageListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };
        StringRequest stringImageRequest = new StringRequest(Request.Method.GET, searchUrl, responseImageListener,
                errorImageListener);
        requestQueue.add(stringImageRequest);
        return view;
    }
    public void onResume() {
        super.onResume();
    }
    public void newSearchActivity() {
        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String addQ = text.getText().toString();
        String url2 = "https://api.thecatapi.com/v1/breeds/search?q=" + addQ;
        System.out.println(addQ);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] cat = gson.fromJson(response, Cat[].class);
                        List<Cat> articleCat = Arrays.asList(cat);
                        catAdapter.CatAdapter(articleCat, getContext());
                        recyclerView.setAdapter(catAdapter);
                        System.out.println("this is the onresponse");
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }

}
