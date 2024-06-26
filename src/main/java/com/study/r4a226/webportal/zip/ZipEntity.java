package com.study.r4a226.webportal.zip;

import java.util.ArrayList;
import java.util.List;

public class ZipEntity {

  /** ステータス· */
  private String status;

  /** 、メッセージ· */
  private String message;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<ZipData> getList() {
    return list;
  }

  public void setList(List<ZipData> list) {
    this.list = list;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /** 、郵便番号のリスト· */
  private List<ZipData> list = new ArrayList<ZipData>();

  /** .エラーメッセージ(表示用)· */
  private String errorMessage;

}
