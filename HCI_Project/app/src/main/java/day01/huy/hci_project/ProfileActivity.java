package day01.huy.hci_project;

import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.data.SessionData;
import day01.huy.hci_project.data.UserData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.dto.User;
import day01.huy.hci_project.ultis.ItemGenerator;

public class ProfileActivity extends TabActivity {

    private final RecipeData recipeData = new RecipeData();
    private final UserData userData = new UserData();
    private List<Recipe> yourRecipes;
    private User user;
    private ImageView imageViewAvatar;
    private GridLayout glYourRecipes, glPending;
    private LinearLayout layoutUpdateProfile, layoutProfileChoice;
    private TextView textViewDisplayName;
    private EditText txtDisplayName, txtEmail, txtAddress, txtDescription;
    private TabHost tabHost;
    String displayName, email, address, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        glYourRecipes = findViewById(R.id.glPostedRecipe);
        glPending = findViewById(R.id.glPendingRecipe);
        layoutProfileChoice = findViewById(R.id.layoutProfileChoice);
        layoutUpdateProfile = findViewById(R.id.layoutUpdateProfile);
        layoutUpdateProfile.setVisibility(View.INVISIBLE);
        layoutProfileChoice.setVisibility(View.VISIBLE);
        textViewDisplayName = findViewById(R.id.displayName);
        txtDisplayName = findViewById(R.id.txtDisplayName);
        txtDisplayName.setText(textViewDisplayName.getText());
        tabHost = getTabHost();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        createYourRecipesView(recipeData.getRecipesSameChef(SessionData.getUsername(), 0, ""), glYourRecipes,
                (ImageView) findViewById(R.id.imgNotFound1));
        createYourRecipesView(recipeData.getFavorites(), glPending, (ImageView) findViewById(R.id.imgNotFound2));
        addTabSpec("Công thức đã đăng", R.id.layoutPosted);
        addTabSpec("Công thức chờ kiểm duyệt", R.id.layoutPending);
        for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
            View v = tabHost.getTabWidget().getChildTabViewAt(i);
            v.setBackgroundResource(R.drawable.tab_indicator);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setLayoutParams(layoutParams);
            tv.setGravity(Gravity.CENTER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        createYourRecipesView(recipeData.getRecipesSameChef(SessionData.getUsername(), 0, ""), glYourRecipes, (ImageView) findViewById(R.id.imgNotFound1));
        createYourRecipesView(recipeData.getFavorites(), glPending, (ImageView) findViewById(R.id.imgNotFound2));
    }

    public void clickToFinish(View view) {
        finish();
    }

    public void clickToLogout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất tài khoản của mình ?");
        builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SessionData.setUsername("");
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Ở lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void clickToUpdateProfile(View view) {
        layoutUpdateProfile.setVisibility(View.VISIBLE);
        layoutProfileChoice.setVisibility(View.INVISIBLE);
    }

    private void createYourRecipesView() {
//        yourRecipes = recipeData.getRecipesSameChef(SessionData.getUsername(), 0, "");
         yourRecipes = recipeData.getFavorites();
    }
    private void createYourRecipesView(List<Recipe> list, GridLayout layout, ImageView imgNotFound) {
        List<Recipe> recipes = list;
        glYourRecipes.removeAllViews();
        if (recipes.isEmpty()) {
            imgNotFound.setVisibility(View.VISIBLE);
            layout.setVisibility(View.INVISIBLE);
        } else {
            double row = recipes.size() / 2;
            int rowCount = (int) row;
            if ((row * 10) % 2 != 0) {
                rowCount++;
            }
            layout.setColumnCount(2);
            layout.setRowCount(rowCount);
            for (Recipe recipe : recipes) {
                ItemGenerator.createCardViewGridLayout(recipe, layout, this, getResources().getColor(R.color.white));
            }
            imgNotFound.setVisibility(View.GONE);
            glYourRecipes.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
        }
    }

    public void clickToBack(View view) {
        layoutUpdateProfile.setVisibility(View.INVISIBLE);
        layoutProfileChoice.setVisibility(View.VISIBLE);
    }


    public void clickToUpdate(View view) {
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        txtDescription = findViewById(R.id.txtDescription);
//        txtDisplayName = findViewById(R.id.txtDisplayName);
        //set EditText to String
        email = txtEmail.getText().toString().trim();
        displayName = txtDisplayName.getText().toString().trim();
        description = txtDescription.getText().toString().trim();
        address = txtAddress.getText().toString().trim();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (!email.equals("") && pattern.matcher(email).matches() == false) {
            Toast.makeText(this, "Email không hợp lệ vui lòng nhập lại.", Toast.LENGTH_SHORT).show();

        } else {
            txtEmail.setText(email);
            txtDisplayName.setText(displayName);
            txtDescription.setText(description);
            txtAddress.setText(address);
            textViewDisplayName.setText(displayName);
            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            layoutUpdateProfile.setVisibility(View.INVISIBLE);
            layoutProfileChoice.setVisibility(View.VISIBLE);
        }
    }

    public void clickToUploadImage(View view) {
        openGallery();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            Uri imageUri = data.getData();
            imageViewAvatar.setImageURI(imageUri);
        }
    }

    private void addTabSpec(String label, int viewId) {
        TabHost.TabSpec spec = tabHost.newTabSpec(label);
        spec.setIndicator(label);
        spec.setContent(viewId);
        tabHost.addTab(spec);
    }

}

