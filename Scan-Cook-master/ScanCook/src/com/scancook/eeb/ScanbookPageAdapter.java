package com.scancook.eeb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ScanbookPageAdapter extends FragmentPagerAdapter {

	public ScanbookPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
            return new ScanFragment();
        case 1:
            return new ProductsOverview();
        case 2:
        	return new CookbookFragment();
    
        }

        return null;
	}

	@Override
	public int getCount() {
		
		return 3;
	}

}
