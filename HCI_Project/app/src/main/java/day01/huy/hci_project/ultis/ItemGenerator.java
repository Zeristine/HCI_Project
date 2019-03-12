package day01.huy.hci_project.ultis;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import day01.huy.hci_project.DetailActivity;
import day01.huy.hci_project.R;
import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.dto.Recipe;

public class ItemGenerator {

    private static final IngredientData data = new IngredientData();

    public static void createCardView(@NotNull final Recipe recipe, GridLayout gridLayout, final Context context, int color) {
        CardView recipeCard = new CardView(context);
        recipeCard.setCardElevation(8);
        recipeCard.setRadius(10);
        recipeCard.setCardBackgroundColor(color);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f)
                , GridLayout.spec(GridLayout.UNDEFINED, 1f));
        layoutParams.setMargins(20, 20, 20, 20);
        recipeCard.setLayoutParams(layoutParams);
        recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("check", false);
                intent.putExtra("image", recipe.getImageLink());
                intent.putExtra("title", recipe.getTitle());
                context.startActivity(intent);
            }
        });

        View recipeView = LayoutInflater.from(context).inflate(R.layout.layout_card_view_recipe, null);
        ImageView imgRecipe = recipeView.findViewById((R.id.imgRecipeImage));
        TextView txtTitle = recipeView.findViewById(R.id.txtRecipeTitle);
        TextView txtTimer = recipeView.findViewById(R.id.txtTimer);
        TextView txtAuthor = recipeView.findViewById(R.id.txtAuthor);
        int resId = getResId("image_food_" + recipe.getImageLink() + "_small",
                "drawable", context.getPackageName(), context);

        if (resId == 0) {
            imgRecipe.setImageResource(R.drawable.icon_no_image);
        } else {
            imgRecipe.setImageResource(resId);
        }
        txtTitle.setText(recipe.getTitle());
        txtTimer.setText(CalendarCal.getCardAge(recipe.getCreatedDate()));
        txtAuthor.setText(recipe.getAuthor());

        recipeCard.addView(recipeView);
        gridLayout.addView(recipeCard);
    }

    public static void createCheckBoxItem(final String text, LinearLayout linearLayout, final Context context, @NotNull final List<String> selectedList) {

        final CheckBox checkBox = new CheckBox(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                60);
        checkBox.setLayoutParams(layoutParams);
        checkBox.setTextSize(20);
        checkBox.setTextColor(context.getResources().getColor(R.color.black));

        checkBox.setText(text);
        if (selectedList.contains(text)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()) {
                    if (selectedList.contains(text)) {
                        selectedList.remove(text);
                    }
                } else {
                    selectedList.add(text);
                }
            }
        });
        linearLayout.addView(checkBox);
    }

    public static View createLine(Context context) {
        View line = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                1);
        line.setLayoutParams(layoutParams);
        line.setBackgroundColor(context.getResources().getColor(R.color.black));
        return line;
    }

    public static int getResId(String resourceName, String type, String projectPackage, Context context) {
        try {
            return context.getResources()
                    .getIdentifier(resourceName, type, projectPackage);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void createIngredientRow(final String text, final String imageLink,
                                           LinearLayout linearLayout, final Context context,
                                           @NotNull final List<String> selectedList,
                                           final ImageButton button, final List<Ingredient> main) {
        final LinearLayout ingredientRow = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_pick_ingredient_row, null);
        final ImageView img = ingredientRow.findViewById(R.id.imgIngredient);
        final TextView txt = ingredientRow.findViewById(R.id.txtIngredient);
        int resId = getResId("icon_ingre_" + imageLink + "_small", "drawable", context.getPackageName(), context);
        if (resId == 0) {
            img.setImageResource(R.drawable.icon_no_image);
        } else {
            img.setImageResource(resId);
        }
        txt.setText(text);
        if (selectedList.contains(text)) {
            ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.brown100));
            txt.setTextColor(context.getResources().getColor(R.color.white));
            if (data.hasContain(main, text)) {
                button.setEnabled(true);
                button.setBackground(ColorGradient.getOrangeGradientCircle(context));
            }
        } else {
            ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.white));
            txt.setTextColor(context.getResources().getColor(R.color.black));
        }
        ingredientRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable background = v.getBackground();
                if (background instanceof ColorDrawable) {
                    if (((ColorDrawable) background).getColor() == context.getResources().getColor(R.color.white)) {
                        ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.brown100));
                        txt.setTextColor(context.getResources().getColor(R.color.white));
                        selectedList.add(text);
                        if (data.hasContain(main, text)) {
                            button.setEnabled(true);
                            button.setBackground(ColorGradient.getOrangeGradientCircle(context));
                        }
                    } else {
                        ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.white));
                        txt.setTextColor(context.getResources().getColor(R.color.black));
                        if (selectedList.contains(text)) {
                            selectedList.remove(text);
                            if (!data.hasContainOneMainIngredient(main, selectedList)) {
                                button.setEnabled(false);
                                button.setBackground(ColorGradient.getGreyGradientCircle(context));
                            }
                        }
                    }
                }
            }
        });
        linearLayout.addView(ingredientRow);
    }
}
