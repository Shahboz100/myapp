package com.example.dell.myapp.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.dell.myapp.R;

import java.util.concurrent.TimeUnit;

public class startActivity extends Activity {
    public static final String APP_PREFERENCES = "user_settings";
    public static final String APP_PREFERENCES_NAME = "name";
    public static final String APP_PREFERENCES_SURNAME = "surname";
    public static final String APP_PREFERENCES_TEL = "tel";
    public static final String APP_PREFERENCES_PASSWORD = "password";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        start start = new start();
        start.execute();
    }
    class start extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent;
            if (mSettings.contains(APP_PREFERENCES_NAME)) {
                // Получаем Имя и Фамилию из настроек
                // Выводим на экран данные из настроек
                intent = new Intent(startActivity.this, driver.class);
                intent.putExtra("name",mSettings.getString(APP_PREFERENCES_NAME,""));
                intent.putExtra("tel",mSettings.getString(APP_PREFERENCES_TEL,""));
                intent.putExtra("surname",mSettings.getString(APP_PREFERENCES_SURNAME,""));
                intent.putExtra("password",mSettings.getString(APP_PREFERENCES_PASSWORD,""));
                startActivity(intent);
            }
            else{
                intent = new Intent(startActivity.this, MainActivity.class);
                startActivity(intent);
            }
            return null;
        }
    }
}
