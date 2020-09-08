package com.essam.leaderboard.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.essam.leaderboard.ui.home.fragments.learning.LearningLeadersFragment;
import com.essam.leaderboard.ui.home.fragments.skillIq.SkillIqLeadersFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LearningLeadersFragment();
            case 1:
                return new SkillIqLeadersFragment();
        }

        return new LearningLeadersFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
