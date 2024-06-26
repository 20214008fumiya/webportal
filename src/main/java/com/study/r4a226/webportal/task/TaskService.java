package com.study.r4a226.webportal.task;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  /**
   * ユーザIDに合致するタスクー覧を取得します。
   * DBエラーが発生した場合は、空のタスクー覧を設定して呼び出し元へ返却します。
   *
   * @param userld ID (null)
   * @return タスクー覧
   */
  public TaskEntity selectAll(String userId) {
    List<Map<String, Object>> resultList = taskRepository.findAll(userId);
    TaskEntity taskEntity = mappingResult(resultList);
    return taskEntity;
  }

  private TaskEntity mappingResult(List<Map<String, Object>> resultList) {
    TaskEntity entity = new TaskEntity();
    for (Map<String, Object> map : resultList) {
      TaskData data = new TaskData();
      data.setId((int) map.get("id"));
      data.setUserId((String) map.get("user_id"));
      data.setTitle((String) map.get("title"));
      data.setLimitDay((Timestamp) map.get("limitday"));
      data.setComplete((boolean) map.get("complete"));
      data.setPriority((String) map.get("priority"));

      entity.getTaskList().add(data);
    }
    return entity;
  }

  /**
   * @param title
   * @param limitday_ 
   * @throws Exception
   */

  public boolean insert(String userId, String title, String limitday, String priority) throws Exception {
    TaskData taskData = refillToData(userId, title, limitday, priority);
    try {
      taskRepository.save(taskData);
    } catch (SQLException e) {
      return false;
    }
    return true;
  }

  private TaskData refillToData(String userId, String title, String limitday, String priority)
      throws java.text.ParseException {
    TaskData taskData = new TaskData();
    taskData.setUserId(userId);
    taskData.setTitle(title);
    taskData.setComplete(false);
    taskData.setPriority(priority);
    try {
      // String timestamp = "2024-06-20T08:58";
      String timestamp = limitday;
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
      Timestamp ts = new Timestamp(dateFormat.parse(timestamp).getTime());
      taskData.setLimitDay(ts);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return taskData;
  }

  public boolean validates(String title, String limitday) {

    // titleの空文字と文字数(50まで)をチェックします
    if ((title == null || title.isEmpty() || title.length() > 50)) {
      return false;
    }
    if (limitday == null || limitday.isEmpty()) {
      return false;
    }
    // 日付の形式チェック
    try {
      new SimpleDateFormat("yyyy-MM-dd");
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * タスクを完了状態にします。
   * DBエラーが発生した場合は、呼び出し元に失敗の通知を行います。
   *
   * @param id タスクID
   * @return 成功可否
   */
  public boolean complete(String id) {
    try {
      taskRepository.update(Integer.parseInt(id));
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * タスクを削除します。
   * DBエラーが発生した場合は、呼び出し元に失敗の通知を行います。
   *
   * @param id タスクID
   * @return 成功可否
   */
  public boolean delete(String id) {
    try {
      taskRepository.delete(Integer.parseInt(id));
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
