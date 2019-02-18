package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NavigationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void clickToPick(View view) {
        Intent intent = new Intent(this, PickIngridientActivity.class);
        startActivity(intent);
    }

    public void clickToFavorite(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }


}
