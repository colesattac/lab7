package kz.talipovsn.weather;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView; // Иконка погоды
    private TextView textView; // Компонент для данных погоды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        JSONWeatherTask task = new JSONWeatherTask(); // Создание потока определения погоды
        task.execute(getString(R.string.username)); // Активация потока
    }

    // Кнопка "Обновить"
    public void onClick(View view) {
        new JSONWeatherTask().execute(getString(R.string.username)); // Создание и активация потока определения погоды
    }

    // Класс отдельного асинхронного потока
    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        // Тут реализуем фоновую асинхронную загрузку данных, требующих много времени
        @Override
        protected Weather doInBackground(String... params) {
            return WeatherBuilder.buildWeather(params[0]);
        }
        // ----------------------------------------------------------------------------

        // Тут реализуем что нужно сделать после окончания загрузки данных
        @Override
        protected void onPostExecute(final Weather weather) {
            super.onPostExecute(weather);

            // Устанавливаем картинку погоды
            imageView.post(new Runnable() { //  Используем синхронизацию с UI
                @Override
                public void run() {
                    // Если есть считанная иконка с web
                    if (weather.getIconData() != null) {
                        imageView.setImageBitmap(weather.getIconData()); // Установка иконки
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher); // Установка своей иконки при ошибке
                    }
                    imageView.invalidate(); // Принудительная прорисовка картинки на всякий случай
                }
            });

            // Выдаем данные о погоде в компонент
            textView.post(new Runnable() { //  с использованием синхронизации UI
                @Override
                public void run() {
                    textView.setText("");
                    if (weather.getName() != null) {
                        textView.append(weather.getLogin() + "/" + weather.getName() + "\n");
                        textView.append("Лицензия: " + weather.getLicense() + "\n");
                        textView.append("Язык: " + weather.getLang() + "\n");
                    } else {
                        textView.append("Нет данных!" + "\n");
                        textView.append("Проверьте доступность Интернета");
                    }
                }
            });

        }
        // ------------------------------------------------------------------------------------

    }

}
