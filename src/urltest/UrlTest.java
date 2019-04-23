package urltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {

    public static void main(String[] args) throws IOException {
        URLConnection uc = new URL("http://t.weather.sojson.com/api/weather/city/101190101").openConnection();

        uc.connect();
        InputStream inputStream = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String x = "";
        while((x=br.readLine()) != null){
            System.out.println(x);
        }
    }
}
