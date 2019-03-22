package day01.huy.hci_project.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import day01.huy.hci_project.DetailActivity;
import day01.huy.hci_project.PostRecipeActivity;
import day01.huy.hci_project.R;
import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.data.SessionData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class RecipeContentFragment extends Fragment {

    private List<Recipe> chefRecipes = new ArrayList<>();
    private String chef = "";
    private String title = "";
    private float rate = 0;
    private final RecipeData recipeData = new RecipeData();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_recipe_detail_content, container, false);
        LinearLayout layoutChef = rootView.findViewById(R.id.layoutChef);
        TextView txtChef = rootView.findViewById(R.id.txtChef);
        TextView txtAuthor = rootView.findViewById(R.id.txtAuthor);
        TextView txtDate = rootView.findViewById(R.id.txtDate);
        final TextView txtMainIngredient = rootView.findViewById(R.id.txtMain);
        final Button btnRate = rootView.findViewById(R.id.btnRate);
        final RatingBar rbRecipe = rootView.findViewById(R.id.rbRecipe);
        final RatingBar rbAverage = rootView.findViewById(R.id.rbRecipeAverage);
        Button btnUpdate = rootView.findViewById(R.id.btnUpdate);
        final Button btnDelete = rootView.findViewById(R.id.btnDelete);
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
        final Recipe currentRecipe = recipeData.getRecipeByTitleAndChef(chef, title);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Bạn đang xóa " + (recipeData.isContributed(currentRecipe) ? "bài đóng góp" : "bài đăng")
                        + " của bạn!");
                builder.setMessage("Bạn có chắc chắn xóa ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        recipeData.removeFromRecipe(currentRecipe, recipeData.isContributed(currentRecipe) ? "contribute" : "post");
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putExtra("name", currentRecipe.getTitle());
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PostRecipeActivity.class);
                intent.putExtra("id", currentRecipe.getId());
                intent.putExtra("title", currentRecipe.getTitle());
                intent.putExtra("content", currentRecipe.getContent());
                intent.putExtra("imageLink", currentRecipe.getImageLink());
                intent.putExtra("nguyenlieuchinh", txtMainIngredient.getText().toString());
                intent.putExtra("chinhsua", true);
                startActivity(intent);
            }
        });
        initHorizontalCardsView(chefRecipes, layoutChef);
        txtChef.setText("Các món ăn khác bởi " + chef);
        txtAuthor.setText("Người đăng: " + chef);
        if (currentRecipe.getAuthor().equals(SessionData.getUsername())) {
            btnUpdate.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }
//        txtDate.setText("Ngày đăng: ");
        return rootView;
    }

    private void initHorizontalCardsView(@NotNull List<Recipe> list, LinearLayout layout) {
        for (Recipe recipe : list) {
            ItemGenerator.createCardViewLinearLayout(recipe, layout, getContext(), getResources().getColor(R.color.white));
        }
    }

    public void getDataFromParent(List<Recipe> chefRecipes, String chef, float rate, String title) {
        this.chefRecipes = chefRecipes;
        this.chef = chef;
        this.rate = rate;
        this.title = title;
    }
}
