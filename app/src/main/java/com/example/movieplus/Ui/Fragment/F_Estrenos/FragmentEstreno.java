package com.example.movieplus.Ui.Fragment.F_Estrenos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieplus.Interfaces.ApiClient;
import com.example.movieplus.Interfaces.ApiMovie;
import com.example.movieplus.Model.Movie;
import com.example.movieplus.databinding.FragmentEstrenoBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentEstreno extends Fragment {
private FragmentEstrenoBinding binding;


private List<Movie> movie;
private AdapterVideo adapterVideo;

    public FragmentEstreno() {

    }


    public static FragmentEstreno newInstance(String param1, String param2) {
        FragmentEstreno fragment = new FragmentEstreno();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstrenoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

     //   binding.recyclerViewView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        getVideoMovie();
        return view;
    }

    private void getVideoMovie() {
        Call<List<Movie>> listCall = ApiClient.getClient().create(ApiMovie.class).getVideoMovies();
        listCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movie = response.body();
                    adapterVideo = new AdapterVideo( movie,getContext());
                    binding.viewPage.setAdapter(adapterVideo);
                    //binding.recyclerViewView.setAdapter(adapterVideo);


                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getContext(),"Errpr",Toast.LENGTH_LONG).show();
            }
        });
    }
}