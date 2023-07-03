package com.example.movieplus.Ui.Fragment.F_Home;

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

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.ViewHolder> {
    private List<Movie> movies;
    private Context context;
    final AdapterTwo.OnClickListener listener;
    public interface OnClickListener{
        void onItemClick(Movie item);
    }

    public AdapterTwo(List<Movie> movies, Context context, AdapterTwo.OnClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_two,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTwo.ViewHolder holder, int position) {

        Picasso.get().load(movies.get(position).getPortada()).into(holder.portada);
        holder.binData(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView portada;
        public ViewHolder(@NonNull View itemView) {
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
