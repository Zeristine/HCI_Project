package day01.huy.hci_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            return;
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
            txtUsername.setText(txtRUsername.getText().toString());
            txtPassword.setText(txtRPassword.getText().toString());
            txtRUsername.setText("");
            txtRPassword.setText("");
            layoutNormalRegister.setVisibility(View.INVISIBLE);
            layoutNormalLogin.setVisibility(View.VISIBLE);
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
}

