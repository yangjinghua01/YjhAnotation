package com.yihantaiduo.myanotation;

public class CardData {

    private String mTitle;
    private String mContent;
    private int mImageResId;

    public CardData(String title, String content, int imageResId) {
        mTitle = title;
        mContent = content;
        mImageResId = imageResId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public int getImageResId() {
        return mImageResId;
    }
}
