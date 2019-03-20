package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class RecipeActivity extends AppCompatActivity {

    private EditText txtRecipeTitle;
    private GridLayout glResult;
    private final RecipeData recipeData = new RecipeData();
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        txtRecipeTitle = findViewById(R.id.txtRecipeTitle);
        glResult = findViewById(R.id.glResult);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        txtRecipeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                glResult.removeAllViews();
                String value = s.toString();
                List<Recipe> result = recipeData.findRecipesByTitle(value);
                setUpGridLayoutRecipeResult(result);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void clickToFinish(View view) {
        finish();
    }

    private void setUpGridLayoutRecipeResult(@NotNull List<Recipe> result) {
        double row = result.size() / 2.0;
        int rowCount = (int) row;
        if ((row * 10.0) % 2.0 != 0) {
            rowCount++;
        }
        if (result.size() == 1) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((displayMetrics.widthPixels/2),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            glResult.setLayoutParams(params);
            glResult.setColumnCount(1);
        } else {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            glResult.setLayoutParams(params);
            glResult.setColumnCount(2);
        }
        glResult.setRowCount(rowCount);
        for (Recipe recipe : result) {
            ItemGenerator.createCardViewGridLayout(recipe, glResult, RecipeActivity.this,
                    getColor(R.color.white));
        }
    }
}
