package com.example.vartikasharma.androidchatconversation;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtility {
    private static final String PREF_FILE_NAME = BuildConfig.APPLICATION_ID + ".common.pref";


    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                PREF_FILE_NAME, Context.MODE_PRIVATE);

        return sharedPref.edit();
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static void setPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.apply();
    }
}
