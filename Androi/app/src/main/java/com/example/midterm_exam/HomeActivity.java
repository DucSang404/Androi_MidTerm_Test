package com.example.midterm_exam;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.Category;
import com.example.midterm_exam.service.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rcCate;

    CategoryAdapter categoryAdapter;
    ApiService apiService;
    List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView = findViewById(R.id.menu);

        // Mặc định hiển thị HomeFragment khi mở app
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = new HomeFragment();

            loadFragment(selectedFragment);
            return true;
        });
        AnhXa();
        GetCategory();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
    private void GetCategory() {
        apiService = RetrofitClient.getRetrofit().create(ApiService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();

                    categoryAdapter = new CategoryAdapter(HomeActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()
                            , LinearLayoutManager.HORIZONTAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg", t.getMessage());

            }
        });
    }

    private void AnhXa() {
        rcCate = (RecyclerView) findViewById(R.id.rc_category);
    }
}