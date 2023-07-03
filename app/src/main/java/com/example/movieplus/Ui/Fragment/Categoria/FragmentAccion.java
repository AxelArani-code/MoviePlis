package com.example.movieplus.Ui.Fragment.Categoria;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieplus.Interfaces.ApiClient;
import com.example.movieplus.Interfaces.ApiMovie;
import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;
import com.example.movieplus.Ui.Fragment.Categoria.Adapter.AdapterAccion;
import com.example.movieplus.Ui.ViewActivity;
import com.example.movieplus.databinding.FragmentAccionBinding;
import com.example.movieplus.databinding.FragmentSeriesBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccion extends Fragment {

private FragmentAccionBinding binding;
private AdapterAccion adapterAccion;
private List<Movie> movies;
    public FragmentAccion() {
        // Required empty public constructor
    }

    public static FragmentAccion newInstance(String param1, String param2) {
        FragmentAccion fragment = new FragmentAccion();
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
        // Inflate the layout for this fragment
        binding = FragmentAccionBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.recyclerViewAccion.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter();
        return view;
    }

    private void adapter() {
        Call<List<Movie>> listCall = ApiClient.getClient().create(ApiMovie.class).getAccion();
        listCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movies = response.body();
                    adapterAccion = new AdapterAccion(movies, getContext(), new AdapterAccion.OnClickListener() {
                        @Override
                        public void onItemClick(Movie movie) {
                            moveToDescrip(movie);
                        }
                    });
                    binding.recyclerViewAccion.setAdapter(adapterAccion);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }

    private void moveToDescrip(Movie item) {
        Intent intent = new Intent(getActivity(), ViewActivity.class);
        intent.putExtra("ListElement",item);
        startActivity(intent);
    }
}