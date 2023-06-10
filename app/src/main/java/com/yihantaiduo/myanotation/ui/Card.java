package com.yihantaiduo.myanotation.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Card {
    private String mText;
    private int mColor;
    private View mView;
    private Context contex;
    public Card(String text, int color,Context context) {
        mText = text;
        mColor = color;
        this.contex = context;
        mView = createView();
    }

    public String getText() {
        return mText;
    }

    public int getColor() {
        return mColor;
    }

    public View getView() {
        return mView;
    }

    private View createView() {
        TextView textView = new TextView(contex);
        textView.setText(mText);
        textView.setBackgroundColor(mColor);
        return textView;
    }

}
