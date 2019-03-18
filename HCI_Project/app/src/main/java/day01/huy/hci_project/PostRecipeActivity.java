package day01.huy.hci_project;

import android.Manifest;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.data.RecipeData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.ultis.ItemGenerator;

public class PostRecipeActivity extends AppCompatActivity {

    private ImageView imageViewRecipe;
    private final IngredientData ingredientData = new IngredientData();
    private final RecipeData recipeData = new RecipeData();
    private final int REQUEST_CAMERA = 102, REQUEST_GALLERY = 101;
    private ImageView layoutRecipeImage;
    private Spinner spDishType;
    private LinearLayout layoutAddIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_recipe);
//        layoutRecipeImage = findViewById(R.id.layoutRecipeImage);
        layoutAddIngredient = findViewById(R.id.layoutAddIngredient);
        spDishType = findViewById(R.id.spDishType);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String[] dishTypes = new String[]{"Chọn loại món ăn", "Món chay", "Món Mặn", "Thức uống"};
        spDishType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dishTypes));
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "Ví dụ: 100g bột");
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "Ví dụ: 100g bột");
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "Ví dụ: 100g bột");
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
        ItemGenerator.createAddIngredientRow(this, layoutAddIngredient, "");
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
//                    imageViewRecipe.setImageBitmap(imageBitmap);
                    layoutRecipeImage.setBackground(drawable);
                    layoutRecipeImage.setImageDrawable(drawable);
                }
                break;
        }
    }
}
