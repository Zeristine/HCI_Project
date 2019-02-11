package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import day01.huy.hci_project.custom.TextAdapter;

public class PickIngridientActivity extends AppCompatActivity {

    private ListView lstSearch;
    private AutoCompleteTextView txtIngredient;
    private List<String> tags;
    private final List<String> suggestions = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "trung", "thit bo", "thit heo", "thit ga");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ingridient);
        lstSearch = findViewById(R.id.lstIngredientSearch);
        txtIngredient = findViewById(R.id.txtIngredient);
        tags = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        txtIngredient.setAdapter(adapter);
        txtIngredient.setThreshold(3);
        txtIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = txtIngredient.getText().toString();
                if (!value.isEmpty()) {
                    if (suggestions.contains(value)) {
                        tags.add(value);
                        TextAdapter adapter = new TextAdapter(PickIngridientActivity.this, R.layout.layout_list_view_simple_row, tags);
                        lstSearch.setAdapter(adapter);
                    }
                }
            }
        });
    }

    public void clickToSearch(View view) {
    }

    public void clickToAddTag(View view) {
    }
}
