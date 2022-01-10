package com.example.cards;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button player1;
    private Button player2;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        player1 = findViewById(R.id.OnePlayerButton);
        player2 = findViewById(R.id.TwoPlayerButton);
        logo  = findViewById(R.id.logo);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(logo, "rotation", 0f, 360f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(logo, "scaleX", 1f, 8f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(logo, "scaleY", 1f, 8f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(logo, "translationX", 0f, 850f);

        ObjectAnimator bt1X = ObjectAnimator.ofFloat(player1, "translationX", -1000f, 0f);
        ObjectAnimator bt1Alpha = ObjectAnimator.ofFloat(player1, "alpha", 0f, 1f);

        ObjectAnimator bt2X = ObjectAnimator.ofFloat(player2, "translationX", 1000f, 0f);
        ObjectAnimator bt2Alpha = ObjectAnimator.ofFloat(player2, "alpha", 0f, 1f);

        animator1.setDuration(2000);

        animator2.setStartDelay(2000);
        animator2.setDuration(1000);

        animator3.setStartDelay(2000);
        animator3.setDuration(1000);

        animator4.setStartDelay(2000);
        animator4.setDuration(1000);

        bt1X.setStartDelay(3000);
        bt1X.setDuration(500);
        bt1Alpha.setStartDelay(3000);

        bt2X.setStartDelay(3000);
        bt2X.setDuration(500);
        bt2Alpha.setStartDelay(3000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2, animator3, animator4, bt1X, bt1Alpha, bt2X, bt2Alpha);
        animatorSet.start();

        //Intents
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_animation(MenuScreen.class);
            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_animation(Multiplayer.class);
            }
        });

    }
    private void intent_animation(Class activityClass) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, activityClass);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                }
            }
        };

        ObjectAnimator bt1RX = ObjectAnimator.ofFloat(player1, "translationX", 0f, -1000f);
        bt1RX.setDuration(500);
        ObjectAnimator bt1X = ObjectAnimator.ofFloat(player1, "translationX", -1000f, 0f);
        bt1X.setStartDelay(2000);

        ObjectAnimator bt2RX = ObjectAnimator.ofFloat(player2, "translationX", 0f, 1000f);
        bt2RX.setDuration(500);
        ObjectAnimator bt2X = ObjectAnimator.ofFloat(player2, "translationX", 1000f, 0f);
        bt2X.setStartDelay(2000);

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(bt1RX, bt2RX, bt1X, bt2X);
        animatorSet1.start();

        Handler h = new Handler(Looper.myLooper());
        h.postDelayed(r, 400);
    }
}