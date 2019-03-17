package day01.huy.hci_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.data.SessionData;
import day01.huy.hci_project.data.UserData;
import day01.huy.hci_project.dto.Recipe;
import day01.huy.hci_project.dto.User;
import day01.huy.hci_project.ultis.ItemGenerator;

public class ProfileActivity extends AppCompatActivity {

    private final RecipeData recipeData = new RecipeData();
    private final UserData userData = new UserData();
    private List<Recipe> yourRecipes;
    private User user;
    private ImageView imgNotFound, imageViewAvatar;
    private GridLayout glYourRecipes;
    private LinearLayout layoutUpdateProfile, layoutProfileChoice, layoutConfirmPassword;
    private EditText txtUsername, txtPassword, txtConfirm;
    private Button btnCancel, btnUpdate;
    private LinearLayout.LayoutParams layoutParams;
    private EditText txtDisplayName, txtEmail, txtAddress, txtDescription;
    String displayName, email, address, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgNotFound = findViewById(R.id.imgNotFound);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        glYourRecipes = findViewById(R.id.glPostedRecipe);
        layoutProfileChoice = findViewById(R.id.layoutProfileChoice);
        layoutUpdateProfile = findViewById(R.id.layoutUpdateProfile);
//        layoutConfirmPassword = findViewById(R.id.layoutConfirmPassword);
//        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
//        txtConfirm = findViewById(R.id.txtConfirm);
        layoutUpdateProfile.setVisibility(View.INVISIBLE);
        layoutProfileChoice.setVisibility(View.VISIBLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        createYourRecipesView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createYourRecipesView();
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
        user = userData.getAccountByUsername(SessionData.getUsername());
        if (user != null) {
            txtUsername.setText(user.getUsername());
            txtPassword.setText(user.getPassword());
        }
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
        btnUpdate.setLayoutParams(layoutParams);
//        layoutConfirmPassword.setVisibility(View.GONE);
//        btnCancel.setVisibility(View.GONE);
    }

    private void createYourRecipesView() {
        yourRecipes = recipeData.getRecipesSameChef(SessionData.getUsername(), 0);
        glYourRecipes.removeAllViews();
        if (yourRecipes.isEmpty()) {
            imgNotFound.setVisibility(View.VISIBLE);
            glYourRecipes.setVisibility(View.INVISIBLE);
        } else {
            double row = yourRecipes.size() / 2;
            int rowCount = (int) row;
            if ((row * 10) % 2 != 0) {
                rowCount++;
            }
            glYourRecipes.setColumnCount(2);
            glYourRecipes.setRowCount(rowCount);
            for (Recipe recipe : yourRecipes) {
                ItemGenerator.createCardViewGridLayout(recipe, glYourRecipes, this, getResources().getColor(R.color.white));
            }
            imgNotFound.setVisibility(View.INVISIBLE);
            glYourRecipes.setVisibility(View.VISIBLE);
        }
    }

    public void clickToBack(View view) {
        layoutUpdateProfile.setVisibility(View.INVISIBLE);
        layoutProfileChoice.setVisibility(View.VISIBLE);
    }

//    public void clickToCancel(View view) {
//        layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
//        btnUpdate.setLayoutParams(layoutParams);
////        layoutConfirmPassword.setVisibility(View.GONE);
////        btnCancel.setVisibility(View.GONE);
//    }

    public void clickToUpdate(View view) {
        txtEmail = findViewById(R.id.txtEmail);
        //set EditText to String
        email = txtEmail.toString().trim();
        if (!email.equals("")) {
            if (!email.matches("^(.+)@(.+)$")) {
                Toast.makeText(this, "Email không hợp lệ vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            layoutUpdateProfile.setVisibility(View.INVISIBLE);
            layoutProfileChoice.setVisibility(View.VISIBLE);
//            if (btnCancel.getVisibility() == View.GONE) {
//                layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//                btnCancel.setLayoutParams(layoutParams);
//                btnUpdate.setLayoutParams(layoutParams);
//                txtPassword.setText("");
//                txtPassword.setEnabled(true);
//                layoutConfirmPassword.setVisibility(View.VISIBLE);
//                btnCancel.setVisibility(View.VISIBLE);
//            } else {
//                String password = txtPassword.getText().toString();
//                String confirm = txtConfirm.getText().toString();
//                if (password.equals(confirm)) {
//
//                }
//            }
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

}

