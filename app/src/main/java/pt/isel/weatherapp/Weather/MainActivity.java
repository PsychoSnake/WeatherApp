package pt.isel.weatherapp.Weather;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;

import pt.isel.weatherapp.Infos.Date;
import pt.isel.weatherapp.Infos.Place;
import pt.isel.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    private TextView temp, wind, cloud, humidity;
    private Manager manager = new Manager();
    private String place_name;
    private String[] dates,times;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = (TextView) findViewById(R.id.temperature);
        wind = (TextView) findViewById(R.id.wind);
        cloud = (TextView) findViewById(R.id.cloud);
        humidity = (TextView) findViewById(R.id.humidity);
        Intent intent = getIntent();
        place_name = intent.getStringExtra("place_name");
        dates = intent.getStringExtra("date_text").split("-");
        times = intent.getStringExtra("time_text").split(":");
        getWeatherInfo();
    }

    private void getWeatherInfo() {
        try {
            Geocoder geo = new Geocoder(MainActivity.this);
            Address addr = geo.getFromLocationName(place_name,1).get(0);
            Place pl = new Place(place_name,addr.getLatitude(),addr.getLongitude());
            Date dt = new Date(dates,times);
            String response = manager.download(pl,dt);
            JsonObject json = manager.translateToJSON(response);
            extractInfo(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toast("Please choose another Time/Date/Place");
    }

    /**
     * Extracts the information from json and puts it into the EditText
     * @param obj object that has all the info about the weather
     */
    private void extractInfo(JsonObject obj) {
        String temperature = obj.get("currently").getAsJsonObject().get("temperature").getAsString()+"ºC";
        String cloudCover = obj.get("currently").getAsJsonObject().get("cloudCover").getAsFloat()*100+"%";
        String windSpeed = obj.get("currently").getAsJsonObject().get("windSpeed").getAsString()+"Km/h";
        String humidPer = obj.get("currently").getAsJsonObject().get("humidity").getAsFloat()*100+"%";
        temp.setText(temperature);
        wind.setText(windSpeed);
        cloud.setText(cloudCover);
        humidity.setText(humidPer);
    }

    /**
     * Shows a message for the user
     * @param s the specific message to show to the user
     */
    private void toast(String s) {
        Context ctx = getApplicationContext();
        Toast toast = Toast.makeText(ctx,s,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
        toast.show();
    }

}
