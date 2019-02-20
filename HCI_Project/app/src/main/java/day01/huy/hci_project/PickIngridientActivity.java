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
    private final List<String> mainIngredient = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "trung", "thit bo", "thit heo", "thit ga");
    private final List<String> subIngredient = Arrays.asList("aaa", "bbb", "ccc");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_ingridient);
        lstMainIngredient = findViewById(R.id.lstMainIngredient);
        lstSubIngredient = findViewById(R.id.lstSubIngredient);
        txtIngredient = findViewById(R.id.txtIngredient);
        imgIcon1 = findViewById(R.id.imgIcon1);
        imgIcon2 = findViewById(R.id.imgIcon2);
        btnSearch = findViewById(R.id.btnSearch);

        List<String> ingredients = new ArrayList<>(mainIngredient);
        ingredients.addAll(subIngredient);

        imgIcon1.setImageResource(R.drawable.icons_double_down);
        imgIcon2.setImageResource(R.drawable.icons_double_down);
        btnSearch.setImageResource(R.drawable.icons_search);
        initListViews();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(PickIngridientActivity.this, android.R.layout.simple_dropdown_item_1line,
                        ingredients);
        txtIngredient.setAdapter(adapter);
        txtIngredient.setThreshold(3);
        txtIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);
                if (subIngredientAdapter.addSelectedIngredient(value)) {
                    initSubListView(false);
                } else if (mainIngredientAdapter.addSelectedIngredient(value)) {
                    initMainListView(false);
                }
                Toast.makeText(PickIngridientActivity.this,
                        "The ingredient " + value + " has been selected", Toast.LENGTH_SHORT).show();
                txtIngredient.setText("");
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> ingredients = new ArrayList<>(mainIngredientAdapter.getSelectedItems());
                ingredients.addAll(subIngredientAdapter.getSelectedItems());
                Intent intent = new Intent(PickIngridientActivity.this, SearchResultActivity.class);
                intent.putStringArrayListExtra("ingredients", (ArrayList<String>) ingredients);
                startActivity(intent);
            }
        });

    }


    public void clickToShowMainList(View view) {
        if (imgIcon1.getDrawable().getConstantState().equals(getDrawable(R.drawable.icons_double_down).getConstantState())) {
            setListHieght(lstMainIngredient, ViewGroup.LayoutParams.WRAP_CONTENT);
            imgIcon1.setImageResource(R.drawable.icons_double_up);
        } else {
            setListHieght(lstMainIngredient, 0);
            imgIcon1.setImageResource(R.drawable.icons_double_down);
        }
    }

    public void clickToShowSubList(View view) {
        if (imgIcon2.getDrawable().getConstantState().equals(getDrawable(R.drawable.icons_double_down).getConstantState())) {
            setListHieght(lstSubIngredient, ViewGroup.LayoutParams.WRAP_CONTENT);
            imgIcon2.setImageResource(R.drawable.icons_double_up);
        } else {
            setListHieght(lstSubIngredient, 0);
            imgIcon2.setImageResource(R.drawable.icons_double_down);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetSearchView();
    }

    private void initListViews() {
        initMainListView(true);
        initSubListView(true);
    }

    private void initMainListView(boolean isGoodToResetAdapter) {
        if (isGoodToResetAdapter) {
            mainIngredientAdapter = new TextAdapter(this, R.layout.layout_list_view_simple_row, mainIngredient);
        }
        lstMainIngredient.setAdapter(mainIngredientAdapter);
    }

    private void initSubListView(boolean isGoodToResetAdapter) {
        if (isGoodToResetAdapter) {
            subIngredientAdapter = new TextAdapter(this, R.layout.layout_list_view_simple_row, subIngredient);
        }
        lstSubIngredient.setAdapter(subIngredientAdapter);
    }

    private void resetSearchView() {
        txtIngredient.setText("");
        imgIcon1.setImageResource(R.drawable.icons_double_down);
        imgIcon2.setImageResource(R.drawable.icons_double_down);
        setListHieght(lstMainIngredient, 0);
        setListHieght(lstSubIngredient, 0);
    }

    private void setListHieght(ListView list, int value) {
        ViewGroup.LayoutParams layoutParams = list.getLayoutParams();
        layoutParams.height = value;
        list.setLayoutParams(layoutParams);
    }

    private LinearLayout.LayoutParams getWeightParams(float value) {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, value);
    }
}
