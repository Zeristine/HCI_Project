package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class FavoriteActivity extends AppCompatActivity {

    private GridLayout glFavorite;
    private TextView txtTitle;
    private final RecipeData recipeData = new RecipeData();
    private List<Recipe> favorites;
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        glFavorite = findViewById(R.id.glFavorite);
        txtTitle = findViewById(R.id.txtTitle);
        favorites = recipeData.getFavorites();
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        txtTitle.setTextSize((displayMetrics.widthPixels * 1) / 40);
        setUpRecipeGridLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        favorites = recipeData.getFavorites();
        setUpRecipeGridLayout();
    }

    public void clickToFinish(View view) {
        finish();
    }

    private void setUpRecipeGridLayout() {
        glFavorite.removeAllViews();
        double row = favorites.size() / 2;
        int rowCount = (int) row;
        if ((row * 10) % 2 != 0) {
            rowCount++;
        }
        glFavorite.setColumnCount(2);
        glFavorite.setRowCount(rowCount);
        for (Recipe recipe : favorites) {
            ItemGenerator.createCardView(recipe, glFavorite, this, getResources().getColor(R.color.white));
        }
    }
}
