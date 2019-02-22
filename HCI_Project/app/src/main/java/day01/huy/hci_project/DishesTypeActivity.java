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
        startActivity(intent);
    }

    public void clickToNonVegan(View view) {
        Intent intent = new Intent(this, PickIngridientActivity.class);
        startActivity(intent);
    }

    public void clickToDrink(View view) {
        Intent intent = new Intent(this, PickIngridientActivity.class);
        startActivity(intent);
    }
}
