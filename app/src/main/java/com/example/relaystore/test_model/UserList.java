package com.example.relaystore.test_model;

import java.util.List;

public class UserList {

    private String NumId;
    private String NIC_Handle;
    private String D_Creation;

    public UserList(String numId, String NIC_Handle, String d_Creation) {
        NumId = numId;
        this.NIC_Handle = NIC_Handle;
        D_Creation = d_Creation;
    }

    public UserList(List<UserList> users) {
    }

    public String getNumId() {
        return NumId;
    }

    public void setNumId(String numId) {
        NumId = numId;
    }

    public String getNIC_Handle() {
        return NIC_Handle;
    }

    public void setNIC_Handle(String NIC_Handle) {
        this.NIC_Handle = NIC_Handle;
    }

    public String getD_Creation() {
        return D_Creation;
    }

    public void setD_Creation(String d_Creation) {
        D_Creation = d_Creation;
    }
}
