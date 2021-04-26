package com.arpangroup.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingSession {
    private final String TAG = "SettingSession";
    private Context mContext;
    public final String SETTING_SESSION = "setting_session";
    public final String KEY_USER_NAME = "username";
    public final String KEY_SEND_TO = "send_to";

    public SettingSession(Context context){
        mContext = context.getApplicationContext();
    }


    private SharedPreferences getUserPreference(){
        return mContext.getSharedPreferences(SETTING_SESSION,0);
    }

    public boolean setStr(String key,  String value){
        SharedPreferences.Editor editor = getUserPreference().edit();
        editor.putString(key, value);
        editor.apply();//editor.commit();
        return true;
    }
    public String getStr(String key){
        return getUserPreference().getString(key, null);
    }

    public void setUserName(String userName){
        setStr(KEY_USER_NAME, userName);
    }

    public String getUserName(){
        return getStr(KEY_USER_NAME);
    }

    public void setSendTo(String sendTo){
        setStr(KEY_SEND_TO, sendTo);
    }

    public String getSendTo(){
        return getStr(KEY_SEND_TO);
    }

}
