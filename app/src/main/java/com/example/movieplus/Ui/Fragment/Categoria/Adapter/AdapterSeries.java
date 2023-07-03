package com.example.movieplus.Ui.Fragment.Categoria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSeries  extends RecyclerView.Adapter<AdapterSeries.ViewHolderSeries> {

    private List<Movie> movieList;
    private Context context;
    final AdapterSeries.OnClickListener listener;
    public interface OnClickListener{
        void onItemClick(Movie movie);
    }

    public AdapterSeries(List<Movie> movieList, Context context, OnClickListener listener) {
        this.movieList = movieList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderSeries onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_container_two, parent, false);
        return new ViewHolderSeries(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSeries holder, int position) {
        holder.binData(movieList.get(position));
        Picasso.get().load(movieList.get(position).getPortada()).into(holder.portada);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolderSeries extends RecyclerView.ViewHolder {
        private ImageView portada;
        public ViewHolderSeries(@NonNull View itemView) {
            super(itemView);
            portada = itemView.findViewById(R.id.iv_portada_2);
        }
        void binData(final Movie movie){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });

        }
    }
}
