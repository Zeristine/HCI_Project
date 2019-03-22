package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class HomeActivity extends AppCompatActivity {

    private final RecipeData recipeData = new RecipeData();
    private GridLayout glFavorite, glRate;
    private ImageView imgNotFound1, imgNotFound2;
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        glFavorite = findViewById(R.id.glFavorite);
        glRate = findViewById(R.id.glRate);
        imgNotFound1 = findViewById(R.id.imgNotFound1);
        imgNotFound2 = findViewById(R.id.imgNotFound2);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        addRecipesToGrid(recipeData.getFavorites(), imgNotFound1, glFavorite);
        addRecipesToGrid(recipeData.getFavorites(), imgNotFound2, glRate);
    }

    private void addRecipesToGrid(List<Recipe> list, ImageView imgNotFound, GridLayout layout) {
        layout.removeAllViews();
        if (list.isEmpty()) {
            imgNotFound.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        } else {
            double row = list.size() / 2.0;
            int rowCount = (int) row;
            if ((row * 10.0) % 2.0 != 0) {
                rowCount++;
            }
            if (list.size() == 1) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((displayMetrics.widthPixels / 2),
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
            layout.setRowCount(rowCount);
            for (Recipe recipe : list) {
                ItemGenerator.createCardViewGridLayout(recipe, layout, this, R.color.white);
            }
            imgNotFound.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
    }
}
