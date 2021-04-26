package com.arpangroup.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arpangroup.myapplication.models.ChatMessage;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.WebSocket;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    SettingSession settingSession;
    String mUserName;
    BaseApp baseApp;
    private CompositeDisposable compositeDisposable;
    StompClient mStompClient;
    EditText etMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingSession = new SettingSession(this);
        getSupportActionBar().setTitle("Chat with " + settingSession.getSendTo());
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);
        mUserName = settingSession.getUserName();
        baseApp = BaseApp.instance();

        //this.subscribe();
        btnSend.setOnClickListener(view -> {
            sendMessage();
        });

        setupSocket();


    }

    private void setupSocket(){
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.101:8080/pureeats/websocket");
//        client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.101:8080/pureeats");
        mStompClient.connect();

        ChatMessage chatMessage = new ChatMessage()
                .setType(ChatMessage.MessageType.JOIN)
                .setSender(settingSession.getUserName())
                .setContent("hello");
        Log.d("______BASE_APP_SEND______: ", chatMessage.json());

        mStompClient.topic("/topic/public").subscribe(message -> {
            Log.d(TAG, "Received public message: " + message.getPayload());
        });

//        client.topic("/topic/messages/"+mUserName).subscribe(message -> {
//            Log.d(TAG, "Received private message: " + message.getPayload());
//        });


//        mStompClient.send("/app/chat.register", chatMessage.json()).subscribe(
//                () -> Log.d(TAG, "Sent data!"),
//                error -> Log.e(TAG, "Encountered error while sending data!", error)
//        );

        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {

                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;

                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    break;

                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });


        mStompClient.send("/app/chat.register", chatMessage.json()).subscribe();
    }



    private void subscribe() {
//        Log.d(TAG, "Inside subscribe............");
//        BaseApp.instance().getSubscriptions().addSubscription("/topic/public/" , topicMessage -> {
//            System.out.println("#########################addSubscription to public: ");
//
//        });
//
//        BaseApp.instance().getSubscriptions().addSubscription("/topic/messages/" + mUserName, topicMessage -> {
//            System.out.println("#########################addSubscription to message: " );
//        });
    }

    private void sendMessage() {
        Log.d(TAG, "Inside sendMessage....");
        ChatMessage chatMessage = new ChatMessage()
                .setSender(settingSession.getUserName())
                .setContent( etMessage.getText().toString())
                .setType(ChatMessage.MessageType.CHAT);
        Log.d(TAG, "SENDING_DATA: " + chatMessage.json());

        //BaseApp.instance().getStompClient().send("/app/chat/" + settingSession.getSendTo(), chatMessage.json()).subscribe();
//        mStompClient.send("/app/chat/"+settingSession.getSendTo(), chatMessage.json()).subscribe(
//                () -> Log.d(TAG, "Sent data!"),
//                error -> Log.e(TAG, "Encountered error while sending data!", error)
//        );
        mStompClient.send("/app/chat/"+settingSession.getSendTo(), chatMessage.json()).subscribe();
        //etMessage.setText("");
    }


    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }
}