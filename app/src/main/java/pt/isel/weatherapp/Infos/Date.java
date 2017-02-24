package pt.isel.weatherapp.Infos;

/**
 * Used to store Date specific information
 */
public class Date {
    private String year,month,day,hour,minute,second;
    private static final int DATE_SIZE = 10;

    public Date(String[] date,String[] time) {
        this.year = date[0];
        this.month = date[1];
        this.day = date[2];
        this.hour = time[0];
        this.minute = time[1];
        this.second = time[2];
    }

    public Date(String[] date, String hour, String minute, String second) {
        this.year = date[0];
        this.month = date[1];
        this.day = date[2];
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Date(String year, String month, String day, String[] time) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = time[0];
        this.minute = time[1];
        this.second = time[2];
    }

    public Date(String year, String month, String day, String hour, String minute, String second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getSecond() {
        return second;
    }

    public static int getDateSize() {
        return DATE_SIZE;
    }

    @Override
    public String toString() {
        return year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+second;
    }
}
