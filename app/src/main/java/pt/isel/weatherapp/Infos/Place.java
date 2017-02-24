package pt.isel.weatherapp.Infos;

/**
 * Used to store Location specific information
 */
public class Place {
    private double latitude;
    private double longitude;
    private String name;

    public Place(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return latitude+","+longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
