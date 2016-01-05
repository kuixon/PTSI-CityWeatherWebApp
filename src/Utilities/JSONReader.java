package Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.text.html.HTML;

import Utilities.JSONException;
import Utilities.JSONObject;

public class JSONReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=London&lang=es&units=metric&APPID=c62dd1a67db43c63b19e933e51028163");
    System.out.println("////////Descomposición del objeto JSON////////");
    System.out.println("Tiempo:");
    for (int i = 0; i < json.getJSONArray("weather").length(); i++) {
    	System.out.println("* " + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description"));
    }
    System.out.println("Temperatura actual: " + json.getJSONObject("main").getDouble("temp"));
    System.out.println("Temperatura máxima: " + json.getJSONObject("main").getDouble("temp_max"));
    System.out.println("Temperatura mínima: " + json.getJSONObject("main").getDouble("temp_min"));
    System.out.println("Velocidad del viento (m/s): " + json.getJSONObject("wind").getDouble("speed"));
    System.out.println("Velocidad del viento (km/h): " + (json.getJSONObject("wind").getDouble("speed") * 3600) / 1000);
    System.out.println("Humedad: " + json.getJSONObject("main").getDouble("humidity"));
    System.out.println(json.toString());
    System.out.println(json.get("id"));
  }
}