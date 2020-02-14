package com.example.relaystore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.relaystore.MoviesAdapter;
import com.example.relaystore.R;
import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.UserOrderListDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdaptertwo  extends RecyclerView.Adapter<MoviesAdaptertwo.MyViewHolder>  {

    private List<UserOrderListDetails.ProductAvlShop> moviesList;

    private MoviesAdaptertwo.OnItemClickListener mListener;

    Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(MoviesAdaptertwo.OnItemClickListener listener) {
        mListener = listener;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public ImageView img_product;
        public View mainView;
        Context context;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            genre =  view.findViewById(R.id.genre);
            year =  view.findViewById(R.id.year);
            img_product =  view.findViewById(R.id.img_product);

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


    public MoviesAdaptertwo(List<UserOrderListDetails.ProductAvlShop> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MoviesAdaptertwo.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row_product, parent, false);

        return new MoviesAdaptertwo.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesAdaptertwo.MyViewHolder holder, int position) {


        final UserOrderListDetails.ProductAvlShop productAvlShop = moviesList.get(position);
        holder.title.setText(productAvlShop.getLibelle_TAG());
        holder.genre.setText(productAvlShop.getQuantite());
        holder.year.setText("$"+ productAvlShop.getTotal()+" / Item");

        Picasso.with(context).load(productAvlShop.getProduct_image()).into(holder.img_product);

        Log.e("image",""+productAvlShop.getProduct_image());



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }




    public interface AdapterListener {
        void onPriceSelected(ProductAvlShop priceList);
    }

}