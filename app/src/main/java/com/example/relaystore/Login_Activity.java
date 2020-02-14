package com.example.relaystore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relaystore.Session.SessionManager;
import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_Activity extends AppCompatActivity {

    Button btnConnection;
    TextView tvSignUp;
    TextView tvForgotPassword;
    TextInputLayout textInputLayout;
    TextInputEditText editText;
    EditText et_Email;
    EditText etPassword;

    String email, password;
    ProgressBar progress_bar;

    // Session Manager Class
    SessionManager session;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isConnected(Login_Activity.this)) buildDialog(Login_Activity.this).show();
        else {


            setContentView(R.layout.login);

            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            session = new SessionManager(this);

            btnConnection = findViewById(R.id.btn_connection);
            tvSignUp = findViewById(R.id.tv_sign_up);
            tvForgotPassword = findViewById(R.id.tv_forgot_password);
            textInputLayout = findViewById(R.id.passwordlayout);
            editText = findViewById(R.id.etpasswordinput);
            progress_bar = findViewById(R.id.progress_bar);

            et_Email = findViewById(R.id.et_Email);
//        etPassword = findViewById(R.id.et_PasWorD);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {


                    password = editable.toString();
                    Log.e("new PAsswrod", password);

                }
            });


/**
 *     Forgot Password Functionality
 */
            tvForgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Login_Activity.this, Forgot_Activity.class);
                    startActivity(intent);
//                finish();?
                }
            });


/**
 *    Sign Up Functionality
 */


            tvSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

/**
 *   Login Functionality
 */


            btnConnection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                loginUserAccount();

                    getUsers();

                }
            });

        }
    }


    // TODO LOGIN USER

    private void getUsers() {

        progress_bar.setVisibility(View.VISIBLE);


        email = et_Email.getText().toString();

        if (TextUtils.isEmpty(email)){
            et_Email.setError("Please Enter Email");
            et_Email.requestFocus();
//            Toast.makeText(this, "Nahi jane dunga", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password First..", Toast.LENGTH_SHORT).show();
            return;
        }

/*

        Log.e("email", email);
        Log.e("password", password);
*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<DefaultResponse> call = api.userLogin(email, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if (response.body().isStatus() != true) {

                    Log.e("welcome", "welcome");
                    progress_bar.setVisibility(View.GONE);
                    Toast.makeText(Login_Activity.this, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    progress_bar.setVisibility(View.GONE);
                    DefaultResponse defaultResponse = response.body();


//                checkIfEmailVerified();
                    Toast.makeText(Login_Activity.this, "" + defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    session.createLoginSession(email,password);
                    Intent in = new Intent(Login_Activity.this, Order_list_Activity.class);
                    in.putExtra("email", email);
                    in.putExtra("pass", password);
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

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setCancelable(false);
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Login_Activity.this,Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        return builder;
    }


}
