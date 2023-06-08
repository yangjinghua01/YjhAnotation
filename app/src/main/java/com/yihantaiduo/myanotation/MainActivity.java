package com.yihantaiduo.myanotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yihantaiduo.myanotation.inject.InjectView;

@Lance(value = 1, id = "2")
public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.txt1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}