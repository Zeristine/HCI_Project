package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import day01.huy.hci_project.custom.RecipeAdapter;
import day01.huy.hci_project.dto.Recipe;

public class ShowHistoryActivity extends AppCompatActivity {

    private ListView lstHistory;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        lstHistory = findViewById(R.id.lstHistory);
        recipes = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        recipes.add(new Recipe(1, "Rau muong xao toi", "HuyLM", "adada", date,"", null ));
        recipes.add(new Recipe(2, "Nui xao bo", "HuyLM", "adada", date,"", null ));
        recipes.add(new Recipe(3, "Che chan trau", "HuyLM", "adada", date,"", null ));
        recipes.add(new Recipe(4, "Choco Ball", "HuyLM", "adada", date,"", null ));
        recipes.add(new Recipe(5, "Canh rau muong", "HuyLM", "adada", date,"", null ));

        RecipeAdapter adapter = new RecipeAdapter(this, R.layout.layout_list_view_recipe, recipes);
        lstHistory.setAdapter(adapter);

        lstHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowHistoryActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
