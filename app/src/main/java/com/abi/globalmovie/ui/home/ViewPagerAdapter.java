package com.abi.globalmovie.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abi.globalmovie.R;
import com.abi.globalmovie.ui.favorite.FavoriteFragment;
import com.abi.globalmovie.ui.movies.MoviesFragment;
import com.abi.globalmovie.ui.tvshow.TVShowFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_NAMES = new int[]{R.string.movies, R.string.tvshow, R.string.favorite};
    private final Context mContext;

    ViewPagerAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MoviesFragment();
            case 1:
                return new TVShowFragment();
            case 2:
                return new FavoriteFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_NAMES[position]);
    }
    @Override
    public int getCount() {
        return 3;
    }
}
