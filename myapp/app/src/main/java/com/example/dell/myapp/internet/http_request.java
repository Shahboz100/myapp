package com.example.dell.myapp.internet;


import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dell on 27.04.2017.
 */

public class http_request {

    public String get_info_driver(String token,String url) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // По умолчанию GET
        con.setRequestMethod("GET");

        //Заголовок GET запроса
        con.setRequestProperty("Authorization", "JWT "+token);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    /*
            Авторизация  ..  Метод выдает пользователю TOKEN
    */
    public String http_auth(String username, String password) throws IOException, JSONException {
        String url = "http://10.0.3.2:5000/auth";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Создание Json данных:  {""username":"username","password":"password"}
        JSONObject urlParameters= new JSONObject();

        urlParameters.put("username",username);
        urlParameters.put("password",password);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(String.valueOf(urlParameters));
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}
