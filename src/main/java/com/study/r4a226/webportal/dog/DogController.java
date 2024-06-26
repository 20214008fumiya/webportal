package com.study.r4a226.webportal.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ワンちゃん検索機能のコントローラーです。
 */
@Controller
public class DogController {

  @Autowired
  private DogService dogService;

  /**
   * ワンちゃん検索画面を表示します。
   *
   * @return ワンちゃん検索画面のパス
   */
  @GetMapping("/dog")
  public String getDog() {
    return "dog/input";
  }

  /**
   * ワンちゃん検索結果画面を表示します。
   *
   * @param dogName ワンちゃんの名前
   * @return ワンちゃん検索結果画面のパス
   */
  @PostMapping("/dog")
  public String postDog(Model model,
      @RequestParam(name = "dogg") String dogName) {
    // データを取得
    DogData dogData = dogService.exec(dogName);
    // データをモデルオブジェクトに設定
    model.addAttribute("data", dogData);
    // 画面を返却
    return "dog/result";
  }
}
