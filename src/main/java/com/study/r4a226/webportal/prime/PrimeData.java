package com.study.r4a226.webportal.prime;

/**
 * 素数を格納するためのエンティティクラスです。
 * このクラスは入力値、素数一覧を保持します。
 */
public class PrimeData {

  /** 入力値 */
  private String num;

  /** 素数一覧 */
  private String result;

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
