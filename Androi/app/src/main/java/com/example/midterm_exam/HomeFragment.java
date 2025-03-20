package com.example.midterm_exam;

import android.os.Bundle;
import android.os.Handler;
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
import com.example.midterm_exam.service.ApiService;
import com.example.midterm_exam.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    //phạm tiến anh - nguyễn hoàng thùy linh - hoàng thị mỹ linh

    private RecyclerView rcCate;
    private GridView gridView;
    private CategoryAdapter categoryAdapter;
    private ProductGridAdapter productAdapter; // Adapter riêng cho GridView
    private ApiService apiService;
    private List<Category> categoryList = new ArrayList<>();
    private List<Category> lastProduct = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcCate = view.findViewById(R.id.rc_category);
        gridView = view.findViewById(R.id.gridview1);

        setupRecyclerView();
        setupGridView();

        fetchAllCategory();
        fetchLastProduct();

        return view;
    }


    private void setupRecyclerView() {
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        rcCate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcCate.setHasFixedSize(true);
        rcCate.setAdapter(categoryAdapter);
        autoScrollRecyclerView(); // Bắt đầu tự động cuộn
    }

    private void setupGridView() {
        productAdapter = new ProductGridAdapter(getContext(), lastProduct);
        gridView.setAdapter(productAdapter);
    }


    private void fetchAllCategory () {
        CategoryService categoryService = new CategoryService();

        categoryService.fetchCategoryData(new CategoryService.CategoryCallBack() {
            @Override
            public void onSuccess(ApiResponse<List<CategoryResponse>> categorysResponses) {
                List<CategoryResponse> categoryResponseList = categorysResponses.getResult();
                categoryList.clear(); // Xóa dữ liệu cũ
                categoryList.addAll(CategoryMapper.toCategoryList(categoryResponseList)); // Thêm dữ liệu mới
                categoryAdapter.notifyDataSetChanged();
                productAdapter.notifyDataSetChanged(); // Cập nhật GridView
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("MainActivity", errorMessage);
            }
        });
    }

    private void fetchLastProduct () {

        CategoryService categoryService = new CategoryService();
        CategoryMapper mapper = new CategoryMapper();

        String username = "admin";

        categoryService.fetchLastProductForUser(username,new CategoryService.CategoryCallBack() {
            @Override
            public void onSuccess(ApiResponse<List<CategoryResponse>> categorysResponses) {
                List<CategoryResponse> categoryResponseList = categorysResponses.getResult();
                lastProduct.clear(); // Xóa dữ liệu cũ
                lastProduct.addAll(CategoryMapper.toCategoryList(categoryResponseList)); // Thêm dữ liệu mới
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("MainActivity", errorMessage);
            }
        });
    }



    // hiệu ứng cuộn
    private int currentPosition = 0;
    private final long DELAY_MS = 1000; // 2 giây
    private final long PERIOD_MS = 1000; // 3 giây
    private Handler handler = new Handler();
    private Runnable runnable;

    private void autoScrollRecyclerView() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (categoryAdapter != null && categoryAdapter.getItemCount() > 0) {
                    currentPosition++;
                    if (currentPosition >= categoryAdapter.getItemCount()) {
                        currentPosition = 0; // Quay về đầu khi đến cuối
                    }
                    rcCate.smoothScrollToPosition(currentPosition);
                }
                handler.postDelayed(this, PERIOD_MS);
            }
        };

        handler.postDelayed(runnable, DELAY_MS);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
