package day01.huy.hci_project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import day01.huy.hci_project.R;
import day01.huy.hci_project.ultis.ItemGenerator;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends Fragment {

    private String title;
    private List<String> ingredients;
    private List<String> selected;

    public IngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout mainLayout = (LinearLayout) inflater.inflate(R.layout.layout_pick_ingredient_fragment, null);
//        Bundle bundle = getArguments();
//        String title = bundle.getString("title");
//        List<String> ingredients = bundle.getStringArrayList("ingredients");
//        List<String> selectedIngredient = bundle.getStringArrayList("selected");

        TextView txtTitle = mainLayout.findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        for (String ingredient : ingredients) {
            ItemGenerator.createCheckBoxItem(ingredient, mainLayout, getActivity(), selected);
        }
        return mainLayout;
    }

    public void setResource(String title, List<String> ingredients, List<String> selected) {
        this.title = title;
        this.ingredients = ingredients;
        this.selected = selected;
    }
}
