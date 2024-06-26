package com.study.r4a226.webportal.user;

import java.util.ArrayList;
import java.util.List;

/**
 * 複数件のタスク情報を保持
 * @author 羅津文也
 */
public class UserEntity {

  /** タスク情報のリスト */
  private List<UserData> userList = new ArrayList<UserData>();

  /** エラーメッセージ(表示用) */
  private String errorMessage;

  public List<UserData> getUserList() {
    return userList;
  }

  public void setUserList(List<UserData> userList) {
    this.userList = userList;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
