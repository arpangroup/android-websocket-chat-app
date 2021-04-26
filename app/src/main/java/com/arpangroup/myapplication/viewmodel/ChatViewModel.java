package com.arpangroup.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class ChatViewModel extends ViewModel {
    private static final String TAG = "BaseViewModel";
    protected CompositeDisposable compositeDisposable;





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
