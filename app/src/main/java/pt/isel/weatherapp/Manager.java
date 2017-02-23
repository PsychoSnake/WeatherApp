package pt.isel.weatherapp;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import pt.isel.weatherapp.Infos.Date;
import pt.isel.weatherapp.Infos.Location;

public class Manager {
    private static final String DARKSKY = "https://api.forecast.io/forecast";
    private static final String UNIQUE_CODE = "9e3285a1a1cd1b36f2432cdcf4f11e82";
    private static Gson gson = new Gson();

    public String download(Location loc, Date date) throws IOException {
        String ulr = DARKSKY+"/"+UNIQUE_CODE+"/"+loc.toString()+","+date.toString()+"?units=auto";
        NetworkManager manager = new NetworkManager();
        String url = null;
        try {
            url = manager.execute(ulr).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return url;
    }

    public JsonObject translateToJSON(String response) {
        return gson.fromJson(response,JsonObject.class);
    }
}
