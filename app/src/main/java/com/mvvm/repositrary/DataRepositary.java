package com.mvvm.repositrary;

import android.arch.lifecycle.MutableLiveData;

import com.mvvm.ui.mvvm.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DataRepositary {

    private ArrayList<UserModel> modelList = new ArrayList<>();


    public MutableLiveData<ArrayList<UserModel>> getUser() {

        setData();

        MutableLiveData<ArrayList<UserModel>> data = new MutableLiveData<>();

        data.setValue(modelList);

        return data;
    }

    private void setData() {

        modelList.add(new UserModel("Android", "Hello " + " Android"));
        modelList.add(new UserModel("Beta", "Hello " + " Beta"));
        modelList.add(new UserModel("Cupcake", "Hello " + " Cupcake"));
        modelList.add(new UserModel("Donut", "Hello " + " Donut"));
        modelList.add(new UserModel("Eclair", "Hello " + " Eclair"));
        modelList.add(new UserModel("Froyo", "Hello " + " Froyo"));
        modelList.add(new UserModel("Gingerbread", "Hello " + " Gingerbread"));
        modelList.add(new UserModel("Honeycomb", "Hello " + " Honeycomb"));
        modelList.add(new UserModel("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich"));
        modelList.add(new UserModel("Jelly Bean", "Hello " + " Jelly Bean"));
        modelList.add(new UserModel("KitKat", "Hello " + " KitKat"));
        modelList.add(new UserModel("Lollipop", "Hello " + " Lollipop"));
        modelList.add(new UserModel("Marshmallow", "Hello " + " Marshmallow"));
        modelList.add(new UserModel("Nougat", "Hello " + " Nougat"));
        modelList.add(new UserModel("Android O", "Hello " + " Android O"));


    }

}
