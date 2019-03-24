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

//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setUpFavoriteRecipes();
//    }
//
//
//
//    public void clickToAddMeal(View view) {
//        final Dialog dialog = new Dialog(this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        LinearLayout layout = new LinearLayout(this);
//        Button sameRecipe = new Button(this);
//        Button newRecipe = new Button(this);
//        TextView textView = new TextView(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        textView.setText("Chọn cách thêm công thức");
//        textView.setLayoutParams(layoutParams);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTypeface(Typeface.DEFAULT_BOLD);
//        layoutParams.setMargins(10, 5, 10, 5);
//        sameRecipe.setText("Thêm vào món ăn có sẵn");
//        sameRecipe.setLayoutParams(layoutParams);
//        sameRecipe.setBackgroundResource(R.drawable.shape_button);
//        sameRecipe.setTextColor(getColor(R.color.white));
//        sameRecipe.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        sameRecipe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                Intent intent = new Intent(NavigationActivity.this, RecipeActivity.class);
//                startActivity(intent);
//            }
//        });
//        newRecipe.setText("Thêm vào món ăn mới");
//        newRecipe.setLayoutParams(layoutParams);
//        newRecipe.setBackgroundResource(R.drawable.shape_button);
//        newRecipe.setTextColor(getColor(R.color.white));
//        newRecipe.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        newRecipe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                Intent intent = new Intent(NavigationActivity.this, PostRecipeActivity.class);
//                startActivity(intent);
//            }
//        });
//        layout.setLayoutParams(layoutParams);
//        layout.addView(textView);
//        layout.addView(sameRecipe);
//        layout.addView(newRecipe);
//        dialog.addContentView(layout, layoutParams);
//        dialog.show();
//    }
//
//    private void setUpFavoriteRecipes() {
//        favorites = recipeData.getFavorites();
//        glFavorite.removeAllViews();
//        if (favorites.isEmpty()) {
//            imgNotFound.setVisibility(View.VISIBLE);
//            glFavorite.setVisibility(View.GONE);
//        } else {
//            double row = favorites.size() / 2.0;
//            int rowCount = (int) row;
//            if ((row * 10.0) % 2.0 != 0) {
//                rowCount++;
//            }
//            if (favorites.size() == 1) {
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((displayMetrics.widthPixels / 2),
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                glFavorite.setLayoutParams(params);
//                glFavorite.setColumnCount(1);
//            } else {
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                glFavorite.setLayoutParams(params);
//                glFavorite.setColumnCount(2);
//            }
//            glFavorite.setColumnCount(2);
//            glFavorite.setRowCount(rowCount);
//            for (Recipe recipe : favorites) {
//                ItemGenerator.createCardViewGridLayout(recipe, glFavorite, this, getResources().getColor(R.color.white));
//            }
//            imgNotFound.setVisibility(View.GONE);
//            glFavorite.setVisibility(View.VISIBLE);
//        }
//    }
}

