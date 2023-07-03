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

public class AdapterAccion extends RecyclerView.Adapter<AdapterAccion.ViewHolderAccion> {
   private List<Movie> movies;
   private Context context;
    final AdapterAccion.OnClickListener listener;
    public interface OnClickListener{
        void onItemClick(Movie movie);
    }

    public AdapterAccion(List<Movie> movies, Context context, OnClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderAccion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_two, parent, false);
        return new ViewHolderAccion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAccion holder, int position) {
        holder.binData(movies.get(position));
        Picasso.get().load(movies.get(position).getPortada()).into(holder.portada);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderAccion extends RecyclerView.ViewHolder{
        private ImageView portada;
        public ViewHolderAccion(@NonNull View itemView) {
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
