package day01.huy.hci_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import day01.huy.hci_project.ultis.ColorGradient;

public class MainActivity extends AppCompatActivity {

    private ImageView imgTitle;
    private Button btnBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgTitle = findViewById(R.id.imgTitle);
        btnBegin = findViewById(R.id.btnBegin);

        imgTitle.setImageResource(R.drawable.image_title);

        btnBegin.setBackground(ColorGradient.getRedGradient(this));
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}

