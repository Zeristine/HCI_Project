package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.custom.SlicePagerAdapter;
import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.fragments.RecipeContentFragment;
import day01.huy.hci_project.ultis.ColorGradient;
import day01.huy.hci_project.ultis.ItemGenerator;
import day01.huy.hci_project.ultis.UnitConverter;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private LinearLayout layoutSuggest;
    private Button btnFavorite, btnRate;
    private RelativeLayout layoutRecipeImage;
    private TextView txtRecipeTitle, txtRating;
    private DisplayMetrics displayMetrics;
    private TabLayout tabDots;
    private final RecipeData recipeData = new RecipeData();
    private final List<Fragment> fragmentList = new ArrayList<>();
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        LinearLayout.LayoutParams layoutParams;
        viewPager = findViewById(R.id.pagerRecipe);
        layoutRecipeImage = findViewById(R.id.layoutRecipeImage);
        txtRecipeTitle = findViewById(R.id.txtRecipeTitle);
        btnFavorite = findViewById(R.id.btnFavorite);
        tabDots = findViewById(R.id.tabDots);
        txtRating = findViewById(R.id.txtRating);
        btnRate = findViewById(R.id.btnRate);
        layoutSuggest = findViewById(R.id.layoutSuggest);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Intent intent = getIntent();
        recipe = recipeData.findRecipeById(intent.getLongExtra("id", 0));

        if (recipe == null) {
            Toast.makeText(this, "Unknown Recipe", Toast.LENGTH_SHORT).show();
            finish();
        }
        List<String> cooks = recipeData.getChefsMakeSameRecipe(recipe.getTitle());
        initRecipeInstructions(cooks);
        pagerAdapter = new SlicePagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (displayMetrics.heightPixels * 3) / 10);
        layoutRecipeImage.setLayoutParams(layoutParams);
        layoutRecipeImage.setBackgroundResource(getResources()
                .getIdentifier("image_food_" + recipe.getImageLink(), "drawable", getPackageName()));
        RelativeLayout.LayoutParams btnLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btnLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        btnLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btnLayout.bottomMargin = 10;
        btnLayout.rightMargin = (displayMetrics.widthPixels * 1) / 30;
        btnFavorite.setLayoutParams(btnLayout);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (displayMetrics.heightPixels * 1) / 10);
        txtRecipeTitle.setLayoutParams(layoutParams);
        txtRecipeTitle.setText(recipe.getTitle());
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                UnitConverter.getPixelValue(1150, this));
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        viewPager.setLayoutParams(layoutParams);

        tabDots.setupWithViewPager(viewPager);

        if (recipeData.isContainedInFavorite(recipe) != null) {
            btnFavorite.setText("Bỏ Thích");
            btnFavorite.setBackground(ColorGradient.getRedGradientBlackGray(this));
        } else {
            btnFavorite.setText("Thích");
            btnFavorite.setBackground(ColorGradient.getRedGradientOrange(this));
        }
        initHorizontalCardsView(recipeData.getFavorites(), layoutSuggest);
    }

    public void clickToFavorite(View view) {
        String text = (String) btnFavorite.getText();
        if (text.equalsIgnoreCase("thích")) {
            recipeData.addToFavorite(recipe);
            btnFavorite.setText("Bỏ Thích");
            btnFavorite.setBackground(ColorGradient.getRedGradientBlackGray(this));
        } else {
            recipeData.removeFromFavorite(recipe);
            btnFavorite.setText("Thích");
            btnFavorite.setBackground(ColorGradient.getRedGradientOrange(this));
        }
    }

    public void clickToFinish(View view) {
        finish();
    }

    private void initHorizontalCardsView(@NotNull List<Recipe> list, LinearLayout layout) {
        for (Recipe recipe : list) {
            ItemGenerator.createCardViewLinearLayout(recipe, layout, this, getResources().getColor(R.color.white));
        }
    }

    private void initRecipeInstructions(@NotNull List<String> cooks) {
        RecipeContentFragment fragment = null;
        for (String cook : cooks) {
            fragment = new RecipeContentFragment();
            fragment.getDataFromParent(recipeData.getRecipesSameChef(cook, 5, recipe.getTitle()), cook);
            fragmentList.add(fragment);
        }
    }
}
