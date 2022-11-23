package com.dogsy;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dogsy.presentation.classes.PhotoItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhotoRecyclerViewAdaptor extends RecyclerView.Adapter<PhotoRecyclerViewAdaptor.ViewHolder> {
    private final ArrayList<PhotoItem> mData;

    public PhotoRecyclerViewAdaptor(ArrayList<PhotoItem> data) {
        mData = data;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_photo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoItem item = mData.get(position);

//        holder.imageView.setBackground(item.getIcon());
        Glide.with(holder.itemView)
                .load(item.getIcon())
                .circleCrop()
                .into(holder.imageView);
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조
            imageView = itemView.findViewById(R.id.photo_item_imageview);
        }
    }
}