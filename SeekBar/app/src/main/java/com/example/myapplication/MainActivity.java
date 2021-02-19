package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.seekbar.JellySeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JellySeekBar jellySeekBar, jellySeekBar1, jellySeekBar2, jellySeekBar3, jellySeekBar4;

        jellySeekBar = findViewById(R.id.jellySeekBar);
        jellySeekBar.setRange(0, 80);
        jellySeekBar.setColor("#E8E8E8" ,"#1C1A1A" ,"#1C1C1C");
        jellySeekBar.setSignFirstPos(50);
        jellySeekBar.getSeekBarLocation(System.out::println);


        jellySeekBar1 = findViewById(R.id.jellySeekBar1);
        jellySeekBar1.setRange(20 , 80);
        jellySeekBar1.setColor("#be9b7b" ,"#3c2f2f" ,"#3c2f2f");
        jellySeekBar1.setSignFirstPos(20);
        jellySeekBar1.getSeekBarLocation(System.out::println);

        jellySeekBar2 = findViewById(R.id.jellySeekBar2);
        jellySeekBar2.setColor("#bdeaee" ,"#76b4bd" ,"#29a8ab");
        jellySeekBar2.setRange(220 , 800);
        jellySeekBar2.setSignFirstPos(505);
        jellySeekBar2.getSeekBarLocation(System.out::println);

        jellySeekBar3 = findViewById(R.id.jellySeekBar3);
        jellySeekBar3.setRange(20 , 80);
        jellySeekBar3.setColor("#eee3e7" ,"#f4b6c2" ,"#ff8b94");
        jellySeekBar3.setSignFirstPos(20);
        jellySeekBar3.getSeekBarLocation(System.out::println);
    }
}