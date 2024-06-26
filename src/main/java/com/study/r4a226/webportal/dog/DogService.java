package com.study.r4a226.webportal.dog;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ワンちゃん検索機能のサービス
 */
@Service
public class DogService {

  private static final String URL = "https://dog.ceo/api/breed/";

  @Autowired
  private RestTemplate restTemplate;

  /**
   * ワンちゃん検索機能を実行
   *
   * @param dogName ワンちゃんの種類
   * @return ワンちゃん検索結果
   */
  public DogData exec(String dogName) {

    String dogUrl = (URL + dogName + "/images/random");
    String json = restTemplate.getForObject(dogUrl, String.class);
    ObjectMapper mapper = new ObjectMapper();
    DogData dogData = new DogData();
    try {
      JsonNode node = mapper.readTree(json);
      // status
      String status = node.get("status").asText();
      dogData.setStatus(status);
      // message
      String message = node.get("message").asText();
      dogData.setMessage(message);
    } catch (IOException e) {
    }
    dogData.setErrorMessage("通信に失敗しました");
    return dogData;
  }
}
