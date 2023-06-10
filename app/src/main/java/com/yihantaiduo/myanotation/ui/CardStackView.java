package com.yihantaiduo.myanotation.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardStackView extends ViewGroup {
    private List<Card> mCards = new ArrayList<>();
    private GestureDetector mGestureDetector;
    private View.OnClickListener mOnClickListener;

    public CardStackView(Context context) {
        super(context);
        init();
    }

    public CardStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                for (int i = mCards.size() - 1; i >= 0; i--) {
                    Card card = mCards.get(i);
//                    if (card.contains(e.getX(), e.getY())) {
//                        if (mOnClickListener != null) {
//                            mOnClickListener.onClick(card.getView());
//                        }
//                        return true;
//                    }
                }
                return false;
            }
        });
    }

    public void addCard(Card card) {
        mCards.add(card);
        addView(card.getView());
        requestLayout();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int left = 0;
        int top = 0;
        for (int i = count - 1; i >= 0; i--) {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (i == count - 1) {
                left = getWidth() - width;
            } else {
                left -= width / 3;
            }
            child.layout(left, top, left + width, top + height);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
