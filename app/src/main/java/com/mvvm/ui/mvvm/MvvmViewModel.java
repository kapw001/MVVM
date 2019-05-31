package com.mvvm.ui.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mvvm.repositrary.DataRepositary;

import java.util.ArrayList;
import java.util.List;

public class MvvmViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<ArrayList<UserModel>> userModelList;

    DataRepositary dataRepositary;

    public void init() {

        if (userModelList != null) return;

        dataRepositary = new DataRepositary();

        userModelList = dataRepositary.getUser();

    }

    public LiveData<ArrayList<UserModel>> getUser() {

        return userModelList;
    }
}
