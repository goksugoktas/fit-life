package com.mey.fitlife.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mey.fitlife.CustomDialog;
import com.mey.fitlife.R;

public class FinishDayActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    ImageView imgBack;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_day);
        imgBack = findViewById(R.id.imgBack);
        tvResult = findViewById(R.id.tvResult);
        mediaPlayer = MediaPlayer.create(this, R.raw.clap); // "clap.mp3" dosyanızın adını kullanın

        clickEvents();

        controlStatus();
    }

    private void clickEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // anlık ulaşılan kalori miktarı ile günlük kalori hedefi karşılaştırılır
    private void controlStatus() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        int dailyTarget = preferences.getInt("dailyTarget", 0); // 2000
        int currentCal = preferences.getInt("currentCal", 0); // 3700


        if (currentCal == dailyTarget) {
            dietCompleted();
        } else if (currentCal < dailyTarget) {
            boolean isCompleted = currentCal >= dailyTarget - 100;
            if (isCompleted) {
                dietCompleted();
            } else {
                // kalori alması lazım, besin önerisinde bulunalım.
                int difference = dailyTarget - currentCal;
                tvResult.setText(difference + " kalori eksiğiniz bulunmakta. Diyet sayfasından uygun diyetleri tamamlayınız.");
            }
        } else {
            boolean isCompleted = currentCal <= dailyTarget + 100;
            if (isCompleted) {
                dietCompleted();
            } else {
                // egzersiz yapması lazım
                int difference = currentCal - dailyTarget;
                tvResult.setText(difference + " kalori fazlanız bulunmakta. Egzersiz sayfasından uygun egzersizleri tamamlayınız.");
            }
        }
    }

    // kalori hedefine ulaşıldıysa burası çalışır
    private void dietCompleted() {
        CustomDialog dialog = new CustomDialog(this, "Tebrikler, hedefinize ulaştınız!");
        dialog.showCustomAlertDialog();
        playAudio();
        // SharedPreferences nesnesini al
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        // SharedPreferences üzerinde düzenleme yapabilmek için bir editor oluştur
        SharedPreferences.Editor editor = preferences.edit();

        // Key-Value çiftini kaydet
        editor.putInt("dailyTarget", 0); // "dailyTarget" adlı bir anahtarla 0 değerini kaydediyoruz.
        editor.putInt("currentCal", 0); // "currentCal" adlı bir anahtarla 0 değerini kaydediyoruz.

        // Değişiklikleri kaydet
        editor.apply();
    }

    // alkış sesi çalınır
    private void playAudio() {
        mediaPlayer.start();

        // MediaPlayer'ı dinleme (listening) moduna alın
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Oynatma tamamlandığında buraya gelecek
                stopAudio();
            }
        });
    }

    private void stopAudio() {
        mediaPlayer.release(); // MediaPlayer'ı serbest bırak
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}