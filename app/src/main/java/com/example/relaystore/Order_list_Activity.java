package com.example.relaystore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.relaystore.adapter.SearchAdapter;
import com.example.relaystore.adapter.UserListAdapter;
import com.example.relaystore.api.Api;
import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.SearchOrder;
import com.example.relaystore.test_model.UserList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Order_list_Activity extends AppCompatActivity implements UserListAdapter.AdapterListener {

    TextView txt_Num_id, tv_tool_profile, empty_view;
    EditText et_search;
    ImageButton  drawer_btn;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    RecyclerView recyclerView;
    //     ListView listViewOrders;
//    UserListAdapter mAdapter;
    SearchAdapter mAdapter;
    String email, pass;
    ProgressBar progress_bar;

    String numId, date, total_amount, nic;

    Button btn_order;
//    List<UserList> users = new ArrayList<>();
    List<SearchOrder> usersSearch = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isConnected(Order_list_Activity.this)) buildDialog(Order_list_Activity.this).show();
        else {

            setContentView(R.layout.order_list);

            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            drawerLayout = findViewById(R.id.activity_drawer);
            recyclerView = findViewById(R.id.recycler_view);
            et_search = findViewById(R.id.et_search);
            empty_view = findViewById(R.id.empty_view);
            txt_Num_id = findViewById(R.id.txt_Num_id);
            drawer_btn = findViewById(R.id.drawer_btn);
            progress_bar = findViewById(R.id.progress_bar);


            drawer_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });





            // FOR SEARCH FILTER

            et_search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    //after the change calling the method and passing the search input
                    filter(editable.toString());
                }
            });

//        users = new ArrayList<>();
            usersSearch = new ArrayList<>();


            Intent intent = getIntent();
            email = intent.getStringExtra("email");
            pass = intent.getStringExtra("pass");

           /* tv_tool_profile = findViewById(R.id.tv_tool_profile);

            tv_tool_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(Order_list_Activity.this, Profile_Activity.class);
                    in.putExtra("email", email);
                    in.putExtra("pass", pass);
                    startActivity(in);

                }
            });*/

//       GET USER DETAILS

            getShopProducts();


            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(Order_list_Activity.this, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent(Order_list_Activity.this, Order_product_list.class);

                            SearchOrder priceListsearch = usersSearch.get(position);
                            String numid = usersSearch.get(position).getNumId();
                            String nic = priceListsearch.getNIC_Handle();
                            String order_date = priceListsearch.getD_Creation();

                            Log.e("name", numid);
                            Log.e("order_number", nic);
                            Log.e("order_date", order_date);


                            intent.putExtra("date", order_date);
                            intent.putExtra("numId", numid);
                            intent.putExtra("nic", nic);

                            startActivity(intent);

                        }
                    })
            );


            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);


        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.profile:
                Intent in = new Intent(Order_list_Activity.this, Profile_Activity.class);
//                in.putExtra("email", email);
//                in.putExtra("pass", pass);
                startActivity(in);

//                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
                return true;
                        default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }


    //  API CALLING FOR GETTING USER THAT ORDERD SOMETHING

    private void getShopProducts() {
        progress_bar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<SearchOrder>> call = api.getSelected();

        call.enqueue(new Callback<List<SearchOrder>>() {
            @Override
            public void onResponse(Call<List<SearchOrder>> call, Response<List<SearchOrder>> response) {
//                List<SearchOrder> heroList = response.body();

//                users = response.body();
                //Creating an String array for the ListView
//                String[] heroes = new String[heroList.size()];

//                users = response.body();
                progress_bar.setVisibility(View.GONE);
                usersSearch = response.body();
//                mAdapter = new UserListAdapter(users);
                mAdapter = new SearchAdapter(usersSearch);
                recyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onFailure(Call<List<SearchOrder>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void filter(String text) {
        //new array list that will hold the filtered data
        List<SearchOrder> movieList = new ArrayList<>();

        //looping through existing elements
        //if the existing elements contains the search input
        for (SearchOrder searchOrder : usersSearch) {
            // if (searchOrder.getMSISDN().contains(text))
            Log.e("filterDate:", searchOrder.getMSISDN() + " : " + text);
            String nicHandle = searchOrder.getNIC_Handle().toLowerCase();
            String txt = text.toLowerCase();
            if (nicHandle.startsWith(txt)) {
                //adding the element to filtered list
                movieList.add(searchOrder);
            }
        }

        if (movieList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            txt_Num_id.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        } else {


            recyclerView.setVisibility(View.VISIBLE);
            txt_Num_id.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);

            //calling a method of the adapter class and passing the filtered list
            mAdapter.filterList(movieList);
        }
    }

    @Override
    public void onPriceSelected(ProductAvlShop priceList) {

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
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Order_list_Activity.this,Order_list_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        return builder;
    }
}
