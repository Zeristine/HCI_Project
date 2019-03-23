package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class FridgeActivity extends AppCompatActivity {

    private LinearLayout layoutAddIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
    }

    public void clickToAddIngredient(View view) {
    }
}
