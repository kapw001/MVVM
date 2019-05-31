package com.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.mvvm.databinding.ActivityLoginBinding;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.setVm(viewModel);

        final User user = new User();


        viewModel.spinnerItem.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                Log.e(TAG, "onChanged: spinnet position " + integer);
            }
        });

        viewModel.spinnerName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                Log.e(TAG, "onChanged: spinner name " + s);
            }
        });


        viewModel.email.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                user.setEmail(s);

                viewModel.userData.setValue(user);

            }
        });

        viewModel.name.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                user.setName(s);

                viewModel.userData.setValue(user);
            }
        });


        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

                if (user != null) {

                    boolean isValid = isValid(user);
                    binding.login.setEnabled(isValid);


                }
                Log.e(TAG, "onChanged: " + user.toString());
            }
        });


        viewModel.isUpdating.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

                if (aBoolean) {

                    binding.progressBar.setVisibility(View.VISIBLE);

                } else {
                    binding.progressBar.setVisibility(View.GONE);
                }

            }
        });

//
        binding.customSelectedInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.customSelectedInput.showPopUp(Arrays.asList(new String[]{"Hello", "Test", "Are you"}));

//                binding.customSelectedInput.setItemSelectedListener(new CustomSpinnerInputLayout.ItemSelectedListener() {
//                    @Override
//                    public void onItemChanged(Object o) {
//
//                        viewModel.customSpinner.setValue(o.toString());
//                    }
//                });
            }
        });


        viewModel.customSpinner.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                Log.e(TAG, "onChanged: custom spinner changed  " + s.toString());
            }
        });

    }

//    public void onLogin(View view) {
//
//        viewModel.onLogin();
//
//    }


    private boolean isValid(User user) {

        boolean isValid = false;

        viewModel.emailError.setValue(null);
        viewModel.nameError.setValue(null);

        if (TextUtils.isEmpty(user.getName())) {
            viewModel.nameError.setValue("Enter a valid name");

            return false;

        } else if (TextUtils.isEmpty(user.getName())) {
            viewModel.emailError.setValue("Enter a valid email");

            return false;

        } else {

            return true;
        }

    }


//    private void setError(EditText editText, String s) {
//
//        if (s != null) {
//            editText.setEnabled(true);
//            editText.setError("Enter a valid name");
//        } else {
//            editText.setEnabled(false);
//            editText.setError(null);
//        }
//    }


    @BindingAdapter({"error"})
    public static void error(EditText editText, String s) {


        if (s != null) {
//            editText.setErr(true);
            editText.setError(s);
        } else {
//            editText.setEnabled(false);
            editText.setError(null);
        }

    }


    @BindingAdapter("realValue")
    public static void setTime(CustomSpinnerInputLayout view, final Object newValue) {
        // Important to break potential infinite loops.

        if (newValue != null)
            view.getEditText().setText(newValue.toString());


    }

    @InverseBindingAdapter(attribute = "time")
    public static Object getTime(CustomSpinnerInputLayout view) {
        return view.getItem();
    }

    @BindingAdapter("app:timeAttrChanged")
    public static void setListeners(
            CustomSpinnerInputLayout view, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.

        view.setItemSelectedListener(new CustomSpinnerInputLayout.ItemSelectedListener() {
            @Override
            public void onItemChanged(Object o) {

                attrChange.onChange();
            }
        });
    }


    @BindingAdapter(value = "realValueAttrChanged")
    public static void setListener(final CustomSpinnerInputLayout editText, final InverseBindingListener listener) {
        if (listener != null) {

//            editText.getEditText().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    editText.showPopUp(Arrays.asList(new String[]{"Hello", "Test", "Are you"}));
//                }
//            });
            editText.setItemSelectedListener(new CustomSpinnerInputLayout.ItemSelectedListener() {
                @Override
                public void onItemChanged(Object o) {

                    listener.onChange();
                }
            });
        }
    }

    @BindingAdapter("realValue")
    public static void setRealValue(CustomSpinnerInputLayout view, Object value) {
        if (value != null) {
            view.getEditText().setText(value.toString());
        }
    }

    @InverseBindingAdapter(attribute = "realValue")
    public static Object getRealValue(CustomSpinnerInputLayout editText) {
        return editText.getItem();
    }

}
