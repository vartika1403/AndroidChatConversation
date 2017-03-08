package com.example.vartikasharma.androidchatconversation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class ChatFragment extends Fragment implements FragmentChangeListener {
    private static final String LOG_TAG = ChatFragment.class.getSimpleName();
    private static final int TOTAL_NUMBER_LOCKED = 20;
    private static final int UNLOCK_INCENTIVE = 2;
    private View view;
    private int numberOfFriends;
    private int numberLocked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_games_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onShowFragment() {
        Log.d(LOG_TAG, "onShowFragment: Games Fragment shown");
    }

    @Override
    public void onHideFragment() {
        Log.d(LOG_TAG, "onShowFragment: Games Fragment hidden");
    }

    @Override
    public void onScrollFragment(int position, int offset) {

    }
}

