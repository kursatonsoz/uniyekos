package com.example.kursat.myapplication;

/**
 * Created by kursat on 31.05.2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kursat.myapplication.FragPazartesi;
import com.example.kursat.myapplication.FragSali;
import com.example.kursat.myapplication.FragCarsamba;
import com.example.kursat.myapplication.FragPersembe;
import com.example.kursat.myapplication.FragCuma;
import com.example.kursat.myapplication.FragCumartesi;
import com.example.kursat.myapplication.FragPazar;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new FragPazartesi(getEmail());
            case 1:
                return new FragSali(getEmail());
            case 2:
                return new FragCarsamba(getEmail());
            case 3:
                return new FragPersembe(getEmail());
            case 4:
                return new FragCuma(getEmail());
            case 5:
                return new FragCumartesi(getEmail());
            case 6:
                return new FragPazar(getEmail());
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 7;
    }

}
