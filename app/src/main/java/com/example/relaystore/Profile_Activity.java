package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.relaystore.api.Api;
import com.example.relaystore.test_model.UserDetails;
import com.google.gson.Gson;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_Activity extends AppCompatActivity {

    Button btnUpdate;
    EditText et_Full_Name,et_mobile,et_city,et_Locality,et_Appartment,et_postal,et_state,et_Landmark ;

    RadioGroup radioGender;
    RadioButton female,male;
    String email,pass;

//    List<Users> users = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_Full_Name = findViewById(R.id.et_Full_Name);
        et_mobile = findViewById(R.id.et_mobile);
        et_city = findViewById(R.id.et_city);
        et_Locality = findViewById(R.id.et_Locality);
        et_Appartment = findViewById(R.id.et_Appartment);
        et_postal = findViewById(R.id.et_postal);
        et_state = findViewById(R.id.et_state);
        et_Landmark = findViewById(R.id.et_Landmark);
        radioGender = findViewById(R.id.radioGender);
        female = findViewById(R.id.female);
        male = findViewById(R.id.male);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("pass");



        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                addArtist();
                Intent intent = new Intent(Profile_Activity.this,EditProfile_Activity.class);
                startActivity(intent);
            }
        });
        getUserDetails();


    }

    private void getUserDetails() {

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

        Call<UserDetails> call = api.getUserDetails(email,pass);

        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                Log.e("=========","Success");

                Log.e("reee",""+response.body());
                UserDetails userDetails = response.body();
                Gson gson = new Gson();
                String res = gson.toJson(response.body());
                Log.e("============",res);
                if (userDetails!= null) {
                    Log.e("+++++", "" + userDetails.getId());


                    et_Full_Name.setText(userDetails.getL_name());
                    et_mobile.setText(userDetails.getM_number());
                    et_city.setText(userDetails.getCity());
                    et_Locality.setText(userDetails.getStreet_no());
                    et_Appartment.setText(userDetails.getName_building());
                    et_postal.setText(userDetails.getPostal_code());
                    et_state.setText(userDetails.getState());
                    et_Landmark.setText(userDetails.getLandmark());




//                LisUserDetails> heroList = response.body();


//                Log.e("res",""+heroList);



                }

               /* users = new ArrayList<>();

                users = response.body();

                Log.e("user response",""+response.message());

                //Creating an String array for the ListView
                String[] heroes = new String[users.size()];


                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < users.size(); i++) {
                    heroes[i] = users.get(i).getId();

                }*/


                //displaying the string array into listview
//                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
