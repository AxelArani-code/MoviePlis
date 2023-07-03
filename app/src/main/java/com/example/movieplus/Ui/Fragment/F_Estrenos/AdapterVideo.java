package com.example.movieplus.Ui.Fragment.F_Estrenos;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;

import java.util.List;

public class AdapterVideo  extends  RecyclerView.Adapter<AdapterVideo.VideoViewHolder>{

   private List<Movie> movieList;
   private Context context;

    public AdapterVideo(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class VideoViewHolder  extends RecyclerView.ViewHolder{
        ///
        VideoView videoView;
        TextView textTitulo, textDescripsion;
        ProgressBar progressBar;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textDescripsion = itemView.findViewById(R.id.textDescrp_Video);
            textTitulo = itemView.findViewById(R.id.textTitle_Video);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
        void setVideoData(Movie movie){
            textTitulo.setText(movie.getTitulo());
            textDescripsion.setText(movie.getDescripsion());
            videoView.setVideoPath(movie.getVideo());
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screeenRadio = videoView.getWidth() / (float) videoView.getWidth();

                    float scale = videoRatio / screeenRadio;
                    if (scale >=1f){
                        videoView.setScaleX(scale);
                    }else {
                        videoView.setScaleY(1f / scale);
                    }
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}
