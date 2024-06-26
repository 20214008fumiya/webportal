package com.study.r4a226.webportal.bmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * BMI画面のコントローラーです。
 */
@Controller
public class BmiController {
  @Autowired
  private BmiService bmiService;

  /**
   * BMI入力画面へのリクエストハンドラです。!
   *
   * @return BMI入力画面のパス
   */
  @GetMapping("/bmi")
  public String getBmi() {
    // 画面のみを返却
    return "bmi/input";
  }

  /**
   * BMI計算結果画面へのリクエストハンドラです。
   *
   * @return BMI計算結果画面のパス
   */
  @PostMapping("/bmi")
  public String postBmi(Model model,
      @RequestParam(name = "cm") String height,
      @RequestParam(name = "kg") String weight) {

    // 入力チェック
    boolean isValid = bmiService.validate(height, weight);
    if (isValid) {
      // 入力チェックエラーの場合、前の画面へ
      return "bmi/input";
    }
    // データを取得
    BmiData data = bmiService.exec(height, weight);
    // データをモデルオブジェクトに設定
    model.addAttribute("bmi", data);
    // 画面を返却
    return "bmi/result";
  }

}
