package com.example.movieplus.Ui.F_Search;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.movieplus.Interfaces.ApiClient;
import com.example.movieplus.Interfaces.ApiMovie;
import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;
import com.example.movieplus.Ui.Fragment.Categoria.Adapter.AdapterAccion;
import com.example.movieplus.Ui.ViewActivity;
import com.example.movieplus.databinding.FragmentSearchBinding;
import com.example.movieplus.databinding.FragmentSeriesBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment {
    private FragmentSearchBinding binding;
    private AdapterSearch adapterSearch;
    private List<Movie> movies;
    private SearchView searchView;


    public FragmentSearch() {
        // Required empty public constructor
    }

    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
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
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.searchView2.clearFocus();
        searchViewClass();

        binding.recyclerViewBusqueda.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter();
        return view;
    }

    private void searchViewClass() {
        binding.searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String newText) {
        List<Movie> filteredList = new ArrayList<>();
        for (Movie item: movies){
            if (item.getTitulo().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(),"", Toast.LENGTH_LONG).show();
        }else{
            adapterSearch.setFilteredList(filteredList);
        }
    }

    private void adapter() {
        Call<List<Movie>> listCall = ApiClient.getClient().create(ApiMovie.class).getSearch();
        listCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    movies = response.body();
                    adapterSearch = new AdapterSearch(movies, getContext(), new AdapterAccion.OnClickListener() {
                        @Override
                        public void onItemClick(Movie movie) {
                            moveToDescrip(movie);
                        }
                    });
                    binding.recyclerViewBusqueda.setAdapter(adapterSearch);
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