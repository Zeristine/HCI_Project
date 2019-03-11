package day01.huy.hci_project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import day01.huy.hci_project.R;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.ultis.ItemGenerator;
import day01.huy.hci_project.ultis.UnitConverter;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends Fragment {

    private String title;
    private List<Ingredient> ingredients;
    private List<Ingredient> mainIngredients;
    private List<String> selected;
    private boolean isFirst, isMiddle, isLast;
    private ViewPager parentViewPager;
    private ImageButton btnSearch;

    public IngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout page = (LinearLayout) inflater.inflate(R.layout.layout_pick_ingredient_fragment, null);

//        TextView txtTitle = page.findViewById(R.id.txtTitle);
//        ImageButton btnForward = page.findViewById(R.id.imgPickIngredientIconFor);
//        ImageButton btnBack = page.findViewById(R.id.imgPickIngredientIconBack);
//        LinearLayout mainLayout = page.findViewById(R.id.mainLayout);
//        txtTitle.setText(title);
//        txtTitle.setTextSize(UnitConverter.getPixelValue(15, getContext()));
//        for (Ingredient ingredient : ingredients) {
//            ItemGenerator.createIngredientRow(ingredient.getName(),ingredient.getImageLink(),
//                    mainLayout, getActivity(), selected, btnSearch, mainIngredients);
//            mainLayout.addView(ItemGenerator.createLine(getContext()));
//        }
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,
//                4f);
//        layoutParams.gravity = Gravity.CENTER;
//
//        if (isFirst == true && isMiddle == false && isLast == false) {
//            btnBack.setVisibility(View.GONE);
//            btnForward.setVisibility(View.VISIBLE);
//            layoutParams.weight = 5f;
//        }
//        if (isFirst == false && isMiddle == true && isLast == false) {
//            btnBack.setVisibility(View.VISIBLE);
//            btnForward.setVisibility(View.VISIBLE);
//        }
//        if (isFirst == false && isMiddle == false && isLast == true) {
//            btnBack.setVisibility(View.VISIBLE);
//            btnForward.setVisibility(View.GONE);
//            layoutParams.weight = 5f;
//        }
//        txtTitle.setLayoutParams(layoutParams);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickToMove(0, 1);
//            }
//        });
//        btnForward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickToMove(1, 0);
//            }
//        });
        return page;
    }

    public void setResource(String title, List<Ingredient> ingredients,
                            List<String> selected, boolean isFirst, boolean isMiddle, boolean isLast,
                            ViewPager parentViewPager, ImageButton btnSearch,
                            List<Ingredient> mainIngredients
                            ) {
        this.title = title;
        this.ingredients = ingredients;
        this.selected = selected;
        this.isFirst = isFirst;
        this.isMiddle = isMiddle;
        this.isLast = isLast;
        this.parentViewPager = parentViewPager;
        this.btnSearch = btnSearch;
        this.mainIngredients = mainIngredients;
    }

    private void clickToMove(int forwardValue, int backwardValue) {
        parentViewPager.setCurrentItem(parentViewPager.getCurrentItem() + forwardValue - backwardValue);
    }
}
