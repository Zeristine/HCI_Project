package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import day01.huy.hci_project.custom.TextAdapter;

public class PickIngridientActivity extends AppCompatActivity {

    private ListView lstSuggest, lstSearch;
    private EditText txtIngredient;
    private String searchValue;
    private final List<String> suggestions = Arrays.asList("rau muong", "toi", "ca rot", " cu cai trang",
            "khoai tay", "hanh", "hanh phi", "trung", "thit bo", "thit heo", "thit ga");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ingridient);
        lstSuggest = findViewById(R.id.lstIngredientSuggest);
        lstSearch = findViewById(R.id.lstIngredientSearch);
        txtIngredient = findViewById(R.id.txtIngredient);

        lstSuggest.setVisibility(ListView.GONE);
        if (lstSearch.getAdapter() == null) {
            lstSearch.setVisibility(ListView.GONE);
        }
        txtIngredient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchValue = s.toString();
                List<String> result;
                if (searchValue.length() >= 3) {
                    result = findSuggestion(searchValue);

                    TextAdapter adapter = new TextAdapter(PickIngridientActivity.this, R.layout.layout_list_view_simple_row, result);
                    lstSuggest.setAdapter(adapter);
                    lstSuggest.setVisibility(ListView.VISIBLE);
                } else {
                    lstSuggest.setVisibility(ListView.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void clickToSearch(View view) {
    }

    private List<String> findSuggestion(String searchValue) {
        List<String> result = new ArrayList<>();
        String ingredient;
        for (int i = 0; i < suggestions.size(); i++) {
            ingredient = suggestions.get(i);
            if (ingredient.toLowerCase().contains(searchValue.toLowerCase())) {
                result.add(ingredient);
            }
            if (i == 4) {
                break;
            }
        }
        return result;
    }
}
