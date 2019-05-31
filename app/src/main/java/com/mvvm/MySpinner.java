package com.mvvm;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MySpinner extends TextInputLayout implements AdapterView.OnItemClickListener, View.OnClickListener {

    private int mPosition = ListView.INVALID_POSITION;
    private ListPopupWindow listPopupWindow;
    private ListAdapter mAdapter;
    private AdapterView.OnItemClickListener mItemClickListener;

    public MySpinner(Context context) {
        super(context);
        init();

    }

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

////        setOnClickListener(this);
//        setFocusable(false);
//        if (getEditText() != null) {
//            getEditText().setFocusable(false);
//            getEditText().setOnClickListener(this);
//        }
//
//        listPopupWindow = new ListPopupWindow(getContext());
//        listPopupWindow.setAnchorView(this);
//
//        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
//        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
//        listPopupWindow.setOnItemClickListener(this);
////        listPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        listPopupWindow.setModal(true);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setOnClickListener(this);
        setFocusable(false);
        if (getEditText() != null) {
            getEditText().setFocusable(false);
            getEditText().setOnClickListener(this);
        }

        listPopupWindow = new ListPopupWindow(getContext());
        listPopupWindow.setAnchorView(this);

        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setOnItemClickListener(this);
//        listPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        listPopupWindow.setModal(true);
    }

    public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {

        this.mAdapter = adapter;
        listPopupWindow.setAdapter(this.mAdapter);

    }


    public <T> void setAdapterList(List<T> list) {

        setAdapter(new ArrayAdapter<T>(getContext(), android.R.layout.simple_list_item_1, list));

    }

    /**
     * <p>Sets the listener that will be notified when the user clicks an item
     * in the drop down list.</p>
     *
     * @param l the item click listener
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener l) {
        mItemClickListener = l;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position;

        if (listPopupWindow != null) listPopupWindow.dismiss();

        if (getEditText() != null) {
            Object o = mAdapter.getItem(position);
            getEditText().setText(o != null ? o.toString() : "");
        }

        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(parent, view, position, id);
        }
    }

    public Object getSelectedItem() {

        return mPosition != -1 ? mAdapter.getItem(mPosition) : null;
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    public int getSelectedPosition() {
        return mPosition;
    }

    @Override
    public void onClick(View v) {


        if (listPopupWindow.getAnchorView() != null) {

            if (mAdapter != null) {

                listPopupWindow.show();

            } else {

                showToast("List adapter is not set ");
            }

        } else {

            showToast("Please provide AnchorView to popuplistview");
        }

    }


    private void showToast(String msg) {

        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }
}
