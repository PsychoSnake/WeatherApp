package pt.isel.weatherapp.Control;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import pt.isel.weatherapp.Infos.Date;
import pt.isel.weatherapp.Infos.Place;
import pt.isel.weatherapp.Manager;
import pt.isel.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    private TextView temp, wind, cloud, humidity;
    private Button return_app;
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
        return_app = (Button) findViewById(R.id.return_app);
        Intent intent = getIntent();
        place_name = intent.getStringExtra("place_name");
        dates = intent.getStringExtra("date_text").split("-");
        times = intent.getStringExtra("time_text").split(":");
        getWeatherInfo();
        /*weather.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    if (place_name.getText().length() == 0) {
                        toast("No Country/City selected.");
                        return;
                    }
                    String bool = date.getText().length()!=Date.getDateSize()?"1":"0";
                    bool += time.getText().length()!=8?"1":"0";
                    Date dt = getdateInfos(bool);

                    Place loc = new Place(list.get(0).getCountryName(), list.get(0).getLatitude(), list.get(0).getLongitude());
                    String response = manager.download(loc,dt);
                    JsonObject obj = manager.translateToJSON(response);
                    extractInfo(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SecurityException e){
                    e.getStackTrace();
                }
            }
        });*/

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

    public void endAPP(View view){
        toast("Select a new Date/Time/Place");
        finish();
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

/*    *//**
     * Gets the information about the date and time to get the weather
     * @param bool specifies what the user had as input
     * @return the specific date
     *//*
    private Date getdateInfos(String bool) {
        Date dt = null;
        String year,month,day,hour,minute,second;
        Calendar calendar = Calendar.getInstance();
        Locale lc = new Locale(Locale.getDefault().getDisplayLanguage());
        year = calendar.get(Calendar.YEAR)+"";
        month = String.format(lc,"%02d",calendar.get(Calendar.MONTH)+1);
        day = String.format(lc,"%02d",calendar.get(Calendar.DAY_OF_MONTH));
        hour = String.format(lc,"%02d",calendar.get(Calendar.HOUR_OF_DAY));
        minute = String.format(lc,"%02d",calendar.get(Calendar.MINUTE));
        second = String.format(lc,"%02d",calendar.get(Calendar.SECOND));
        switch(bool) {
            case "00":
                dt = new Date(date.getText().toString().split("-"),time.getText().toString().split(":"));
                break;
            case "01":
                toast("No Time selected, using current Time");
                dt = new Date(date.getText().toString().split("-"),hour,minute,second);
                break;
            case "10":
                toast("No Date selected, using current Day");
                dt = new Date(year,month,day,time.getText().toString().split(":"));
                break;
            case "11":
                toast("Nothing has been selected, using current Time/Day");
                dt = new Date(year,month,day,hour,minute,second);
                break;
        }
        return dt;
    }*/

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
