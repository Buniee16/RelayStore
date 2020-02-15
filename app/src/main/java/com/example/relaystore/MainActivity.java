package com.example.relaystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relaystore.Session.SessionManager;
import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvLogin;
    EditText etFullName, etEmail, etPassword, et_Confirm_Password;
    Button btnSignUp;
    ProgressBar progress_bar;
    SessionManager session;

    String name;
    String email;
    String password;
    String confirm_Password;
/*
* This is the main activity
* as'dasd
* asdasdd
* gs
* twr
* gdfb
* xdfghawre
* gsf
* gvzs
* dfgqw
* req
* ed
* v
* sadf
* adsfa
* sfa
* dsfoa
* sf
*
* */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {




            setContentView(R.layout.activity_main);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            session = new SessionManager(this);

            etFullName = findViewById(R.id.etFullName);
            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            et_Confirm_Password = findViewById(R.id.et_Confirm_Password);
            btnSignUp = findViewById(R.id.btn_register);
            progress_bar = findViewById(R.id.progress_bar);

            tvLogin = findViewById(R.id.tv_login);
            tvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Login_Activity.class);
//                startActivity(intent);
                    onBackPressed();
                }
            });


            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progress_bar.setVisibility(View.VISIBLE);
//                registerUser();
              /*  Intent in = new Intent(MainActivity.this, Order_list_Activity.class);
                startActivity(in);*/

                    if (!etPassword.getText().toString().equals(et_Confirm_Password.getText().toString())) {
                        progress_bar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "MisMatch Password", Toast.LENGTH_SHORT).show();
                    }

                    // TODO TEST APK
//                    registerNewUser();
                    verifyUser();

                }
            });

        }
    }

    // TODO REGISTER NEW USER

//    private void registerNewUser() {
//        progress_bar.setVisibility(View.VISIBLE);
//         name = etFullName.getText().toString().trim();
//         email = etEmail.getText().toString().trim();
//         password = etPassword.getText().toString().trim();
//         confirm_Password = et_Confirm_Password.getText().toString().trim();
//
//        if (etFullName.getText().toString().length() == 0) {
//            progress_bar.setVisibility(View.GONE);
//            etFullName.setError("Please Enter Full Name");
//            etFullName.requestFocus();
//            return;
//        }
//
//        if (etEmail.getText().toString().length() == 0) {
//            progress_bar.setVisibility(View.GONE);
//            etEmail.setError("Please Enter Email");
//            etEmail.requestFocus();
//            return;
//        }
//
//        if (etPassword.getText().toString().length() == 0) {
//            progress_bar.setVisibility(View.GONE);
//            etPassword.setError("Please Enter Password");
//            etEmail.requestFocus();
//            return;
//        }
//
//        if (et_Confirm_Password.getText().toString().length() == 0) {
//            progress_bar.setVisibility(View.GONE);
//            et_Confirm_Password.setError("Please Enter Password Again");
//            et_Confirm_Password.requestFocus();
//            return;
//        }
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
//                .build();
//
//        Api api = retrofit.create(Api.class);
//
//        Call<DefaultResponse> call = api.createUser(name, email, password, confirm_Password);
//
//        call.enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//
//                if (response.body().isStatus() != true) {
//                    progress_bar.setVisibility(View.GONE);
//
//                    Toast.makeText(MainActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                } else {
//                    DefaultResponse defaultResponse = response.body();
//
////                checkIfEmailVerified();
//                    progress_bar.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity.this, "" + defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
//
//
//                    //    TODO ACCOUNT VERIFICATION PAR ABHI DIRECT LIST PR DAAL RHE HAI JBTK API NHI BAN JATI
//
////                    session.createLoginSession(email,password);
//                    Intent in = new Intent(MainActivity.this, Account_verification.class);
//                    in.putExtra("name", name);
//                    in.putExtra("email", email);
//                    in.putExtra("password", password);
//                    in.putExtra("confirm_Password", confirm_Password);
//                    startActivity(in);
//                    finish();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//
//            }
//        });
//
//    }


    private void verifyUser() {
        progress_bar.setVisibility(View.VISIBLE);

        name = etFullName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirm_Password = et_Confirm_Password.getText().toString().trim();

        if (etFullName.getText().toString().length() == 0) {
            progress_bar.setVisibility(View.GONE);
            etFullName.setError("Please Enter Full Name");
            etFullName.requestFocus();
            return;
        }

        if (etEmail.getText().toString().length() == 0) {
            progress_bar.setVisibility(View.GONE);
            etEmail.setError("Please Enter Email");
            etEmail.requestFocus();
            return;
        }

        if (etPassword.getText().toString().length() == 0) {
            progress_bar.setVisibility(View.GONE);
            etPassword.setError("Please Enter Password");
            etEmail.requestFocus();
            return;
        }

        if (et_Confirm_Password.getText().toString().length() == 0) {
            progress_bar.setVisibility(View.GONE);
            et_Confirm_Password.setError("Please Enter Password Again");
            et_Confirm_Password.requestFocus();
            return;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<DefaultResponse> call = api.verifyUser(name, email, password, confirm_Password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if (response.body().isStatus() != true) {

                    Log.e("welcome", "welcome");
                    Toast.makeText(MainActivity.this, "Something Went Wrong..", Toast.LENGTH_SHORT).show();

                } else {
                    DefaultResponse defaultResponse = response.body();

                    progress_bar.setVisibility(View.GONE);
//                checkIfEmailVerified();
                    Toast.makeText(MainActivity.this, "" + defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(MainActivity.this,Account_verification.class);
                    startActivity(in);
                    finish();
                } /*else {

                    Toast.makeText(Login_Activity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }*/
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }


    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


}
