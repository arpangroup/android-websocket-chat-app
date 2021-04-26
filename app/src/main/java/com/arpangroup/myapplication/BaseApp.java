package com.arpangroup.myapplication;

import android.app.Application;
import android.util.Log;

import com.arpangroup.myapplication.models.ChatMessage;
import com.arpangroup.myapplication.utils.Subscriptions;
import com.google.gson.Gson;

public class BaseApp extends Application {
    private static BaseApp application;
//    private StompClient stompClient;
    private Subscriptions subscriptions;
    SettingSession settingSession;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        settingSession = new SettingSession(this);
        this.initDatabase();
        //this.loadUser();
        //this.initWebsocket();
    }

    private void initDatabase() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "answers-db");
//        Database db = helper.getWritableDb();
//        daoSession = new DaoMaster(db).newSession();
    }
    public void initWebsocket() {
//        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.101:8080/pureeats/websocket");
//        stompClient.connect();
//
//        ChatMessage chatMessage = new ChatMessage()
//                .setType(ChatMessage.MessageType.JOIN)
//                .setSender(settingSession.getUserName())
//                .setContent("hello");
//        Log.d("______BASE_APP_SEND______: ", chatMessage.json());
//
//        stompClient.send("/app/chat.register", chatMessage.json());
//        subscriptions = new Subscriptions();
    }


    public static BaseApp instance() {
        return application;
    }

//    public StompClient getStompClient() {
//        return stompClient;
//    }

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

}
