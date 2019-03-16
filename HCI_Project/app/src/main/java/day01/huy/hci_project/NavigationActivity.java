package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class NavigationActivity extends AppCompatActivity {

    private final RecipeData recipeData = new RecipeData();
    private GridLayout glFavorite;
    private List<Recipe> favorites;
    private ImageView imgNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        glFavorite = findViewById(R.id.glFavorite);
        imgNotFound = findViewById(R.id.imgNotFound);
        setUpFavoriteRecipes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpFavoriteRecipes();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void clickToProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void clickToAddMeal(View view) {
        Intent intent = new Intent(this, PostRecipeActivity.class);
        startActivity(intent);
    }

    public void clickToPickIngredient(View view) {
        Intent intent = new Intent(this, DishesTypeActivity.class);
        startActivity(intent);
    }

    private void setUpFavoriteRecipes() {
        favorites = recipeData.getFavorites();
        glFavorite.removeAllViews();
        if (favorites.isEmpty()) {
            imgNotFound.setVisibility(View.VISIBLE);
            glFavorite.setVisibility(View.INVISIBLE);
        } else {
            double row = favorites.size() / 2;
            int rowCount = (int) row;
            if ((row * 10) % 2 != 0) {
                rowCount++;
            }
            glFavorite.setColumnCount(2);
            glFavorite.setRowCount(rowCount);
            for (Recipe recipe : favorites) {
                ItemGenerator.createCardViewGridLayout(recipe, glFavorite, this, getResources().getColor(R.color.white));
            }
            imgNotFound.setVisibility(View.INVISIBLE);
            glFavorite.setVisibility(View.VISIBLE);
        }
    }
}

