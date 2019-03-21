package day01.huy.hci_project.ultis;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import day01.huy.hci_project.DetailActivity;
import day01.huy.hci_project.R;
import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.dto.Recipe;

public class ItemGenerator {

    private static final IngredientData data = new IngredientData();

    public static CardView createCardView(@NotNull final Recipe recipe, final Context context, int color) {
        CardView recipeCard = new CardView(context);
        recipeCard.setCardElevation(8);
        recipeCard.setRadius(10);
        recipeCard.setCardBackgroundColor(color);
        recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", recipe.getId());
                context.startActivity(intent);
            }
        });

        View recipeView = LayoutInflater.from(context).inflate(R.layout.layout_card_view_recipe, null);
        ImageView imgRecipe = recipeView.findViewById((R.id.imgRecipeImage));
        TextView txtTitle = recipeView.findViewById(R.id.txtRecipeTitle);
        TextView txtContent = recipeView.findViewById(R.id.txtContent);
        int resId = getResId("image_food_" + recipe.getImageLink() + "_small",
                "drawable", context.getPackageName(), context);

        if (resId == 0) {
            imgRecipe.setImageResource(R.drawable.icon_no_image);
        } else {
            imgRecipe.setImageResource(resId);
        }
        txtTitle.setText(recipe.getTitle());
        String content = recipe.getContent();
        if (content.length() > 20) {
            content = content.substring(0, 20) + "...";
        }
        txtContent.setText(content);

        recipeCard.addView(recipeView);
        return recipeCard;
    }

    public static void createCardViewGridLayout(@NotNull final Recipe recipe, @NotNull GridLayout gridLayout, final Context context, int color) {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f)
                , GridLayout.spec(GridLayout.UNDEFINED, 1f));
        layoutParams.setMargins(20, 20, 20, 20);
        CardView cardView = createCardView(recipe, context, color);
        cardView.setLayoutParams(layoutParams);
        gridLayout.addView(cardView);
    }

    public static void createCardViewLinearLayout(@NotNull final Recipe recipe, @NotNull LinearLayout linearLayout, final Context context, int color) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 450);
        layoutParams.setMargins(20, 20, 20, 20);
        CardView cardView = createCardView(recipe, context, color);
        cardView.setLayoutParams(layoutParams);
        linearLayout.addView(cardView);
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
                                           final ImageButton button, final List<Ingredient> main,
                                           final List<String> selectedMain) {
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
                if (!selectedMain.contains(text)) {
                    ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.white));
                    txt.setTextColor(context.getResources().getColor(R.color.black));
                }
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
                        selectedList.add(text);
                        ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.brown100));
                        txt.setTextColor(context.getResources().getColor(R.color.white));
                        if (data.hasContain(main, text)) {
                            if (selectedMain.size() == 1) {
                                Toast.makeText(context, "Bạn chỉ có thể chọn 1 nguyên liệu chính thôi", Toast.LENGTH_SHORT).show();
                                selectedList.remove(text);
                                ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.white));
                                txt.setTextColor(context.getResources().getColor(R.color.black));
                            } else {
                                selectedMain.add(text);
                                button.setBackground(ColorGradient.getOrangeGradientCircle(context));
                            }
                        }
                    } else {
                        ingredientRow.setBackgroundColor(context.getResources().getColor(R.color.white));
                        txt.setTextColor(context.getResources().getColor(R.color.black));
                        if (selectedList.contains(text)) {
                            selectedList.remove(text);
                            if (!data.hasContainOneMainIngredient(main, selectedList)) {
                                button.setBackground(ColorGradient.getGreyGradientCircle(context));
                            }
                            if (data.hasContain(main, text)) {
                                selectedMain.remove(text);
                            }
                        }
                    }
                }
            }
        });
        linearLayout.addView(ingredientRow);
    }

    public static void createAddIngredientRow(final Context context, final LinearLayout layout, @NotNull String value,
                                              final List<String> subIngredients, final List<String> mainIngredients,
                                              final boolean isMain) {
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_edit_text_with_cancel, layout, false);
        final TextView textView = view.findViewById(R.id.lblIngredient);
        final EditText editText = view.findViewById(R.id.txtIngredient);
        final ImageButton button = view.findViewById(R.id.btnCancel);
        if (isMain) {
            button.setVisibility(View.GONE);
        }
        if (!value.isEmpty()) {
            editText.setHint(value);
            textView.setText(value);
            editText.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = editText.getText().toString();
                    String oldValue = textView.getText().toString();
                    if (value.isEmpty()) {
                        if (oldValue.isEmpty()) {
                            if (isMain) {
                                Toast.makeText(context, "Không thể không có nguyên liệu chính!", Toast.LENGTH_SHORT).show();
                                editText.setHint("Cần có nguyên liệu chính");
                                if (mainIngredients.contains(oldValue)) {
                                    mainIngredients.remove(oldValue);
                                }
                            } else {
                                layout.removeView(view);
                                if (subIngredients.contains(oldValue)) {
                                    subIngredients.remove(oldValue);
                                }
                            }
                        } else {
                            editText.setVisibility(View.INVISIBLE);
                            textView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        textView.setText(value);
                        editText.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        if (isMain) {
                            mainIngredients.add(value);
                        } else {
                            subIngredients.add(value);
                        }
                    }
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
                editText.requestFocus();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.isFocused()) {
                    editText.clearFocus();
                    if (view != null) {
                        layout.removeView(view);
                    }
                } else {
                    layout.removeView(view);
                }
            }
        });
        layout.addView(view);
        if (value.isEmpty()) {
            editText.requestFocus();
        }
    }

    public static String checkSelectedListContainIngredientWithAmount(List<String> selected, String ingredient) {
        for (String item : selected) {
            if (item.contains(ingredient)) {
                return item;
            }
        }
        return null;
    }
}
