package application.controller;

import application.entities.Token;

public class UserLoginResponse {

    private String username;

    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserLoginResponse(Token token) {
        this.username = token.getUser().getUsername();
        this.token = token.getToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
