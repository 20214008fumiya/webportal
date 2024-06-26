package com.study.r4a226.webportal.dog;

public class DogData {

  /* 犬画像のURL */
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /* APIの結果ステータス */
  private String status;

  /* 処理失敗時のエラーメッセージ */
  private String errorMessage;

}
