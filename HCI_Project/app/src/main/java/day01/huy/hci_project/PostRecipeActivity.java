package day01.huy.hci_project;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.ultis.ItemGenerator;

public class PostRecipeActivity extends AppCompatActivity {

    private final IngredientData ingredientData = new IngredientData();
    private final RecipeData recipeData = new RecipeData();
    private final int REQUEST_CAMERA = 102, REQUEST_GALLERY = 101;
    private final List<String> subIngredients = new ArrayList<>();
    private final List<String> mainIngredients = new ArrayList<>();
    private EditText txtRecipeTitle, txtRecipeContent, txtRecipeQuality, txtRecipeInstruct;
    private ImageView layoutRecipeImage;
    private Spinner spDishType;
    private LinearLayout layoutAddIngredient, layoutMainIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_recipe);
        layoutRecipeImage = findViewById(R.id.layoutRecipeImage);
        layoutAddIngredient = findViewById(R.id.layoutAddIngredient);
        layoutMainIngredient = findViewById(R.id.layoutMainIngredient);
        txtRecipeTitle = findViewById(R.id.txtRecipeTitle);
        txtRecipeContent = findViewById(R.id.txtRecipeContent);
        txtRecipeQuality = findViewById(R.id.txtRecipeQuality);
        txtRecipeInstruct = findViewById(R.id.txtRecipeInstruct);
        spDishType = findViewById(R.id.spDishType);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        if (title != null && content != null) {
            txtRecipeTitle.setText(title);
            txtRecipeContent.setText(content);
        }
        String[] dishTypes = new String[]{"Chọn loại món ăn", "Món chay", "Món Mặn", "Thức uống"};
        spDishType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dishTypes));
        ItemGenerator.createAddIngredientRow(this, layoutMainIngredient, "Ví dụ: 100g thịt HuyLM", subIngredients, mainIngredients, true);
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "Ví dụ: 100g bột", subIngredients, mainIngredients, false);
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "Ví dụ: 100g bột", subIngredients, mainIngredients, false);
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "Ví dụ: 100g bột", subIngredients, mainIngredients, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void clickToFinish(View view) {
        finish();
    }

    public void clickToOpenIngredientDialog(View view) {
//        final List<Ingredient> main = new ArrayList<>();
//        final List<Ingredient> sub = new ArrayList<>();
//        if (dishType.equals("Chọn loại món ăn")) {
//            Toast.makeText(this, "Xin hãy chọn loại món trước!", Toast.LENGTH_SHORT).show();
//        } else {
//            switch (dishType) {
//                case "Món mặn":
//                    main.addAll(ingredientData.getNonVegetarians().get("main"));
//                    sub.addAll(ingredientData.getNonVegetarians().get("sub"));
//                    break;
//                case "Món chay":
//                    main.addAll(ingredientData.getVegetarians().get("main"));
//                    sub.addAll(ingredientData.getVegetarians().get("sub"));
//                    break;
//                case "Thức uóng":
//                    main.addAll(ingredientData.getDrinks().get("main"));
//                    sub.addAll(ingredientData.getDrinks().get("sub"));
//                    break;
//            }
//            final  chooseDialog = new Dialog(this);
//            chooseDialog.setContentView(R.layout.layout_dialog_choose_option);
//            TextView title = chooseDialog.findViewById(R.id.txtTitle);
//            ListView lvOption = chooseDialog.findViewById(R.id.lvOption);
//            title.setText("Chọn loại nguyên Liệu");
//            String[] ingredientTypes = new String[]{"Nguyên Liệu Chính", "Nguyên Liệu Phụ", "Trở về"};
//            lvOption.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredientTypes));
//            lvOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    chooseDialog.dismiss();
//                    switch (position) {
//                        case 0:
//                            getDialogIngredient(main);
//                            break;
//                        case 1:
//                            getDialogIngredient(sub);
//                            break;
//                    }
//                }
//            });
//            chooseDialog.show();
//        }
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "", subIngredients, mainIngredients, false);
    }

    private void getDialogIngredient(List<Ingredient> list) {
//        List<String> stringList = ingredientData.getIngredientListOfString(list);
//        stringList.add("Trở về");
//        final Dialog chooseDialog = new Dialog(this);
//        chooseDialog.setContentView(R.layout.layout_dialog_choose_option);
//        TextView title = chooseDialog.findViewById(R.id.txtTitle);
//        final ListView lvOption = chooseDialog.findViewById(R.id.lvOption);
//
//        title.setText("Chọn nguyên Liệu");
//
//        lvOption.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList));
//        lvOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                chooseDialog.dismiss();
//                String selectedValue = lvOption.getItemAtPosition(position).toString();
//                if (!selectedValue.equals("Trở về")) {
//                    Toast.makeText(PostRecipeActivity.this, selectedValue, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        chooseDialog.show();
    }

    public void clickToAddImage(View view) {
        final Dialog chooseDialog = new Dialog(this);
        chooseDialog.setContentView(R.layout.layout_dialog_choose_option);
        TextView title = chooseDialog.findViewById(R.id.txtTitle);
        ListView lvOption = chooseDialog.findViewById(R.id.lvOption);
        title.setText("Chọn phương thức tải ảnh");
        String[] ingredientTypes = new String[]{"Ảnh từ thư viện", "Ảnh từ Camera", "Trở về"};
        lvOption.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredientTypes));
        lvOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseDialog.dismiss();
                switch (position) {
                    case 0:
                        if (ActivityCompat.checkSelfPermission(PostRecipeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(PostRecipeActivity.this, "Không có sự cho phép đọc dữ liệu điện thoại của bạn", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, REQUEST_GALLERY);
                        }
                        break;
                    case 1:
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, REQUEST_CAMERA);
                        break;
                }
            }
        });
        chooseDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imageBitmap;
        InputStream inputStream = null;
        switch (requestCode) {
            case REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri imageUri = data.getData();
                        inputStream = getContentResolver().openInputStream(imageUri);
                        imageBitmap = BitmapFactory.decodeStream(inputStream);
                        layoutRecipeImage.setImageBitmap(imageBitmap);
                    } catch (FileNotFoundException ex) {
                        Toast.makeText(this, "Có chuyện xảy ra khi tải hình từ thự viện",
                                Toast.LENGTH_SHORT).show();
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException ex) {

                            }
                        }
                    }
                }
                break;
            case REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    imageBitmap = (Bitmap) extras.get("data");
                    BitmapDrawable drawable = new BitmapDrawable(imageBitmap);
                    layoutRecipeImage.setImageDrawable(drawable);
                }
                break;
        }
    }

    public void clickToValidate(View view) {
        String title = txtRecipeTitle.getText().toString();
        String content = txtRecipeContent.getText().toString();
        String quality = txtRecipeQuality.getText().toString();
        String instruction = txtRecipeInstruct.getText().toString();
        List<String> errors = new ArrayList<>();
        if (title.isEmpty()) {
            errors.add("thêm tên món ăn");
        }
        if (content.isEmpty()) {
            errors.add("thêm phần giới thiệu về món ăn");
        }
        if (instruction.isEmpty()) {
            errors.add("thêm phần cách làm món ăn");
        }
        if (mainIngredients.isEmpty()) {
            errors.add("thêm nguyên liệu chính vào món ăn");
        }
        if (!errors.isEmpty()) {
            String errorString = "";
            for (String error : errors) {
                errorString += "- " + error + "\n";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bài đăng món ăn còn thiếu sót!");
            builder.setMessage("Bạn còn thiếu một số dữ liệu. Xin hãy:\n" + errorString);
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bài đăng món ăn đã hoàn chỉnh!");
            builder.setMessage("Bạn còn muốn chỉnh sửa gì không?");
            builder.setNegativeButton("Đăng bài", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(PostRecipeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("Chỉnh sửa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }
}
