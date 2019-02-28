package day01.huy.hci_project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.custom.SlicePagerAdapter;
import day01.huy.hci_project.fragments.PageFragment1;
import day01.huy.hci_project.fragments.PageFragment2;
import day01.huy.hci_project.fragments.PageFragment3;

public class DetailActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    Button btFavority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PageFragment1());
        fragmentList.add(new PageFragment2());
        fragmentList.add(new PageFragment3());
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new SlicePagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);

        //set color for favorite button
        btFavority = findViewById(R.id.button);
        //get boolean like from previous activity

        boolean like = false;
        Intent intent = getIntent();
        if(intent.hasExtra("check")){

             like = intent.getExtras().getBoolean("check");
        }
        if (like) {
            btFavority.setBackgroundColor(getResources().getColor(R.color.grayLight));
        }

    }

    public void clickToFavorite(View view) {
        btFavority.setBackgroundColor(getResources().getColor(R.color.grayLight));
    }

}
