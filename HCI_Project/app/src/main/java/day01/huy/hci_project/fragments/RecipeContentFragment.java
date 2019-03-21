package day01.huy.hci_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.R;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class RecipeContentFragment extends Fragment {

    private List<Recipe> chefRecipes = new ArrayList<>();
    private String chef = "";
    private float rate = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_recipe_detail_content, container, false);
        LinearLayout layoutChef = rootView.findViewById(R.id.layoutChef);
        TextView txtChef = rootView.findViewById(R.id.txtChef);
        TextView txtAuthor = rootView.findViewById(R.id.txtAuthor);
        TextView txtDate = rootView.findViewById(R.id.txtDate);
        final Button btnRate = rootView.findViewById(R.id.btnRate);
        final RatingBar rbRecipe = rootView.findViewById(R.id.rbRecipe);
        final RatingBar rbAverage = rootView.findViewById(R.id.rbRecipeAverage);
        rbAverage.setRating(rate);
        final TextView txtRating = rootView.findViewById(R.id.txtRating);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float value = rbRecipe.getRating();
                txtRating.setText("Đánh giá của bạn");
                value = (value + rate) / 2;
                rbAverage.setRating(value);
                rbRecipe.setIsIndicator(true);
                Toast.makeText(getContext(), "Cảm ơn bạn đã đánh giá", Toast.LENGTH_SHORT).show();
                btnRate.setVisibility(View.GONE);
            }
        });
        initHorizontalCardsView(chefRecipes, layoutChef);
        txtChef.setText(chef + "'s Other Recipes");
        txtAuthor.setText("Người đăng: " + chef);
//        txtDate.setText("Ngày đăng: ");
        return rootView;
    }

    private void initHorizontalCardsView(@NotNull List<Recipe> list, LinearLayout layout) {
        for (Recipe recipe : list) {
            ItemGenerator.createCardViewLinearLayout(recipe, layout, getContext(), getResources().getColor(R.color.white));
        }
    }

    public void getDataFromParent(List<Recipe> chefRecipes, String chef, float rate) {
        this.chefRecipes = chefRecipes;
        this.chef = chef;
        this.rate = rate;
    }
}
