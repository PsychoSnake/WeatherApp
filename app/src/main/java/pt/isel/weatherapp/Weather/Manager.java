package pt.isel.weatherapp.Weather;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import pt.isel.weatherapp.Infos.Date;
import pt.isel.weatherapp.Infos.Place;

public class Manager {
    private static final String DARKSKY = "https://api.forecast.io/forecast";
    private static final String UNIQUE_CODE = "9e3285a1a1cd1b36f2432cdcf4f11e82";
    private static Gson gson = new Gson();

    /**
     * Gets a reponse from the url
     * @param loc location specified by the user
     * @param date date specified by the user
     * @return returns the response in String format(json) from the site
     * @throws IOException
     */
    public String download(Place loc, Date date) throws IOException {
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

    /**
     * Translates the String to the correspondent JSON
     * @param response the String to be transformed
     * @return returns the JSON after being translated
     */
    public JsonObject translateToJSON(String response) {
        return gson.fromJson(response,JsonObject.class);
    }
}
