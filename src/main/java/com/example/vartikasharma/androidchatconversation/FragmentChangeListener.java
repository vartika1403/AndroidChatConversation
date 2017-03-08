package com.example.vartikasharma.androidchatconversation;

public interface FragmentChangeListener {
    void onShowFragment();

    void onHideFragment();

    void onScrollFragment(int position, int offset);
}
