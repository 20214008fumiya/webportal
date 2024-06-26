package com.study.r4a226.webportal.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

  @Autowired
  private WeatherService weatherService;

  @GetMapping("/weather")
  public String getWeather() {
    return "weather/input";
  }

  @PostMapping("/weather")
  public String postWeather(@RequestParam(name = "cityy") String city, Model model) {

    // 検索結果を取得
    WeatherEntity entity = weatherService.execute(city);
    // ·結果を画面へ設定
    model.addAttribute("results", entity);

    return "weather/result";
  }
}
