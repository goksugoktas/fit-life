package com.mey.fitlife.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mey.fitlife.CustomDialog;
import com.mey.fitlife.R;
import com.mey.fitlife.adapter.DietClickListener;
import com.mey.fitlife.adapter.DietItemAdapter;
import com.mey.fitlife.model.DietModel;

import java.util.Arrays;
import java.util.List;

public class DietActivity extends AppCompatActivity implements DietClickListener {

    RecyclerView rvDiets;
    ImageView imgBack;
    DietItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        rvDiets = findViewById(R.id.rvDiets);
        imgBack = findViewById(R.id.imgBack);

        adapter = new DietItemAdapter(this, getDiets(), this);
        rvDiets.setLayoutManager(new LinearLayoutManager(this));
        rvDiets.setAdapter(adapter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    // diyeti uygula butonuna tıklanıldığında çalışır
    @Override
    public void onApplyButtonClick(DietModel model) {
        Log.e("TAG", "clicked : " + model.getName());
        dietApplied(model.getCal());

    }

    // diyetin kalori değeri, veritabanındaki değerin üzerine eklenir
    private void dietApplied(int cal) {
        // shared'daki currentCal'ı al.
        // cal değerini ekle
        // currentCal'ın yeni değerini set'le.
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int currentCal = preferences.getInt("currentCal", 0);
        int newCal = currentCal + cal;
        editor.putInt("currentCal", newCal);
        editor.apply();


        // dialog gösterilir
        CustomDialog dialog = new CustomDialog(this, "Diyet uygulandı!");
        dialog.showCustomAlertDialog();

    }


    // diyet listesi oluşturulur ve döndürülür
    public List<DietModel> getDiets() {
        DietModel d1 = new DietModel("Diyet 1", 2000, "1 kase yağsız yoğurt, 1 dilim tam buğday ekmeği, 1 adet yumurta", "1 tabak sebze çorbası, 1 kase bulgur pilavı, 100 gram ızgara tavuk", "1 tabak zeytinyağlı sebze yemeği, 1 kase yoğurt");
        DietModel d2 = new DietModel("Diyet 2", 2500, "2 adet kepekli bisküvi, 1 kase meyve salatası", "1 tabak çorba, 1 kase etli sebze yemeği", "1 tabak balık ızgara, 1 kase salata");
        DietModel d3 = new DietModel("Diyet 3", 3000, "1 kase süt, 1 dilim ekmek, 1 adet meyve", "1 tabak mercimek çorbası, 1 kase kepekli makarna, 100 gram ızgara köfte", "1 tabak baklagil yemeği, 1 kase yoğurt");
        DietModel d4 = new DietModel("Diyet 4", 2600, "1 kase yoğurt, 1 adet haşlanmış yumurta, 1 dilim tam buğday ekmeği", "1 tabak sebze çorbası, 1 kase bulgur pilavı, 100 gram ızgara balık", "1 tabak zeytinyağlı sebze yemeği, 1 kase yoğurt");
        DietModel d5 = new DietModel("Diyet 5", 2200, "2 adet kepekli bisküvi, 1 kase meyve salatası", "1 tabak çorba, 1 kase tavuklu sebze yemeği", "1 tabak balık ızgara, 1 kase salata");
        DietModel d6 = new DietModel("Diyet 6", 1800, "1 kase süt, 1 dilim ekmek, 1 adet meyve", "1 tabak mercimek çorbası, 1 kase kepekli makarna, 100 gram ızgara tavuk", "1 tabak baklagil yemeği, 1 kase yoğurt");
        DietModel d7 = new DietModel("Diyet 7", 1900, "1 kase yoğurt, 1 adet haşlanmış yumurta, 1 dilim tam buğday ekmeği", "1 tabak sebze çorbası, 1 kase bulgur pilavı, 100 gram ızgara köfte", "1 tabak zeytinyağlı sebze yemeği, 1 kase yoğurt");
        DietModel d8 = new DietModel("Diyet 8", 2100, "2 adet kepekli bisküvi, 1 kase meyve salatası", "1 tabak çorba, 1 kase etli sebze yemeği", "1 tabak balık ızgara, 1 kase salata");
        DietModel d9 = new DietModel("Diyet 9", 2400, "1 kase süt, 1 dilim ekmek, 1 adet meyve", "1 tabak mercimek çorbası, 1 kase kepekli makarna, 100 gram ızgara balık", "1 tabak baklagil yemeği, 1 kase yoğurt");
        DietModel d10 = new DietModel("Diyet 10", 2200, "1 kase yoğurt, 1 adet haşlanmış yumurta, 1 dilim tam buğday ekmeği", "1 tabak sebze çorbası, 1 kase bulgur pilavı, 100 gram ızgara tavuk", "1 tabak zeytinyağlı sebze yemeği, 1 kase yoğurt");

        List<DietModel> itemList = Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);

        return itemList;
    }
}

