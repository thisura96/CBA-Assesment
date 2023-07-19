package com.cba.thisurakarunanayaka.data.database;

import android.content.Context;
import android.util.Log;

public class CrudOperations {
    private AppDatabase mDb;

    public CrudOperations(Context context)
    {
        mDb = AppDatabase.getInstance(context);
    }

    public void deleteAllFromUserData()
    {
        mDb.userDataDao().deleteUserData();
    }

    public void insertUserData(User_Data userData)
    {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (userData != null)
                {
                    mDb.userDataDao().insertUserData(userData);
                    Log.wtf("CRUD_OPERATION", "AN USER DATA INSERTED TO DB");
                }
            }
        });
    }

}
