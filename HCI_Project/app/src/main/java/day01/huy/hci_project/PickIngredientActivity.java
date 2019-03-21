package day01.huy.hci_project;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.ultis.ColorGradient;
import day01.huy.hci_project.ultis.ItemGenerator;
import day01.huy.hci_project.ultis.UnitConverter;

public class PickIngredientActivity extends TabActivity {

    private AutoCompleteTextView txtIngredient;
    private ImageButton btnSearch;
    private LinearLayout mainLayout, subLayout;
    private TabHost tabHost;
    private final IngredientData recipeData = new IngredientData();
    private String mainIngredient;
    private final List<String> selectedIngredients = new ArrayList<>();
    private final List<String> selectedMain = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ingredient);

        int choiceValue = getIntent().getIntExtra("choice", 0);
        txtIngredient = findViewById(R.id.txtIngredient);
        btnSearch = findViewById(R.id.btnSearch);
        mainLayout = findViewById(R.id.mainLayout);
        subLayout = findViewById(R.id.subLayout);
        tabHost = getTabHost();
//        viewPagerIngredient = findViewById(R.id.vpIngredient);
        TabHost.TabSpec spec = tabHost.newTabSpec("Chính");
        spec.setIndicator("Nguyên liệu\nchính");
        spec.setContent(R.id.mainLayout);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Phụ");
        spec.setIndicator("Nguyên liệu\nphụ");
        spec.setContent(R.id.subLayout);
        tabHost.addTab(spec);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
            View v = tabHost.getTabWidget().getChildTabViewAt(i);
            v.setBackgroundResource(R.drawable.tab_indicator);
            TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setLayoutParams(layoutParams);
            tv.setGravity(Gravity.CENTER);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btnSearch.setImageResource(R.drawable.icon_right_arrow);
        switch (choiceValue) {
            case 1:
                initAdapterForView(recipeData.getNonVegetarians().get("main"),
                        recipeData.getNonVegetarians().get("sub"), "nam");
                break;
            case 2:
                initAdapterForView(recipeData.getVegetarians().get("main"),
                        recipeData.getVegetarians().get("sub"), "chay");
                break;
            case 3:
                initAdapterForView(recipeData.getDrinks().get("main"),
                        recipeData.getDrinks().get("sub"), "nuoc");
                break;
            default:
                initAdapterForView(null, null, null);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initListViews(List<Ingredient> main, List<Ingredient> sub) {
//        List<Fragment> list = new ArrayList<>();
        mainLayout.removeAllViews();
        subLayout.removeAllViews();
        mainLayout.addView(createView(main, main, true, false, false));
        subLayout.addView(createView(sub, main, false, false, true));
//        SlicePagerAdapter pagerAdapter = new SlicePagerAdapter(getSupportFragmentManager(), list);
//        viewPagerIngredient.setAdapter(pagerAdapter);
    }

//    private Fragment initIngredientFragment(String title, List<Ingredient> ingredients, boolean isFirst,
//                                            boolean isMiddle, boolean isLast, List<Ingredient> main) {
//        IngredientFragment fragment = new IngredientFragment();
//        fragment.setResource(title, ingredients, selectedIngredients, isFirst, isMiddle, isLast, viewPagerIngredient, btnSearch, main);
//        return fragment;
//    }

    private View createView(@NotNull List<Ingredient> ingredients, List<Ingredient> main,
                            boolean isFirst, boolean isMiddle, boolean isLast) {
        LinearLayout page = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_pick_ingredient_fragment, null);

//        TextView txtTitle = page.findViewById(R.id.txtTitle);
//        ImageButton btnForward = page.findViewById(R.id.imgPickIngredientIconFor);
//        ImageButton btnBack = page.findViewById(R.id.imgPickIngredientIconBack);
        LinearLayout mainLayout = page.findViewById(R.id.mainLayout);
//        txtTitle.setText(title);
//        txtTitle.setTextSize(UnitConverter.getPixelValue(15, this));
        for (Ingredient ingredient : ingredients) {
            ItemGenerator.createIngredientRow(ingredient.getName(), ingredient.getImageLink(),
                    mainLayout, PickIngredientActivity.this, selectedIngredients, btnSearch, main, selectedMain);
            mainLayout.addView(ItemGenerator.createLine(this));
        }
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,
//                4f);
//        layoutParams.gravity = Gravity.CENTER;

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

    private void initAdapterForView(final List<Ingredient> main, final List<Ingredient> sub, final String type) {
        if (main == null || sub == null || type == null) {
            Toast.makeText(this, "Unknown Approach", Toast.LENGTH_SHORT).show();
            finish();
        }

        initListViews(main, sub);

        List<String> ingredients = recipeData.getIngredientOneType(main, sub);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(PickIngredientActivity.this, android.R.layout.simple_dropdown_item_1line,
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
//                        viewPagerIngredient.setCurrentItem(viewPagerIngredient.getCurrentItem() - 1);
                        tabHost.setCurrentTab(0);
                        if (!selectedMain.isEmpty() && selectedMain.contains(value)) {
                            value = value + " được chọn";
                        } else {
                            value = "Bạn chỉ chọn được 1 nguyên liệu chính";
                        }
                        break;
                    case "sub":
                        initListViews(main, sub);
//                        viewPagerIngredient.setCurrentItem(viewPagerIngredient.getCurrentItem() + 1);
                        tabHost.setCurrentTab(1);
                        value = value + " được chọn";
                        break;
                    case "cancel":
                        value = "Đã hủy lựa chọn";
                        break;
                    default:
                        value = "Unknown Approach";
                        finish();
                }
                Toast.makeText(PickIngredientActivity.this,
                        value, Toast.LENGTH_SHORT).show();
                txtIngredient.setText("");
            }
        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                UnitConverter.getPixelValue(50, this),
                UnitConverter.getPixelValue(50, this));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.rightMargin = UnitConverter.getPixelValue(30, this);
        params.bottomMargin = UnitConverter.getPixelValue(30, this);
        btnSearch.setLayoutParams(params);
        btnSearch.setBackgroundDrawable(ColorGradient.getGreyGradientCircle(this));
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedIngredients.isEmpty()) {
                    if (recipeData.hasContainOneMainIngredient(main, selectedIngredients)) {
                        Intent intent = new Intent(PickIngredientActivity.this, IngredientAmountActivity.class);
                        intent.putStringArrayListExtra("ingredients", (ArrayList<String>) selectedIngredients);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    } else {
                        Toast.makeText(PickIngredientActivity.this,
                                "Không có nguyên liệu chính nào được chọn. Xin hãy chọn ít nhất một.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PickIngredientActivity.this,
                            "Không có nguyên liệu chính nào được chọn. Xin hãy chọn ít nhất một.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @NotNull
    private String addToSelectedList(String value, @NotNull List<Ingredient> main, List<Ingredient> sub) {
        if (ItemGenerator.checkSelectedListContainIngredientWithAmount(selectedIngredients, value) != null) {
            return "already";
        }
        selectedIngredients.add(value);
        if (recipeData.hasContain(main, value)) {
            if (selectedMain.isEmpty()) {
                selectedMain.add(value);
            }
            return "main";
        }
        if (recipeData.hasContain(sub, value)) {
            return "sub";
        }
        return "";
    }

    public void clickToFinish(View view) {
        finish();
    }

}
