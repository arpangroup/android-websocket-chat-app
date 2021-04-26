package com.arpangroup.myapplication;


import android.view.View;

import androidx.annotation.NonNull;

import com.arpangroup.myapplication.adapter.BaseAdapter;
import com.arpangroup.myapplication.databinding.ItemMessage1Binding;
import com.arpangroup.myapplication.models.Message;

import java.util.List;

public class MessagingAdapter extends BaseAdapter<Message, ItemMessage1Binding> {

    public MessagingAdapter(List<Message> items) {
        super(items);
    }

    @Override
    public int getLayout() {
        return R.layout.item_message1;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ItemMessage1Binding> holder, int position) {
        Message currentMessage = items.get(position);
        switch (currentMessage.getId()){
            case Constants.SEND_ID:
                holder.binding.tvMessage.setText(currentMessage.getMessage());
                holder.binding.tvMessage.setVisibility(View.VISIBLE);
                holder.binding.tvBotMessage.setVisibility(View.GONE);
                break;
            case Constants.RECEIVE_ID:
                holder.binding.tvBotMessage.setText(currentMessage.getMessage());
                holder.binding.tvBotMessage.setVisibility(View.VISIBLE);
                holder.binding.tvMessage.setVisibility(View.GONE);
                break;

        }
    }


    public void insertMessage(Message message){
        this.items.add(message);
        notifyItemInserted(items.size());
    }


}
