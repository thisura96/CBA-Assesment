package com.cba.thisurakarunanayaka.data.API.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("res_code")
    @Expose
    private Integer resCode;
    @SerializedName("res_desc")
    @Expose
    private String resDesc;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
