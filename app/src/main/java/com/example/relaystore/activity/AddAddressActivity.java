package com.example.relaystore.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.relaystore.Choose_payment_method;
import com.example.relaystore.MainActivity;
import com.example.relaystore.R;
import com.example.relaystore.Session.SessionManager;
import com.example.relaystore.test_model.UserAddress;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class AddAddressActivity extends AppCompatActivity {



    TextInputLayout textInputLayoutName,textInputLayoutPhone,textInputLayoutEmail,textInputLayoutAddress;
    TextInputEditText editTextName, editTextPhone,editTextEmail,editTextAddress;

    String name,email,phone,address,completeAddress, nic;

    TextView txt_latitude, txt_longitude, txt_address_loc;
    ImageButton btn_get_location;
    Button btn_Done;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    Double x, y;

    Geocoder geocoder;
    List<Address> addresses;

    SessionManager session;

   ;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent in = getIntent();
        nic = in.getStringExtra("nic");



        session = new SessionManager(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutAddress = findViewById(R.id.textInputLayoutAddress);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);


        btn_get_location = findViewById(R.id.btn_get_location);
        btn_Done = findViewById(R.id.btn_Done);



        // For Name


        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                name = editable.toString();
                Log.e("new Name", name);

            }
        });

        // For Phone Number

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                phone = editable.toString();
                Log.e("new Phone", phone);

            }
        });


        // For Email

        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                email = editable.toString();
                Log.e("new Email", email);

            }
        });

        // for address


//        editTextAddress.setText(completeAddress);



        editTextAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//            completeAddress = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                completeAddress = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {


                completeAddress = editable.toString();
               Log.e("completeAddress",completeAddress);
//                Log.e("new Address", address);

            }
        });


        btn_get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();

            }
        });




        btn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savedata();

                Intent in = new Intent(AddAddressActivity.this, Choose_payment_method.class);
                in.putExtra("name",name);
                in.putExtra("email",email);
                in.putExtra("phone",phone);
                in.putExtra("address",completeAddress);

                startActivity(in);
            }
        });



    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
//                                    txt_latitude.setText(location.getLatitude() + "");
//                                    txt_longitude.setText(location.getLongitude() + "");
                                    x = location.getLatitude();
                                    y = location.getLongitude();

                                    try {
                                        geocoder = new Geocoder(AddAddressActivity.this, Locale.ENGLISH);
                                        addresses = geocoder.getFromLocation(x, y, 1);
                                        StringBuilder str = new StringBuilder();
                                        if (geocoder.isPresent()) {
                                            /*Toast.makeText(getApplicationContext(),
                                                    "geocoder present", Toast.LENGTH_SHORT).show();*/
                                            Address returnAddress = addresses.get(0);
                                             completeAddress = addresses.get(0).getAddressLine(0);

                                            Log.e("address", completeAddress);

//                                            String a = returnAddress.getAddressLine(1);
//                                            String b = returnAddress.getPremises();
//                                            Log.e("address a", a);
//                                            Log.e("address b ", b);
                                            String localityString = returnAddress.getLocality();
                                            String city = returnAddress.getCountryName();
                                            String region_code = returnAddress.getCountryCode();
                                            String zipcode = returnAddress.getPostalCode();
                                            Log.e("locality", localityString);
                                            Log.e("city", city);
                                            Log.e("region_code", region_code);
                                            Log.e("zipcode", zipcode);


                                            str.append(localityString + "");
                                            str.append(city + "" + region_code + "");
                                            str.append(zipcode + "");

                                            editTextAddress.setText(completeAddress);
//                                            Toast.makeText(getApplicationContext(), str,
//                                                    Toast.LENGTH_SHORT).show();

                                        } else {
                                            /*Toast.makeText(getApplicationContext(),
                                                    "geocoder not present", Toast.LENGTH_SHORT).show();*/
                                        }

// } else {
// Toast.makeText(getApplicationContext(),
// "address not available", Toast.LENGTH_SHORT).show();
// }
                                    } catch (IOException e) {
// TODO Auto-generated catch block

                                        Log.e("tag", e.getMessage());
                                    }
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
//            txt_latitude.setText(mLastLocation.getLatitude() + "");
//            txt_longitude.setText(mLastLocation.getLongitude() + "");

        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    /*@Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }
*/

    public void savedata(){

        session.createUserSession(nic,name,phone,email,completeAddress);


        HashMap<String , HashMap<String,String> > temp= new HashMap<>();



        // get user data from session
        HashMap<String, String> user = session.getDetails();

        // name
        String nic = user.get(SessionManager.KEY_NIC);

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);

        // phone
        String phone = user.get(SessionManager.KEY_PHONE);

        // address
        String address = user.get(SessionManager.KEY_ADDRESS);

        temp.put(nic,user);

        Log.e("Storedname",""+name);
        Log.e("Storedemail",""+email);
        Log.e("Storedphone",""+phone);
        Log.e("Storedaddress",""+address);

        UserAddress userAddress = new UserAddress(name,phone,email,address,String.valueOf(x),String.valueOf(y));
        String json=session.getValue("addressMap");
        if(json.equals(""))
        {
            HashMap<String,UserAddress> userAddressHashMap = new HashMap<>();
            userAddressHashMap.put(nic,userAddress);
            session.insertToSP(userAddressHashMap);
        }
        else {
            HashMap<String,UserAddress> userAddressHashMap = session.readFromSP();
            userAddressHashMap.put(nic,userAddress);
            session.insertToSP(userAddressHashMap);
        }


//        Log.e("temp Data",""+temp);








    }

}
