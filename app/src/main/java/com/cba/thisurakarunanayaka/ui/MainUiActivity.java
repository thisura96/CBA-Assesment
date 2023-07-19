package com.cba.thisurakarunanayaka.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cba.thisurakarunanayaka.R;
import com.cba.thisurakarunanayaka.data.database.AppDatabase;
import com.cba.thisurakarunanayaka.data.database.AppExecutors;
import com.cba.thisurakarunanayaka.data.database.User_Data;
import com.cba.thisurakarunanayaka.utilities.ProgressBars;

import java.util.List;

public class MainUiActivity extends AppCompatActivity {


    TextView tv_email, tv_name, tv_dob, tv_gender, tv_company, tv_position;
    TextView btnLoadUserData;
    List<User_Data> user_data;
    private AppDatabase mDb;
    private ProgressBars progressBars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);


        tv_email = findViewById(R.id.tv_email);
        tv_name = findViewById(R.id.tv_name);
        tv_dob = findViewById(R.id.tv_dob);
        tv_gender = findViewById(R.id.tv_gender);
        tv_company = findViewById(R.id.tv_company);
        tv_position = findViewById(R.id.tv_position);
        progressBars = new ProgressBars();
        progressBars.showProgress(MainUiActivity.this, "", "Waiting...");
        fetchData();

        btnLoadUserData = findViewById(R.id.loadUserData);
        mDb = AppDatabase.getInstance(MainUiActivity.this);

        btnLoadUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValuesForScreen(user_data);
            }
        });
    }

    private void setValuesForScreen(List<User_Data> user_data) {
        if (user_data.size() > 0) {
            for (User_Data data : user_data) {
                tv_email.setText(data.getEmail() == null ? "-" : data.getEmail());
                tv_name.setText(data.getName() == null ? "-" : data.getName());
                tv_dob.setText(data.getDob() == null ? "-" : data.getDob());
                tv_gender.setText(data.getGender() == null ? "-" : data.getGender());
                tv_company.setText(data.getCompany() == null ? "-" : data.getCompany());
                tv_position.setText(data.getPosition() == null ? "-" : data.getPosition());
            }

        }
    }

    private void fetchData() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                progressBars.dismissProgress();
                user_data = mDb.userDataDao().loadAllUserData();
                if (user_data != null) {
                    try {
                        if (user_data.size() > 0) {
                            btnLoadUserData.setEnabled(true);
                            btnLoadUserData.setBackgroundResource(R.color.black);
                        } else {
                            btnLoadUserData.setEnabled(true);
                            btnLoadUserData.setBackgroundResource(R.color.black);
                        }
                    } catch (Exception e) {
                        Log.wtf("setValuesForScreen", e);
                    }
                }

            }
        });
    }
}