package com.cba.thisurakarunanayaka.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cba.thisurakarunanayaka.R;

public class LoginActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    EditText etUserName, etPassword;
    SharedPreferences sharedPreferences;
    private CheckBox chkbxPwdRemember;
    TextView btnLogin;
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_REMEMBER = "remember";
    private static final String PREF_NAME = "prefs";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        chkbxPwdRemember = findViewById(R.id.chkbx_pwdRemember);
        btnLogin = findViewById(R.id.btn_Login);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean(KEY_REMEMBER, false)) {
            chkbxPwdRemember.setChecked(true);
            btnLogin.setBackgroundResource(R.color.black);
        } else {
            chkbxPwdRemember.setChecked(false);
            btnLogin.setBackgroundResource(R.color.grey);
        }

        etUserName.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        etPassword.setText(sharedPreferences.getString(KEY_PASS, ""));

        chkbxPwdRemember.setOnCheckedChangeListener(this);
        chkbxPwdRemember.addTextChangedListener(this);
        etUserName.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        controlLoginButton();
        managePrefs();

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }


    public void controlLoginButton() {
        try {

            if (!etUserName.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {
                btnLogin.setEnabled(true);
                btnLogin.setBackgroundResource(R.color.black);
            } else {
                btnLogin.setEnabled(false);
                btnLogin.setBackgroundResource(R.color.grey);
            }
        } catch (Exception e) {

            Toast.makeText(LoginActivity.this, "error" + e.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    public void managePrefs() {
        if (chkbxPwdRemember.isChecked()) {
            editor.putString(KEY_USERNAME, etUserName.getText().toString());
            editor.putString(KEY_PASS, etPassword.getText().toString());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();

        } else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_USERNAME);
            editor.remove(KEY_PASS);
            editor.apply();
        }
    }

}