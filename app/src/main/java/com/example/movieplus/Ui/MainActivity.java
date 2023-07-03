package com.example.movieplus.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieplus.Ui.F_Search.FragmentSearch;
import com.example.movieplus.Ui.Fragment.Categoria.FragmentAccion;
import com.example.movieplus.Ui.Fragment.Categoria.FragmentSeries;
import com.example.movieplus.Ui.Fragment.Categoria.FragmentTerror;
import com.example.movieplus.Ui.Fragment.F_Home.AdapterOne;
import com.example.movieplus.Interfaces.ApiClient;
import com.example.movieplus.Interfaces.ApiMovie;
import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;

import com.example.movieplus.Ui.Fragment.F_Estrenos.FragmentEstreno;
import com.example.movieplus.Ui.Fragment.F_Home.FragmentHome;

import com.example.movieplus.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
private ActivityMainBinding binding;
private FirebaseUser user;
private FirebaseAuth mAuth;

private String person  = "by person name";

private List<Movie> movie;
private AdapterOne adapter;
private int selectTabNuber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
             binding.textViewUsername.setText("Hola, "+user.getEmail());
        }
     //   getSupportFragmentManager().beginTransaction().replace(R.id.drawerLayout,new FragmentHome()).commit();

        binding.imageUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        // binding.resultsRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        //binding.resultsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //binding.resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        replaceFragment(new FragmentHome());
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_Home:
                        binding.relativeLayout3.setVisibility(View.VISIBLE);
                        binding.textViewUsername.setVisibility(View.VISIBLE);
                        binding.imageUsername.setVisibility(View.VISIBLE);
                        replaceFragment(new FragmentHome());
                        break;
                    case R.id.menu_Estreno:
                        binding.relativeLayout3.setVisibility(View.GONE);
                        binding.textViewUsername.setVisibility(View.GONE);
                        binding.imageUsername.setVisibility(View.GONE);
                        replaceFragment(new FragmentEstreno());
                        break;
                    case R.id.menu_Perfil:
                        binding.relativeLayout3.setVisibility(View.GONE);
                        binding.textViewUsername.setVisibility(View.GONE);
                        binding.imageUsername.setVisibility(View.GONE);
                        replaceFragment(new FragmentSearch());
                        break;
                }

                return true;
            }
        });

        binding.tabItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecTan(1);
            }
        });
        binding.tabItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecTan(2);
            }
        });
        binding.tabItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecTan(3  );
            }
        });
        binding.tabItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecTan(4);
            }
        });


        //showMoview();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Secion")
                .setMessage("¿Desea cerrar secion?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acción a realizar cuando se presiona el botón Aceptar
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acción a realizar cuando se presiona el botón Cancelar
                    }
                })
                .show();
    }

    private void selecTan(int tabNumero){
        TextView selectedTextView;
        TextView nonSelectedTextView1;
        TextView nonSelectedTextView2;
        TextView nonSelectedTextView3;
        if (tabNumero==1){
            selectedTextView = binding.tabItem1;

            nonSelectedTextView1 = binding.tabItem2;
            nonSelectedTextView2 = binding.tabItem3;
            nonSelectedTextView3 = binding.tabItem4;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.frameLayout, FragmentHome.class, null)
                    .commit();
        }
        else if ( tabNumero ==2){
            selectedTextView = binding.tabItem2;

            nonSelectedTextView1 = binding.tabItem1;
            nonSelectedTextView2 = binding.tabItem3;
            nonSelectedTextView3 = binding.tabItem4;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.frameLayout, FragmentTerror.class, null)
                    .commit();
        }else if(tabNumero ==3){
            selectedTextView = binding.tabItem3;

            nonSelectedTextView1 = binding.tabItem1;
            nonSelectedTextView2 = binding.tabItem2;
            nonSelectedTextView3 = binding.tabItem4;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.frameLayout, FragmentSeries.class, null)
                    .commit();
        }
        else {
            selectedTextView = binding.tabItem4;

            nonSelectedTextView1 = binding.tabItem1;
            nonSelectedTextView2 = binding.tabItem2;
            nonSelectedTextView3 = binding.tabItem3;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.frameLayout, FragmentAccion.class, null)
                    .commit();
        }
        float slideTo = (tabNumero - selectTabNuber)* selectedTextView.getWidth();
        TranslateAnimation translateAnimation = new TranslateAnimation(0,slideTo,0,0);
        translateAnimation.setDuration(100);
        if (selectTabNuber ==1){
            binding.tabItem1.startAnimation(translateAnimation);
        }
        else if (selectTabNuber ==2){
            binding.tabItem2.startAnimation(translateAnimation);
        }else if (selectTabNuber ==3){
            binding.tabItem3.startAnimation(translateAnimation);
        }
        else {
            binding.tabItem4.startAnimation(translateAnimation);
        }
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                selectedTextView.setBackgroundResource(R.drawable.round_back_white_100);
                selectedTextView.setTypeface(null, Typeface.BOLD);
                selectedTextView.setTextColor(Color.BLACK);

                nonSelectedTextView1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView1.setTextColor((Color.parseColor("#FF000000")));
                nonSelectedTextView1.setTypeface(null,Typeface.NORMAL);

                nonSelectedTextView2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView2.setTextColor((Color.parseColor("#FF000000")));
                nonSelectedTextView2.setTypeface(null,Typeface.NORMAL);

                nonSelectedTextView3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView3.setTextColor((Color.parseColor("#FF000000")));
                nonSelectedTextView3.setTypeface(null,Typeface.NORMAL);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        selectTabNuber = tabNumero;
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }


    private void showMoview() {
        Call<List<Movie>> call = ApiClient.getClient().create(ApiMovie.class).getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movie = response.body();
                  //  adapter  = new AdapterOne(movie,getApplicationContext());
                    //binding.resultsRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Errpr",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        //Set the position of spinner in offline to retrieve at start

    //   Paper.book().write("position", binding.sourceSpinner.getSelectedItemPosition());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}