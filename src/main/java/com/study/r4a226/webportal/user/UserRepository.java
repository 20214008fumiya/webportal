package com.study.r4a226.webportal.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  /** · SQL·ログインチェック */
  private static final String SQL_LOGIN = "SELECT * FROM user_m WHERE user_id = :userId AND password = :password AND enabled = true";

  @Autowired
  private NamedParameterJdbcTemplate jdbc;

  /**
   * · 指定されたユーザーIDとバスワードにマッチするユーザーデータを取得するメソッドです。
   * ユーザーIDとバスワードは引数として受け取ります。
   * · @param userId 取得するユーザーデータのユーザーID
   * · @param password 取得するユーザーデータのバスワード
   * · @return 指定されたユーザーIDのユーザーデータのリスト
   */
  public List<Map<String, Object>> login(String userId, String password) {
    // ·バラメータを格納するためのマップを作成
    Map<String, Object> params = new HashMap<>();
    params.put("userId", userId);
    params.put("password", password);

    // ·データベースのクエリを実行し、結果を取得
    List<Map<String, Object>> resultList = jdbc.queryForList(SQL_LOGIN, params);

    // ·取得したユーザーデータのリストを返す
    return resultList;
  }
}
