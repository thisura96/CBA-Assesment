package com.cba.thisurakarunanayaka.data.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cba.thisurakarunanayaka.data.database.UserData;

import java.util.List;

public interface UserDataDao {

    @Query("SELECT * FROM user_data")
    List<UserData> loadAllUserData();

    @Insert
    void insertUserData(UserData userData);

    @Update
    void updateUserData(UserData userData);

    @Delete
    void delete(UserData userData);

    @Query("SELECT * FROM user_data WHERE id = :id")
    UserData UserDataById(int id);

    @Query("DELETE FROM user_data")
    void deleteUSerData();

}
