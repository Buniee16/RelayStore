package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relaystore.activity.PopUpActivity;
import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;

import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.UserList;
import com.example.relaystore.test_model.UserOrderListDetails;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Edit_order_Activity extends AppCompatActivity{



    Button btnUpdateBasket;
    TextView tvBackPress, txt_product_name, txt_product_amount, txt_user_edit_name,total_amount,empty_view;

    ImageView image_product;
    TextView displayInteger;

    RecyclerView recyclerView;
    List<UserOrderListDetails.ProductAvlShop> users = new ArrayList<>();
    MoviesAdapter mAdapter;
    String quantity, nic;

    int minteger;
    ProgressBar progress_bar;
    String numId, nic_handle, D_Creation, Reference, Libelle_TAG, Quantite, Prix_Unitaire, Prix_Total, Remise, Total, product_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        displayInteger = findViewById(R.id.integer_number);
//        txt_product_name = findViewById(R.id.txt_product_name);
//        txt_product_amount = findViewById(R.id.txt_product_amount);
        txt_user_edit_name = findViewById(R.id.txt_user_edit_name);
        empty_view = findViewById(R.id.empty_view);
        total_amount = findViewById(R.id.total_amount);
//        image_product = findViewById(R.id.image_product);
        progress_bar = findViewById(R.id.progress_bar);

        getProductList();
        users = new ArrayList<>();


        btnUpdateBasket = findViewById(R.id.btn_update_basket);


        final Intent in = getIntent();
        nic = in.getStringExtra("nic");


        txt_user_edit_name.setText(nic + " Order Details");


        btnUpdateBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onBackPressed();
//                finish();
                Intent in = new Intent(Edit_order_Activity.this, Order_product_list.class);
                in.putExtra("nic", nic_handle);
                startActivity(in);
                finish();

//                updateProduct();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);


        //for dummy data show in recyclerview...
        mAdapter = new MoviesAdapter(users);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


/*
        if (users.size() == 0 ){
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
                new RecyclerItemClickListener(Edit_order_Activity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        //Intent get Data
//                        Intent intent = new Intent(Edit_order_Activity.this, PopUpActivity.class);
                        UserOrderListDetails.ProductAvlShop priceList = users.get(position);
                        String name = users.get(position).getLibelle_TAG();
                        quantity = priceList.getQuantite();
                        String amount = priceList.getTotal();


                        numId = priceList.getNumId();
                        nic_handle = priceList.getNIC_Handle();
                        D_Creation = priceList.getD_Creation();
                        Reference = priceList.getReference();
                        Libelle_TAG = priceList.getLibelle_TAG();
                        Quantite = priceList.getQuantite();
                        Prix_Unitaire = priceList.getPrix_Unitaire();
                        Prix_Total = priceList.getPrix_Total();
                        Remise = priceList.getRemise();
                        Total = priceList.getTotal();
                        product_image = priceList.getProduct_image();


/*
                        txt_product_name.setText(Libelle_TAG);
                        txt_product_amount.setText("$"+Total);
                        Picasso.with(getApplicationContext()).load(product_image).into(image_product);
                        displayInteger.setText(Quantite);*/




                        Log.e(" new numID", numId);
                        Log.e(" new nic_handle", nic_handle);
                        Log.e(" new D_Creation", D_Creation);
                        Log.e(" new Reference", Reference);
                        Log.e(" new Libelle_TAG", Libelle_TAG);
                        Log.e(" new Quantite", Quantite);
                        Log.e(" new Prix_Unitaire", Prix_Unitaire);
                        Log.e(" new Prix_Total", Prix_Total);
                        Log.e(" new Remise", Remise);
                        Log.e(" new Total", Total);

/*
                        intent.putExtra("numId", numId);
                        intent.putExtra("nic_handle", nic_handle);
                        intent.putExtra("D_Creation", D_Creation);
                        intent.putExtra("Reference", Reference);
                        intent.putExtra("Libelle_TAG", Libelle_TAG);
                        intent.putExtra("Quantite", Quantite);
                        intent.putExtra("Prix_Unitaire", Prix_Unitaire);
                        intent.putExtra("Prix_Total", Prix_Total);
                        intent.putExtra("Remise", Remise);
                        intent.putExtra("Total", Total);


                        startActivity(intent);
                        finish();*/

                    }
                })
        );


    }



    /*
     *     Update User Product List
     * */


/*
    private void updateProduct() {


//        String quantity = in.getStringExtra("quantity");
//        String numeId = in.getStringExtra("numId");

        String updated_quantity = displayInteger.getText().toString();
        Log.e("name", nic);
        Log.e("order_number", quantity);
        Log.e("order_number", updated_quantity);
        Log.e("order_date", numId);


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .client(httpClient.build())
                .build();

        Api api = retrofit.create(Api.class);

        Call<DefaultResponse> call = api.updateProduct(numId, nic_handle, D_Creation, Reference, Libelle_TAG, updated_quantity
                , Prix_Unitaire, Prix_Total, Remise, Total);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse defaultResponse = response.body();


                if (response.isSuccessful()) {
                    String message = defaultResponse.getMsg();
                    Log.e("))))))", message);
                    Toast.makeText(Edit_order_Activity.this, message, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Edit_order_Activity.this, Edit_order_Activity.class);
                    in.putExtra("nic",nic_handle);
                    startActivity(in);
                    finish();
                }


//                users = response.body();
//
//  ProductAvlShop productAvlShop = (ProductAvlShop) response.body();
//                Gson gson = new Gson();
//                String res = gson.toJson(response.message());
//                Log.e("============",res);
//                if (productAvlShop!= null) {
//                    Log.e("+++++", "" + productAvlShop.getQuantite());
//
//                }
////                    users = response.body();
//                mAdapter = new MoviesAdapter(users);
//                recyclerView.setAdapter(mAdapter);
//


            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
*/



    /*
     *     Get User Product List
     * */


    private void getProductList() {

//        progress_bar.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.VISIBLE);
        Intent in = getIntent();
        String nic = in.getStringExtra("nic");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<UserOrderListDetails> call = api.getOrderDetails(nic);

        call.enqueue(new Callback<UserOrderListDetails>() {
            @Override
            public void onResponse(Call<UserOrderListDetails> call, Response<UserOrderListDetails> response) {


                if (response.isSuccessful()) {

                    progress_bar.setVisibility(View.GONE);
                    if (response.body().getStatus().equals("Success")) {
//                        progressBar.setVisibility(View.GONE);
                        UserOrderListDetails userOrderListDetails = response.body();

                        users.addAll(response.body().getDatalist());

                        recyclerView.setAdapter(mAdapter);

                        mAdapter.notifyDataSetChanged();

//                        UserOrderListDetails.ProductAvlShop productAvlShop = (UserOrderListDetails.ProductAvlShop) userOrderListDetails.getDatalist();
                        ArrayList<UserOrderListDetails.ProductAvlShop> productAvlShop = (ArrayList<UserOrderListDetails.ProductAvlShop>) userOrderListDetails.getDatalist();


                        for (int i = 0; i < productAvlShop.size(); i++) {


                            UserOrderListDetails.ProductAvlShop pro = productAvlShop.get(i);
//                            total_amount.setText(pro.getTotal());

                        }
                    }
                    else{
                        Log.e("fail","fail");
                    }
                }
/*
                users = response.body().getData();
                progress_bar.setVisibility(View.GONE);
                users = response.body().getData();
                mAdapter = new MoviesAdapter(users);
                recyclerView.setAdapter(mAdapter);*/


            }

            @Override
            public void onFailure(Call<UserOrderListDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void increaseInteger(View view) {
        minteger = Integer.parseInt(displayInteger.getText().toString());
        minteger = minteger + 1;
        display(minteger);

    }

    public void decreaseInteger(View view) {
        minteger = Integer.parseInt(displayInteger.getText().toString());

        if (minteger == 0) {
//            Toast.makeText(this, "Can't decrease the value more..", Toast.LENGTH_SHORT).show();

        } else

            minteger = minteger - 1;
        display(minteger);

    }

    private void display(int number) {

//        displayInteger.setText(""+ quantity);
        displayInteger.setText("" + number);

    }

    @Override
    public void onBackPressed() {
       /* Intent in = new Intent(Edit_order_Activity.this,Order_product_list.class);
        in.putExtra("nic",nic_handle);
        startActivity(in);
        finish();*/
        super.onBackPressed();

    }


}
