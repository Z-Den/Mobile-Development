package ru.mirea.zverevds.mireaproject.ui.webView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WebViewViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WebViewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}