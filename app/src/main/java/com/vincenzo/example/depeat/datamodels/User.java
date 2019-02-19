package com.vincenzo.example.depeat.datamodels;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public static final String REGISTER_ENDPOINT = "auth/local/register";
    public static final String LOGIN_ENDPOINT = "auth/local";
    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";

    private String username,email,accessToken, id;

    public User(JSONObject user,String accessToken) throws JSONException {

        this.accessToken = accessToken;
        id = user.getString("id");
        email = user.getString("email");
        username = user.getString("username");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
