package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import day01.huy.hci_project.ultis.ColorGradient;

public class NavigationActivity extends AppCompatActivity {

    private Button btnPickIngredient, btnHistory, btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        btnPickIngredient = findViewById(R.id.btnPickIngredient);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnHistory = findViewById(R.id.btnHistory);

        btnPickIngredient.setBackground(ColorGradient.getRedGradientDeep(this));
        btnHistory.setBackground(ColorGradient.getRedGradientDeeper(this));
        btnFavorite.setBackground(ColorGradient.getRedGradientDeeper(this));
        btnPickIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, DishesTypeActivity.class);
                startActivity(intent);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ShowHistoryActivity.class);
                startActivity(intent);
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
