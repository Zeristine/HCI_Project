package day01.huy.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridLayout layoutLoginChoice;
    private LinearLayout layoutNormalLogin, layoutNormalRegister;
    private TextView lblUsername, lblPassword, lblRUsername, lblRPassword, lblRConfirm;
    private EditText txtUsername, txtPassword, txtRUsername, txtRPassword, txtRConfirm;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
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

            Intent intent = new Intent(this,NavigationActivity.class);
            startActivityForResult(intent,0);
            Toast.makeText(this, "Chào mừng, "+txtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode==RESULT_OK){

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
            if(username.equals("") || password.equals("") || confirm.equals("")){
                Toast.makeText(this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }else if(!password.equals(confirm)){
                Toast.makeText(this, "Bạn chưa lập lại đúng mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }else{
                txtUsername.setText(txtRUsername.getText().toString());
                txtPassword.setText(txtRPassword.getText().toString());
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
        Intent intent = new Intent(this,NavigationActivity.class);
        startActivityForResult(intent,0);
    }

}

