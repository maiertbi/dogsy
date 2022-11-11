package com.example.dogsy.domain.model;

public class Like {
    private int id;
    private boolean isLiked;
    private int outgoingUser;
    private int incomingUser;

    public Like() {
    }

    public Like(int id, boolean isLiked, int outgoingUser, int incomingUser) {
        this.id = id;
        this.isLiked = isLiked;
        this.outgoingUser = outgoingUser;
        this.incomingUser = incomingUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getOutgoingUser() {
        return outgoingUser;
    }

    public void setOutgoingUser(int outgoingUser) {
        this.outgoingUser = outgoingUser;
    }

    public int getIncomingUser() {
        return incomingUser;
    }

    public void setIncomingUser(int incomingUser) {
        this.incomingUser = incomingUser;
    }
}
