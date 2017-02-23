package pt.isel.weatherapp;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;

import pt.isel.weatherapp.Infos.Date;
import pt.isel.weatherapp.Infos.Location;

public class MainActivity extends AppCompatActivity {
    private TextView temp,wind,cloud,humidity;
    private EditText country_name;
    private Button weather;
    private final double LATITUDE = 37.019355;
    private final double LONGITUDE = -7.93044;
    private View view;
    private Manager manager = new Manager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = new View(this);
        temp = (TextView) findViewById(R.id.temperature);
        wind = (TextView) findViewById(R.id.wind);
        cloud = (TextView) findViewById(R.id.cloud);
        humidity = (TextView) findViewById(R.id.humidity);
        weather = (Button) findViewById(R.id.get_weather);
        country_name = (EditText) findViewById(R.id.country_name);
        weather.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    Geocoder geo = new Geocoder(MainActivity.this);
                    List<Address> list = geo.getFromLocationName(country_name.getText().toString(),1);
                    Location loc = new Location(list.get(0).getCountryName(),list.get(0).getLatitude(),list.get(0).getLongitude());
                    Date date = new Date("2016","02","05","12","13","01");
                    String response = manager.download(loc,date);
                    JsonObject obj = manager.translateToJSON(response);
                    String temperature = obj.get("currently").getAsJsonObject().get("temperature").getAsString();
                    String cloudCover = obj.get("currently").getAsJsonObject().get("cloudCover").getAsFloat()*100+"%";
                    String windSpeed = obj.get("currently").getAsJsonObject().get("windSpeed").getAsString();
                    String humidPer = obj.get("currently").getAsJsonObject().get("humidity").getAsFloat()*100+"%";
                    temp.setText(temperature);
                    wind.setText(windSpeed);
                    cloud.setText(cloudCover);
                    humidity.setText(humidPer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
