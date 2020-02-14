package com.example.relaystore.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
;

import com.example.relaystore.Login_Activity;
import com.example.relaystore.Order_list_Activity;
import com.example.relaystore.test_model.UserAddress;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;
    String password;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "CrossLanePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NIC = "nic";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_TOKEN_NAME = "token";

    // Email address (make variable public to access from outside)
    public static final String KEY_USERTYPE = "password";
    public static final String KEY_USER_ID = "userID";
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String email, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing nameId in pref
        editor.putString(KEY_NAME, email);

        // Storing userType in pref
        editor.putString(KEY_USERTYPE, password);
//        editor.putString(KEY_USER_ID, uid);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login_Activity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        } else {
            // user is logged in redirect him to Main Activity
            Intent i = new Intent(_context, Order_list_Activity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);



        }
    }


    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        user.put(KEY_TOKEN_NAME, pref.getString(KEY_TOKEN_NAME, null));

        // user userType
        user.put(KEY_USERTYPE, pref.getString(KEY_USERTYPE, null));

        // return user
        return user;
    }





    /**
     * Create login session
     * */
    public void createUserSession(String nic,String name, String phone, String email,String address){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing nic in pref
        editor.putString(KEY_NIC,nic);

        // Storing nameId in pref
        editor.putString(KEY_NAME, name);

        // Storing phone in pref
        editor.putString(KEY_PHONE, phone);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // Storing address in pref
        editor.putString(KEY_ADDRESS, address);

        // commit changes
        editor.commit();
    }


    /**
     * Get stored session data
     * */
    public HashMap<String, String> getDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NIC, pref.getString(KEY_NIC, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_ADDRESS, pref.getString(KEY_ADDRESS, null));

        // return user
        return user;
    }

    public void insertToSP(HashMap<String, UserAddress> jsonMap) {
        String jsonString = new Gson().toJson(jsonMap);
  //      SharedPreferences sharedPreferences = pref("HashMap", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("addressMap", jsonString);
        editor.apply();
        Log.e("tempData",""+jsonString);
    }

    public HashMap<String, UserAddress> readFromSP(){
     //   SharedPreferences sharedPreferences = pref("HashMap", MODE_PRIVATE);
        //String defValue = new Gson().toJson(new HashMap<String, UserAddress>());
        String json=pref.getString("addressMap","");
        TypeToken<HashMap<String,UserAddress>> token = new TypeToken<HashMap<String,UserAddress>>() {};
        HashMap<String,UserAddress> retrievedMap=new Gson().fromJson(json,token.getType());
        Log.e("tempNewData",""+json);
        return retrievedMap;
    }



    /**
     * Get stored session data
     * */
    public String getValue(String key)
    {
        return pref.getString(key,"");
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login_Activity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


    /**
     * Create login session
     * */
    public void createToken(String token){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing nameId in pref
        editor.putString(KEY_TOKEN_NAME, token);


        // commit changes
        editor.commit();
    }



}