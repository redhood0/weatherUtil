package urltest.model;

import com.alibaba.fastjson.JSONObject;
import urltest.controller.WeatherController;
import urltest.javabean.CityInfo;
import urltest.util.WeatherContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class WeatherHandler {

    public static String getWeatherJson(String cityName) throws IOException {

        String citycode = WeatherContext.NAME_CODE_MAP.get(cityName);

        URL url = new URL("http://t.weather.sojson.com/api/weather/city/" + citycode);
        URLConnection connection = url.openConnection();
        connection.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String s = "";
        while((s = br.readLine()) != null){
            sb.append(s);
        }
        return sb.toString();
    }

    public static Object getWeatherObj(String json,String key,String className) throws ClassNotFoundException {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Object obj = jsonObject.getObject(key,Class.forName("urltest.javabean." + className));
//        System.out.println(cityInfo.getCityId());
//        System.out.println(jsonObject.get("time"));
        return obj;
    }


    public static void main(String[] args) {

    }

}
