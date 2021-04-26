package com.arpangroup.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import ua.naiksoftware.stomp.StompClient;

public class ChatViewModel extends ViewModel {
    private static final String TAG = "BaseViewModel";
    private CompositeDisposable compositeDisposable;
    private StompClient stompClient;





    public void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
