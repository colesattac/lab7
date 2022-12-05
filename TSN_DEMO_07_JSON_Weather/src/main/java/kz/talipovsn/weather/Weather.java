package kz.talipovsn.weather;

import android.graphics.Bitmap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Класс погоды
public class Weather {
    private String name; // Навзание репозитория
    private String login; // Атвор репозитория
    private String licName; // Имя лицензии
    private String iconName; // Код иконки
    private Bitmap iconData; // Иконка
    private String language; //Язык программирования

    public Weather(){
    }

    public void setName(String name) { this.name = name; }
    public void setLogin(String login) { this.login = login; }
    public void setLicense(String licName) { this.licName = licName; }
    public void setLang(String language) { this.language = language; }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
    public void setIconData(Bitmap iconData) {
        this.iconData = iconData;
    }

    public String getName() {
        return name;
    }
    public String getLogin() { return login; }

    public String getLang() { return language; }
    public String getLicense() { return licName; }
    public String getIconName() {
        return iconName;
    }
    public Bitmap getIconData() {
        return iconData;
    }
}
