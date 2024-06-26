package com.study.r4a226.webportal.prime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 素数検索機能のコントローラーです。
 */
@Controller
public class PrimeComtroller {

  @Autowired
  private PrimeService PrimeService;

  /**
   * 素数検索画面を表示
   *
   * @return 素数検索画面のパス
   */
  @GetMapping("/prime")
  public String getPrime() {
    // 画面のみを返却
    return "prime/input";
  }

  /**
   * 素数検索結果画面を表示
   *
   * @return 素数検索結果画面のパス
   */
  @PostMapping("/prime")
  public String postPrime(Model model,
      @RequestParam(name = "num") String num) {

    // データを取得
    PrimeData data = PrimeService.exec(num);
    // データをモデルオブジェクトに設定
    model.addAttribute("prime", data);
    // 画面を返却
    return "prime/result";
  }
}
