package com.example.cards;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends AppCompatActivity {
    private int playerScoreCount = 0;
    private int bankScoreCount = 0;
    private int delay = 0;
    private double playerPoints = 0;
    private double bankPoints = 0;
    private TextView youScore;
    private TextView bankScore;
    private TextView matchResult;
    private TextView bankMatch;
    private TextView youMatch;
    private int initialPositionX = -350;
    private int initialPositionY = 630;
    private int currentCard = 0;
    private ImageView back;
    List<Integer> cardValue = new ArrayList<>();
    List<Double> scoreList = new ArrayList<>();
    private Button more;
    private Button stop;
    private Button play;
    private Button playAgain;
    private ImageView carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8, carta9, carta10, carta11, carta12, carta13, carta14, carta15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgain = findViewById(R.id.playAgain);
        bankMatch = findViewById(R.id.scoreBankValue);
        youMatch = findViewById(R.id.scoreYouValue);
        matchResult = findViewById(R.id.matchResultText);
        youScore = findViewById(R.id.youScore);
        bankScore = findViewById(R.id.bankScore);
        back = findViewById(R.id.back);
        more = findViewById(R.id.oneMore);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        carta1 = findViewById(R.id.card1);
        carta2 = findViewById(R.id.card2);
        carta3 = findViewById(R.id.card3);
        carta4 = findViewById(R.id.card4);
        carta5 = findViewById(R.id.card5);
        carta6 = findViewById(R.id.card6);
        carta7 = findViewById(R.id.card7);
        carta8 = findViewById(R.id.card8);
        carta9 = findViewById(R.id.card9);
        carta10 = findViewById(R.id.card10);
        carta11 = findViewById(R.id.card11);
        carta12 = findViewById(R.id.card12);
        carta13 = findViewById(R.id.card13);
        carta14 = findViewById(R.id.card14);
        carta15 = findViewById(R.id.card15);
        //array of images of cards on the board
        ImageView[] cardsGame = {carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8, carta9, carta10, carta11, carta12, carta13, carta14, carta15};
        generateCards();
        ObjectAnimator animatorX, animatorY, animatorOnemore, animatorStop;

        hideButtons();

        animatorX = ObjectAnimator.ofFloat(play, "scaleX", 0f, 1f);
        animatorX.setDuration(1000);
        animatorY = ObjectAnimator.ofFloat(play, "scaleY", 0f, 1f);
        animatorY.setDuration(1000);
        animatorOnemore = ObjectAnimator.ofFloat(more, "alpha", 0f, 1f);
        animatorOnemore.setStartDelay(700);
        animatorStop = ObjectAnimator.ofFloat(stop, "alpha", 0f, 1f);
        animatorStop.setStartDelay(700);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY, animatorOnemore, animatorStop);
        animatorSet.start();
        //showButtons();
        playAgain.setEnabled(false);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator playanimator;
                playanimator = ObjectAnimator.ofFloat(play, "alpha", 1f, 0f);
                play.setClickable(false);
                playanimator.start();
                pickCard(cardsGame[currentCard]);
                showButtons();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButtons();
                if (pickCard(cardsGame[currentCard]) > 7.5) {
                    ObjectAnimator bank_alfa, bank_maxX, bank_maxY, bank_minX, bank_minY;
                    matchResult.setText(String.valueOf("You have Lost"));
                    bank_alfa = ObjectAnimator.ofFloat(matchResult, "alpha", 0f, 1f);
                    bank_alfa.setStartDelay(1500);
                    bank_maxX = ObjectAnimator.ofFloat(matchResult, "scaleX", 1f, 1.3f);
                    bank_maxX.setStartDelay(1500);
                    bank_maxX.setDuration(1000);
                    bank_maxY = ObjectAnimator.ofFloat(matchResult, "scaleY", 1f, 1.3f);
                    bank_maxY.setStartDelay(1500);
                    bank_maxY.setDuration(1000);
                    bank_minX = ObjectAnimator.ofFloat(matchResult, "scaleX", 1.3f, 1f);
                    bank_minX.setStartDelay(2500);
                    bank_minX.setDuration(1000);
                    bank_minY = ObjectAnimator.ofFloat(matchResult, "scaleY", 1.3f, 1f);
                    bank_minY.setStartDelay(2500);
                    bank_minY.setDuration(1000);

                    AnimatorSet animatorBankwon = new AnimatorSet();
                    animatorBankwon.playTogether(bank_alfa, bank_maxX, bank_maxY, bank_minX, bank_minY);
                    animatorBankwon.start();
                    bankScoreCount++;
                    bankMatch.setText(String.valueOf(bankScoreCount));
                    playAgainMethod(3500);
                } else {
                    showButtons();
                }

            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain.setClickable(false);
                playAgain.setAlpha(0f);
                playAgain.setEnabled(false);
                reiniciarActivity(MenuScreen.this);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator alphaResult;
                hideButtons();
                initialPositionX = -350;
                initialPositionY = -600;
                do {

                    pickCardBank(cardsGame[currentCard]);
                }
                while (bankPoints <= playerPoints && bankPoints < 7.5);
                if (bankPoints >= playerPoints && bankPoints <= 7.5) {
                    matchResult.setText(String.valueOf("bank has won"));
                    bankScoreCount++;
                    //bankMatch.setText(String.valueOf(bankScoreCount));
                    textSetter(bankMatch,bankScoreCount,1000+delay);
                } else {
                    matchResult.setText(String.valueOf("You have won"));
                    playerScoreCount++;
                    //youMatch.setText(String.valueOf(playerScoreCount));
                    textSetter(youMatch,playerScoreCount,1000+delay);
                }
                ObjectAnimator matchalpha, matchmaxX, matchmaxY, matchminX, matchminY;
                matchalpha = ObjectAnimator.ofFloat(matchResult, "alpha", 0f, 1f);
                matchalpha.setStartDelay(1500);
                matchmaxX = ObjectAnimator.ofFloat(matchResult, "scaleX", 1f, 1.3f);
                matchmaxX.setStartDelay(1500);
                matchmaxX.setDuration(1000);
                matchmaxY = ObjectAnimator.ofFloat(matchResult, "scaleY", 1f, 1.3f);
                matchmaxY.setStartDelay(1500);
                matchmaxY.setDuration(1000);
                matchminX = ObjectAnimator.ofFloat(matchResult, "scaleX", 1.3f, 1f);
                matchminX.setStartDelay(2500);
                matchminX.setDuration(1000);
                matchminY = ObjectAnimator.ofFloat(matchResult, "scaleY", 1.3f, 1f);
                matchminY.setStartDelay(2500);
                matchminY.setDuration(1000);

                AnimatorSet animatorBankwon = new AnimatorSet();
                animatorBankwon.setStartDelay(delay);
                animatorBankwon.playTogether(matchalpha, matchmaxX, matchmaxY, matchminX, matchminY);
                animatorBankwon.start();
                playAgainMethod(3500+ delay);

            }
        });
    }
    public void textSetter(TextView textView, int var , int time){
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(var));
            }
        },time);
    }

    public static void reiniciarActivity(Activity actividad) {
        Intent intent = new Intent();
        intent.setClass(actividad, actividad.getClass());
        //llamamos a la actividad
        actividad.startActivity(intent);
        //finalizamos la actividad actual
        actividad.finish();
    }

    private void playAgainMethod(int delaytime) {
        playAgain.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator alpha;
                alpha = ObjectAnimator.ofFloat(playAgain,"alpha", 0f,1f);
                alpha.setDuration(1000);
                alpha.start();
                playAgain.setClickable(true);
                playAgain.setEnabled(true);
            }
        }, delaytime);

    }

    private void generateCards() {
        DecimalFormat df = new DecimalFormat("00");
        String[] types = {"clubs", "cups", "golds", "swords"};
        for (String type : types) {
            for (int i = 1; i < 13; i++) {
                cardValue.add(getResources().getIdentifier(type + df.format(i), "drawable", getPackageName()));
            }
        }
        //score generation
        for (double k = 0; k < 4; k++) {
            for (double i = 1; i < 13; i++) {
                if (i > 7) scoreList.add(0.5);
                else scoreList.add(i);
            }
        }
    }

    private double pickCardBank(ImageView selectedCard) {
        //random number from 1 to 48
        int randomNum = (int) (Math.random() * (cardValue.size() + 1) + 0);
        //selected card by bank
        selectedCard.setImageResource(getCard(randomNum));
        bankPoints += getPoints(randomNum);
        bankScore.postDelayed(new Runnable() {
            @Override
            public void run() {
                bankScore.setText(String.valueOf(bankPoints));
            }
        }, 2200);

        ObjectAnimator animatorXback, animatorYback, alphaback;
        alphaback = ObjectAnimator.ofFloat(back, "alpha", 0f, 1f);
        animatorXback = ObjectAnimator.ofFloat(back, "translationX", 0f, initialPositionX);
        animatorXback.setDuration(1000);
//        animatorXback.setStartDelay(delay);

        animatorYback = ObjectAnimator.ofFloat(back, "translationY", 0f, initialPositionY);
        animatorYback.setDuration(1000);
//        animatorYback.setStartDelay(delay);

        ObjectAnimator spinback = ObjectAnimator.ofFloat(back, "rotationY", 0f, 180f);
        spinback.setStartDelay(1000);
        spinback.setDuration(300);
        ObjectAnimator alphaback2 = ObjectAnimator.ofFloat(back, "alpha", 1f, 0f);
        alphaback2.setStartDelay(1150);
        alphaback2.setDuration(1);

        ObjectAnimator animatorXcard = ObjectAnimator.ofFloat(selectedCard, "translationX", 0f, initialPositionX);
        ObjectAnimator animatorYcard = ObjectAnimator.ofFloat(selectedCard, "translationY", 0f, initialPositionY);
        animatorXcard.setDuration(1000);
        animatorYcard.setDuration(1000);

        ObjectAnimator spincard = ObjectAnimator.ofFloat(selectedCard, "rotationY", 180f, 360f);
        spincard.setStartDelay(1000);
        spinback.setDuration(300);
        ObjectAnimator alphacard = ObjectAnimator.ofFloat(selectedCard, "alpha", 0f, 1f);
        alphacard.setStartDelay(1150);
        alphacard.setDuration(1);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(animatorXback, animatorYback, alphaback, spinback, alphaback2, animatorXcard, animatorYcard, spincard, alphacard);
        animatorSet.setStartDelay(1000 + delay);
        animatorSet.start();
        initialPositionX += 60f;
        currentCard++;
        delay += 1000;
        return bankPoints;
    }

    private double pickCard(ImageView selectedCard) {
        //There are 48 different cards
        //random number from 1 to 48
        int randomNum = (int) (Math.random() * (cardValue.size() + 1) + 0);
        selectedCard.setImageResource(getCard(randomNum));
        playerPoints += getPoints(randomNum);
        youScore.postDelayed(new Runnable() {
            @Override
            public void run() {
                youScore.setText(String.valueOf(playerPoints));
            }
        }, 2200);

        ObjectAnimator animatorXback, animatorYback, alphaback;
        alphaback = ObjectAnimator.ofFloat(back, "alpha", 0f, 1f);
        animatorXback = ObjectAnimator.ofFloat(back, "translationX", 0f, initialPositionX);
        animatorXback.setDuration(1000);

        animatorYback = ObjectAnimator.ofFloat(back, "translationY", 0f, initialPositionY);
        animatorYback.setDuration(1000);
        //300 1150 1

        ObjectAnimator spinback = ObjectAnimator.ofFloat(back, "rotationY", 0f, 180f);
        spinback.setStartDelay(1000);
        spinback.setDuration(300);
        ObjectAnimator alphaback2 = ObjectAnimator.ofFloat(back, "alpha", 1f, 0f);
        alphaback2.setStartDelay(1150);
        alphaback2.setDuration(1);

        ObjectAnimator animatorXcard = ObjectAnimator.ofFloat(selectedCard, "translationX", 0f, initialPositionX);
        ObjectAnimator animatorYcard = ObjectAnimator.ofFloat(selectedCard, "translationY", 0f, initialPositionY);
        animatorXcard.setDuration(1000);
        animatorYcard.setDuration(1000);
        ObjectAnimator spincard = ObjectAnimator.ofFloat(selectedCard, "rotationY", 180f, 360f);
        spincard.setStartDelay(1000);
        spinback.setDuration(300);
        ObjectAnimator alphacard = ObjectAnimator.ofFloat(selectedCard, "alpha", 0f, 1f);
        alphacard.setStartDelay(1150);
        alphacard.setDuration(1);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(1000);
        animatorSet.playTogether(animatorXback, animatorYback, alphaback, spinback, alphaback2, animatorXcard, animatorYcard, spincard, alphacard);
        animatorSet.start();
        initialPositionX += 60f;
        currentCard++;
        return playerPoints;
    }

    private double getPoints(int index) {
        double value = scoreList.get(index);
        scoreList.remove(index);
        return value;
    }

    private int getCard(int index) {
        //The random number gets a card (its code) from the list of cards
        int value = cardValue.get(index);
        //We remove the chosen card so that we wont get repeated cards
        cardValue.remove(index);
        return value;
    }

    private void showButtons() {
        ObjectAnimator buttonOneMore, buttonStop;
        buttonOneMore = ObjectAnimator.ofFloat(more, "translationX", -1000f, 0f);
        buttonOneMore.setStartDelay(1000);
        buttonOneMore.setDuration(1000);

        buttonStop = ObjectAnimator.ofFloat(stop, "translationX", 1000f, 0f);
        buttonStop.setStartDelay(1000);
        buttonStop.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(buttonOneMore, buttonStop);
        animatorSet.start();
    }

    private void hideButtons() {
        ObjectAnimator animatormor, animatorstop;
        animatorstop = ObjectAnimator.ofFloat(more, "translationX", 0f, -1000f);
        animatorstop.setDuration(1000);
        animatormor = ObjectAnimator.ofFloat(stop, "translationX", 0f, 1000f);
        animatormor.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatormor, animatorstop);
        animatorSet.start();
    }

}