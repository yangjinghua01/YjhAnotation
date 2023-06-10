package com.yihantaiduo.myanotation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<CardData> mDataList;

    public CardStackAdapter(List<CardData> dataList) {
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardData data = mDataList.get(position);
        holder.mTitleTextView.setText(data.getTitle());
        holder.mContentTextView.setText(data.getContent());
        holder.mImageView.setImageResource(data.getImageResId());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView mTitleTextView;
        private TextView mContentTextView;
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mContentTextView = itemView.findViewById(R.id.content_text_view);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
