package com.whitespace.sleepcycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        fab = findViewById(R.id.floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this,MainActivity.class);
                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });

        viewPager2 = findViewById(R.id.viewPager2);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("Newborn","0-3 months old","14-17 hours"));
        sliderItems.add(new SliderItem("Infant","4-11 months old","12-15 hours "));
        sliderItems.add(new SliderItem("Toddler","1-2 years old","11-14 hours"));
        sliderItems.add(new SliderItem("Preschool","3-5 years old","10-13 hours"));
        sliderItems.add(new SliderItem("School-age","6-13 years old","9-11 hours"));
        sliderItems.add(new SliderItem("Teen","14-17 years old","8-10 hours"));
        sliderItems.add(new SliderItem("Young Adult","18-25 years old","7-9 hours"));
        sliderItems.add(new SliderItem("Adult","26-64 years old","7-9 hours"));
        sliderItems.add(new SliderItem("Older Adult","65 or more years old","7-8 hours"));



        viewPager2.setAdapter(new SliderAdapted(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,4000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,4000);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,4000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MoreActivity.this,MainActivity.class);
        startActivity(i);
//        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }
}