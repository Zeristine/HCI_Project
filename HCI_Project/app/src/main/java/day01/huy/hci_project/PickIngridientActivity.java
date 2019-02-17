package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import day01.huy.hci_project.custom.TextAdapter;

public class PickIngridientActivity extends AppCompatActivity {

    private ListView lstMainIngredient, lstSubIngredient;
    private AutoCompleteTextView txtIngredient;
    private ImageView imgIcon1, imgIcon2;
    private ImageButton btnSearch;
    private TextAdapter ingredientAdapter;
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

        imgIcon1.setImageResource(R.drawable.icons_plus);
        imgIcon2.setImageResource(R.drawable.icons_plus);
        btnSearch.setImageResource(R.drawable.icons_search);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(PickIngridientActivity.this, android.R.layout.simple_dropdown_item_1line,
                        ingredients);
        txtIngredient.setAdapter(adapter);
        txtIngredient.setThreshold(3);
        txtIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PickIngridientActivity.this,
                        "The ingredient has been selected", Toast.LENGTH_SHORT).show();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lstSubIngredient.setAdapter(new TextAdapter(this, R.layout.layout_list_view_simple_row, subIngredient));
        lstMainIngredient.setAdapter(new TextAdapter(this, R.layout.layout_list_view_simple_row, mainIngredient));
    }

    public void clickToShowMainList(View view) {
        if(imgIcon1.getDrawable().getConstantState().equals(getDrawable(R.drawable.icons_plus).getConstantState())){
            lstMainIngredient.setVisibility(View.VISIBLE);
            imgIcon1.setImageResource(R.drawable.icons_cancel);
        }else{
            lstMainIngredient.setVisibility(View.GONE);
            imgIcon1.setImageResource(R.drawable.icons_plus);
        }
    }

    public void clickToShowSubList(View view) {
        if(imgIcon2.getDrawable().getConstantState().equals(getDrawable(R.drawable.icons_plus).getConstantState())){
            lstSubIngredient.setVisibility(View.VISIBLE);
            imgIcon2.setImageResource(R.drawable.icons_cancel);
        }else{
            lstSubIngredient.setVisibility(View.GONE);
            imgIcon2.setImageResource(R.drawable.icons_plus);
        }
    }
}
