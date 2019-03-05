package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import day01.huy.hci_project.custom.SlicePagerAdapter;
import day01.huy.hci_project.fragments.IngredientFragment;
import day01.huy.hci_project.ultis.ItemGenerator;

public class PickIngridientActivity extends AppCompatActivity {

    private LinearLayout lstMainIngredient, lstSubIngredient;
    private ImageView imgIcon1, imgIcon2;
    private AutoCompleteTextView txtIngredient;
    private ImageButton btnSearch;
    private ViewPager viewPagerIngredient;
    private final List<String> mainMan = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "trung", "thit bo", "thit heo", "thit ga");
    private final List<String> subMan = Arrays.asList("aaa", "bbb", "ccc");
    private final List<String> mainChay = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "gao lut");
    private final List<String> subChay = Arrays.asList("ddd", "eee", "fff");
    private final List<String> mainDrink = Arrays.asList("pepsi", "coca cola", "Coffee", "Black Coffee");
    private final List<String> subDrink = Arrays.asList("ggg", "hhh", "iii");
    private final List<String> selectedIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ingridient);

        int choiceValue = getIntent().getIntExtra("choice", 0);
//        lstMainIngredient = findViewById(R.id.lstMainIngredient);
//        lstSubIngredient = findViewById(R.id.lstSubIngredient);
        txtIngredient = findViewById(R.id.txtIngredient);
//        imgIcon1 = findViewById(R.id.imgIcon1);
//        imgIcon2 = findViewById(R.id.imgIcon2);
        btnSearch = findViewById(R.id.btnSearch);
        viewPagerIngredient = findViewById(R.id.vpIngredient);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

//        imgIcon1.setImageResource(R.drawable.icons_arrow_expand);
//        imgIcon2.setImageResource(R.drawable.icons_arrow_expand);
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
            default:
                initAdapterForView(null, null);
                break;
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedIngredients.isEmpty()) {
                    Intent intent = new Intent(PickIngridientActivity.this, SearchResultActivity.class);
                    intent.putStringArrayListExtra("ingredients", (ArrayList<String>) selectedIngredients);
                    startActivity(intent);
                } else {
                    Toast.makeText(PickIngridientActivity.this,
                            "No ingredient selected! Please choose at least one.", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void initListViews(List<String> main, List<String> sub) {
//        initListView(main, lstMainIngredient);
//        initListView(sub, lstSubIngredient);
        List<Fragment> list = new ArrayList<>();
        list.add(initIngredientFragment("Nguyên liệu chính", main));
        list.add(initIngredientFragment("Nguyên liêu phụ", sub));
        SlicePagerAdapter pagerAdapter = new SlicePagerAdapter(getSupportFragmentManager(), list);
        viewPagerIngredient.setAdapter(pagerAdapter);
    }

//    private void initListView(@NotNull List<String> ingredients, @NotNull LinearLayout list) {
//        list.removeAllViews();
//        for (String ingredient : ingredients) {
//            ItemGenerator.createCheckBoxItem(ingredient, list, this, selectedIngredients);
//        }
//    }

    private Fragment initIngredientFragment(String title, List<String> ingredients) {
        IngredientFragment fragment = new IngredientFragment();
        fragment.setResource(title, ingredients, selectedIngredients);
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

    private void initAdapterForView(final List<String> main, final List<String> sub) {
        if (main == null || sub == null) {
            Toast.makeText(this, "Unknown Approach", Toast.LENGTH_SHORT).show();
            finish();
        }

        initListViews(main, sub);

        List<String> ingredients = new ArrayList<>(main);
        ingredients.addAll(sub);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(PickIngridientActivity.this, android.R.layout.simple_dropdown_item_1line,
                        ingredients);
        txtIngredient.setAdapter(adapter);
        txtIngredient.setThreshold(1);

        txtIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);
                switch (addToSelectedList(value, main, sub)) {
                    case "already":
                        value = "The ingredient " + value + " has already been selected";
                        break;
                    case "main":
                        initListViews(main, sub);
                        viewPagerIngredient.setCurrentItem(viewPagerIngredient.getCurrentItem() - 1);
                        value = "The ingredient " + value + " has been selected";
                        break;
                    case "sub":
                        initListViews(main, sub);
                        viewPagerIngredient.setCurrentItem(viewPagerIngredient.getCurrentItem() + 1);
                        value = "The ingredient " + value + " has been selected";
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
    private String addToSelectedList(String value, @NotNull List<String> main, List<String> sub) {
        if (selectedIngredients.contains(value)) {
            return "already";
        }
        if (main.contains(value)) {
            selectedIngredients.add(value);
            return "main";
        }
        if (sub.contains(value)) {
            selectedIngredients.add(value);
            return "sub";
        }
        return "";
    }

    public void clickToFinish(View view) {
        finish();
    }

}
