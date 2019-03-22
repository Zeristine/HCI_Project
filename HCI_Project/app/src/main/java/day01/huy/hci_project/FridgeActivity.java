package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FridgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
    }

    public void clickToConfirm(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
        finish();
    }
}
