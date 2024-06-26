package com.study.r4a226.webportal.bmi;

import org.springframework.stereotype.Service;

/**
 * BMI計算の業務ロジックを提供するサービスクラスです。
 * このクラスは身長と体重を受け取り、BMI情報を計算して返します。
 *
 * @author 羅津文也
 */
@Service
public class BmiService {
  /**
   * 身長と体重を受け取り、BMI情報を計算して返すメソッドです。
   *
   * @param height 身長(単位:センチメートル)
   * @param weight 体重(単位:キログラム)
   * @return BMI情報を格納したBmiEntityオブジェクト」
   */
  public BmiData exec(String height, String weigh) {
    // BMI情報を格納するクラスを生成
    BmiData data = new BmiData();
    // 計算結果を格納
    String ans = calc(height, weigh);
    data.setAns(ans);
    // コメントを格納
    String comment = judge(ans);
    data.setComment(comment);
    // 画像パスを格納
    String img = img(ans);
    data.setPath(img);
    return data;
  }

  /**
   * 入力文字のルールを設定します
   *
   * @param name   名前
   * @param height 身長(単位:センチメートル)
   * @param weight 体重(単位:キログラム)
   * @return 入力された文字が正しいかどうかの判定をbooleanで返す
   */
  public boolean validate(String height, String weight) {
    int heightNum = 0;
    int weightNum = 0;
    try {
      heightNum = Integer.parseInt(height);
      weightNum = Integer.parseInt(weight);
    } catch (NumberFormatException e) {
      return true;
    }
    if (heightNum < 30 || heightNum > 250) {
      return true;
    }
    if (weightNum < 5 || weightNum > 200) {
      return true;
    }
    return false;
  }

  private String calc(String height, String weigh) {
    // 身長をセンチメートルからメートルへ変換
    double m = Double.parseDouble(height) / 100;
    // BMIを計算
    double bmi = Double.parseDouble(weigh) / (Integer.parseInt(height) * m);
    // 小数点第三位まで切り捨て
    String ans = String.format("%.3f", bmi * 100);
    return ans;
  }

  private String judge(String bmiString) {
    double bmi = Double.parseDouble(bmiString);
    String comment = "";

    if (bmi < 16) {
      comment = "痩せすぎ";
    } else if (bmi >= 16 && bmi <= 16.99) {
      comment = "痩せ";
    } else if (bmi >= 17 && bmi <= 18.49) {
      comment = "痩せぎみ";
    } else if (bmi >= 18.50 && bmi <= 24.99) {
      comment = "普通体重";
    } else if (bmi >= 25.00 && bmi <= 29.99) {
      comment = "前肥満";
    } else if (bmi >= 30.00 && bmi <= 34.99) {
      comment = "肥満(1度)";
    } else if (bmi >= 35.00 && bmi <= 39.99) {
      comment = "肥満(2度)";
    } else {
      comment = "肥満(3度)";
    }

    return comment;
  }

  private String img(String bmiString) {
    double bmi = Double.parseDouble(bmiString);
    String path = "";

    if (bmi <= 18.49) {
      path = "img/bmi/gari.png";
    } else if (bmi >= 18.50 && bmi <= 24.99) {
      path = "img/bmi/normal.png";
    } else if (bmi >= 25.00) {
      path = "img/bmi/puni.png";
    }

    return path;
  }
}
