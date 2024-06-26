package com.study.r4a226.webportal.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.r4a226.webportal.user.LoginService;

@Controller
public class TaskController {

  /* ログインチェック用サービス */
  @Autowired
  private LoginService loginService;

  /*
   * タスク管理の業務ロジック
   */

  @Autowired
  private TaskService taskService;

  /**
   * ログイン中のユーザに紐づく、タスクー覧画面を表示します。
   *
   * <p>
   * 本機能は、タスク管理機能の一覧機能を提供します。
   *
   * @param model Viewに値を渡すオブジェクト(null不可)
   *
   * @return タスクー覧画面へのパス(null不可)
   *
   */

  @GetMapping("/task")
  public String getTaskList(Model model) {
    if (!loginService.isLogin())
      return "login";

    TaskEntity taskEntity = taskService.selectAll(loginService.getLoginUserId());
    model.addAttribute("taskEntity", taskEntity);

    return "task/list";
  }

  @PostMapping("/task/insert")
  public String insertTask(
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "limit", required = false) String limit,
      @RequestParam(name = "priority", required = false) String priority,
      Model model) throws Exception {
    // バリデーションチェック
    if (taskService.validates(title, limit)) {
      boolean isSuccess = taskService.insert(loginService.getLoginUserId(), title,
          limit, priority);
      if (isSuccess) {
        model.addAttribute("message", "正常に更新されました");
        return getTaskList(model);
      }
    }
    model.addAttribute("errorMessage", "更新できませんでした。再度更新しなおしてください");
    return getTaskList(model);
  }

  /**
   * 指定されたタスクIDの状態を完了に変更します。
   * 本機能は、タスク管理機能の状態変更機能を提供します。
   *
   * @param id    タスクIDの文字列を格納(null不可)
   * @param model Viewに値を渡すオブジェクト(null不可)
   * @return タスクー覧画面へのパス(null不可)
   */
  @PostMapping("/task/complete")
  public String completeTask(@RequestParam(name = "id") String id, Model model) {
    if (taskService.complete(id)) {
      model.addAttribute("message", "正常に更新されました");
    } else {
      model.addAttribute("errorMessage", "更新できませんでした。再度更新しなおしてください");
    }

    // 完了処理
    return getTaskList(model);

  }

  @PostMapping("/task/delete")
  public String deleteTask(@RequestParam("id") String id, Model model) {
    if (taskService.delete(id)) {
      model.addAttribute("message", "削除しました");
    } else {
      model.addAttribute("errorMessage", "削除できませんでした");
    }
    return getTaskList(model);
  }
}
