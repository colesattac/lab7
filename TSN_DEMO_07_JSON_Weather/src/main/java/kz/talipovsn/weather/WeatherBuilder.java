package kz.talipovsn.weather;

import android.graphics.Bitmap;
import org.json.JSONArray;
import org.json.JSONObject;
import static kz.talipovsn.weather.HttpClient.getHTMLData;
import static kz.talipovsn.weather.HttpClient.getHTMLImage;

// СОЗДАТЕЛЬ ПОГОДЫ
public class WeatherBuilder {

    // Получение JSON html-данных погоды по городу и языку
    private static String getWeatherData(String username) {
        String BASE_URL = "https://api.github.com/users/";
        String url = BASE_URL + username + "/repos";
        System.out.println(url);
        return getHTMLData(url);
    }

    // Получение с web bitmap картинки погоды по ее коду
    private static Bitmap getWeatherImage(String code) {
        String IMG_URL = "https://avatars.githubusercontent.com/u/";
        String url = IMG_URL + code;
        System.out.println(url);
        return getHTMLImage(url);


    }

    // Парсинг даты в формате JSON с созданием объекта погоды
    private static Weather dataParsing(String json) {
        Weather weather = new Weather();
        try {

            JSONArray _git      =   new JSONArray(json);
            JSONObject _repo    =   _git.getJSONObject(0);
            JSONObject _owner   =   _repo.getJSONObject("owner");
            JSONObject _license =   _repo.getJSONObject("license");

            weather.setLogin(_owner.getString("login"));
            weather.setIconName(_owner.getString("id"));
            weather.setName(_repo.getString("name"));
            weather.setLang(_repo.getString("language"));
            weather.setLicense(_license.getString("name"));
            if (weather.getLang() == "null") {weather.setLang("Не указан");}

        } catch (Exception ignore) {
        }
        return weather;
    }

    // Получение готового объекта погоды по городу и языку
    public static Weather buildWeather (String username) {
        Weather weather = dataParsing(getWeatherData(username));
        weather.setIconData(getWeatherImage(weather.getIconName()));
        return weather;
    }
}