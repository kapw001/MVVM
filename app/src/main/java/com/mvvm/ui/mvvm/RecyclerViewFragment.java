package com.mvvm.ui.mvvm;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import com.mvvm.R;

import android.widget.Toast;
import android.os.Handler;


import android.view.ViewGroup;
import android.view.MenuInflater;

/**
 * A simple {@link Fragment} subclass.
 */


public class RecyclerViewFragment extends Fragment {

    private MvvmViewModel mViewModel;

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;


    private RecyclerViewAdapter mAdapter;


    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        // ButterKnife.bind(this);
        findViews(view);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MvvmViewModel.class);

        mViewModel.init();
        setAdapter();



        mViewModel.getUser().observe(this, new Observer<ArrayList<UserModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserModel> userModels) {

                mAdapter.notifyDataSetChanged();

            }
        });

    }


    private void findViews(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }


    private void setAdapter() {


        mAdapter = new RecyclerViewAdapter(getActivity(), mViewModel.getUser().getValue());

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, UserModel model) {

                //handle item click events here
                Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });


    }

}
