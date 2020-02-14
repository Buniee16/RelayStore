package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaystore.adapter.MoviesAdaptertwo;
import com.example.relaystore.adapter.UserListAdapter;
import com.example.relaystore.api.Api;
import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.UserList;
import com.example.relaystore.test_model.UserOrderDetails;
import com.example.relaystore.test_model.UserOrderListDetails;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Order_product_list extends AppCompatActivity {

    Button btnEdit, btn_payment;
    TextView txt_order_number, txt_order_date, txt_total_amount, txt_payment_method, txt_status, txt_address, txt_user_name;

    TextView txt_order_list,empty_view;
    String nic;
    String quantity, numId;
    //for dummy data show in recyclerview...
    List<UserOrderListDetails.ProductAvlShop> users = new ArrayList<>();
    RecyclerView recyclerView;
    MoviesAdaptertwo mAdapter;
    ProgressBar progress_bar;
    //    ListView listViewOrders;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_product_list);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        txt_order_date = findViewById(R.id.txt_order_date);
        txt_order_number = findViewById(R.id.txt_order_number);
        txt_total_amount = findViewById(R.id.txt_total_amount);
        txt_payment_method = findViewById(R.id.txt_payment_method);
        txt_address = findViewById(R.id.txt_address);
        txt_status = findViewById(R.id.txt_status);
        txt_user_name = findViewById(R.id.txt_user_name);
        txt_order_list = findViewById(R.id.txt_order_list);
        empty_view = findViewById(R.id.empty_view);
        btn_payment = findViewById(R.id.btn_payment);
        progress_bar = findViewById(R.id.progress_bar);

        final Intent in = getIntent();
//         numId = in.getStringExtra("numId");
        String date = in.getStringExtra("date");
        nic = in.getStringExtra("nic");

//        Log.e("numId", numId);
//        Log.e("date", ""+date);
        Log.e("nic", ""+nic);


        txt_user_name.setText(nic + " Order Details");

        txt_order_list.setText(nic + " Order List");
        getHeroes();
        getProductList();

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Order_product_list.this, Choose_payment_method.class);
                in.putExtra("nic",nic);
                startActivity(in);
            }
        });


//        listViewOrders = findViewById(R.id.listViewOrders);
//        recyclerView = findViewById(R.id.recycler_view);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        btnEdit = findViewById(R.id.btn_edit);


      /*  if (users.size() == 0 ){
            recyclerView.setVisibility(View.GONE);
//            txt_Num_id.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);

        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
//            txt_Num_id.setVisibility(View.GONE);
            empty_view.setVisibility(View.GONE);

        }*/


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(Order_product_list.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(Order_product_list.this, "Please Click on Edit Button...", Toast.LENGTH_SHORT).show();
                        //Intent get Data
//                        Intent intent = new Intent(Order_product_list.this, Order_product_list.class);
                        UserOrderListDetails.ProductAvlShop priceList = users.get(position);
                        String numid = users.get(position).getNumId();
                        String nic = priceList.getNIC_Handle();
                        String order_date = priceList.getD_Creation();
                        quantity = priceList.getQuantite();
                        numId = priceList.getNumId();
//                        String total = priceList.getTotal();

                        Log.e("name", numid);
                        Log.e("order_number", nic);
                        Log.e("order_date", order_date);
                        Log.e("order_image", ""+priceList.getProduct_image());


                    }
                })
        );


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order_product_list.this, Edit_order_Activity.class);
                intent.putExtra("nic",nic);
                intent.putExtra("quantity",quantity);
                intent.putExtra("numId",numId);
                startActivity(intent);
                finish();

            }
        });


        mAdapter = new MoviesAdaptertwo(users,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



    }

    private void getProductList() {
        progress_bar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<UserOrderListDetails> call = api.getOrderDetails(nic);

        call.enqueue(new Callback<UserOrderListDetails>() {
            @Override
            public void onResponse(Call<UserOrderListDetails> call, Response<UserOrderListDetails> response) {
//                List<UserList> heroList = response.body()
//                progress_bar.setVisibility(View.GONE);;
                if (response.isSuccessful()) {

                    progress_bar.setVisibility(View.GONE);
                    if (response.body().getStatus().equals("Success")) {
//                        progressBar.setVisibility(View.GONE);
                        UserOrderListDetails userOrderListDetails = response.body();

                        users.addAll(response.body().getDatalist());

                        recyclerView.setAdapter(mAdapter);

                        mAdapter.notifyDataSetChanged();
//                        recyclerView.setAdapter(mAdapter);
//                    movieList.addAll(response.body().getProperties());
//                        mAdapter.notifyDataSetChanged();

          /*      List<UserOrderListDetails> heroList = (List<UserOrderListDetails>) response.body();

                String[] heroes = new String[heroList.size()];

                Log.e("ListData",""+heroes);

*/

//                users = response.body().getData();
//                //Creating an String array for the ListView
////                String[] heroes = new String[heroList.size()];
//
//                users = response.body().getData();
//                mAdapter = new MoviesAdaptertwo(users,getApplicationContext());
//                recyclerView.setAdapter(mAdapter);


                    } else {
                        Log.e("notsuccessPrint", response.message());
//
                    }
                }
            }

            @Override
            public void onFailure(Call<UserOrderListDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getHeroes() {
        progress_bar.setVisibility(View.VISIBLE);

        String new_a = nic;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
//                .client(httpClient.build())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<UserOrderDetails>> call = api.getUserOrderDetails(new_a);

        call.enqueue(new Callback<List<UserOrderDetails>>() {
            @Override
            public void onResponse(Call<List<UserOrderDetails>> call, Response<List<UserOrderDetails>> response) {
                List<UserOrderDetails> heroList = response.body();
//                UserOrderDetails heroList = response.body();
                Gson gson = new Gson();
                String res = gson.toJson(response.body());
                Log.e("============", res);
                progress_bar.setVisibility(View.GONE);
        /*        if (heroList != null) {
                    Log.e("+++++", "" + heroList.toString());
                }
                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];*/

                //looping through all the heroes and inserting the names inside the string array
                UserOrderDetails userOrderDetails = response.body().get(0);

                String order_number = userOrderDetails.getNIC_Handle();
                String order_date = userOrderDetails.getD_Creation();
                String total_amount = userOrderDetails.getNet_A_Payer();
                String payment_method = userOrderDetails.getPaiement();
                String delivery_address = userOrderDetails.getAdresse();
                String status = userOrderDetails.getMontant_Livraison();

                txt_order_number.setText(order_number);
                txt_total_amount.setText(total_amount);
                txt_order_date.setText(order_date);
                txt_payment_method.setText(payment_method);
                txt_status.setText(status);
                txt_address.setText(delivery_address);

            }

            @Override
            public void onFailure(Call<List<UserOrderDetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}