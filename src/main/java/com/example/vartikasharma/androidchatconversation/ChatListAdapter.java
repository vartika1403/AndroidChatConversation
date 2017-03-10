package com.example.vartikasharma.androidchatconversation;


import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.HashMap;
import java.util.List;

import static android.R.attr.resource;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private static final String LOG_TAG = ChatListAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private List<ChatObject> objects;
    private Context context;
    private HashMap<String, Integer> favMessageNum = new HashMap<>();
    private HashMap<Integer, Boolean> favMessageSelected = new HashMap<>();

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
    public void onBindViewHolder(final ChatListAdapter.ViewHolder holder, final int position) {
        final ChatObject chatObject = objects.get(position);
        // refresh data
        refreshDataForRecyclerView(holder);
        // set data
        String profilePicUrl = chatObject.getImage_url();
        if (!profilePicUrl.isEmpty()) {
            Glide.with(context).load(profilePicUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.userProfileImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    setImageForUser(holder.userProfileImage, resource);
                }
            });
        } else {
            Glide.with(context).load(R.drawable.default_image).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.userProfileImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    setImageForUser(holder.userProfileImage, resource);
                }
            });
        }
        holder.nameText.setText(chatObject.getName());
        holder.userNameText.setText(chatObject.getUsername());
        holder.bodyText.setText(chatObject.getBody());
        String dateTime = chatObject.getMessage_time();
        String[] parts = dateTime.split("T");
        holder.messageDateText.setText(parts[0]);
        holder.messageTimeText.setText(parts[1]);
        holder.favButton.setVisibility(View.VISIBLE);

        if (favMessageSelected.isEmpty() || (favMessageSelected.get(position) == null) || (!favMessageSelected.get(position))) {
            holder.favButton.setImageResource(R.drawable.fav_icon);
            favMessageSelected.put(position, false);
        } else  {
            holder.favButton.setImageResource(R.drawable.fav_red);
        }

        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = holder.nameText.getText().toString();
                if (favMessageSelected.get(position)) {
                    holder.favButton.setImageResource(R.drawable.fav_icon);
                    favMessageNum.put(name, favMessageNum.get(name) - 1);
                    favMessageSelected.put(position, false);
                } else {
                    holder.favButton.setImageResource(R.drawable.fav_red);
                    favMessageSelected.put(position, true);
                    favMessageNum = getFavoriteMessage(name);
                }
            }
        });
        ((MainActivity) context).setFavMessage(favMessageNum);
    }

    private void setImageForUser(ImageView userImage, Bitmap resource) {
        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
        circularBitmapDrawable.setCircular(true);
        userImage.setVisibility(View.VISIBLE);
        userImage.setImageDrawable(circularBitmapDrawable);
    }

    private HashMap<String, Integer> getFavoriteMessage(String name) {
        if (favMessageNum.containsKey(name)) {
            favMessageNum.put(name, favMessageNum.get(name) + 1);
        } else {
            favMessageNum.put(name, 1);
        }
        return favMessageNum;
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
        holder.messageDateText.invalidate();
        holder.messageDateText.setText("");
        holder.favButton.invalidate();
        holder.favButton.setImageResource(0);
        holder.favButton.setVisibility(View.INVISIBLE);
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
        TextView messageDateText;
        ImageView favButton;

        public ViewHolder(View view) {
            super(view);

            userProfileImage = (ImageView) view.findViewById(R.id.user_profile_image);
            nameText = (TextView) view.findViewById(R.id.name_text);
            userNameText = (TextView) view.findViewById(R.id.user_name_text);
            bodyText = (TextView) view.findViewById(R.id.body_text);
            messageTimeText = (TextView) view.findViewById(R.id.message_time);
            messageDateText = (TextView) view.findViewById(R.id.message_date);
            favButton = (ImageView) view.findViewById(R.id.fav_button);
        }
    }
}
