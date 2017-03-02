package pt.isel.weatherapp.Menu;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;



public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
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
