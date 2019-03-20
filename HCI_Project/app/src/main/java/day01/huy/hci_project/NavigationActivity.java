package day01.huy.hci_project;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        final Dialog dialog = new Dialog(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout layout = new LinearLayout(this);
        Button sameRecipe = new Button(this);
        Button newRecipe = new Button(this);
        TextView textView = new TextView(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        textView.setText("Chọn cách thêm cách làm");
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        layoutParams.setMargins(10, 5, 10, 5);
        sameRecipe.setText("Thêm vào món ăn có sẵn");
        sameRecipe.setLayoutParams(layoutParams);
        sameRecipe.setBackgroundResource(R.drawable.shape_button);
        sameRecipe.setTextColor(getColor(R.color.white));
        sameRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(NavigationActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });
        newRecipe.setText("Thêm vào món ăn mới");
        newRecipe.setLayoutParams(layoutParams);
        newRecipe.setBackgroundResource(R.drawable.shape_button);
        newRecipe.setTextColor(getColor(R.color.white));
        newRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(NavigationActivity.this, PostRecipeActivity.class);
                startActivity(intent);
            }
        });
        layout.addView(textView);
        layout.addView(sameRecipe);
        layout.addView(newRecipe);
        dialog.addContentView(layout, layoutParams);
        dialog.show();
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
            glFavorite.setVisibility(View.GONE);
        } else {
            double row = favorites.size() / 2.0;
            int rowCount = (int) row;
            if ((row * 10.0) % 2.0 != 0) {
                rowCount++;
            }
            glFavorite.setColumnCount(2);
            glFavorite.setRowCount(rowCount);
            for (Recipe recipe : favorites) {
                ItemGenerator.createCardViewGridLayout(recipe, glFavorite, this, getResources().getColor(R.color.white));
            }
            imgNotFound.setVisibility(View.GONE);
            glFavorite.setVisibility(View.VISIBLE);
        }
    }
}

