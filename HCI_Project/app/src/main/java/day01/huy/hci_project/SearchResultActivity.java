package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import day01.huy.hci_project.custom.RecipeAdapter;
import day01.huy.hci_project.dto.Recipe;

public class SearchResultActivity extends AppCompatActivity {

    private ListView listView,suggestView;
    private List<Recipe> recipes, recipesS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        List<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        TextView test = findViewById(R.id.edtTest);
        String value = "";
        for (int i = 0; i < ingredients.size(); i++) {
            value += ingredients.get(i) + ", ";
        }
        test.setText(value);

        listView =findViewById(R.id.listView);
        suggestView = findViewById(R.id.suggestView);

        makeListForResult();

        makeListForSuggestion();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });

        suggestView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void makeListForResult(){
        recipes = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        recipes.add(new Recipe(1, "Rau muong xao toi", "HuyLM", "adada", date,"", null ));
        recipes.add(new Recipe(2, "Nui xao bo", "HuyLM", "adada", date,"", null ));
        recipes.add(new Recipe(3, "Che chan trau", "HuyLM", "adada", date,"", null ));

        RecipeAdapter adapter = new RecipeAdapter(this, R.layout.layout_list_view_recipe, recipes);
        listView.setAdapter(adapter);
    }

    public void makeListForSuggestion(){

        recipesS = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        recipesS.add(new Recipe(1, "Rau muong xao toi", "HuyLM", "adada", date,"", null ));
        recipesS.add(new Recipe(2, "Nui xao bo", "HuyLM", "adada", date,"", null ));
        recipesS.add(new Recipe(3, "Che chan trau", "HuyLM", "adada", date,"", null ));
        recipesS.add(new Recipe(4, "Choco Ball", "HuyLM", "adada", date,"", null ));
        recipesS.add(new Recipe(5, "Canh rau muong", "HuyLM", "adada", date,"", null ));

        RecipeAdapter suggestAdapter = new RecipeAdapter(this, R.layout.layout_list_view_recipe, recipesS);
        suggestView.setAdapter(suggestAdapter);
    }


}
