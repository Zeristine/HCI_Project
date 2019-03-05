package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.custom.SlicePagerAdapter;
import day01.huy.hci_project.fragments.PageFragment1;
import day01.huy.hci_project.fragments.PageFragment2;
import day01.huy.hci_project.fragments.PageFragment3;
import day01.huy.hci_project.ultis.ColorGradient;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private Button btnFavorite;
    private RelativeLayout layoutRecipeImage;
    private TextView txtRecipeTitle;
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        LinearLayout.LayoutParams layoutParams;
        viewPager = findViewById(R.id.pagerRecipe);
        layoutRecipeImage = findViewById(R.id.layoutRecipeImage);
        txtRecipeTitle = findViewById(R.id.txtRecipeTitle);
        btnFavorite = findViewById(R.id.btnFavorite);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Intent intent = getIntent();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PageFragment1());
        fragmentList.add(new PageFragment2());
        fragmentList.add(new PageFragment3());
        pagerAdapter = new SlicePagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (displayMetrics.heightPixels*3)/10);
        layoutRecipeImage.setLayoutParams(layoutParams);
        layoutRecipeImage.setBackgroundResource(getResources()
                .getIdentifier("image_food_"+ intent.getStringExtra("image"), "drawable", getPackageName()));
        RelativeLayout.LayoutParams btnLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btnLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        btnLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btnLayout.bottomMargin = 10;
        btnLayout.rightMargin = (displayMetrics.widthPixels*1)/30;
        btnFavorite.setLayoutParams(btnLayout);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (displayMetrics.heightPixels*1)/10);
        txtRecipeTitle.setLayoutParams(layoutParams);
        txtRecipeTitle.setText(intent.getStringExtra("title"));
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (displayMetrics.heightPixels*6)/10);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        viewPager.setLayoutParams(layoutParams);


        boolean like = false;
        if (intent.hasExtra("check")) {
            like = intent.getExtras().getBoolean("check");
        }
        if (like) {
            btnFavorite.setText("UNFAVORITE");
            btnFavorite.setBackground(ColorGradient.getRedGradientBlackGray(this));
        } else {
            btnFavorite.setText("FAVORITE");
            btnFavorite.setBackground(ColorGradient.getRedGradient(this));
        }

    }

    public void clickToFavorite(View view) {
        String text = (String) btnFavorite.getText();
        if (text.equalsIgnoreCase("favorite")) {
            btnFavorite.setText("UNFAVORITE");
            btnFavorite.setBackground(ColorGradient.getRedGradientBlackGray(this));
        } else {
            btnFavorite.setText("FAVORITE");
            btnFavorite.setBackground(ColorGradient.getRedGradient(this));
        }
    }

    public void clickToFinish(View view) {
        finish();
    }
}
