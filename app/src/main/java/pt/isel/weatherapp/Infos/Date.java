package pt.isel.weatherapp.Infos;


public class Date {
    private String year,month,day,hour,minute,second;

    public Date(String year, String month, String day, String hour, String minute, String second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public String toString() {
        return year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+second;
    }
}
