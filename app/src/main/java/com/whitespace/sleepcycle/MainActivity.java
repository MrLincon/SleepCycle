package com.whitespace.sleepcycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    ImageView settings;
    ChipGroup categories;

    CardView powerNap, firstCycle, secondCycle, thirdCycle, fourthCycle, fifthCycle, sixthCycle;
    View powerNapSelector, firstCycleSelector, secondCycleSelector,
            thirdCycleSelector,fourthCycleSelector, fifthCycleSelector, sixthCycleSelector;
    String sheetTitle;
    int sheetHour, sheetMin;

    String Category = "Sleep Cycle";

    ThemeSettings themeSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Theme Settings
        themeSettings = new ThemeSettings(this);
        if (themeSettings.loadNightModeState() == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        //...............
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = findViewById(R.id.settings);
        categories = findViewById(R.id.categories);

        powerNap = findViewById(R.id.power_nap);
        firstCycle = findViewById(R.id.cycle_one);
        secondCycle = findViewById(R.id.cycle_two);
        thirdCycle = findViewById(R.id.cycle_three);
        fourthCycle = findViewById(R.id.cycle_four);
        fifthCycle = findViewById(R.id.cycle_five);
        sixthCycle = findViewById(R.id.cycle_six);

        powerNapSelector = findViewById(R.id.power_nap_selector);
        firstCycleSelector = findViewById(R.id.one_cycle_selector);
        secondCycleSelector = findViewById(R.id.two_cycle_selector);
        thirdCycleSelector = findViewById(R.id.three_cycle_selector);
        fourthCycleSelector = findViewById(R.id.four_cycle_selector);
        fifthCycleSelector = findViewById(R.id.five_cycle_selector);
        sixthCycleSelector = findViewById(R.id.six_cycle_selector);

        categories.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                Chip chip = chipGroup.findViewById(i);
                Category = (String) chip.getChipText();
                
                if (Category.equals("More")){
                   Intent more = new Intent(MainActivity.this,MoreActivity.class);
                   startActivity(more);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                   finish();
                }
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });

        loadData();

    }

    private void loadData() {

        BottomSheet bottomSheet = new BottomSheet();

        powerNap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "Power Nap";
                sheetHour = 0;
                sheetMin = 20;
                powerNapSelector.setVisibility(View.VISIBLE);

               
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");

            }
        });

        firstCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "1 Sleep Cycle";
                sheetHour = 1;
                sheetMin = 30;
                firstCycleSelector.setVisibility(View.VISIBLE);

               
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");
            }
        });

        secondCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "2 Sleep Cycles";
                sheetHour = 3;
                sheetMin = 00;
                secondCycleSelector.setVisibility(View.VISIBLE);

              
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");
            }
        });

        thirdCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "3 Sleep Cycles";
                sheetHour = 4;
                sheetMin = 30;
                thirdCycleSelector.setVisibility(View.VISIBLE);

               
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");
            }
        });

        fourthCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "4 Sleep Cycles";
                sheetHour = 6;
                sheetMin = 00;
                fourthCycleSelector.setVisibility(View.VISIBLE);


               
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");
            }
        });

        fifthCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "5 Sleep Cycles";
                sheetHour = 7;
                sheetMin = 30;
                fifthCycleSelector.setVisibility(View.VISIBLE);

               
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");
            }
        });

        sixthCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sheetTitle = "6 Sleep Cycles";
                sheetHour = 9;
                sheetMin = 00;
                sixthCycleSelector.setVisibility(View.VISIBLE);

               
                bottomSheet.show(getSupportFragmentManager(), "Sleep Cycle");
            }
        });

    }

    public String getSheetTitle(){
        return sheetTitle;
    }

    public int getSheetHour(){
        return sheetHour;
    }

    public int getSheetMin(){
        return sheetMin;
    }

    public View getPowerNapSelector(){
        return powerNapSelector;
    }


    public View getFirstCycleSelector(){
        return firstCycleSelector;
    }


    public View getSecondCycleSelector(){
        return secondCycleSelector;
    }


    public View getThirdCycleSelector(){
        return thirdCycleSelector;
    }


    public View getFourthCycleSelector(){
        return fourthCycleSelector;
    }


    public View getFifthCycleSelector(){
        return fifthCycleSelector;
    }


    public View getSixthCycleSelector(){
        return sixthCycleSelector;
    }

}