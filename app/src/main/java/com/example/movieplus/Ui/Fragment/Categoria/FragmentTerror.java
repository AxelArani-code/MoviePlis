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
import com.example.movieplus.Ui.Fragment.Categoria.Adapter.AdapterTerror;
import com.example.movieplus.Ui.ViewActivity;
import com.example.movieplus.databinding.FragmentEstrenoBinding;
import com.example.movieplus.databinding.FragmentSeriesBinding;
import com.example.movieplus.databinding.FragmentTerrorBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTerror#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTerror extends Fragment {

    private FragmentTerrorBinding binding;
    private AdapterTerror adapterTerror;
    private List<Movie> movies;
    public FragmentTerror() {
        // Required empty public constructor
    }


    public static FragmentTerror newInstance(String param1, String param2) {
        FragmentTerror fragment = new FragmentTerror();
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
        binding = FragmentTerrorBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.recyclerViewTerror.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter();
        return view;
    }

    private void adapter() {
        Call<List<Movie>> listCall = ApiClient.getClient().create(ApiMovie.class).getTerror();
        listCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movies = response.body();
                    adapterTerror = new AdapterTerror(movies, getContext(), new AdapterTerror.OnClickListener() {
                        @Override
                        public void onItemClick(Movie movie) {
                            moveToDescrip(movie);
                        }
                    });
                    binding.recyclerViewTerror.setAdapter(adapterTerror);
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