package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class HomeActivity extends AppCompatActivity {

    private final RecipeData recipeData = new RecipeData();
    private HorizontalScrollView scrollViewFavorite, scrollViewRate;
    private LinearLayout layoutFavorite, layoutRate;
    private ImageView imgNotFound1, imgNotFound2;
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgNotFound1 = findViewById(R.id.imgNotFound1);
        imgNotFound2 = findViewById(R.id.imgNotFound2);
        layoutFavorite = findViewById(R.id.layoutFavorite);
        layoutRate = findViewById(R.id.layoutRate);
        scrollViewFavorite = findViewById(R.id.scrollViewFavorite);
        scrollViewRate = findViewById(R.id.scrollViewRate);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        addRecipesToLayout(recipeData.getFavorites(), imgNotFound1, layoutFavorite, scrollViewFavorite, false);
        addRecipesToLayout(recipeData.getFavorites(), imgNotFound2, layoutRate, scrollViewRate, true);
    }

    private void addRecipesToLayout(@NotNull List<Recipe> list, ImageView imgNotFound, @NotNull LinearLayout layout, HorizontalScrollView scrollView, boolean isRated) {
        layout.removeAllViews();
        if (list.isEmpty()) {
            imgNotFound.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            for (Recipe recipe : list) {
                createCardViewLinearLayout(recipe, layout, R.color.white, isRated);
            }
            imgNotFound.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void createCardViewLinearLayout(@NotNull final Recipe recipe, @NotNull LinearLayout linearLayout, int color, boolean isRated) {
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(displayMetrics.widthPixels / 2, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10, 20, 10, 20);
        CardView cardView = createCardView(recipe, color, isRated);
        cardView.setLayoutParams(layoutParams);
        linearLayout.addView(cardView);
    }

    private CardView createCardView(@NotNull final Recipe recipe, int colorId, boolean isRated) {
        CardView recipeCard = new CardView(this);
        recipeCard.setCardElevation(8);
        recipeCard.setRadius(10);
        recipeCard.setCardBackgroundColor(getColor(colorId));
        recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra("id", recipe.getId());
                startActivity(intent);
            }
        });
        View recipeView = getLayoutInflater().inflate(R.layout.layout_card_view_recipe, null);
        ImageView imgRecipe = recipeView.findViewById((R.id.imgRecipeImage));
        TextView txtTitle = recipeView.findViewById(R.id.txtRecipeTitle);
        TextView txtContent = recipeView.findViewById(R.id.txtContent);
        int resId = ItemGenerator.getResId("image_food_" + recipe.getImageLink(),
                "drawable", getPackageName(), this);
        if (resId == 0) {
            imgRecipe.setImageResource(R.drawable.icon_no_image);
        } else {
            imgRecipe.setImageResource(resId);
        }
        txtTitle.setText(recipe.getTitle());
        String content = isRated ? ("\t⭐:"+recipe.getRate() + "/5.0\n" + recipe.getContent()) : recipe.getContent();
        if (content.length() > (isRated ? 20 : 30)) {
            content = content.substring(0, (isRated ? 20 : 30)) + "...";
        }
        txtContent.setText(content);
        txtContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        txtContent.setPadding(5, 0, 5, 0);
        recipeCard.addView(recipeView);
        return recipeCard;
    }
}
