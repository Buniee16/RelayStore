package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relaystore.activity.PopUpActivity;
import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Forgot_Activity extends AppCompatActivity {

   Button btn_validate;
    EditText et_forgot_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_forgot_email = findViewById(R.id.et_forgot_email);
        btn_validate = findViewById(R.id.btn_validate);
        
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotpassword();
            }
        });
       


    }

    private void forgotpassword() {
        String email = et_forgot_email.getText().toString();

        if (TextUtils.isEmpty(email)){
            et_forgot_email.setError("Please Enter Email");
            et_forgot_email.requestFocus();
//            Toast.makeText(this, "Nahi jane dunga", Toast.LENGTH_SHORT).show();
            return;
        }


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

        Call<DefaultResponse> call = api.forgotPassword(email);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.body().isStatus() != true) {

                    Log.e("welcome", "welcome");
                    Toast.makeText(Forgot_Activity.this, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }else {
                    DefaultResponse defaultResponse = response.body();


//                checkIfEmailVerified();
                    Toast.makeText(Forgot_Activity.this, "" + defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Forgot_Activity.this, Login_Activity.class);
//                    in.putExtra("email", email);
//                    in.putExtra("pass", password);
                    startActivity(in);
                    finish();

                }


//                DefaultResponse defaultResponse = response.body();
//
//                Gson gson = new Gson();
//                String res = gson.toJson(response.message());
//                Log.e("============",res);
//
//                String S = defaultResponse.getMsg();
//                String S1 = defaultResponse.getStatus();
//                Log.e("aaaa",S);
//                Log.e("bbbb",S1);
//                if (S1.contains("Error")){
//                    Log.e("no","no user");
//                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
//
//                }
//                else if (S1.contains("SUCCESS")){
//                    Log.e("yes","user");
////                    Intent intent = new Intent(MainActivity.this, ForgotActivity.class);
////                    startActivity(intent);
//
//                }
//
//
//
//





//                if (response.isSuccessful()) {
//                    String message = defaultResponse.getMsg();
//                    Log.e("))))))", message);
//                    Toast.makeText(Forgot_Activity.this, message, Toast.LENGTH_SHORT).show();
////                    onBackPressed();
//                    Intent in = new Intent(Forgot_Activity.this, Login_Activity.class);
//                    startActivity(in);
//                    finish();
//                }


//                users = response.body();
              /*  ProductAvlShop productAvlShop = (ProductAvlShop) response.body();
                Gson gson = new Gson();
                String res = gson.toJson(response.message());
                Log.e("============",res);
                if (productAvlShop!= null) {
                    Log.e("+++++", "" + productAvlShop.getQuantite());

                }
//                    users = response.body();
                mAdapter = new MoviesAdapter(users);
                recyclerView.setAdapter(mAdapter);
*/

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
