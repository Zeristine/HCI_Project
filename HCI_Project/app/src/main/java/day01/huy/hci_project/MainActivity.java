package day01.huy.hci_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.data.SessionData;
import day01.huy.hci_project.data.UserData;

public class MainActivity extends AppCompatActivity {

    private final UserData userData = new UserData();
    private GridLayout layoutLoginChoice;
    private LinearLayout layoutNormalLogin, layoutNormalRegister;
    private TextView lblUsername, lblPassword, lblRUsername, lblRPassword, lblRConfirm;
    private EditText txtUsername, txtPassword, txtRUsername, txtRPassword, txtRConfirm;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        List<String> permissions = new ArrayList<>();
        checkPermission(permissions, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!permissions.isEmpty()) {
            Intent intent = new Intent(this, GetPermissionActivity.class);
            intent.putStringArrayListExtra("permissions", (ArrayList<String>) permissions);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        layoutLoginChoice = findViewById(R.id.layoutLoginChoice);
        layoutNormalLogin = findViewById(R.id.layoutNormalLogin);
        layoutNormalRegister = findViewById(R.id.layoutNormalRegister);
        lblUsername = findViewById(R.id.lblUsername);
        lblPassword = findViewById(R.id.lblPassword);
        lblRUsername = findViewById(R.id.lblRUsername);
        lblRPassword = findViewById(R.id.lblRPassword);
        lblRConfirm = findViewById(R.id.lblRConfirm);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtRUsername = findViewById(R.id.txtRUsername);
        txtRPassword = findViewById(R.id.txtRPassword);
        txtRConfirm = findViewById(R.id.txtRConfirm);

        lblUsername.setVisibility(View.INVISIBLE);
        lblPassword.setVisibility(View.INVISIBLE);
        lblRUsername.setVisibility(View.INVISIBLE);
        lblRPassword.setVisibility(View.INVISIBLE);
        lblRConfirm.setVisibility(View.INVISIBLE);
        layoutNormalLogin.setVisibility(View.INVISIBLE);
        layoutNormalRegister.setVisibility(View.INVISIBLE);
        layoutLoginChoice.setVisibility(View.VISIBLE);
    }

    public void clickToLogin(View view) {
        //Move to Normal Login Form
        if (layoutLoginChoice.getVisibility() == View.VISIBLE) {
            layoutLoginChoice.setVisibility(View.INVISIBLE);
            layoutNormalLogin.setVisibility(View.VISIBLE);
            return;
        }
        //Login
        if (layoutNormalLogin.getVisibility() == View.VISIBLE) {
            //Do Login
            if (txtUsername.getText().toString().trim().equals("") || txtPassword.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Bạn cần điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                SessionData.setUsername(txtUsername.getText().toString());
                Toast.makeText(this, "Chào mừng, " + txtUsername.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, NavigationActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    public void clickToRegister(View view) {
        //Move to Register Form
        if (layoutNormalLogin.getVisibility() == View.VISIBLE) {
            layoutNormalLogin.setVisibility(View.INVISIBLE);
            layoutNormalRegister.setVisibility(View.VISIBLE);
            return;
        }
        //Register After Register Reappear Login
        if (layoutNormalRegister.getVisibility() == View.VISIBLE) {
            //Do Register
            String username = txtRUsername.getText().toString().trim();
            String password = txtRPassword.getText().toString().trim();
            String confirm = txtRConfirm.getText().toString().trim();
            if (username.equals("") || password.equals("") || confirm.equals("")) {
                Toast.makeText(this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            } else if (!password.equals(confirm)) {
                Toast.makeText(this, "Bạn chưa lập lại đúng mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            } else {
                userData.register(username, password);
                txtUsername.setText(username);
                txtPassword.setText(password);
                txtRUsername.setText("");
                txtRPassword.setText("");
                txtRConfirm.setText("");
                layoutNormalRegister.setVisibility(View.INVISIBLE);
                layoutNormalLogin.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Bạn đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void clickToBack(View view) {
        //Back from Register form
        if (layoutNormalRegister.getVisibility() == View.VISIBLE &&
                layoutNormalLogin.getVisibility() == View.INVISIBLE &&
                layoutLoginChoice.getVisibility() == View.INVISIBLE
        ) {
            layoutNormalRegister.setVisibility(View.INVISIBLE);
            layoutLoginChoice.setVisibility(View.INVISIBLE);
            layoutNormalLogin.setVisibility(View.VISIBLE);
            return;
        }
        //Back from Login form
        if (layoutNormalLogin.getVisibility() == View.VISIBLE &&
                layoutNormalRegister.getVisibility() == View.INVISIBLE &&
                layoutLoginChoice.getVisibility() == View.INVISIBLE
        ) {
            layoutNormalRegister.setVisibility(View.INVISIBLE);
            layoutLoginChoice.setVisibility(View.VISIBLE);
            layoutNormalLogin.setVisibility(View.INVISIBLE);
            return;
        }
    }

    public void clickToSocial(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkPermission(List<String> permissions, String permission) {
        if (ActivityCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(permission);
        }
    }
}

