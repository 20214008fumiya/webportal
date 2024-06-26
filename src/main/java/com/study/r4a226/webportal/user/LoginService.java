package com.study.r4a226.webportal.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;

/**
 * ログインサービスクラス
 * @author 羅津文也
 */
@Transactional
@Service
public class LoginService {

  private final String SESSION_USER_DATA_KEY = "userData";

  /** 、セッションオブジェクト · */
  @Autowired
  HttpSession session;

  /** · ユーザーリボジトリ */
  @Autowired
  private UserRepository userRepository;

  /**
   * ·ログイン処理を行います。*@param userId
   *
   * @param password
   * @return ログイン成功時はtrue、失敗時はfalse
   */
  public boolean login(String userId, String password) {
    // ·ユーザーデータを取得するためのリスト
    List<Map<String, Object>> resultList;
    try {
      // ·データベースから指定されたユーザーIDのユーザーデータを取得
      resultList = userRepository.login(userId, password);
    } catch (DataAccessException e) {
      // ·データアクセス時にエラーが発生した場合は処理を終了
      e.printStackTrace();
      return false;

    }

    if (resultList.size() != 1) {
      // ·ユーザーデータが取得できなかった場合は処理を終了
      return false;

    }

    // 取得したユーザーデータを指定されたユーザーフォームにマッビング
    UserData userData = mappingResult(resultList.get(0));
    session.setAttribute("userData", userData);

    return true;
  }

  /***
   * ユーザーデータをマッビングします。
   *
   * @param item ユーザーデータ
   * @return マッビングしたユーザーデータ
   */
  private UserData mappingResult(Map<String, Object> item) {
    UserData UserData = new UserData();

    UserData.setUserId((String) item.get("user_id"));
    UserData.setUserName((String) item.get("user_name"));
    UserData.setRole((String) item.get("role"));
    UserData.setEnabled((boolean) item.get("enabled"));

    return UserData;
  }

  /***
   * ログアウト処理
   *
   */
  public void logout() {
    session.invalidate();
  }

  /*** ログイン中ユーザーのユーザーIDを取得します。
   * @return ログイン中ユーザーのユーザーID **/

  public String getLoginUserId() {
    UserData userData = (UserData) session.getAttribute(SESSION_USER_DATA_KEY);
    if (userData == null) {
      return "Unknown User";
    }
    return userData.getUserId();

  }

  public boolean isLogin() {
    return session.getAttribute(SESSION_USER_DATA_KEY) != null;
  }
}
