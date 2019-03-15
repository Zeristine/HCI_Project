package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import day01.huy.hci_project.ultis.ColorGradient;
import day01.huy.hci_project.ultis.UnitConverter;

public class NavigationActivity extends AppCompatActivity {

    private Button btnPickIngredient, btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        btnPickIngredient = findViewById(R.id.btnPickIngredient);
        btnFavorite = findViewById(R.id.btnFavorite);

        btnPickIngredient.setBackground(ColorGradient.getRedGradientOrange(this));
//        btnPickIngredient.setTextSize(UnitConverter.getPixelValue(15, this));
        btnFavorite.setBackground(ColorGradient.getRedGradientOrange(this));
//        btnFavorite.setTextSize(UnitConverter.getPixelValue(15, this));
        btnPickIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, DishesTypeActivity.class);
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

