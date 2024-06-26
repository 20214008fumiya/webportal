package com.study.r4a226.webportal.task;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * タスク管理に関わるDBアクセスを実現するクラスです。
 * 処理が継続できない場合は、呼び出し元へ例外をスローします。
 *
 * @author 羅津文也
 */
@Repository
public class TaskRepository {

  /**
   * SQL 全件取得(期限日昇順)
   */

  private static final String SQL_SELECT_ALL = "SELECT * FROM task_t WHERE user_id = :userId ORDER BY limitday";

  /** SQL 1 件追加 **/
  private static final String SQL_INSERT_ONE = "INSERT INTO task_t (id, user_id, title, limitday,priority, complete) VALUES ((SELECT MAX(id) + 1 FROM task_t), :userId, :title, :limitday,:priority , false)";

  /** SQL 1件更新 */

  private static final String SQL_UPDATE_ONE = "UPDATE task_t SET complete = true WHERE id = :id";

  /* SQL 1件削除 */
  private static final String SQL_DELETE_ONE = "DELETE FROM task_t WHERE id = :id";

  private static final int EXPECTED_UPDATE_COUNT = 1;

  @Autowired
  private NamedParameterJdbcTemplate jdbc;

  /***
   * 指定されたユーザーIDに関連するすべてのデータを検索します。*
   *
   * @param userId -- ID
   * @return 検索結果のリスト(マップのリスト)
   */
  public List<Map<String, Object>> findAll(String userId) {
    // クエリのパラメータを設定するマップ

    Map<String, String> param = new HashMap<>();
    param.put("userId", userId);
    // クエリのパラメータを設定するマップ
    List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL, param);
    return resultList;

  }

  /**
   * タスクデータを保存します。
   *
   * @param taskData 保存するタスクデータ
   * @return 更新された行数
   * @throws SQLException 更新に失敗した場合にスローされる例外
   */

  public int save(TaskData taskData) throws Exception {
    // クエリのパラメータを設定するマップ
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("userId", taskData.getUserId());
    param.put("title", taskData.getTitle());
    param.put("limitday", taskData.getLimitDay());
    param.put("priority", taskData.getPriority());

    // クエリを実行し、更新された行数を取得
    int updateRow = jdbc.update(SQL_INSERT_ONE, param);
    if (updateRow != EXPECTED_UPDATE_COUNT) {
      throw new SQLException("更新に失敗しました件数:" + updateRow);
    }
    return updateRow;
  }

  /**
   * 指定されたIDのデータを削除します。
   *
   * @param id 削除するデータのID
   * @return 更新された行数
   * @throws SQLException 更新に失敗した場合にスローされる例外
   */

  public int update(int id) throws Exception {
    // クエリのパラメータを設定するマップ
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("id", id);

    // クエリを実行し、更新された行数を取得
    int updateRow = jdbc.update(SQL_UPDATE_ONE, param);
    if (updateRow != EXPECTED_UPDATE_COUNT) {
      // 更新件数が異常な場合は例外をスロー
      throw new Exception("更新に失敗しました件数:" + updateRow);
    }
    return updateRow;
  }

  /**
   * 指定されたIDのデータを更新します。
   *
   * @param id 更新するデータのID
   * @return 更新された行数
   * @throws SQLException 更新に失敗した場合にスローされる例外
   */

  public int delete(int id) throws Exception {
    // クエリのパラメータを設定するマップ
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("id", id);
    // クエリを実行し、更新された行数を取得
    int updateRow = jdbc.update(SQL_DELETE_ONE, param);

    if (updateRow != EXPECTED_UPDATE_COUNT) {
      // 更新件数が異常な場合は例外をスロー
      throw new Exception("更新に失敗しました件数:" + updateRow);
    }
    return updateRow;

  }

}
