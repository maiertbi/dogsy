package com.dogsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dogsy.presentation.classes.ChatItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRecyclerViewAdaptor extends RecyclerView.Adapter<ChatRecyclerViewAdaptor.ViewHolder> {
    private final ArrayList<ChatItem> mData;

    public ChatRecyclerViewAdaptor(ArrayList<ChatItem> data) {
        mData = data;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_chat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatItem item = mData.get(position);

//        holder.imageView.setBackground(item.getIcon());
        Glide.with(holder.itemView)
                .load(item.getIcon())
                .circleCrop()
                .into(holder.imageView);

        holder.mainText.setText(item.getTitle());
        holder.subText.setText(item.getDesc());
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView mainText;
        TextView subText;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조
            imageView = itemView.findViewById(R.id.chat_item_imageview);
            mainText = itemView.findViewById(R.id.chat_item_textview_title);
            subText = itemView.findViewById(R.id.chat_item_textview_lastmessage);
        }
    }
}