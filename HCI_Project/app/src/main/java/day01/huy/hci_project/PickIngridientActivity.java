package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import day01.huy.hci_project.custom.TextAdapter;

public class PickIngridientActivity extends AppCompatActivity {

    private ListView lstMainIngredient, lstSubIngredient;
    private AutoCompleteTextView txtIngredient;
    private ImageView imgIcon1, imgIcon2;
    private ImageButton btnSearch;
    private TextAdapter subIngredientAdapter, mainIngredientAdapter;
    private final List<String> mainMan = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "trung", "thit bo", "thit heo", "thit ga");
    private final List<String> subMan = Arrays.asList("aaa", "bbb", "ccc");
    private final List<String> mainChay = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "gao lut");
    private final List<String> subChay = Arrays.asList("ddd", "eee", "fff");
    private final List<String> mainDrink = Arrays.asList("pepsi", "coca cola", "Coffee", "Black Coffee");
    private final List<String> subDrink = Arrays.asList("ggg", "hhh", "iii");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ingridient);

        int choiceValue = getIntent().getIntExtra("choice", 0);
        lstMainIngredient = findViewById(R.id.lstMainIngredient);
        lstSubIngredient = findViewById(R.id.lstSubIngredient);
        txtIngredient = findViewById(R.id.txtIngredient);
        imgIcon1 = findViewById(R.id.imgIcon1);
        imgIcon2 = findViewById(R.id.imgIcon2);
        btnSearch = findViewById(R.id.btnSearch);

        imgIcon1.setImageResource(R.drawable.icons_double_down);
        imgIcon2.setImageResource(R.drawable.icons_double_down);
        btnSearch.setImageResource(R.drawable.icons_search);
        switch (choiceValue) {
            case 1:
                initAdapterForView(mainMan, subMan);
                break;
            case 2:
                initAdapterForView(mainChay, subChay);
                break;
            case 3:
                initAdapterForView(mainDrink, subDrink);
                break;
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> ingredients = new ArrayList<>(mainIngredientAdapter.getSelectedItems());
                if (!ingredients.isEmpty()) {
                    ingredients.addAll(subIngredientAdapter.getSelectedItems());
                    Intent intent = new Intent(PickIngridientActivity.this, SearchResultActivity.class);
                    intent.putStringArrayListExtra("ingredients", (ArrayList<String>) ingredients);
                    startActivity(intent);
                } else {
                    Toast.makeText(PickIngridientActivity.this,
                            "No ingredient selected! Please choose at least one.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void clickToShowMainList(View view) {
        resizeListView(imgIcon1, lstMainIngredient);
    }

    public void clickToShowSubList(View view) {
        resizeListView(imgIcon2, lstSubIngredient);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetSearchView();
    }

    private void initListViews(List<String> main, List<String> sub) {
        initMainListView(true, main);
        initSubListView(true, sub);
    }

    private void initMainListView(boolean isGoodToResetAdapter, List<String> main) {
        if (isGoodToResetAdapter) {
            mainIngredientAdapter = new TextAdapter(this, R.layout.layout_list_view_simple_row, main);
        }
        lstMainIngredient.setAdapter(mainIngredientAdapter);
    }

    private void initSubListView(boolean isGoodToResetAdapter, List<String> sub) {
        if (isGoodToResetAdapter) {
            subIngredientAdapter = new TextAdapter(this, R.layout.layout_list_view_simple_row, sub);
        }
        lstSubIngredient.setAdapter(subIngredientAdapter);
    }

    private void resetSearchView() {
        txtIngredient.setText("");
        imgIcon1.setImageResource(R.drawable.icons_double_down);
        imgIcon2.setImageResource(R.drawable.icons_double_down);
        setListHeight(lstMainIngredient, 0);
        setListHeight(lstSubIngredient, 0);
    }

    private void setListHeight(@NotNull ListView list, int value) {
        ViewGroup.LayoutParams layoutParams = list.getLayoutParams();
        layoutParams.height = value;
        list.setLayoutParams(layoutParams);
    }

    private LinearLayout.LayoutParams getWeightParams(float value) {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, value);
    }

    private void initAdapterForView(final List<String> main, final List<String> sub) {
        List<String> ingredients = new ArrayList<>(main);
        ingredients.addAll(sub);
        initListViews(main, sub);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(PickIngridientActivity.this, android.R.layout.simple_dropdown_item_1line,
                        ingredients);
        txtIngredient.setAdapter(adapter);
        txtIngredient.setThreshold(1);
        txtIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);
                if (subIngredientAdapter.addSelectedIngredient(value)) {
                    initSubListView(false, main);
                } else if (mainIngredientAdapter.addSelectedIngredient(value)) {
                    initMainListView(false, sub);
                }
                Toast.makeText(PickIngridientActivity.this,
                        "The ingredient " + value + " has been selected", Toast.LENGTH_SHORT).show();
                txtIngredient.setText("");
            }
        });
    }

    private void resizeListView(@NotNull ImageView icon, ListView list) {
        if (icon.getDrawable().getConstantState().equals(getDrawable(R.drawable.icons_double_down).getConstantState())) {
            setListHeight(list, ViewGroup.LayoutParams.WRAP_CONTENT);
            icon.setImageResource(R.drawable.icons_double_up);
        } else {
            setListHeight(list, 0);
            icon.setImageResource(R.drawable.icons_double_down);
        }
    }
}
