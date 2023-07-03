package com.example.movieplus.Ui.Fragment.F_Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterOne extends RecyclerView.Adapter<AdapterOne.ViewHolder> {
    private List<Movie> movies;
    private Context context;
    final  AdapterOne.OnClickListener listener;
    public interface OnClickListener {
        void onItemClick(Movie item);
    }
    public AdapterOne(List<Movie> movies, Context context, AdapterOne.OnClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterOne.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cotainer,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOne.ViewHolder holder, int position) {
//        holder.tv_titulo.setText(movies.get(position).getTitulo());
    //    holder.textName.setText(movies.get(position).getTitulo());
      //  holder.textStarRating.setText(movies.get(position).getEstrella());
        Picasso.get().load(movies.get(position).getPortada()).into(holder.iv_portada);
        holder.binData(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    public class ViewHolder  extends  RecyclerView.ViewHolder{
     private KenBurnsView iv_portada;
        //  private ImageView iv_portada;
        private TextView textName, textStarRating;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_portada = itemView.findViewById(R.id.iv_portada_2);
            textName = itemView.findViewById(R.id.textName);
            textStarRating = itemView.findViewById(R.id.textStarRating);
        }
        void binData(final Movie movie){
             textName.setText(movie.getTitulo());
             textStarRating.setText(movie.getEstrella());
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onItemClick(movie);
                 }
             });
        }
    }
}
