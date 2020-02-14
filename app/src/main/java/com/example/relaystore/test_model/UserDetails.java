package com.example.relaystore.test_model;

public class UserDetails {

    private String id;
    private String l_name;
    private String m_number;
    private String city;
    private String street_no;
    private String name_building;
    private String postal_code;
    private String state;
    private String landmark;


    public UserDetails(String id, String l_name, String m_number, String city, String street_no, String name_building, String postal_code, String state, String landmark) {
        this.id = id;
        this.l_name = l_name;
        this.m_number = m_number;
        this.city = city;
        this.street_no = street_no;
        this.name_building = name_building;
        this.postal_code = postal_code;
        this.state = state;
        this.landmark = landmark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getM_number() {
        return m_number;
    }

    public void setM_number(String m_number) {
        this.m_number = m_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_no() {
        return street_no;
    }

    public void setStreet_no(String street_no) {
        this.street_no = street_no;
    }

    public String getName_building() {
        return name_building;
    }

    public void setName_building(String name_building) {
        this.name_building = name_building;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
