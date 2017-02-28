package pt.isel.weatherapp.Menu;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import pt.isel.weatherapp.R;
import pt.isel.weatherapp.Weather.MainActivity;

public class Menu extends AppCompatActivity {
    private EditText place_name;
    private TextView date_text,time_text;
    private Button get_weather,date_button,time_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        place_name = (EditText) findViewById(R.id.country_name);
        date_button = (Button) findViewById(R.id.date_button);
        date_text = (TextView) findViewById(R.id.date_text);
        time_button = (Button) findViewById(R.id.time_button);
        time_text = (TextView) findViewById(R.id.time_text);
        get_weather = (Button) findViewById(R.id.weather);

        get_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cont = true;
                Geocoder geo = new Geocoder(Menu.this);
                try {
                    if(date_text.length()==0){
                        toast("No Date was Selected");
                        cont = false;
                    }
                    if(time_text.length()==0){
                        toast("No Time was Selected");
                        cont = false;
                    }
                    if(geo.getFromLocationName(place_name.getText().toString(),1).size()==0){
                        toast("No Country/City was Selected or the one selected doesn't exist");
                        cont = false;
                    }
                    if(cont){
                        Intent intent = new Intent(Menu.this, MainActivity.class);
                        intent.putExtra("date_text",date_text.getText().toString());
                        intent.putExtra("time_text",time_text.getText().toString());
                        intent.putExtra("place_name", place_name.getText().toString());
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setDate(String[] dates){
        date_text.setText(dates[0]+"-"+dates[1]+"-"+dates[2]);
    }

    public void setTime(String[] times){
        time_text.setText(times[0]+":"+times[1]+":00");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment fragment = new TimePickerFragment();
        fragment.show(this.getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View view){
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(this.getFragmentManager(), "datePicker");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        date_text.setText(savedInstanceState.getString("date_text"));
        time_text.setText(savedInstanceState.getString("time_text"));
        place_name.setText(savedInstanceState.getString("place_name"));
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString("date_text",date_text.getText().toString());
        saveInstanceState.putString("time_text",time_text.getText().toString());
        saveInstanceState.putString("place_name", place_name.getText().toString());
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
