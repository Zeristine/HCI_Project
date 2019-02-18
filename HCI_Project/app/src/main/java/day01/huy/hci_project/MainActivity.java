package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgTitle = findViewById(R.id.imgTitle);
        imgTitle.setImageResource(R.drawable.image_title);
    }

    public void clickToNavigation(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
}

