package urltest.controller;



import com.alibaba.fastjson.JSONObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import urltest.javabean.CityInfo;
import urltest.javabean.Data;
import urltest.javabean.Forecast;
import urltest.model.WeatherHandler;
import urltest.util.WeatherContext;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WeatherController implements Initializable {

    public Label lb_tomorrow1;
    public Label lb_tomorrow2;
    public Label lb_tomorrow3;
    @FXML
    private BorderPane mainLayout;
    @FXML
    private Label temperature;
    @FXML
    private Label lb_weather;
    @FXML
    private Label lb_maxTem;
    @FXML
    private Label lb_minTem;
    @FXML
    private Label lb_notice;
    @FXML
    private Label lb_windDirection;
    @FXML
    private Label lb_windLevel;
    @FXML
    private Label lb_cityName;
    @FXML
    private Button btn_search;
    @FXML
    private TextField tf_cityname;
 



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            WeatherContext.makeContext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            showWeatherMsg("南通");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btn_click() throws IOException, ClassNotFoundException {
        String cityname = tf_cityname.getText();
        if(cityname.equals("") || cityname == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("error");
            alert.headerTextProperty().set("输入不能为空");
            alert.showAndWait();
        }else if(!WeatherContext.NAME_CODE_MAP.keySet().contains(cityname)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("error");
            alert.headerTextProperty().set("不存在该城市");
            alert.showAndWait();
        }else {
            showWeatherMsg(cityname);
        }
    }

    public void showWeatherMsg(String cityName) throws IOException, ClassNotFoundException {
        String json = WeatherHandler.getWeatherJson(cityName);
        CityInfo cityInfo = (CityInfo)WeatherHandler.getWeatherObj(json,"cityInfo","CityInfo");
        Data data = (Data)WeatherHandler.getWeatherObj(json,"data","Data");
        List<Forecast> forecasts = data.getForecast();

        Forecast today = forecasts.get(0);//今天
        Forecast tomorrow_1 = forecasts.get(1);//明天
        Forecast tomorrow_2 = forecasts.get(2);//后天
        Forecast tomorrow_3 = forecasts.get(3);//大后天

        String weather = today.getType();
        lb_weather.setText(weather);
        if(weather.contains("晴")){
            mainLayout.setBackground(new Background(new BackgroundImage(new Image("/img/bg_sun.png"),null,null,null,null)));
        }else if(weather.contains("云")){
            mainLayout.setBackground(new Background(new BackgroundImage(new Image("/img/bg_cloud.png"),null,null,null,null)));
        }else if(weather.contains("雨")){
            mainLayout.setBackground(new Background(new BackgroundImage(new Image("/img/bg_rain.png"),null,null,null,null)));
        }else{
            mainLayout.setBackground(new Background(new BackgroundImage(new Image("/img/bg_fog.png"),null,null,null,null)));
        }

        lb_cityName.setText(cityInfo.getCity());
        temperature.setText(data.getWendu());
        lb_maxTem.setText(today.getHigh().substring(2));
        lb_minTem.setText(today.getLow().substring(2));
        lb_windDirection.setText(today.getFx());
        lb_windLevel.setText(today.getFl());
        lb_notice.setText(today.getNotice());

        lb_tomorrow1.setText("  明天： " + tomorrow_1.getHigh()+" / "+tomorrow_1.getLow() + "   " + tomorrow_1.getType());
        lb_tomorrow2.setText("  后天： " + tomorrow_2.getHigh()+" / "+tomorrow_2.getLow() + "   " + tomorrow_2.getType());
        lb_tomorrow3.setText("大后天： " + tomorrow_3.getHigh()+" / "+tomorrow_3.getLow() + "   " + tomorrow_3.getType());
        //Platform.runLater();刷性数据
    }



    public static void main(String[] args) {

    }
}
