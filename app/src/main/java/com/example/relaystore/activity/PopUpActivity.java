package com.example.relaystore.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relaystore.Edit_order_Activity;
import com.example.relaystore.Order_product_list;
import com.example.relaystore.R;
import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopUpActivity extends AppCompatActivity {
    Button btnDone;
    EditText et_subject_name, et_subject_marks;

    TextView displayInteger, txt_Product_Name;
    int minteger;

    String numId, nic_handle, D_Creation, Reference, Libelle_TAG, Quantite, Prix_Unitaire, Prix_Total, Remise, Total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        getSupportActionBar().hide();

        Intent in = getIntent();
        numId = in.getStringExtra("numId");
        nic_handle = in.getStringExtra("nic_handle");
        D_Creation = in.getStringExtra("D_Creation");
        Reference = in.getStringExtra("Reference");
        Libelle_TAG = in.getStringExtra("Libelle_TAG");
        Quantite = in.getStringExtra("Quantite");
        Prix_Unitaire = in.getStringExtra("Prix_Unitaire");
        Prix_Total = in.getStringExtra("Prix_Total");
        Remise = in.getStringExtra("Remise");
        Total = in.getStringExtra("Total");

        Log.e(" new numID", "" + numId);
        Log.e(" new nic_handle", "" + nic_handle);
        Log.e(" new D_Creation", "" + D_Creation);
        Log.e(" new Reference", "" + Reference);
        Log.e(" new Libelle_TAG", "" + Libelle_TAG);
        Log.e(" new Quantite", "" + Quantite);
        Log.e(" new Prix_Unitaire", "" + Prix_Unitaire);
        Log.e(" new Prix_Total", "" + Prix_Total);
        Log.e(" new Remise", "" + Remise);
        Log.e(" new Total", "" + Total);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        btnDone = findViewById(R.id.btn_done);
        displayInteger = findViewById(R.id.integer_number);
        txt_Product_Name = findViewById(R.id.txt_Product_Name);


        txt_Product_Name.setText(Libelle_TAG);
        displayInteger.setText(Quantite);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                updateProduct();
//                Toast.makeText(PopUpActivity.this, "updated...", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }

    public void decreaseInteger(View view) {
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


/*
    private void updateProduct() {


//        String quantity = in.getStringExtra("quantity");
//        String numeId = in.getStringExtra("numId");

        String updated_quantity = displayInteger.getText().toString();
//        Log.e("name", nic);
//        Log.e("order_number", quantity);
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
                    Toast.makeText(PopUpActivity.this, message, Toast.LENGTH_SHORT).show();
//                    onBackPressed();
                    Intent in = new Intent(PopUpActivity.this, Edit_order_Activity.class);
                    in.putExtra("nic",nic_handle);
                    startActivity(in);
                    finish();
                }


//                users = response.body();
  ProductAvlShop productAvlShop = (ProductAvlShop) response.body();
                Gson gson = new Gson();
                String res = gson.toJson(response.message());
                Log.e("============",res);
                if (productAvlShop!= null) {
                    Log.e("+++++", "" + productAvlShop.getQuantite());

                }
//                    users = response.body();
                mAdapter = new MoviesAdapter(users);
                recyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
*/
}
