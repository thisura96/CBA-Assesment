package com.cba.thisurakarunanayaka.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_data")
public class UserData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String name;
    private String dob;
    private String gender;
    private String company;
    private String position;


    public UserData(String email, String name, String dob, String gender, String company, String position) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.company = company;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
