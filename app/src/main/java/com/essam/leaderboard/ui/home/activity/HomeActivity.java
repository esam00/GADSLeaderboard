package com.essam.leaderboard.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.essam.leaderboard.R;
import com.essam.leaderboard.ui.submit.SubmitActivity;
import com.essam.leaderboard.ui.home.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 mViewPager2;
    private ViewPagerAdapter viewPagerAdapter;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    private void initViews() {
        // toolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // viewPager and tabLayout
        mViewPager2 = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPager2.OnPageChangeCallback pageChangeListener = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        };

        viewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager2.setAdapter(viewPagerAdapter);
        mViewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);

        // set tabs text using TabLayoutMediator
        new TabLayoutMediator(tabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(getString(R.string.first_fragment_title).toUpperCase());
                        break;
                    case 1:
                        tab.setText(getString(R.string.second_fragment_title).toUpperCase());
                        break;
                }
            }
        }).attach();

        mViewPager2.registerOnPageChangeCallback(pageChangeListener);

        // submit button
        mSubmitButton = findViewById(R.id.submit_btn);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SubmitActivity.class));
            }
        });
    }

}