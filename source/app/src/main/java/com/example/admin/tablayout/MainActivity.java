package com.example.admin.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentFirst fragmentFirst;
    FragmentSecond fragmentSecond;
    FragmentThird fragmentThird;
    public static GroupSQLite groupSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupSQLite = new GroupSQLite(this);

//        Calendar cal = Calendar.getInstance();
//        long a = cal.getTimeInMillis();

        Log.d("abc",  R.layout.activity_event + " hihi");

        fragmentFirst = new FragmentFirst();
        fragmentSecond = new FragmentSecond();
        fragmentThird = new FragmentThird();

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( fragmentFirst, "Trang chủ");
        adapter.addFragment(fragmentSecond, "Thành viên");
        adapter.addFragment(fragmentThird, "Sự kiện");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
