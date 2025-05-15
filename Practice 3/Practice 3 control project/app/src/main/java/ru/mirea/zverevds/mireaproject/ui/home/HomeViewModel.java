package ru.mirea.zverevds.mireaproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> greetingText;
    private final MutableLiveData<String> ownerText;
    final String message;

    public HomeViewModel() {
        greetingText = new MutableLiveData<>();
        ownerText = new MutableLiveData<>();
        message = "Добро пожаловать на домашнюю страницу приложения!";
        ownerText.setValue("Выполнил:\n" +
                "студент группы БСБО-07-22\n" +
                "Денис Зверев (№6)");
    }

    public LiveData<String> getGreetingText(String additionalText) {
        if (additionalText.isEmpty()) {
            greetingText.setValue(message);
        } else {
            greetingText.setValue("Привет, " + additionalText + "!\n" + message);
        }
        return greetingText;
    }
    public LiveData<String> getOwnerText() { return ownerText; }
}