package day01.huy.hci_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.R;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class RecipeContentFragment extends Fragment {

    private List<Recipe> chefRecipes = new ArrayList<>();
    private String chef = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_recipe_detail_content, container, false);
        LinearLayout layoutChef = rootView.findViewById(R.id.layoutChef);
        TextView txtChef = rootView.findViewById(R.id.txtChef);
        TextView txtAuthor = rootView.findViewById(R.id.txtAuthor);
        TextView txtDate = rootView.findViewById(R.id.txtDate);
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

    public void getDataFromParent(List<Recipe> chefRecipes, String chef) {
        this.chefRecipes = chefRecipes;
        this.chef = chef;
    }
}
