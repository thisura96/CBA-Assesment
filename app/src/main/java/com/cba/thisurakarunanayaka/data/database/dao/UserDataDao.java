package com.cba.thisurakarunanayaka.data.database.dao;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cba.thisurakarunanayaka.data.database.User_Data;

import java.util.List;

@Dao
public interface UserDataDao {

    @Query("SELECT * FROM User_Data")
    List<User_Data> loadAllUserData();

    @Insert
    void insertUserData(User_Data userData);

    @Update
    void updateUserData(User_Data userData);

    @Delete
    void delete(User_Data userData);

    @Query("SELECT * FROM User_Data WHERE id = :id")
    User_Data UserDataById(int id);

    @Query("DELETE FROM User_Data")
    void deleteUserData();

}
