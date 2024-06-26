package com.study.r4a226.webportal.zip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 郵便番号検索サービス
 *
 * @author s20214008
 */
@Service
public class ZipService {

  @Autowired
  private RestTemplate restTemplate;

  /** . エンドポイント · */
  private static final String URL = "https://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

  /**
   * 郵便番号検索を実行します
   *
   * @param param 郵便番号
   * @return 郵便番号に基づく住所の検索結果
   */
  public ZipEntity execute(String param) {
    // ·レスポンス(json形式)を取得
    String json = request(param);
    // ·結果(箱)を生成
    ZipEntity entity = new ZipEntity();
    // ·レスポンスを結果(箱)に変換
    convert(json, entity);

    return entity;
  }

  private void convert(String json, ZipEntity entity) {
    // ·変換ライブラリの生成
    ObjectMapper mapper = new ObjectMapper();

    try {
      // ·レスポンス(json)を構造体へ変換
      JsonNode node = mapper.readTree(json);
      // ·「status」ノードを取得し文字列に変換
      String status = node.get("status").asText();
      entity.setStatus(status);
      String message = node.get("message").asText();
      entity.setMessage(message);
      // ·「results」ノード(配列)を取得し繰り返す
      for (JsonNode result : node.get("results")) {
        ZipData data = new ZipData();
        data.setZipcode(result.get("zipcode").asText());
        data.setPrefcode(result.get("prefcode").asText());
        data.setAddress1(result.get("address1").asText());
        data.setAddress2(result.get("address2").asText());
        data.setAddress3(result.get("address3").asText());
        data.setKana1(result.get("kana1").asText());
        data.setKana2(result.get("kana2").asText());
        data.setKana3(result.get("kana3").asText());
        // ·配列の末尾に追加
        entity.getList().add(data);
      }
    } catch (IOException e) {
      entity.setErrorMessage("通信に失敗しました");
    }
  }

  private String request(String zipcode) {
    String json = restTemplate.getForObject(URL, String.class, zipcode);
    return json;
  }

  /**
   * 入力チェックを行います。
   * 
   * @param num入力された郵便番号
   * @return 郵便番号が正しいかどうか
   */
  public boolean validate(String num) {
    try {
      Integer.parseInt(num);
    } catch (NumberFormatException e) {
      return true;
    }
    int len = num.length();
    if (len != 7) {
      return true;
    }
    return false;
  }
}
