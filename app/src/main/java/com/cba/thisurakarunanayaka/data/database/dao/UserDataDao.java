package com.cba.thisurakarunanayaka.data.database.dao;



import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
