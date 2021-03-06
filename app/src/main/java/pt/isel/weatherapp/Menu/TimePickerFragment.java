package pt.isel.weatherapp.Menu;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

import pt.isel.weatherapp.Menu.Menu;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Locale lc = new Locale(Locale.getDefault().getDisplayLanguage());
        String hour = String.format(lc,"%02d",hourOfDay);
        String min = String.format(lc,"%02d",minute);
        Menu activity = (Menu) getActivity();
        activity.setTime(new String[]{hour,min});
    }

}
