package com.study.r4a226.webportal.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

  @Autowired
  private RestTemplate restTemplate;

  private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={city}";

  public WeatherEntity execute(String city) {

    // ·レスポンス(json形式)を取得
    String json = request(city);
    // ·結果(箱)を生成
    WeatherEntity entity = new WeatherEntity();
    // ·レスポンスを結果(箱)に変換
    convert(json, entity);

    return entity;
  }

  private void convert(String json, WeatherEntity entity) {
    // ·変換ライブラリの生成
    ObjectMapper mapper = new ObjectMapper();

    try {
      // ·レスポンス(json)を構造体へ変換
      JsonNode node = mapper.readTree(json);
      // // ·「status」ノードを取得し文字列に変換
      // String status = node.get("status").asText();
      // entity.setStatus(status);
      // String message = node.get("message").asText();
      // entity.setMessage(message);

      // ·「results」ノード(配列)を取得し繰り返す
      for (JsonNode result : node.get("forecasts")) {
        WeatherData data = new WeatherData();
        // data.setForecasts(result.get("dateLabel").asText());
        // data.setTelop(result.get("telop").asText());
        data.setTelop(result.get("detail[1].weather").asText());

        // ·配列の末尾に追加
        entity.getList().add(data);
        System.out.println(data.getTelop());
      }

      // for (JsonNode result : node.get("detail")) {
      // WeatherData data = new WeatherData();
      // data.setForecasts(result.get("weather").asText());
      // // ·配列の末尾に追加
      // entity.getList().add(data);
      // }

      // for (JsonNode result : node.get("image")) {
      // WeatherData data = new WeatherData();
      // data.setForecasts(result.get("weather").asText());
      // data.setForecasts(result.get("url").asText());
      // data.setForecasts(result.get("width").asText());
      // data.setForecasts(result.get("height").asText());

      // // ·配列の末尾に追加
      // entity.getList().add(data);
      // }
    } catch (IOException e) {
      entity.setErrorMessage("通信に失敗しました");
    }
  }

  private String request(String city) {

    String json = restTemplate.getForObject(URL, String.class, city);
    return json;

  }

}
