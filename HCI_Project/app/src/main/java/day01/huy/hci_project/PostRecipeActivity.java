package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PostRecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_recipe);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void clickToFinish(View view) {
        finish();
    }
}
