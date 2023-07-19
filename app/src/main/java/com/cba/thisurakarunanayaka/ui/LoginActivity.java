package com.cba.thisurakarunanayaka.ui;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cba.thisurakarunanayaka.R;
import com.cba.thisurakarunanayaka.data.API.APIClient;
import com.cba.thisurakarunanayaka.data.API.Model.Response.UserData;
import com.cba.thisurakarunanayaka.data.API.Model.Response.UserResponse;
import com.cba.thisurakarunanayaka.data.API.Model.UserRequest;
import com.cba.thisurakarunanayaka.data.database.AppExecutors;
import com.cba.thisurakarunanayaka.data.database.CrudOperations;
import com.cba.thisurakarunanayaka.data.database.User_Data;
import com.cba.thisurakarunanayaka.utilities.Constants;
import com.cba.thisurakarunanayaka.utilities.ProgressBars;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ProgressBars progressBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        chkbxPwdRemember = findViewById(R.id.chkbx_pwdRemember);
        btnLogin = findViewById(R.id.btn_Login);
        progressBars = new ProgressBars();

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


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBars.showProgress(LoginActivity.this, "", "Signing...");
                CallApi(etUserName.getText().toString(), etPassword.getText().toString());
            }
        });
        controlLoginButton();
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

    public void CallApi(String username, String Password) {
        UserRequest signageRequest = new UserRequest();
        signageRequest.setUsername(username);
        signageRequest.setPassword(Password);
        if (new Constants().isNetworkAvailable(getApplicationContext())) {
            Call<UserResponse> userResponse = APIClient.getUserServices().getUserResponse(signageRequest);

            userResponse.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                new CrudOperations(getApplicationContext()).deleteAllFromUserData();
                                UserData userData = response.body().getUserData();
                                System.out.println("response.body().getResCode()" + response.body().getResCode());
                                if (response.body().getResCode() == 0) {
                                    if (userData != null) {
                                        new CrudOperations(getApplicationContext()).deleteAllFromUserData();
                                        Log.wtf(TAG, "INSERT_SIZE: " + userData);

                                        User_Data user_data = new User_Data(userData.getId(),userData.getEmail(), userData.getName(), userData.getDob(), userData.getGender(),
                                                userData.getCompany(), userData.getPosition());
                                        new CrudOperations(getApplicationContext()).insertUserData(user_data);

                                        navigateToNext();

                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                        progressBars.dismissProgress();
                        Log.wtf(TAG, "INSERT_USER_DATA: " + e);
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    progressBars.dismissProgress();
                    Constants.showAlertBox(LoginActivity.this, getString(R.string.invalid_username));

                }
            });
        } else {
            progressBars.dismissProgress();
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
        }

    }


    private void navigateToNext() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBars.dismissProgress();
                        Intent intent = new Intent(LoginActivity.this, MainUiActivity.class);
                        startActivity(intent);
                    }
                }, 5000);
            }
        });
    }


}