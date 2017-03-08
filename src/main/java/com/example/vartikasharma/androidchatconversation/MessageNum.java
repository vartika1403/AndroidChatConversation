package com.example.vartikasharma.androidchatconversation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;

public class MessageNum extends Fragment implements FragmentChangeListener {
    private static final String LOG_TAG = MessageNum.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");

//        ViewUtils.setWindowImmersive(gamesChatActivity.getWindow());
        View view = inflater.inflate(R.layout.activity_chat, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onShowFragment() {
        Log.d(LOG_TAG, "onShowFragment: Chat Fragment shown");
    }

    @Override
    public void onHideFragment() {
        Log.i(LOG_TAG, "onHideFragment: Chat Fragment ");
    }

    @Override
    public void onScrollFragment(int position, int postionOffsetPixels) {
        Log.i(LOG_TAG, "onScrollFragment: Chat Fragment " + position);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume" + (isAdded() && isVisible() && getUserVisibleHint()));
    }

    @Override
    public void onPause() {
        Log.i(LOG_TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.i(LOG_TAG, "onDestoryView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
