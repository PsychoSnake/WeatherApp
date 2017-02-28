package pt.isel.weatherapp.Menu;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;

import pt.isel.weatherapp.Menu.Menu;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Locale lc = new Locale(Locale.getDefault().getDisplayLanguage());
        String y = String.valueOf(year);
        String m = String.format(lc,"%02d",month+1);
        String d = String.format(lc,"%02d",day);
        Menu activity = (Menu) getActivity();
        activity.setDate(new String[]{y,m,d});
    }

}
