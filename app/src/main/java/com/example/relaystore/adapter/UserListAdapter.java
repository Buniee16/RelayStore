package com.example.relaystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.relaystore.R;
import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.UserList;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder>  {

    private List<UserList> moviesList;

    private UserListAdapter.OnItemClickListener mListener;

    Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(UserListAdapter.OnItemClickListener listener) {
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


    public UserListAdapter(List<UserList> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row_user, parent, false);

        return new UserListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.MyViewHolder holder, int position) {


        final UserList productAvlShop = moviesList.get(position);
        holder.title.setText(productAvlShop.getNIC_Handle());
        holder.genre.setText(productAvlShop.getNumId());
//        holder.year.setText(productAvlShop.getNumId());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }




    public interface AdapterListener {
        void onPriceSelected(ProductAvlShop priceList);
    }

}