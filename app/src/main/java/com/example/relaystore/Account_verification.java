package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Account_verification extends AppCompatActivity {

    Button btnLogin,btnSignUp;

    TextView tvBackPress;
    String name, email, password,confirm_Password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_verification);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent in = getIntent();
        name = in.getStringExtra("name");
        email = in.getStringExtra("email");
        password = in.getStringExtra("password");
        confirm_Password = in.getStringExtra("confirm_Password");

//        verifyUser();
        tvBackPress = findViewById(R.id.tv_back_press);
        tvBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_verification.this,Login_Activity.class);
                startActivity(intent);
            }
        });

        btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_verification.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

   /* private void verifyUser() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<DefaultResponse> call = api.verifyUser(name,email, password,confirm_Password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if (response.body().isStatus() != true) {

                    Log.e("welcome", "welcome");
                    Toast.makeText(Account_verification.this, "Something Went Wrong..", Toast.LENGTH_SHORT).show();

                } else {
                    DefaultResponse defaultResponse = response.body();


//                checkIfEmailVerified();
                    Toast.makeText(Account_verification.this, "" + defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } *//*else {

                    Toast.makeText(Login_Activity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }*//*
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }*/
}
