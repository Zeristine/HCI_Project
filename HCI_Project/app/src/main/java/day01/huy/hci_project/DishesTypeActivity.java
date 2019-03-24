package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import day01.huy.hci_project.data.FridgeData;

public class DishesTypeActivity extends AppCompatActivity {

    private final FridgeData fridgeData = new FridgeData();
    private LinearLayout btnMan, btnChay, btnDrinks, layoutDishType, layoutOption;
    private ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_type);
        btnChay = findViewById(R.id.btnChay);
        btnMan = findViewById(R.id.btnMan);
        btnDrinks = findViewById(R.id.btnDrinks);
        layoutDishType = findViewById(R.id.layoutDishType);
        layoutOption = findViewById(R.id.layoutOption);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);
        layoutDishType.setVisibility(View.GONE);
        layoutOption.setVisibility(View.VISIBLE);
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
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDishType.getVisibility() == View.VISIBLE) {
                    layoutOption.setVisibility(View.VISIBLE);
                    layoutDishType.setVisibility(View.GONE);
                    btnBack.setVisibility(View.GONE);
                }
            }
        });
    }

    public void clickToShowDishType(View view) {
        layoutOption.setVisibility(View.GONE);
        layoutDishType.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
    }

    public void clickToSearch(View view) {
        Intent intent = new Intent(DishesTypeActivity.this, SearchResultActivity.class);
        intent.putStringArrayListExtra("frigde", (ArrayList<String>) fridgeData.getList());
        startActivity(intent);
    }
}
