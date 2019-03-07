package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class SearchResultActivity extends AppCompatActivity {

    private GridLayout resultGridLayout, suggestGridLayout;
    private LinearLayout mainLayout;
    private List<Recipe> recipes, recipesS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
//        List<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        resultGridLayout = findViewById(R.id.glResult);
        suggestGridLayout = findViewById(R.id.glSuggest);
        mainLayout = findViewById(R.id.mainLayout);
        makeListForResult();
        makeListForSuggestion();

        double row = recipes.size() / 2;
        int rowCount = (int) row;
        if ((row * 10) % 2 != 0) {
            rowCount++;
        }
        resultGridLayout.setColumnCount(2);
        resultGridLayout.setRowCount(rowCount);
        for (Recipe recipe : recipes) {
            ItemGenerator.createCardView(recipe, resultGridLayout, this, getResources().getColor(R.color.red600));
        }

        double rowS = recipesS.size() / 2;
        int rowCountS = (int) rowS;
        if ((rowS * 10) % 2 != 0) {
            rowCountS++;
        }
        suggestGridLayout.setColumnCount(2);
        suggestGridLayout.setRowCount(rowCountS);
        for (Recipe recipe : recipesS) {
            ItemGenerator.createCardView(recipe, suggestGridLayout, this, getResources().getColor(R.color.red600));
        }
    }

    public void makeListForResult() {
        recipes = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", date, "raumuong", null));
        recipes.add(new Recipe(2, "Nui xào bò", "HuyLM", "adada", date, "nuixaobo", null));
        recipes.add(new Recipe(3, "Chè trân châu", "HuyLM", "adada", date, "che", null));
        recipes.add(new Recipe(4, "Bánh viên sô cô la", "HuyLM", "adada", date, "chocoball", null));
    }

    public void makeListForSuggestion() {

        recipesS = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        recipesS.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", date, "raumuong", null));
        recipesS.add(new Recipe(2, "Nui xào bò", "HuyLM", "adada", date, "nuixaobo", null));
        recipesS.add(new Recipe(3, "Chè trân châu", "HuyLM", "adada", date, "che", null));
        recipesS.add(new Recipe(4, "Bánh viên sô cô la", "HuyLM", "adada", date, "chocoball", null));
        recipesS.add(new Recipe(5, "Canh rau muống", "HuyLM", "adada", date, "canhraumuong", null));

    }
}
