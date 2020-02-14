package com.example.relaystore.api;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;


    public DefaultResponse(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
