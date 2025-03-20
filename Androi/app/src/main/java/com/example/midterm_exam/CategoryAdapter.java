package com.example.midterm_exam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.midterm_exam.config.PrefManager;
import com.example.midterm_exam.mapper.CategoryMapper;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.Category;
import com.example.midterm_exam.model.CategoryResponse;
import com.example.midterm_exam.model.GetCategoryRequest;
import com.example.midterm_exam.service.CategoryService;

import java.util.List;

// Phạm Tiến Anh - 22110282
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<Category> categoryList;
    private OnProductAddedListener listener;

    public interface OnProductAddedListener {
        void onProductAdded();
    }

    public CategoryAdapter(Context context, List<Category> categoryList, OnProductAddedListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    private void addLastProduct (String categoryId) {

        CategoryService categoryService = new CategoryService();

        String username = new PrefManager(context).getEmail();
        GetCategoryRequest request = new GetCategoryRequest(username,categoryId);

        categoryService.addCategoryForUser(request,new CategoryService.CallBack() {
            @Override
            public void onSuccess(Boolean result) {
                Log.d("Category adapter","add last product sucess");
                if (listener != null) {
                    listener.onProductAdded(); // Gọi để cập nhật GridView khác
                }
                Toast.makeText(context, "add last product sucess!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("Category adapter",errorMessage);
                Toast.makeText(context, "Lỗi khi thêm sản phẩm: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tvCategoryName.setText(category.getName());

        Glide.with(context)
                .load(category.getImages())
                .placeholder(R.drawable.ic_1) // Ảnh mặc định khi tải
                .error(R.drawable.ic_2) // Ảnh khi lỗi
                .into(holder.ivCategoryImage);

        holder.itemView.setOnClickListener(v -> {
                    // gọi api cho category
                    addLastProduct(category.getId());
                }
        );
    }

    @Override
    public int getItemCount() {
        return (categoryList != null) ? categoryList.size() : 0;
    }

    public void updateData(List<Category> newCategories) {
        this.categoryList = newCategories;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCategoryImage;
        private TextView tvCategoryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryImage = itemView.findViewById(R.id.image_cate);
            tvCategoryName = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}