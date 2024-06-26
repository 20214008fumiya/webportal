package com.study.r4a226.webportal.task;

import java.util.ArrayList;
import java.util.List;

/**
 * 複数件のタスク情報を保持します。
 *
 * DBとController間を本クラスでモデル化します。
 * DBからタスク情報が取得できない場合は、リストが空となります。
 * リストにnullは含まれません
 *
 * @author 羅津文也
 */
public class TaskEntity {

  /** タスク情報のリスト */
  private List<TaskData> taskList = new ArrayList<TaskData>();

  /** エラーメッセージ(表示用) */
  private String errorMessage;

  public List<TaskData> getTaskList() {
    return taskList;
  }

  public void setTaskList(List<TaskData> taskList) {
    this.taskList = taskList;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
