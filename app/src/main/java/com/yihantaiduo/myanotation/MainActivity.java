package com.yihantaiduo.myanotation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.yihantaiduo.myanotation.inject.InjectView;
import com.yihantaiduo.myanotation.ui.Card;
import com.yihantaiduo.myanotation.ui.CardStackView;

//@Lance(value = 1, id = "2")
public class MainActivity extends AppCompatActivity {
//    @InjectView(R.id.txt1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        cardStackView.addCard(new Card("Card 1", Color.RED,this));
        cardStackView.addCard(new Card("Card 2", Color.GREEN,this));
        cardStackView.addCard(new Card("Card 3", Color.BLUE,this));

    }
}