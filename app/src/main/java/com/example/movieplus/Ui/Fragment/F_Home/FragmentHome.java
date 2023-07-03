package com.example.movieplus.Ui.Fragment.F_Home;

import android.content.Intent;
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
import com.example.movieplus.Ui.ViewActivity;
import com.example.movieplus.databinding.FragmentHomeBinding;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment {
    private FragmentHomeBinding binding;
    private List<Movie> movie;
    private AdapterOne adapter;
    private AdapterTwo adapterTwo;
    private int seletTanNumero;

    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

       binding.resultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerViewTwo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));




        showMoview();
        SeconAdapter();
        return view;
    }

    private void selectTab(){

    }

    private void SeconAdapter() {
        Call<List<Movie>> listCall = ApiClient.getClient().create(ApiMovie.class).getMasMovies();
        listCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movie = response.body();
                    adapterTwo = new AdapterTwo(movie, getContext(), new AdapterTwo.OnClickListener() {
                        @Override
                        public void onItemClick(Movie item) {
                            moveToDescripsion(item);
                        }
                    });
                   binding.recyclerViewTwo.setAdapter(adapterTwo);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getContext(),"Errpr",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showMoview() {
        Call<List<Movie>> call = ApiClient.getClient().create(ApiMovie.class).getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movie = response.body();
                    adapter  = new AdapterOne(movie, getContext(), new AdapterOne.OnClickListener() {
                        @Override
                        public void onItemClick(Movie item) {
                            moveToDescripsion(item);
                        }

                    });
                   binding.resultsRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getContext(),"Errpr",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void moveToDescripsion(Movie item) {
        Intent intent = new Intent(getActivity() , ViewActivity.class);
        intent.putExtra("ListElement",item);
        startActivity(intent);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}