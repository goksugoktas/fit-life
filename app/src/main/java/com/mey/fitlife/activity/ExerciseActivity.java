package com.mey.fitlife.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mey.fitlife.CustomDialog;
import com.mey.fitlife.R;
import com.mey.fitlife.adapter.ExerciseItemAdapter;
import com.mey.fitlife.adapter.ExerciseItemClickListener;
import com.mey.fitlife.model.ExcerciseModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements ExerciseItemClickListener {

    RecyclerView rvExercises;
    ExerciseItemAdapter adapter;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        rvExercises = findViewById(R.id.rvExercises);
        imgBack = findViewById(R.id.imgBack);

        adapter = new ExerciseItemAdapter(this, getExercises(), this);

        rvExercises.setLayoutManager(new LinearLayoutManager(this));
        rvExercises.setAdapter(adapter);

        imgBack.setOnClickListener(v -> finish());
    }


    // egzersizi uygula butonuna tıklayınca çalışır
    @Override
    public void onApplyButtonClick(ExcerciseModel model) {
        Log.e("TAG", "clicked : " + model.getName());

        exerciseCompleted(model.getCal());

    }

    // veritabanındaki anlık kalori değeri güncellenir
    private void exerciseCompleted(int cal) {
        // shared'daki currentCal'ı al.
        // cal değerini çıkar
        // currentCal'ın yeni değerini set'le.
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int currentCal = preferences.getInt("currentCal", 0);
        int newCal = currentCal - cal;
        editor.putInt("currentCal", newCal);
        editor.apply();

        CustomDialog dialog = new CustomDialog(this, "Egzersiz uygulandı!");
        dialog.showCustomAlertDialog();
    }

    // egzersiz listesi oluşturulur ve döndürülür
    public List<ExcerciseModel> getExercises() {
        String image1 = "https://blog.korayspor.com/wp-content/uploads/2020/11/Y%C3%9CR%C3%9CY%C3%9C%C5%9E%C3%9C-SPORA-D%C3%96N%C3%9C%C5%9ET%C3%9CREN-%C3%96NEML%C4%B0-DETAYLAR.jpg"; // yürüyüş
        String image2 = "https://minio.yalispor.com.tr/yalispor/blog/kosuya-yeni-baslayanlar-icin-10-oneri-1_64521ef14e6ab.jpg"; // koşu
        String image3 = "https://www.diyetkolik.com/site_media/media/exercise_workout/ip_atlamak.jpg"; // ip atlamak
        String image4 = "https://www.acibadem.com.tr/hayat/Images/YayinMakaleler/yuzmenin-faydalari_733414_1.png"; // yüzme
        String image5 = "https://www.macfit.com/wp-content/uploads/2023/01/bisiklet-hangi-kaslari-calistirir-1024x680.jpg"; // Bisiklet Sürmek
        String image6 = "https://d1muf25xaso8hp.cloudfront.net/https%3A%2F%2Ff8f9e943878ef16140e966fe06c645cd.cdn.bubble.io%2Ff1689062086348x413568873722532900%2Fyoga-nedir.jpg?w=512&h=263&auto=compress&dpr=3&fit=max"; //yoga
        String image7 = "https://blog.korayspor.com/wp-content/uploads/2020/11/A%C4%9EIRLIK-%C3%87ALI%C5%9EMANIN-P%C3%9CF-NOKTALARINI-%C3%96%C4%9EREN%C4%B0N.jpeg"; // Ağırlık kaldırma
        String image8 = "https://www.kickboxclubbasel.ch/wp-content/uploads/2015/11/APEX-Kickboxen-Kickboxen-2.jpg"; // kickbox
        String image9 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQZpzzeapsP4efl9ufjs_aEYwaJZ7fACZJlw&usqp=CAU"; // squat
        String image10 = "https://hips.hearstapps.com/hmg-prod/images/strength-vs-endurance-push-up-075-joshua-simpson-1638295487.jpg?crop=1.00xw:0.674xh;0,0.326xh&resize=1200:*"; // Push Up
        String image11 = "https://cdn.dsmcdn.com/mrktng/seo/22aralik4/mekik-nasil-cekilir.jpg"; // Mekik
        String image12 = "https://www.shape.com/thmb/T2GyvzFah3XYR8_L8W16ANWBTXs=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/low-plank-hold-b8a63da1ef844f00b6f6a21141ba1d87.jpg"; // Plank

        ExcerciseModel e1 = new ExcerciseModel(image1, "Yürüyüş", 300, "1 saat");
        ExcerciseModel e2 = new ExcerciseModel(image2, "Koşu", 600, "1 saat");
        ExcerciseModel e3 = new ExcerciseModel(image3, "İp Atlamak", 180, "10 dakika");
        ExcerciseModel e4 = new ExcerciseModel(image4, "Yüzme", 600, "1 saat");
        ExcerciseModel e5 = new ExcerciseModel(image5, "Bisiklet Sürmek", 700, "1 saat");
        ExcerciseModel e6 = new ExcerciseModel(image6, "Yoga", 200, "1 saat");
        ExcerciseModel e7 = new ExcerciseModel(image7, "Ağırlık Kaldırma", 100, "30 dakika");
        ExcerciseModel e8 = new ExcerciseModel(image8, "Kick Boks", 700, "1 saat");
        ExcerciseModel e9 = new ExcerciseModel(image9, "Squat", 230, "1 saat");
        ExcerciseModel e10 = new ExcerciseModel(image10, "Push Up", 200, "30 dakika");
        ExcerciseModel e11 = new ExcerciseModel(image11, "Mekik", 100, "10 dakika");
        ExcerciseModel e12 = new ExcerciseModel(image12, "Plank", 20, "5 dakika");

        List<ExcerciseModel> itemList = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12);

        Collections.shuffle(itemList);

        return itemList;
    }
}