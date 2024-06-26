/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE user_m;
 */
DROP TABLE user_m;
/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS
  user_m (
    user_id VARCHAR(50) PRIMARY KEY,
    PASSWORD VARCHAR(100),
    user_name VARCHAR(50),
    ROLE VARCHAR(50),
    enabled BOOLEAN
  );

/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE task_t;
 */
DROP TABLE task_t;
/* タスクテーブル */
CREATE TABLE IF NOT EXISTS
  task_t (
    id INT PRIMARY KEY,
    user_id VARCHAR(50),
    title VARCHAR(50),
    limitday TIMESTAMP,
    priority VARCHAR(10),
    complete BOOLEAN
  );
