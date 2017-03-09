package com.example.vartikasharma.androidchatconversation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.vartikasharma.androidchatconversation.dataModel.ChatObject;
import com.example.vartikasharma.androidchatconversation.dataModel.UserChatDetail;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageNum extends Fragment implements FragmentChangeListener {
    private static final String LOG_TAG = MessageNum.class.getSimpleName();
    private static final String URL = "http://haptik.mobi/android/test_data/";
    @BindView(R.id.message_num_list)
    public RecyclerView messageNumList;
    private List<UserChatDetail> userDetail = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");

//        ViewUtils.setWindowImmersive(gamesChatActivity.getWindow());
        View view = inflater.inflate(R.layout.fragment_user_message_num, container, false);
        ButterKnife.bind(this, view);

        //fetch data from end point
        fetchDataFromApiEndPointForMessageNum();

        return view;
    }

    private void fetchDataFromApiEndPointForMessageNum() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), R.string.toast_err_msg_fetch_data, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                Log.i(LOG_TAG, "json data" + jsonData);
                Gson gson = new Gson();
                if (response.isSuccessful()) {
                    JSONObject json = null;
                    JSONArray jsonArray;
                    try {
                        json = new JSONObject(jsonData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        assert json != null;
                        jsonArray = json.getJSONArray("messages");
                        Log.i(LOG_TAG, "jsonArray," + jsonArray.getJSONObject(0).getString("message-time"));
                        Log.i(LOG_TAG, "json array length, " + jsonArray.length());

                        ArrayList<String> name = new ArrayList<String>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Log.i(LOG_TAG, "userChatDetails, " + jsonArray.getJSONObject(i).getString("Name"));
                            name.add(jsonArray.getJSONObject(i).getString("Name"));
                        }
                        // count no of messages for each user
                        HashMap<String, Integer> countMessage = new HashMap<String, Integer>();

                        for (int i = 0; i < name.size(); i++) {
                            if (countMessage.containsKey(name.get(i))) {
                                String value = name.get(i);
                                Log.i(LOG_TAG, "the value, " + value);
                                countMessage.put(name.get(i), countMessage.get(value) + 1);
                                Log.i(LOG_TAG, "the value count," + countMessage.get(name.get(i)));
                            } else {
                                countMessage.put(name.get(i), 1);
                            }
                        }
                        //get the value for each user and no of message of each user
                        for (String key : countMessage.keySet()) {
                            UserChatDetail userChatDetail = new UserChatDetail();
                            Log.i(LOG_TAG, "the key, " + key);
                            Log.i(LOG_TAG, "the key value or count, " + countMessage.get(key));
                            userChatDetail.setName(key);
                            userChatDetail.setUserNumMessage(countMessage.get(key));
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // initializeAdapter();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i(LOG_TAG, "response is not successful");
                }
            }
        });
    }

   /* private void initializeAdapter() {
        if (listItem != null) {
            Log.i(LOG_TAG, "list item," + listItem.size());

            chatListAdapter = new ChatListAdapter(getActivity(), listItem);
            chatList.setAdapter(chatListAdapter);
        }

    }*/

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
