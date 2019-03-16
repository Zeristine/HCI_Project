package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import day01.huy.hci_project.ultis.ColorGradient;

public class DishesTypeActivity extends AppCompatActivity {

    private LinearLayout btnMan, btnChay, btnDrinks;
    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_type);
        btnChay = findViewById(R.id.btnChay);
        btnMan = findViewById(R.id.btnMan);
        btnDrinks = findViewById(R.id.btnDrinks);
        txtTitle = findViewById(R.id.txtTitle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        txtTitle.setTextSize((displayMetrics.widthPixels*1)/25);
        btnChay.setBackground(ColorGradient.getRedGradientOrange(this));
        btnMan.setBackground(ColorGradient.getRedGradientOrange(this));
        btnDrinks.setBackground(ColorGradient.getRedGradientOrange(this));
        btnChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DishesTypeActivity.this, PickIngredientActivity.class);
                intent.putExtra("choice", 2);
                startActivity(intent);
            }
        });
        btnMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DishesTypeActivity.this, PickIngredientActivity.class);
                intent.putExtra("choice", 1);
                startActivity(intent);
            }
        });
        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DishesTypeActivity.this, PickIngredientActivity.class);
                intent.putExtra("choice", 3);
                startActivity(intent);
            }
        });
    }

    public void clickToFinish(View view) {
        finish();
    }
}
