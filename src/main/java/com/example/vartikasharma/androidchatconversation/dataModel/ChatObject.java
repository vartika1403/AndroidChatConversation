package com.example.vartikasharma.androidchatconversation.dataModel;


public class ChatObject {
    private String body;
    private String username;
    private String Name;
    private String image_url;
    private String message_time;

    public ChatObject(String body, String username, String name, String image_url, String message_time) {
        this.body = body;
        this.username = username;
        Name = name;
        this.image_url = image_url;
        this.message_time = message_time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }


}
