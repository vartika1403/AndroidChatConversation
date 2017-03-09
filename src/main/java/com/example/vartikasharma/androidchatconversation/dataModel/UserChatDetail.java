package com.example.vartikasharma.androidchatconversation.dataModel;


public class UserChatDetail {
    private String Name;
    private int userNumMessage;
    private int userFavMessage;

    public UserChatDetail() {

    }

    public UserChatDetail(String Name, int userNumMessage, int userFavMessage) {
        this.Name = Name;
        this.userNumMessage = userNumMessage;
        this.userFavMessage = userFavMessage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUserNumMessage() {
        return userNumMessage;
    }

    public void setUserNumMessage(int userNumMessage) {
        this.userNumMessage = userNumMessage;
    }

    public int getUserFavMessage() {
        return userFavMessage;
    }

    public void setUserFavMessage(int userFavMessage) {
        this.userFavMessage = userFavMessage;
    }
}
