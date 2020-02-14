package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaystore.adapter.SearchAdapter;
import com.example.relaystore.api.Api;
import com.example.relaystore.test_model.SearchOrder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search_order_Activity extends AppCompatActivity {
    EditText et_search;

    //for dummy data show in recyclerview...
    List<SearchOrder> usersSearch = new ArrayList<>();
    RecyclerView recyclerView;
    SearchAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = findViewById(R.id.recyclerViewTasks);
        et_search = findViewById(R.id.et_search);
        usersSearch = new ArrayList<>();

//for dummy data show in recyclerview...

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        prepareMovieData();
        getShopProducts();


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });


    }


    private void getShopProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<SearchOrder>> call = api.getSelected();

        call.enqueue(new Callback<List<SearchOrder>>() {
            @Override
            public void onResponse(Call<List<SearchOrder>> call, Response<List<SearchOrder>> response) {

                usersSearch = response.body();

                mAdapter = new SearchAdapter(usersSearch);
                recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<SearchOrder>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<SearchOrder> movieList = new ArrayList<>();

        //looping through existing elements
        //if the existing elements contains the search input
        for (SearchOrder searchOrder : usersSearch ) {
           // if (searchOrder.getMSISDN().contains(text))
            Log.e("filterDate:", searchOrder.getMSISDN()+" : "+text);
            String nicHandle =  searchOrder.getNIC_Handle().toLowerCase();
            String txt =  text.toLowerCase();
            if (nicHandle.startsWith(txt))
            {
                //adding the element to filtered list
                movieList.add(searchOrder);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        mAdapter.filterList(movieList);
    }
}



