package com.example.vartikasharma.androidchatconversation;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vartikasharma.androidchatconversation.dataModel.UserChatDetail;

import java.util.HashMap;
import java.util.List;

public class UserMessageDetailAdapter extends RecyclerView.Adapter<UserMessageDetailAdapter.ViewHolder> {
    private static final String LOG_TAG = UserMessageDetailAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private List<UserChatDetail> objects;
    private HashMap<String, Integer> favMessage;
    private Context context;

    public UserMessageDetailAdapter(Context context, List<UserChatDetail> objects, HashMap<String, Integer> favMessage) {
        this.objects = objects;
        this.context = context;
        this.favMessage = favMessage;
        Log.i(LOG_TAG, "favMessage name, " + favMessage);

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.message_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserChatDetail userChatDetail = objects.get(position);
        // refresh data
        refreshDataForRecyclerView(holder);
        // set data
        holder.userName.setText(userChatDetail.getName());
        holder.sentMessageNum.setText("sent:" + " " + userChatDetail.getUserNumMessage());
        Log.i(LOG_TAG, "favMessage name onbind, " + favMessage);
        if (favMessage!= null) {
            holder.favMessageNum.setText("fav:" + " " +"(" + favMessage.get(userChatDetail.getName()) + ")");
        } else {
            holder.favMessageNum.setText("fav:" + " " + "0");
        }
    }


    private void refreshDataForRecyclerView(ViewHolder holder) {
        holder.userName.invalidate();
        holder.userName.setText("");
        holder.sentMessageNum.invalidate();
        holder.sentMessageNum.setText("");
        holder.favMessageNum.invalidate();
        holder.favMessageNum.setText("");
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView sentMessageNum;
        TextView favMessageNum;

        public ViewHolder(View view) {
            super(view);

            userName = (TextView) view.findViewById(R.id.user_name);
            sentMessageNum = (TextView) view.findViewById(R.id.sent_message_num_text);
            favMessageNum = (TextView) view.findViewById(R.id.fav_message_num_text);
        }
    }
}
