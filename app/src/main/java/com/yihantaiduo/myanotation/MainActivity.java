package com.yihantaiduo.myanotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
@Lance(value = 1, id = "2")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}