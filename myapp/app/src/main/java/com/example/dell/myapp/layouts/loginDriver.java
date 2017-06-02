package com.example.dell.myapp.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.myapp.R;
import com.example.dell.myapp.internet.http_request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class loginDriver extends AppCompatActivity {
    private String token;
    private String name;
    private boolean flag = false;
    private String surname;
    private String tel;
    private String password;

    Auth mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);
    }

    public void onClickLogin(View view) throws IOException {
        mt = new Auth();
        mt.execute("http://10.0.3.2:5000/");
    }

    public void clickRegister(View view) {
        Intent intent = new Intent(loginDriver.this, register.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mt != null) {
            mt.cancel(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class Auth extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Отключение видимости кнопки входа
            Button btn_login = (Button) findViewById(R.id.btn_login);
            btn_login.setVisibility(View.GONE);

            // Включение видимости процесс бара (Proccess bar)
            ProgressBar bar = (ProgressBar) findViewById(R.id.procbar_login);
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... url) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            http_request auth = new http_request();
            EditText phone = (EditText) findViewById(R.id.phone);
            EditText ed_password = (EditText) findViewById(R.id.password);

            try {
                //Получение токена (Авторизация)
                tel = phone.getText().toString();
                password = ed_password.getText().toString();
                String str_token = auth.http_auth(tel, password);
                JSONObject json_token = new JSONObject(str_token);
                //
                //Если JSON не содержит "error" то получим токен
                // C помошью полученного токена получим информацию о водителе
                //
                if (!json_token.has("error")) {
                    //
                    // Установим флагу true, чтобы получить доступ к аккаунту
                    //
                    flag = true;
                    // Получение токена из JSON
                    token = json_token.getString("access_token");
                    String url_auth = "http://10.0.3.2:5000";
                    String str_json = auth.get_info_driver(token, url_auth);
                    JSONObject json = new JSONObject(str_json);

                    name = json.getString("name");
                    surname = json.getString("surname");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null; //Метод ничего не возвращает!
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Включаем видимость кнопки входа
            Button btn_login = (Button) findViewById(R.id.btn_login);
            btn_login.setVisibility(View.VISIBLE);

            // Отключение видимости процесс бара (Proccess bar)
            ProgressBar bar = (ProgressBar) findViewById(R.id.procbar_login);
            bar.setVisibility(View.GONE);

            TextView f_login = (TextView) findViewById(R.id.f_login);
            if (!flag) {
                f_login.setVisibility(View.VISIBLE);
            } else {
                //Если ввели правильно, то убераем предупреждение
                f_login.setVisibility(View.GONE);
                //Вызов активити водителя
                Intent intent = new Intent(loginDriver.this, driver.class);
                intent.putExtra("tel", tel);
                intent.putExtra("password", password);
                intent.putExtra("name", name);
                intent.putExtra("surname", surname);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        }
    }
}
