package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.example.seekbar.JellySeekBar;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JellySeekBar jellySeekBar;

        jellySeekBar = findViewById(R.id.jellySeekBar);
     //   jellySeekBar.setRange(20 , 80);
      //  jellySeekBar.setColor("#be9b7b" ,"#3c2f2f" ,"#3c2f2f");
        jellySeekBar.setColor("#bdeaee" ,"#76b4bd" ,"#29a8ab");
     //   jellySeekBar.setSignFirstLocation(40);
//        jellySeekBar.setBubblesDuration(800);
//        jellySeekBar.setSignDuration(500);
   //     jellySeekBar.setFontForNum(getResources().getFont(R.font.font));
        jellySeekBar.getSeekBarLocation(System.out::println);



//        jellySeekBar2 = findViewById(R.id.jellySeekBar2);
//        jellySeekBar2.setColor("#bdeaee" ,"#76b4bd" ,"#29a8ab");
//        jellySeekBar2.setRange(220 , 800);
//        jellySeekBar2.setSignFirstPos(505);
//        jellySeekBar2.getSeekBarLocation(System.out::println);
//
//        jellySeekBar3 = findViewById(R.id.jellySeekBar3);
//        jellySeekBar3.setRange(20 , 80);
//        jellySeekBar3.setColor("#eee3e7" ,"#f4b6c2" ,"#ff8b94");
//        jellySeekBar3.setSignFirstPos(20);
//        jellySeekBar3.getSeekBarLocation(System.out::println);

    }
}