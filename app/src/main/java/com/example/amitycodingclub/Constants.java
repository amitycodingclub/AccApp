package com.example.amitycodingclub;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {
    SharedPreferences sharedPreferences;
    Context context;

    private String ID = "";
    private String user_login="";
    private String user_nicename="";
    private String user_email="";
    private String user_url="";
    private String user_registered=""; //date and time of registration
    private String user_activation_key="";
    private String user_status="";
    private String display_name="";
    private String spam="";
    private String deleted="";
    private boolean subscriber=false;
    private String cap_key="";
    private String roles="";
    private boolean read=false;
    private boolean level_0=false;
    private boolean woffice_read_wikies=false;
    private String filter="";



    public String getID() {
        ID = sharedPreferences.getString("ID","");
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
        sharedPreferences.edit().putString("ID",ID).apply();

    }

    public String getUser_login() {
        user_login = sharedPreferences.getString("user_login","");
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
        sharedPreferences.edit().putString("user_login",user_login).apply();
    }

    public String getUser_nicename() {
        user_nicename = sharedPreferences.getString("user_nicename","");
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename) {
        this.user_nicename = user_nicename;
        sharedPreferences.edit().putString("user_nicename",user_nicename).apply();
    }

    public String getUser_email() {
        user_email = sharedPreferences.getString("user_email","");
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
        sharedPreferences.edit().putString("user_email",user_email).apply();
    }

    public String getUser_url() {
        user_url = sharedPreferences.getString("user_url","");
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
        sharedPreferences.edit().putString("user_url",user_url).apply();

    }

    public String getUser_registered() {
        user_registered = sharedPreferences.getString("user_registered","");
        return user_registered;
    }

    public void setUser_registered(String user_user_registered) {
        this.user_registered = user_user_registered;
        sharedPreferences.edit().putString("user_registered",user_registered).apply();
    }

    public String getUser_activation_key() {
        user_activation_key = sharedPreferences.getString("user_activation_key","");
        return user_activation_key;
    }

    public void setUser_activation_key(String user_activation_key) {
        this.user_activation_key = user_activation_key;
        sharedPreferences.edit().putString("user_activation_key",user_activation_key).apply();
    }

    public String getUser_status() {
        user_status = sharedPreferences.getString("user_status","");
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
        sharedPreferences.edit().putString("user_status",user_status).apply();
    }

    public String getDisplay_name() {
        display_name = sharedPreferences.getString("display_name","");
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
        sharedPreferences.edit().putString("display_name",display_name).apply(); 
    }

    public String getSpam() {
        spam = sharedPreferences.getString("spam","");
        return spam;
    }

    public void setSpam(String spam) {
        this.spam = spam;
        sharedPreferences.edit().putString("spam",spam).apply();

    }

    public String getDeleted() {
        deleted = sharedPreferences.getString("deleted","");

        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
        sharedPreferences.edit().putString("deleted",deleted).apply();

    }

    public boolean isSubscriber() {
        try {
            return sharedPreferences.getString("subscriber", "").equals("true");
        }catch (Exception e){
            return false;
        }
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
        if(subscriber) {
            sharedPreferences.edit().putString("deleted", "true").apply();
        }else{
            sharedPreferences.edit().putString("deleted", "false").apply();
        }
    }

    public String getCap_key() {
        cap_key = sharedPreferences.getString("cap_key","");
        return cap_key;
    }

    public void setCap_key(String cap_key) {
        this.cap_key = cap_key;
        sharedPreferences.edit().putString("cap_key",cap_key).apply();
    }

    public String getRoles() {
        roles = sharedPreferences.getString("roles","");
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
        sharedPreferences.edit().putString("roles",roles).apply();

    }

    public boolean isRead() {
        try {
            return sharedPreferences.getString("read ", "").equals("true");
        }catch (Exception e){
           return false; 
        }
    }

    public void setRead(boolean read) {
        this.read = read;
        if(read) {
            sharedPreferences.edit().putString("read", "true").apply();
        }else{
            sharedPreferences.edit().putString("read", "false").apply();
        }
    }
    public boolean isLevel_0() {
        try{
            return sharedPreferences.getString("level_0 ", "").equals("true");
        }catch (Exception e){
            return false;
        }
    }

    public void setLevel_0(boolean level_0) {
        this.level_0 = level_0;
        if(level_0) {
            sharedPreferences.edit().putString("level_0", "true").apply();
        }else{
            sharedPreferences.edit().putString("level_0", "false").apply();
        }
    }

    public boolean isWoffice_read_wikies() {
        try {
            return sharedPreferences.getString("woffice_read_wikies", "").equals("true");
        }catch (Exception e){
            return false;
        }
    }

    public void setWoffice_read_wikies(boolean woffice_read_wikies) {
        this.woffice_read_wikies = woffice_read_wikies;
        if(woffice_read_wikies) {
            sharedPreferences.edit().putString("woffice_read_wikies", "true").apply();
        }else{
            sharedPreferences.edit().putString("woffice_read_wikies", "false").apply();
        }
    }

    public String getFilter() {
        filter = sharedPreferences.getString("filter","null");
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
        sharedPreferences.edit().putString("filter",filter).apply();

    }
    public Constants(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    }
}
