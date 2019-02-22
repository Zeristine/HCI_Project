package day01.huy.hci_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        List<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        EditText test = findViewById(R.id.edtTest);
        String value = "";
        for (int i = 0; i < ingredients.size(); i++) {
            value += ingredients.get(i) + ", ";
        }
        test.setText(value);



    }
}
