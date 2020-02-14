package com.example.relaystore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.relaystore.R;
import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.SearchOrder;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>  {

    private List<SearchOrder> moviesList;

    private SearchAdapter.OnItemClickListener mListener;

    Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(SearchAdapter.OnItemClickListener listener) {
        mListener = listener;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public View mainView;
        Context context;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            genre =  view.findViewById(R.id.genre);
            year =  view.findViewById(R.id.year);

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


    public SearchAdapter(List<SearchOrder> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row_two, parent, false);

        return new SearchAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.MyViewHolder holder, int position) {


        final SearchOrder searchOrder = moviesList.get(position);
        holder.title.setText(searchOrder.getNIC_Handle());
        holder.genre.setText(searchOrder.getNumId());
        holder.year.setText(searchOrder.getMSISDN());


    }

    @Override
    public int getItemCount() {

        if(moviesList.size() == 0){
            return 1;
        }else {
            return moviesList.size();
        }

//        return moviesList.size();
    }


    public void filterList( List<SearchOrder> filterdNames) {
       // moviesList.clear();
        this.moviesList = filterdNames;
        notifyDataSetChanged();
    }


   /* public interface AdapterListener {
        void onPriceSelected(ProductAvlShop priceList);
    }*/

}