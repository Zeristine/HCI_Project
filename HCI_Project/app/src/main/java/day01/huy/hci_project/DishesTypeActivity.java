package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DishesTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_type);
    }

    public void clickToVegan(View view) {
        Intent intent = new Intent(this, PickIngridientActivity.class);
        intent.putExtra("choice", 2);
        startActivity(intent);
    }

    public void clickToNonVegan(View view) {
        Intent intent = new Intent(this, PickIngridientActivity.class);
        intent.putExtra("choice", 1);
        startActivity(intent);
    }

    public void clickToDrink(View view) {
        Intent intent = new Intent(this, PickIngridientActivity.class);
        intent.putExtra("choice", 3);
        startActivity(intent);
    }
}
