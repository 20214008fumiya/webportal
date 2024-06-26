package com.study.r4a226.webportal.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherEntity {

  /** 、郵便番号のリスト· */
  private List<WeatherData> list = new ArrayList<WeatherData>();
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  private String telop;

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public List<WeatherData> getList() {
    return list;
  }

  public void setList(List<WeatherData> list) {
    this.list = list;
  }

  public String getTelop() {
    return telop;
  }

  public void setTelop(String telop) {
    this.telop = telop;
  }

}
