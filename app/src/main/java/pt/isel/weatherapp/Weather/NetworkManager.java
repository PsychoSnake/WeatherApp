package pt.isel.weatherapp.Weather;


import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkManager extends AsyncTask<String, Void, String> {

    /**
     * Connects to the site
     * @param urls the urls to connect
     * @return the responses from the sites
     */
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return download(connection);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    private static final int MAXCHARS = 100;
    /**
     * Using the connection with the site gets its information using a InputStream
     * @param connection the connection to extract the information
     * @return the response
     * @throws IOException
     */
    private String download(HttpURLConnection connection) throws IOException {
        connection.connect();
        String ret = "";
        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        byte[] buffer = new byte[MAXCHARS];
        int count;
        while ((count = bis.read(buffer, 0, MAXCHARS)) != -1) {
            ret += new String(buffer, 0, count);
        }
        bis.close();
        connection.disconnect();
        return ret;
    }
}
