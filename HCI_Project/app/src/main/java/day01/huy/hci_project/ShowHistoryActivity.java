package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.ultis.ItemGenerator;

public class ShowHistoryActivity extends AppCompatActivity {

    private GridLayout glHistory;
    private List<Recipe> recipes;
    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        glHistory = findViewById(R.id.glHistory);
        txtTitle = findViewById(R.id.txtTitle);
        recipes = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", date, "raumuong", null));
        recipes.add(new Recipe(2, "Nui xào bò", "HuyLM", "adada", date, "nuixaobo", null));
        recipes.add(new Recipe(3, "Chè trân châu", "HuyLM", "adada", date, "che", null));
        recipes.add(new Recipe(4, "Bánh viên sô cô la", "HuyLM", "adada", date, "chocoball", null));
        recipes.add(new Recipe(5, "Canh rau muống", "HuyLM", "adada", date, "canhraumuong", null));

        txtTitle.setTextSize((displayMetrics.widthPixels * 1) / 25);
        double row = recipes.size() / 2;
        int rowCount = (int) row;
        if ((row * 10) % 2 != 0) {
            rowCount++;
        }
        glHistory.setColumnCount(2);
        glHistory.setRowCount(rowCount);
        for (Recipe recipe : recipes) {
            ItemGenerator.createCardView(recipe, glHistory, this, getResources().getColor(R.color.red600));
        }
    }

    public void clickToFinish(View view) {
        finish();
    }
}
