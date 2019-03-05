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

public class FavoriteActivity extends AppCompatActivity {

    private GridLayout glFavorite;
    private List<Recipe> recipes;
    private TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        glFavorite = findViewById(R.id.glFavorite);
        txtTitle = findViewById(R.id.txtTitle);
        recipes = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        recipes.add(new Recipe(1, "Rau muong xao toi", "HuyLM", "adada", date, "raumuong", null));
        recipes.add(new Recipe(2, "Nui xao bo", "HuyLM", "adada", date, "nuixaobo", null));
        recipes.add(new Recipe(3, "Che tran chau", "HuyLM", "adada", date, "chatranchau", null));
        recipes.add(new Recipe(4, "Choco Ball", "HuyLM", "adada", date, "", null));
        recipes.add(new Recipe(5, "Canh rau muong", "HuyLM", "adada", date, "", null));

        txtTitle.setTextSize((displayMetrics.widthPixels*1)/40);
        double row = recipes.size() / 2;
        int rowCount = (int) row;
        if ((row * 10) % 2 != 0) {
            rowCount++;
        }
        glFavorite.setColumnCount(2);
        glFavorite.setRowCount(rowCount);
        for (Recipe recipe : recipes) {
            ItemGenerator.createCardView(recipe, glFavorite, this, getResources().getColor(R.color.red600));
        }
    }

    public void clickToFinish(View view) {
        finish();
    }
}
