package com.yihantaiduo.myanotation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;

import com.yihantaiduo.myanotation.inject.InjectView;
import com.yihantaiduo.myanotation.ui.Card;
import com.yihantaiduo.myanotation.ui.CardStackView;

import java.util.ArrayList;
import java.util.List;

//@Lance(value = 1, id = "2")
public class MainActivity extends AppCompatActivity {
//    @InjectView(R.id.txt1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<CardData> cardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cardList.add(new CardData("tittl"+1,"content"+1,R.drawable.ic_launcher_background));
        }
         RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardStackAdapter(cardList));
    }
}