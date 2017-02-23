package pt.isel.weatherapp.Infos;


public class Date {
    private String year,month,day,hour,minute,second;

    public Date(String[] date,String[] time) {
        this.year = date[0];
        this.month = date[1];
        this.day = date[2];
        this.hour = time[0];
        this.minute = time[1];
        this.second = time[2];
    }

    @Override
    public String toString() {
        return year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+second;
    }
}
