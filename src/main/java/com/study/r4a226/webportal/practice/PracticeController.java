package com.study.r4a226.webportal.practice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PracticeController {

  @GetMapping("/dojo")
  public String goDojo() {
    return "practice/dojo";
  }

  /**
   * れべる0です
   *
   * @return
   */
  // レベル0
  @PostMapping("/level0")
  public String level0() {
    String local = "jouhou";
    String domain = "hcs.ac.jp";

    System.out.println(local + "@" + domain);
    return "practice/dojo";
  }

  /**
   * レベル1
   *
   * @return dojoページ
   */

  @PostMapping("/level1")
  public String level1(Model model) {
    // 入力された文字列を取得
    String input = "180 2"; // ※サンプル値です

    // 入力された文字列を空白で分割し、XとYの値を数値に変換して取得
    String[] split = input.split(" ");
    int x = Integer.parseInt(split[0]); // 月間人口増加率
    int y = Integer.parseInt(split[1]); // 経過月数

    // 入力値を出力
    System.out.println("入力値: X = " + x + ", Y = " + y);

    // Yヶ月後の合計人口を計算
    int result = 100 + x * y; // 100人 + 月間増加率 * 経過月数

    // 計算結果を出力
    System.out.println("Yヶ月後の合計人口: " + result);

    // 計算結果をモデルに設定
    model.addAttribute("ans", result);

    // 結果画面に遷移
    return "practice/result";
  }

  @PostMapping("/level2")
  public String level2(Model model) {
    // 入力された文字列を取得
    String input = "namae";

    // 入力された文字列の最後の1文字を取得
    String result = input.substring(input.length() - 1);

    // 結果をモデルに設定
    model.addAttribute("ans", result);

    // 結果画面に遷移
    return "practice/result";
  }

  @PostMapping("/level3")
  public String level3(@RequestParam(name = "level3") String input, Model model) {

    // 変換対象文字列
    String str = input; // 入力値を代入

    // replaceAll() を使用して母音を空文字に置換
    String result = str.replaceAll("a", "")
        .replaceAll("i", "")
        .replaceAll("u", "")
        .replaceAll("e", "")
        .replaceAll("o", "")
        .replaceAll("A", "")
        .replaceAll("I", "")
        .replaceAll("U", "")
        .replaceAll("E", "")
        .replaceAll("O", "");

    // 結果をモデルへ設定
    model.addAttribute("ans", result);
    return "practice/result";
  }

  @PostMapping("/level4")
  public String level4(@RequestParam(name = "level4") String input, Model model) {
    String[] s = input.split(""); // 入力文字列を配列に変換
    int winner = 0; // 勝者フラグ (0: なし, 1: o, 2: x)

    // 横方向に連続する o または x の数をカウント
    for (int i = 0; i < s.length; i++) {
      int count = 0;
      for (int j = 0; j < 3; j++) {
        if (i + j < s.length && s[i + j].equals(s[i])) {
          count++;
        } else {
          break;
        }
      }

      if (count == 3) {
        winner = (s[i].equals("o")) ? 1 : 2;
        break;
      }
    }

    // 判定結果を出力
    String result = "";
    if (winner == 1) {
      result = "o";
    } else if (winner == 2) {
      result = "x";
    } else {
      result = "draw";
    }

    model.addAttribute("ans", result);
    return "practice/result";
  }

  @PostMapping("/level5")
  public String level5(@RequestParam(name = "level5") String input, Model model) {
    // 入力データを読み込む
    String[] lines = input.split("\n");
    int h = Integer.parseInt(lines[0].split(" ")[0].trim()); // Trim leading/trailing spaces
    int w = Integer.parseInt(lines[0].split(" ")[1].trim()); // Trim leading/trailing spaces

    // グリッドを作成
    char[][] grid = new char[h][w];
    for (int i = 1; i <= h; i++) {
      grid[i - 1] = lines[i].toCharArray();
    }

    // スタート地点を見つける
    int sx = 0, sy = 0;
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        if (grid[i][j] == 'S') {
          sx = i;
          sy = j;
          break;
        }
      }
    }

    // 迷路探索 (深さ優先探索)
    boolean[][] visited = new boolean[h][w];
    if (dfs(grid, visited, sx, sy)) {
      model.addAttribute("ans", "YES");
    } else {
      model.addAttribute("ans", "NO");
    }

    return "practice/result";
  }

  private boolean dfs(char[][] grid, boolean[][] visited, int x, int y) {
    // 範囲外または壁の場合
    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '#') {
      return false;
    }

    // 訪問済みまたはゴールの場合
    if (visited[x][y] || grid[x][y] == 'S') {
      return true;
    }

    // 訪問済みフラグを立てる
    visited[x][y] = true;

    // 上下左右に再帰的に探索
    boolean result = dfs(grid, visited, x - 1, y) || dfs(grid, visited, x + 1, y) ||
        dfs(grid, visited, x, y - 1) || dfs(grid, visited, x, y + 1);

    // 訪問済みフラグを元に戻す
    visited[x][y] = false;

    return result;
  }

}
