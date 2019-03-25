package day01.huy.hci_project;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class NavigationActivity extends TabActivity {

    private TabHost host;
    private TabWidget widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        host = getTabHost();
        widget = getTabWidget();
        int currentTab = getIntent().getIntExtra("currentTab", -1);
        addTabSpec("Trang chủ", R.drawable.file_icon_navigation_home, new Intent(this, HomeActivity.class));
        addTabSpec("Tủ lạnh", R.drawable.file_icon_navigation_fridge, new Intent(this, FridgeActivity.class));
        addTabSpec("Đăng bài", R.drawable.file_icon_navigation_post, new Intent(this, PostRecipeActivity.class));
        addTabSpec("Tài khoản", R.drawable.file_icon_navigation_user, new Intent(this, ProfileActivity.class));
        if (currentTab > -1) {
            host.setCurrentTab(currentTab);
        }
    }

    private void addTabSpec(String label, int drawableId, Intent intent) {
        TabHost.TabSpec spec = host.newTabSpec(label);
        View view = getLayoutInflater().inflate(R.layout.layout_tabwidget_tab, widget, false);
        TextView txtTab = view.findViewById(R.id.txtTab);
        txtTab.setText(label);
        ImageView imgTab = view.findViewById(R.id.imgTab);
        imgTab.setImageResource(drawableId);
        spec.setIndicator(view);
        spec.setContent(intent);
        host.addTab(spec);
    }

}

