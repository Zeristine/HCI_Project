package day01.huy.hci_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.data.IngredientData;
import day01.huy.hci_project.dto.Ingredient;
import day01.huy.hci_project.ultis.ItemGenerator;

public class IngredientAmountActivity extends AppCompatActivity {

    private final IngredientData ingredientData = new IngredientData();
    private TextView txtNotification;
    private LinearLayout layoutAmountIngredient, layoutMain;
    private List<String> selectedWithAmount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_amount);
        layoutAmountIngredient = findViewById(R.id.layoutAmountIngredient);
        layoutMain = findViewById(R.id.layoutMain);
        txtNotification = findViewById(R.id.txtNotification);
        Intent intent = getIntent();
        List<String> selectedIngredients = intent.getStringArrayListExtra("ingredients");
        for (String item : selectedIngredients) {
            selectedWithAmount.add(item + "-");
        }
        String type = intent.getStringExtra("type");
        if (selectedIngredients.isEmpty() || type == null) {
            Toast.makeText(this, "Xin hãy chọn nguyên liệu trước!", Toast.LENGTH_SHORT).show();
            finish();
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        List<Ingredient> selected = ingredientData.getSelectedIngredientByTitle(selectedIngredients, type);
        for (int i = 0; i < selected.size(); i++) {
            createIngredientAmountRow(selected.get(i).getName(), selected.get(i).getType(), selected.get(i).getImageLink());
            if (i != selected.size() - 1) {
                layoutAmountIngredient.addView(ItemGenerator.createLine(this));
            }
        }
        layoutAmountIngredient.requestFocus();
    }

    private void createIngredientAmountRow(@NotNull final String title, @NotNull String type, String imageLink) {
        View view = getLayoutInflater().inflate(R.layout.layout_amount_ingredient, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0, 5, 0);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtType = view.findViewById(R.id.txtType);
        TextView txtUnit = view.findViewById(R.id.txtUnit);
        ImageView imgRecipe = view.findViewById(R.id.imgRecipeImage);
        int resId = ItemGenerator.getResId("icon_ingre_" + imageLink + "_small", "drawable", getPackageName(), this);
        if (resId == 0) {
            imgRecipe.setImageResource(R.drawable.icon_no_image);
        } else {
            imgRecipe.setImageResource(resId);
        }
        final EditText txtAmount = view.findViewById(R.id.txtAmount);
        txtTitle.setText(title.substring(0, 1).toUpperCase() + title.substring(1));
        switch (type) {
            case "gram":
                txtType.setText("Khối lượng: ");
                txtUnit.setText("gram");
                break;
            case "ml":
                txtType.setText("Dung tích: ");
                txtUnit.setText("ml");
                break;
        }
        txtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtNotification.getVisibility() == View.VISIBLE) {
                    txtNotification.setVisibility(View.GONE);
                }
                String value = txtAmount.getText().toString();
                String oldValue = getSelectedIngredient(title);
                if (oldValue != null) {
                    selectedWithAmount.remove(oldValue);
                }
                selectedWithAmount.add(title + "-" + value);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        view.setLayoutParams(params);
        layoutAmountIngredient.addView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layoutMain.setFocusable(true);
        layoutMain.setFocusableInTouchMode(true);
    }

    public void clickToFinish(View view) {
        finish();
    }

    @Nullable
    @Contract(pure = true)
    private String getSelectedIngredient(String ingredient) {
        for (String item : selectedWithAmount) {
            if (item.contains(ingredient)) {
                return item;
            }
        }
        return null;
    }

    public void clickToSearch(View view) {
        final Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putStringArrayListExtra("selected", (ArrayList<String>) selectedWithAmount);
        if (isMissingAnyAmountValue()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Vài Nguyên Liệu chưa điền số lượng");
            builder.setMessage("Bạn có muốn tiếp tục với những dữ liệu hiện tại ?");
            builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Trở về", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            startActivity(intent);
        }
    }

    private boolean isMissingAnyAmountValue() {
        for (String item : selectedWithAmount) {
            int pos = item.indexOf("-");
            String amount = item.substring(pos + 1);
            if (amount.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
