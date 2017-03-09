package com.example.vartikasharma.androidchatconversation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vartikasharma.androidchatconversation.dataModel.ChatObject;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatFragment extends Fragment implements FragmentChangeListener {
    private static final String LOG_TAG = ChatFragment.class.getSimpleName();
    private static final String URL = "http://haptik.mobi/android/test_data/";
    private List<ChatObject> listItem = new ArrayList<>();

    @BindView(R.id.chat_list)
    public RecyclerView chatList;

    private ChatListAdapter chatListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games_layout, container, false);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        chatList.setLayoutManager(layoutManager);
        fetchDataFromApiEndPoint();



        return view;
    }

    private void fetchDataFromApiEndPoint() {
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
                        Log.i(LOG_TAG, "jsonArray,"  + jsonArray.getJSONObject(0).getString("message-time"));

                        listItem = Arrays.asList(gson.fromJson(jsonArray.toString(), ChatObject[].class));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            listItem.get(i).setMessage_time(jsonArray.getJSONObject(i).getString("message-time"));
                            listItem.get(i).setImage_url(jsonArray.getJSONObject(i).getString("image-url"));
                            Log.i(LOG_TAG, "list item, " + listItem.get(i).getBody());
                            Log.i(LOG_TAG, "list item image, " + listItem.get(i).getImage_url());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initializeAdapter();
                            }
                        });


                           /* listItem = Arrays.asList(gson.fromJson(jsonArray.toString(), ChatObject[].class));
                            if (listItem.get(i).getCar_Name() == null ||
                                    listItem.get(i).getBrand_Name() == null ||
                                    listItem.get(i).getPart_Name().isEmpty() ||
                                    listItem.get(i).getQuantity_In_Stock() == 0 ||
                                    listItem.get(i).getPart_MRP() == 0.0) {
                                openListItem.add(listItem.get(i));*//**//**//**//**//**//**//**//*
                            }*//**//**//**//**/
                        // write value to the firbase in data uri
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i(LOG_TAG, "response is not successful");
                }
            }
        });

    }

    private void initializeAdapter() {
        if (listItem != null) {
            Log.i(LOG_TAG, "list item," + listItem.size());

            chatListAdapter = new ChatListAdapter(getActivity(), listItem);
            chatList.setAdapter(chatListAdapter);
        }

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

