package com.example.vartikasharma.androidchatconversation;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vartikasharma.androidchatconversation.dataModel.UserChatDetail;

import java.util.List;

public class UserMessageDetailAdapter extends RecyclerView.Adapter<UserMessageDetailAdapter.ViewHolder> {
    private static final String LOG_TAG = UserMessageDetailAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private List<UserChatDetail> objects;
    private Context context;

    public UserMessageDetailAdapter(Context context, List<UserChatDetail> objects) {
        this.objects = objects;
        this.context = context;
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
        Log.i(LOG_TAG, "user name, " + userChatDetail.getName());
        Log.i(LOG_TAG, "user message num, " + userChatDetail.getUserNumMessage());
        // refresh data
        refreshDataForRecyclerView(holder);
        // set data
        holder.userName.setText(userChatDetail.getName());
        holder.sentMessageNum.setText("sent message no" + " " + userChatDetail.getUserNumMessage());
       // holder.favMessageNum.setText(R.string.fav_message);
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
