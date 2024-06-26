package com.study.r4a226.webportal.prime;

import org.springframework.stereotype.Service;

/**
 * BMI計算の業務ロジックを提供するサービスクラスです。
 * このクラスは身長と体重を受け取り、BMI情報を計算して返すビジネスロジックを提供します。
 */
@Service
public class PrimeService {

  /**
   * 身長と体重を受け取り、BMI情報を計算して返すメソッドです。
   *
   * @param limits 入力値
   * @return BMI情報
   */
  public PrimeData exec(String limits) {

    PrimeData primeData = new PrimeData();

    int limit = Integer.parseInt(limits);

    String primeNumbers = "";

    for (int i = 2; i <= limit; i++) {
      if (isPrime(i)) {
        primeNumbers += i + " ";
      }
    }
    primeNumbers = primeNumbers.trim();

    primeData.setResult(primeNumbers);
    return primeData;
  }

  private static boolean isPrime(int num) {
    if (num <= 1) {
      return false;
    }

    for (int i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}
