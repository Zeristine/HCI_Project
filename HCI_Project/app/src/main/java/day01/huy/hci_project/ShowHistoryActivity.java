package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        txtTitle.setTextSize((displayMetrics.widthPixels * 1) / 25);
        double row = recipes.size() / 2;
        int rowCount = (int) row;
        if ((row * 10) % 2 != 0) {
            rowCount++;
        }
        glHistory.setColumnCount(2);
        glHistory.setRowCount(rowCount);
        for (Recipe recipe : recipes) {
            ItemGenerator.createCardViewGridLayout(recipe, glHistory, this, getResources().getColor(R.color.transparent));
        }
    }

    public void clickToFinish(View view) {
        finish();
    }
}
