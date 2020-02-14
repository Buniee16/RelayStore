package com.example.relaystore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.relaystore.api.Api;
import com.example.relaystore.api.DefaultResponse;
import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.UserOrderListDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>  {

    private List<UserOrderListDetails.ProductAvlShop> moviesList;

    private OnItemClickListener mListener;

    int minteger;
    Context context;
    String total,numid;
    String value;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, amount,integer_number;
        public Button increase,decrease;
        public View mainView;
        public ImageView img_product;
        Context context;

        public MyViewHolder(View view) {
            super(view);

            title =  view.findViewById(R.id.title);
            amount =  view.findViewById(R.id.amount);
            img_product =  view.findViewById(R.id.img_product);

            decrease =  view.findViewById(R.id.decrease);
            increase =  view.findViewById(R.id.increase);
            integer_number =  view.findViewById(R.id.integer_number);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPriceSelected(moviesList.get(getAdapterPosition()));
                }
            });
            mainView = view;*/
        }
    }


    public MoviesAdapter(List<UserOrderListDetails.ProductAvlShop> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
//                .inflate(R.layout.test, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        final UserOrderListDetails.ProductAvlShop productAvlShop = moviesList.get(position);


        holder.title.setText(productAvlShop.getLibelle_TAG());
        holder.integer_number.setText(productAvlShop.getQuantite());
        holder.amount.setText("$"+productAvlShop.getPrix_Unitaire()+" / Item");

        numid = productAvlShop.getNumId();
        total = productAvlShop.getPrix_Unitaire();

        Log.e("numiDada",""+numid);
        Log.e("Totalada",""+total);


        // for increase and decrease

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 0;
                try{
//                    val = val + 1;
                    val = Integer.parseInt(holder.integer_number.getText().toString());
                }catch(Exception e) {
                    val = 0;
                }
                val++;
                holder.integer_number.setText (String.valueOf (val));
                value = holder.integer_number.getText().toString();
                update(numid,value,total);
            }
        });



        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 0;
                try{

                    val = Integer.parseInt(holder.integer_number.getText().toString());
                }catch(Exception e) {
                    val = 0;
                }
                if(val>0)
                    val--;
                holder.integer_number.setText (String.valueOf (val));
                Log.e("negative valueeeeee",""+val);

                value = holder.integer_number.getText().toString();
                Log.e("negative new",""+value);
                update(numid,value,total);
                Log.e("negative value",""+val);
            }
        });

        Picasso.with(context).load(productAvlShop.getProduct_image()).into(holder.img_product);

    }

    private void update(final String numid, final String value, final String total) {


        Log.e("numid",""+numid);
        Log.e("value",""+value);
        Log.e("total",""+total);


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

        Call<DefaultResponse> call = api.updateProduct(numid, value , total);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse defaultResponse = response.body();


                if (response.isSuccessful()) {
                    String message = defaultResponse.getMsg();
                    Log.e("qty",""+value);
                    Log.e("))))))", message);

                }


            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.e("Failure",""+t.getMessage());
//                Toast.makeText(getApplicationContex, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });










    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }




    public interface AdapterListener {
        void onPriceSelected(ProductAvlShop priceList);
    }

}