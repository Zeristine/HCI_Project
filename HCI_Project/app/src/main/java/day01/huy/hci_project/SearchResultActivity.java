package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;
import day01.huy.hci_project.ultis.UnitConverter;

public class SearchResultActivity extends AppCompatActivity {

    private final RecipeData recipeData = new RecipeData();
    private GridLayout resultGridLayout, suggestGridLayout;
    private ImageButton btnBack;
    private DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        List<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        resultGridLayout = findViewById(R.id.glResult);
        suggestGridLayout = findViewById(R.id.glSuggest);
        btnBack = findViewById(R.id.btnBack);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                UnitConverter.getPixelValue(50, this),
                UnitConverter.getPixelValue(50, this));
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.rightMargin = UnitConverter.getPixelValue(50, this);
        params.bottomMargin = UnitConverter.getPixelValue(50, this);
        btnBack.setLayoutParams(params);
        btnBack.setImageResource(R.drawable.icon_arrow_return);
        btnBack.setBackgroundResource(R.color.transparent);
        setUpGridView(searchRecipeByIngredients(getIngredientOnly(ingredients)), resultGridLayout);
        setUpGridView(recipeData.getFavorites(), suggestGridLayout);
    }

    public void clickToFinish(View view) {
        finish();
    }

    private List<String> getIngredientOnly(@NotNull List<String> fridge) {
        List<String> result = new ArrayList<>();
        for (String item : fridge) {
            StringTokenizer tokenizer = new StringTokenizer(item, "-");
            result.add(tokenizer.nextToken());
        }
        return result;
    }

    private List<Recipe> searchRecipeByIngredients(List<String> ingredients) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipeData.getRecipes()) {
            for (String ingredient : ingredients) {
                if (recipe.getIngredients().equalsIgnoreCase(ingredient)) {
                    result.add(recipe);
                    break;
                }
            }
        }
        return result;
    }

    private void setUpGridView(@NotNull List<Recipe> recipes, GridLayout layout) {
        double rowS = recipes.size() / 2;
        int rowCountS = (int) rowS;
        if ((rowS * 10) % 2 != 0) {
            rowCountS++;
        }
        if (recipes.size() == 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((metrics.widthPixels / 2),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params);
            layout.setColumnCount(1);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params);
            layout.setColumnCount(2);
        }
        layout.setColumnCount(2);
        layout.setRowCount(rowCountS);
        for (Recipe recipe : recipes) {
            ItemGenerator.createCardViewGridLayout(recipe, layout, this, R.color.white);
        }
    }
}
