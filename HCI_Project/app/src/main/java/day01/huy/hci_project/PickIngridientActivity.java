package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.custom.SlicePagerAdapter;
import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.fragments.IngredientFragment;
import day01.huy.hci_project.ultis.ColorGradient;
import day01.huy.hci_project.ultis.UnitConverter;

public class PickIngridientActivity extends AppCompatActivity {

    private LinearLayout lstMainIngredient, lstSubIngredient;
    private ImageView imgIcon1, imgIcon2;
    private AutoCompleteTextView txtIngredient;
    private ImageButton btnSearch;
    private ViewPager viewPagerIngredient;
    private TabLayout tabDots;
    private final IngredientData recipeData = new IngredientData();
    private final List<String> selectedIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ingridient);

        int choiceValue = getIntent().getIntExtra("choice", 0);
//        lstMainIngredient = findViewById(R.id.lstMainIngredient);
//        lstSubIngredient = findViewById(R.id.lstSubIngredient);
//        imgIcon1 = findViewById(R.id.imgIcon1);
//        imgIcon2 = findViewById(R.id.imgIcon2);
        txtIngredient = findViewById(R.id.txtIngredient);
        btnSearch = findViewById(R.id.btnSearch);
        viewPagerIngredient = findViewById(R.id.vpIngredient);
        tabDots = findViewById(R.id.tabDots);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

//        imgIcon1.setImageResource(R.drawable.icons_arrow_expand);
//        imgIcon2.setImageResource(R.drawable.icons_arrow_expand);
        btnSearch.setImageResource(R.drawable.icons_right_arrow);
        switch (choiceValue) {
            case 1:
                initAdapterForView(recipeData.getNonVegetarians().get("main"),
                        recipeData.getNonVegetarians().get("sub"));
                break;
            case 2:
                initAdapterForView(recipeData.getVegetarians().get("main"),
                        recipeData.getVegetarians().get("sub"));
                break;
            case 3:
                initAdapterForView(recipeData.getDrinks().get("main"),
                        recipeData.getDrinks().get("sub"));
                break;
            default:
                initAdapterForView(null, null);
                break;
        }
    }


//    public void clickToShowMainList(View view) {
//        ViewGroup.LayoutParams layoutParams = lstSubIngredient.getLayoutParams();
//        if (layoutParams.height != 0) {
//            resizeListView(imgIcon2, lstSubIngredient);
//        }
//        resizeListView(imgIcon1, lstMainIngredient);
//    }

//    public void clickToShowSubList(View view) {
//        ViewGroup.LayoutParams layoutParams = lstMainIngredient.getLayoutParams();
//        if (layoutParams.height != 0) {
//            resizeListView(imgIcon1, lstMainIngredient);
//        }
//        resizeListView(imgIcon2, lstSubIngredient);
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        resetSearchView();
    }

    private void initListViews(List<Ingredient> main, List<Ingredient> sub) {
//        initListView(main, lstMainIngredient);
//        initListView(sub, lstSubIngredient);
        List<Fragment> list = new ArrayList<>();
        list.add(initIngredientFragment("Nguyên liệu chính", main, true, false, false, main));
        list.add(initIngredientFragment("Nguyên liêu phụ", sub, false, false, true, main));
        SlicePagerAdapter pagerAdapter = new SlicePagerAdapter(getSupportFragmentManager(), list);
        viewPagerIngredient.setAdapter(pagerAdapter);

    }

//    private void initListView(@NotNull List<String> ingredients, @NotNull LinearLayout list) {
//        list.removeAllViews();
//        for (String ingredient : ingredients) {
//            ItemGenerator.createCheckBoxItem(ingredient, list, this, selectedIngredients);
//        }
//    }

    private Fragment initIngredientFragment(String title, List<Ingredient> ingredients, boolean isFirst,
                                            boolean isMiddle, boolean isLast, List<Ingredient> main) {
        IngredientFragment fragment = new IngredientFragment();
        fragment.setResource(title, ingredients, selectedIngredients, isFirst, isMiddle, isLast, viewPagerIngredient, btnSearch, main);
        return fragment;
    }

//    private void resetSearchView() {
//        txtIngredient.setText("");
//        imgIcon1.setImageResource(R.drawable.icons_arrow_expand);
//        imgIcon2.setImageResource(R.drawable.icons_arrow_expand);
//        setListHeight(lstMainIngredient, 0);
//        setListHeight(lstSubIngredient, 0);
//    }

//    private void setListHeight(@NotNull LinearLayout list, int value) {
//        ViewGroup.LayoutParams layoutParams = list.getLayoutParams();
//        layoutParams.height = value;
//        list.setLayoutParams(layoutParams);
//    }

    private void initAdapterForView(final List<Ingredient> main, final List<Ingredient> sub) {
        if (main == null || sub == null) {
            Toast.makeText(this, "Unknown Approach", Toast.LENGTH_SHORT).show();
            finish();
        }

        initListViews(main, sub);

        List<String> ingredients = recipeData.getIngredientOneType(main, sub);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(PickIngridientActivity.this, android.R.layout.simple_dropdown_item_1line,
                        ingredients);
        txtIngredient.setAdapter(adapter);
        txtIngredient.setThreshold(1);

        txtIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);
                String result = addToSelectedList(value, main, sub);
                value = value.substring(0, 1).toUpperCase() + value.substring(1);
                switch (result) {
                    case "already":
                        value = value + " đã được chọn";
                        break;
                    case "main":
                        initListViews(main, sub);
                        viewPagerIngredient.setCurrentItem(viewPagerIngredient.getCurrentItem() - 1);
                        value = value + " được chọn";
                        break;
                    case "sub":
                        initListViews(main, sub);
                        viewPagerIngredient.setCurrentItem(viewPagerIngredient.getCurrentItem() + 1);
                        value = value + " được chọn";
                        break;
                    default:
                        value = "Unknown Approach";
                        finish();
                }
                Toast.makeText(PickIngridientActivity.this,
                        value, Toast.LENGTH_SHORT).show();
                txtIngredient.setText("");
            }
        });

        tabDots.setupWithViewPager(viewPagerIngredient);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                UnitConverter.getPixelValue(50, this),
                UnitConverter.getPixelValue(50, this));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.rightMargin = UnitConverter.getPixelValue(30, this);
        params.bottomMargin = UnitConverter.getPixelValue(30, this);
        btnSearch.setLayoutParams(params);
        btnSearch.setBackgroundDrawable(ColorGradient.getGreyGradientCircle(this));
        btnSearch.setEnabled(false);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedIngredients.isEmpty()) {
                    Intent intent = new Intent(PickIngridientActivity.this, SearchResultActivity.class);
                    intent.putStringArrayListExtra("ingredients", (ArrayList<String>) selectedIngredients);
                    startActivity(intent);
                } else {
                    Toast.makeText(PickIngridientActivity.this,
                            "Không có nguyên liệu nào được chọn. Xin hãy chọn ít nhất một.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void resizeListView(@NotNull ImageView icon, LinearLayout list) {
//        if (icon.getDrawable().getConstantState().equals(getDrawable(R.drawable.icons_arrow_expand).getConstantState())) {
//            setListHeight(list, LinearLayout.LayoutParams.WRAP_CONTENT);
//            icon.setImageResource(R.drawable.icons_arrow_collapse);
//        } else {
//            setListHeight(list, 0);
//            icon.setImageResource(R.drawable.icons_arrow_expand);
//        }
//    }

    @NotNull
    private String addToSelectedList(String value, @NotNull List<Ingredient> main, List<Ingredient> sub) {
        if (selectedIngredients.contains(value)) {
            return "already";
        }
        if (recipeData.hasContain(main, value)) {
            selectedIngredients.add(value);
            return "main";
        }
        if (recipeData.hasContain(sub, value)) {
            selectedIngredients.add(value);
            return "sub";
        }
        return "";
    }

    public void clickToFinish(View view) {
        finish();
    }

}
