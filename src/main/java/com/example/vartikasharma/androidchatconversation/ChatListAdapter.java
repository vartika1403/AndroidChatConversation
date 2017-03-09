package com.example.vartikasharma.androidchatconversation;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.vartikasharma.androidchatconversation.dataModel.ChatObject;

import java.util.List;
import java.util.Locale;

import static com.example.vartikasharma.androidchatconversation.R.id.image;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ChatObject> objects;
    private Context context;

    public ChatListAdapter(Context context, List<ChatObject> objects) {
        this.objects = objects;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChatListAdapter.ViewHolder holder, int position) {
        final ChatObject chatObject = objects.get(position);
        Log.i("chatobject, ", chatObject.getBody());
        Log.i("chat message time, ", chatObject.getMessage_time());
        Log.i("chat image, ", chatObject.getImage_url());
        // refresh data
        refreshDataForRecyclerView(holder);
        // set data
        String profilePicUrl = chatObject.getImage_url();
        Log.i("profile pic url, ", profilePicUrl);
        Glide.with(context).load(profilePicUrl);
        Glide.with(context).load(profilePicUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.userProfileImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.userProfileImage.setImageDrawable(circularBitmapDrawable);
            }
        });
        holder.nameText.setText(chatObject.getName());
        holder.userNameText.setText(chatObject.getUsername());
        holder.bodyText.setText(chatObject.getBody());
        holder.messageTimeText.setText(chatObject.getMessage_time());
    }

    private void refreshDataForRecyclerView(ViewHolder holder) {
        holder.userProfileImage.invalidate();
        holder.userProfileImage.setVisibility(View.INVISIBLE);
        holder.nameText.invalidate();
        holder.nameText.setText("");
        holder.userNameText.invalidate();
        holder.userNameText.setText("");
        holder.bodyText.invalidate();
        holder.bodyText.setText("");
        holder.messageTimeText.invalidate();
        holder.messageTimeText.setText("");
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userProfileImage;
        TextView nameText;
        TextView userNameText;
        TextView bodyText;
        TextView messageTimeText;

        public ViewHolder(View view) {
            super(view);

            userProfileImage = (ImageView) view.findViewById(R.id.user_profile_image);
            nameText = (TextView) view.findViewById(R.id.name_text);
            userNameText = (TextView) view.findViewById(R.id.user_name_text);
            bodyText = (TextView) view.findViewById(R.id.body_text);
            messageTimeText = (TextView) view.findViewById(R.id.message_time);
        }
    }
}
