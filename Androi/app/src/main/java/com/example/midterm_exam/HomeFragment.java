package com.example.midterm_exam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midterm_exam.mapper.CategoryMapper;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.Category;
import com.example.midterm_exam.model.CategoryResponse;
import com.example.midterm_exam.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcCate;
    private GridView gridView;
    private CategoryAdapter categoryAdapter;
    private ProductGridAdapter productAdapter;
    private List<Category> categoryList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ RecyclerView & GridView
        rcCate = view.findViewById(R.id.rc_category);
        gridView = view.findViewById(R.id.gridview1); // Đảm bảo ID đúng với XML

        setupRecyclerView();
        setupGridView();

        fetchAllCategory();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GetLastProduct();
    }

    private void setupRecyclerView() {
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        rcCate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcCate.setHasFixedSize(true);
        rcCate.setAdapter(categoryAdapter);
    }

    private void setupGridView() {
        if (gridView != null) { // Kiểm tra tránh null
            productAdapter = new ProductGridAdapter(getContext(), categoryList);
            gridView.setAdapter(productAdapter);
        } else {
            Log.e("HomeFragment", "GridView is NULL. Check fragment_home.xml ID.");
        }
    }

    private void fetchAllCategory() {
        CategoryService categoryService = new CategoryService();
        categoryService.fetchCategoryData(new CategoryService.CategoryCallBack() {
            @Override
            public void onSuccess(ApiResponse<List<CategoryResponse>> categoryResponses) {
                if (categoryResponses != null && categoryResponses.getResult() != null) {
                    categoryList.clear();
                    categoryList = CategoryMapper.toCategoryList(categoryResponses.getResult());

                    categoryAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("HomeFragment", "Fetch Categories Failed: " + errorMessage);
            }
        });
    }

    private void fetchLastProduct() {
        CategoryService categoryService = new CategoryService();
        String username = "tienanh19";

        categoryService.fetchLastProductForUser(username, new CategoryService.CategoryCallBack() {
            @Override
            public void onSuccess(ApiResponse<List<CategoryResponse>> categoryResponses) {
                if (categoryResponses != null && categoryResponses.getResult() != null) {
                    categoryList.clear();
                    categoryList.addAll(CategoryMapper.toCategoryList(categoryResponses.getResult()));

                    if (gridView != null) {
                        productAdapter.notifyDataSetChanged(); // Cập nhật GridView
                    }
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("HomeFragment", "Fetch Last Products Failed: " + errorMessage);
            }
        });
    }
}
