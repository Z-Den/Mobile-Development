package ru.mirea.zverevds.mireaproject.ui.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<String> titleText;
    private final MutableLiveData<String> desc1Text;
    private final MutableLiveData<String> desc2Text;

    public DataViewModel() {
        titleText = new MutableLiveData<>();
        desc1Text = new MutableLiveData<>();
        desc2Text = new MutableLiveData<>();
        titleText.setValue("Из чего состоит веб-разработка?");
        desc1Text.setValue("Фронтенд (англ. frontend) — это разработка пользовательских функций и интерфейса. " +
                "К ним относится всё, что пользователи видят на сайте или в приложении, и с чем можно взаимодействовать: " +
                "картинки, выпадающие списки, меню, анимация, карточки товаров, кнопки, чекбоксы, интерактивные элементы.");
        desc2Text.setValue("Бэкенд (англ. backend) — это логика работы сайта, скрытая от пользователя. " +
                "Именно там происходит то, что можно назвать работой сайта. Код бэкенда увидеть невозможно — " +
                "он не отправляется пользователю напрямую в смартфон или браузер, а работает на сервере, " +
                "на котором хранится приложение или сайт.");
    }

    public LiveData<String> getTitleText() {
        return titleText;
    }
    public LiveData<String> getDesc1Text() { return desc1Text; }
    public LiveData<String> getDesc2Text() { return desc2Text; }
}