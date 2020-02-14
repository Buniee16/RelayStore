package com.example.relaystore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relaystore.Session.SessionManager;
import com.example.relaystore.activity.AddAddressActivity;
import com.example.relaystore.test_model.UserAddress;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Choose_payment_method extends AppCompatActivity {

    Button btn_Perform_payment;

    TextView txt_add_address, txt_add_new, txt_email, txt_number, txt_name;
    String name, email, phone, address;
    SharedPreferences prefs;
    Gson gson;
    String nic;

    // Session Manager Class
    SessionManager session;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_payment_method);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        session = new SessionManager(this);

        txt_name = findViewById(R.id.txt_name);
        txt_add_address = findViewById(R.id.txt_add_address);
        txt_add_new = findViewById(R.id.txt_add_new);
        txt_email = findViewById(R.id.txt_email);
        txt_number = findViewById(R.id.txt_number);

        Intent in = getIntent();
        nic = in.getStringExtra("nic");
        name = in.getStringExtra("name");
        phone = in.getStringExtra("phone");
        email = in.getStringExtra("email");
        address = in.getStringExtra("address");


        Gson gson = new Gson();
        SharedPreferences prefs = getSharedPreferences("test", MODE_PRIVATE);



        //session.readFromSP();
        //String s = session.readFromSP().values().toString();
        String json=session.getValue("addressMap");
        if(!json.equals(""))
        {
            HashMap<String,UserAddress> userAddressHashMap = session.readFromSP();
          //  Log.e("nic",nic);


                UserAddress userAddress = userAddressHashMap.get(nic);
            if(userAddress != null)
            {
            //    Log.e("nic////",nic);
                txt_name.setText(userAddress.getName());
                txt_add_new.setText(userAddress.getAddress());
                txt_email.setText(userAddress.getEmail());
                txt_number.setText(userAddress.getPhone());
            }
        }


       /* for(String key : session.readFromSP().keySet())
        {
            Log.e("asdasdasdasda",key);
        }
*/




       /* // get user data from session
        HashMap<String, String> user = session.getDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);

        // phone
        String phone = user.get(SessionManager.KEY_PHONE);

        // address
        String address = user.get(SessionManager.KEY_ADDRESS);


        Log.e("Storedname",""+name);
        Log.e("Storedemail",""+email);
        Log.e("Storedphone",""+phone);
        Log.e("Storedaddress",""+address);
*/


        txt_name = findViewById(R.id.txt_name);
        txt_number = findViewById(R.id.txt_number);
        txt_email = findViewById(R.id.txt_email);
        txt_add_new = findViewById(R.id.txt_add_new);


        btn_Perform_payment = findViewById(R.id.btn_Perform_payment);
        txt_add_address = findViewById(R.id.txt_add_address);
        btn_Perform_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Choose_payment_method.this, Order_Finalisation_Activity.class);
                startActivity(intent);

            }
        });

        txt_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Choose_payment_method.this, AddAddressActivity.class);
                in.putExtra("nic", nic);
                startActivity(in);
            }
        });


       /* Intent in = getIntent();
        name = in.getStringExtra("name");
        phone = in.getStringExtra("phone");
        email = in.getStringExtra("email");
        address = in.getStringExtra("address");
*/
//        txt_name.setText(name);
//        txt_number.setText(phone);
//        txt_email.setText(email);
//        txt_add_new.setText(address);
    }



}
