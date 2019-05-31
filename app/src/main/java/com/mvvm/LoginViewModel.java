package com.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.os.Handler;

import java.util.Arrays;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> nameError = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> emailError = new MutableLiveData<>();

    public MutableLiveData<String> spinnerName = new MutableLiveData<>();

    public MutableLiveData<Integer> spinnerItem = new MutableLiveData<>();

    public MutableLiveData<String> customSpinner = new MutableLiveData<>();

    public MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public MutableLiveData<User> userData = new MutableLiveData<>();


    public LiveData<User> getUser() {

        return userData;
    }


    public void onLogin() {

        isUpdating.setValue(true);

        User user = new User();
        user.setEmail(email.getValue());
        user.setName(name.getValue());

        userData.setValue(user);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                isUpdating.setValue(false);
            }
        }, 3000);

    }


//    public void onInputClick(CustomSpinnerInputLayout inputLayout) {
//
//        inputLayout.showPopUp(Arrays.asList(new String[]{"Hello", "Test", "Are you"}));
//        inputLayout.setItemSelectedListener(new CustomSpinnerInputLayout.ItemSelectedListener() {
//            @Override
//            public void onItemChanged(Object o) {
//                customSpinner.setValue(o.toString());
//            }
//        });
//    }


}
