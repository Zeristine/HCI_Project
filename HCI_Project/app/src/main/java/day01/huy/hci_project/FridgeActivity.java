package day01.huy.hci_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import day01.huy.hci_project.data.FridgeData;

public class FridgeActivity extends AppCompatActivity {

    private final FridgeData fridgeData = new FridgeData();
    private LinearLayout layoutAddIngredient;
    private TextView txtEmpty;
    private final List<String> unitTypeList = Arrays.asList("Số lượng", "Khối lượng", "Dung tích");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        layoutAddIngredient = findViewById(R.id.layoutAddIngredient);
        txtEmpty = findViewById(R.id.txtEmpty);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        addCurrentFridgeData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addCurrentFridgeData();
    }

    public void clickToAddIngredient(View view) {
        if (txtEmpty.getVisibility() == View.VISIBLE) {
            txtEmpty.setVisibility(View.GONE);
        }
        addFridgeRow(null, null, null);
    }

    private void addCurrentFridgeData() {
        List<String> fridge = fridgeData.getList();
        if (fridge.isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            layoutAddIngredient.removeAllViews();
            txtEmpty.setVisibility(View.GONE);
            for (String row : fridge) {
                StringTokenizer tokenizer = new StringTokenizer(row, "-");
                addFridgeRow(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
            }
        }
    }

    private void addFridgeRow(final String ingredient, String amount, final String unitType) {
        final View fridgeRow = getLayoutInflater().inflate(R.layout.layout_fridge_row, null);
        Spinner spinner = fridgeRow.findViewById(R.id.spUnitType);
        final TextView txtUnit = fridgeRow.findViewById(R.id.txtUnit);
        final TextView txtView = fridgeRow.findViewById(R.id.txtView);
        final LinearLayout layoutEdit = fridgeRow.findViewById(R.id.layoutEdit);
        final EditText txtAmount = fridgeRow.findViewById(R.id.txtAmount);
        final EditText txtIngredient = fridgeRow.findViewById(R.id.txtIngredient);
        final Button btnDone = fridgeRow.findViewById(R.id.btnDone);
        final Button btnCancel = fridgeRow.findViewById(R.id.btnCancel);
        final ImageButton btnDelete = fridgeRow.findViewById(R.id.btnDelete);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, unitTypeList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getColor(R.color.white));
                String unit = "";
                switch (((TextView) parent.getChildAt(0)).getText().toString()) {
                    case "Khối lượng":
                        unit = "kg";
                        break;
                    case "Số lượng":
                        unit = "cái";
                        break;
                    case "Dung tích":
                        unit = "ml";
                        break;
                }
                txtUnit.setText(unit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String inputIngredient = txtIngredient.getText().toString();
                final String inputAmount = txtAmount.getText().toString();
                final String unit = txtUnit.getText().toString();
                if (btnDone.getText().toString().equals("Hoàn tất")) {
                    if (inputIngredient.isEmpty() || inputAmount.isEmpty()) {
                        Toast.makeText(FridgeActivity.this,
                                "Tên nguyên liệu hoặc số lượng chưa được nhập đầy đủ!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (fridgeData.checkIngredientExist(inputIngredient)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FridgeActivity.this)
                                    .setTitle("Có nguyên liệu khác cùng tên có sẵn trong tủ lạnh")
                                    .setMessage("Bạn có muốn thay đổi việc thêm vào thành cập nhật không ?")
                                    .setPositiveButton("Cập Nhật", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            fridgeData.updateIngredientInFridge(inputIngredient, inputAmount, unit);
                                            Toast.makeText(FridgeActivity.this, "Cập nhật thành công",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(FridgeActivity.this, NavigationActivity.class);
                                            intent.putExtra("currentTab", 1);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton("Hủy Bỏ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            builder.show();
                        } else {
                            fridgeData.addIngredientToFridge(inputIngredient, inputAmount, unit);
                            Toast.makeText(FridgeActivity.this, "Thêm vào thành công", Toast.LENGTH_SHORT).show();
                            txtView.setText(inputIngredient + " : " + inputAmount + unit);
                            txtView.setVisibility(View.VISIBLE);
                            btnDelete.setVisibility(View.VISIBLE);
                            layoutEdit.setVisibility(View.GONE);
                            btnDone.setText("Cập Nhật");
                        }
                    }
                } else if (btnDone.getText().toString().equals("Cập Nhật")) {
                    fridgeData.updateIngredientInFridge(inputIngredient, inputAmount, unit);
                    txtView.setText(inputIngredient + " : " + inputAmount + unit);
                    txtView.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                    layoutEdit.setVisibility(View.GONE);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnDone.getText().toString().equals("Hoàn tất")) {
                    if (txtIngredient.isFocused() || txtAmount.isFocused()) {
                        txtIngredient.clearFocus();
                        txtAmount.clearFocus();
                        if (fridgeRow != null) {
                            layoutAddIngredient.removeView(fridgeRow);
                        }
                    } else {
                        layoutAddIngredient.removeView(fridgeRow);
                    }
                    if (layoutAddIngredient.getChildCount() == 0) {
                        txtEmpty.setVisibility(View.VISIBLE);
                    }
                } else {
                    txtView.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                    layoutEdit.setVisibility(View.GONE);
                }
            }
        });
        fridgeRow.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String ingredient = txtIngredient.getText().toString();
                    String amount = txtAmount.getText().toString();
                    if (ingredient.isEmpty() || amount.isEmpty()) {
                        layoutAddIngredient.removeView(fridgeRow);
                    }
                }
            }
        });
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtIngredient.getBackground().clearColorFilter();
                txtAmount.getBackground().clearColorFilter();
                layoutEdit.setVisibility(View.VISIBLE);
                txtView.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                txtIngredient.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(txtIngredient, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringTokenizer tokenizer = new StringTokenizer(txtView.getText().toString(), ":");
                fridgeData.deleteIngredientInFridge(tokenizer.nextToken().trim());
                layoutAddIngredient.removeView(fridgeRow);
                Toast.makeText(FridgeActivity.this, "Đã loại khỏi tủ lạnh thành công", Toast.LENGTH_SHORT).show();
                if (layoutAddIngredient.getChildCount() == 0) {
                    txtEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
        layoutAddIngredient.addView(fridgeRow);
        if (ingredient != null && amount != null && unitType != null) {
            txtView.setText(ingredient + " : " + amount + unitType);
            txtView.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            setTextToEditText(txtIngredient, ingredient);
            setTextToEditText(txtAmount, amount);
            btnDone.setText("Cập nhật");
            switch (unitType) {
                case "kg":
                    spinner.setSelection(1);
                    break;
                case "cái":
                    spinner.setSelection(0);
                    break;
                case "ml":
                    spinner.setSelection(2);
                    break;
            }
            txtUnit.setText(unitType);
            layoutEdit.setVisibility(View.GONE);
        } else {
            txtIngredient.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtIngredient, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void setTextToEditText(@NotNull EditText text, String value) {
        text.setText(value);
        text.clearFocus();
        text.setBackgroundResource(R.color.transparent);
    }
}
