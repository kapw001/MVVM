package com.mvvm;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import java.util.List;

public class CustomSpinnerInputLayout extends TextInputLayout {

    private ListPopupWindow listPopupWindow;
    private ArrayAdapter arrayAdapter;
    private List list;
    private int selectedPosition = -1;
    private ItemSelectedListener itemSelectedListener;

    public CustomSpinnerInputLayout(Context context) {
        super(context);

        init(null);
    }

    public CustomSpinnerInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomSpinnerInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        listPopupWindow = new ListPopupWindow(getContext());


        setFocusable(false);

        if (getEditText() != null) getEditText().setFocusable(false);


//        getEditText().setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (list != null) {
//
//                    showPopUp(list);
//                }
//            }
//        });

    }

    public void setItemSelectedListener(ItemSelectedListener listener) {

        this.itemSelectedListener = listener;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        listPopupWindow = new ListPopupWindow(getContext());


        setFocusable(false);

        if (getEditText() != null) getEditText().setFocusable(false);
    }

    public void showPopUp(final List list) {

        this.list = list;

        if (list != null && !list.isEmpty()) {

            arrayAdapter = new ArrayAdapter(getContext(),
                    android.R.layout.simple_list_item_1, list);

            listPopupWindow.setAdapter(arrayAdapter);

            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listPopupWindow.dismiss();
                    selectedPosition = position;
                    getEditText().setText(arrayAdapter.getItem(position).toString());

                    if (itemSelectedListener != null)
                        itemSelectedListener.onItemChanged(arrayAdapter.getItem(position));

                }


            });

            listPopupWindow.setAnchorView(this);
            listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
            listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);

            listPopupWindow.setModal(true);

            listPopupWindow.show();


        } else {

            Toast.makeText(getContext(), "Please provide data to select", Toast.LENGTH_SHORT).show();
        }


    }


    public Object getItem() {

        try {
            return list != null && list.get(selectedPosition) != null ? list.get(selectedPosition) : null;
        } catch (ArrayIndexOutOfBoundsException e) {

            return null;
        }

    }


    public interface ItemSelectedListener {

        void onItemChanged(Object o);

    }
}
