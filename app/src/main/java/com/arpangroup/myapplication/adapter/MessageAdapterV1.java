package com.arpangroup.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arpangroup.myapplication.databinding.ItemSenderBinding;
import com.arpangroup.myapplication.databinding.MyMessageBinding;
import com.arpangroup.myapplication.databinding.TheirMessageBinding;
import com.arpangroup.myapplication.databinding.UserConnectedBinding;
import com.arpangroup.myapplication.models.ChatMessage;
import com.arpangroup.myapplication.models.Message;


import java.util.List;

public class MessageAdapterV1 extends RecyclerView .Adapter{
    private final String TAG = "MessageAdapterV1";
    private List<ChatMessage> items;
    private String userName;

    private final int VIEW_TYPE_USER_CONNECTED= 0;
    private final int VIEW_TYPE_MY_MESSAGE = 1;
    private final int VIEW_TYPE_THERE_MESSAGE = 2;

    public MessageAdapterV1(List<ChatMessage> items, String userName) {
        this.items = items;
        this.userName = userName;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = items.get(position);
        if(chatMessage == null) return super.getItemViewType(position);
        if(chatMessage.getType() == ChatMessage.MessageType.JOIN || chatMessage.getType() == ChatMessage.MessageType.LEAVE){
            return VIEW_TYPE_USER_CONNECTED;
        }else{
            if(chatMessage.getSender().equalsIgnoreCase(userName)){
                return VIEW_TYPE_MY_MESSAGE;
            }else{
                return VIEW_TYPE_THERE_MESSAGE;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case VIEW_TYPE_USER_CONNECTED:
                return new UserConnectedViewHolder(UserConnectedBinding.inflate(layoutInflater, parent, false));
            case VIEW_TYPE_MY_MESSAGE:
                return new MyMessageViewHolder(ItemSenderBinding.inflate(layoutInflater, parent, false));
            case VIEW_TYPE_THERE_MESSAGE:
                return new ThereMessageViewHolder(TheirMessageBinding.inflate(layoutInflater, parent, false));
            default:
                return new ThereMessageViewHolder(TheirMessageBinding.inflate(layoutInflater, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = items.get(position);
        if(holder instanceof UserConnectedViewHolder){
            ((UserConnectedViewHolder)holder).binding.setChatMessage(chatMessage);
        }else if(holder instanceof MyMessageViewHolder){
            ((MyMessageViewHolder)holder).binding.setChatMessage(chatMessage);
        }else if(holder instanceof ThereMessageViewHolder){
            ((ThereMessageViewHolder)holder).binding.setChatMessage(chatMessage);
        }else{
            ((UserConnectedViewHolder)holder).binding.setChatMessage(chatMessage);
        }
    }

    public static class UserConnectedViewHolder extends RecyclerView.ViewHolder{
        public UserConnectedBinding binding;

        public UserConnectedViewHolder(UserConnectedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public static class MyMessageViewHolder extends RecyclerView.ViewHolder{
        public ItemSenderBinding binding;

        public MyMessageViewHolder(ItemSenderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public static class ThereMessageViewHolder extends RecyclerView.ViewHolder{
        public TheirMessageBinding binding;

        public ThereMessageViewHolder(TheirMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void insertMessage(ChatMessage message){
        this.items.add(message);
        notifyDataSetChanged();
    }
}
