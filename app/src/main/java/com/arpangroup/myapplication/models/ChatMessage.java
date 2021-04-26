package com.arpangroup.myapplication.models;

import androidx.core.util.TimeUtils;

import com.arpangroup.myapplication.utils.DateTimeUtil;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ChatMessage {
    private String content;
    private String sender;
    private Long timestamp;
    private MessageType type;

    public static enum MessageType {
        CHAT, LEAVE, JOIN
    }

    public String getContent() {
        return content;
    }

    public ChatMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public ChatMessage setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public MessageType getType() {
        return type;
    }

    public ChatMessage setType(MessageType type) {
        this.type = type;
        return this;
    }

    public String json() {
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", type=" + type +
                '}';
    }


    public String getTime(){
        return DateTimeUtil.getTimeInhh_mm_aFormat(timestamp);
    }
}
