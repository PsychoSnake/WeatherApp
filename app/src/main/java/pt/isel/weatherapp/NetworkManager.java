package pt.isel.weatherapp;


import android.os.AsyncTask;

import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkManager extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return download(connection);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    private String download(HttpURLConnection connection) throws IOException {
        connection.connect();
        String ret = "";
        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        byte[] buffer = new byte[1024];
        int count;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            ret += new String(buffer, 0, count);
        }
        bis.close();
        connection.disconnect();
        return ret;
    }
}
