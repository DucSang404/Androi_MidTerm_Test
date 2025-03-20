package com.example.midterm_exam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.CategoryResponse;
import com.example.midterm_exam.service.CategoryService;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // Phạm Tiến Anh - 22110282

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    CategoryService categoryService;

    private List<CategoryResponse> categoryResponseList;
    private List<CategoryResponse> lastProductList;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        CategoryService categoryService = new CategoryService();
        fetchAllCategory();
        fetchLastProduct();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void fetchAllCategory () {
        CategoryService categoryService = new CategoryService();

        categoryService.fetchCategoryData(new CategoryService.CategoryCallBack() {
            @Override
            public void onSuccess(ApiResponse<List<CategoryResponse>> categorysResponses) {
                categoryResponseList = categorysResponses.getResult();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("MainActivity", errorMessage);
            }
        });
    }

    private void fetchLastProduct () {

        CategoryService categoryService = new CategoryService();

        String username = "tienanh19";

        categoryService.fetchLastProductForUser(username,new CategoryService.CategoryCallBack() {
            @Override
            public void onSuccess(ApiResponse<List<CategoryResponse>> categorysResponses) {
                categoryResponseList = categorysResponses.getResult();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("MainActivity", errorMessage);
            }
        });
    }


}