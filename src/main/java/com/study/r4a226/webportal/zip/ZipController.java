package com.study.r4a226.webportal.zip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 郵便番号検索のコントローラー
 */
@Controller
public class ZipController {
  /* 郵便番号検索の業務ロジッククラス */
  @Autowired
  private ZipService zipService;

  /**
   * 郵便番号入力画面を表示します
   *
   * @return 郵便番号入力画面へのパス
   */
  @GetMapping("/zip")
  public String getZip() {
    return "zip/input";
  }

  /**
   * 郵便番号入力画面を表示します
   *
   * @return 郵便番号入力画面へのパス
   */
  @PostMapping("/zip")
  public String postZipcode(@RequestParam(name = "zipcode") String zipcode, Model model) {

    boolean isValid = zipService.validate(zipcode);
    if (isValid) {
      // 入力チェックエラーの場合、前の画面へ
      return "zip/input";
    }

    // 検索結果を取得
    ZipEntity entity = zipService.execute(zipcode);
    // ·結果を画面へ設定
    model.addAttribute("results", entity);

    return "zip/result";
  }
}
