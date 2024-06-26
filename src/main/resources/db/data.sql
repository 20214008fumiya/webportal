/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM user_m;

/* ユーザマスタのデータ（ADMIN権限） */
INSERT INTO
  user_m (user_id, PASSWORD, user_name, ROLE, enabled)
VALUES
  (
    'taro@xxx.co.jp',
    'p@ss',
    'たなかたろう',
    'ROLE_ADMIN',
    TRUE
  );

/* ユーザマスタのデータ（上位権限） */
INSERT INTO
  user_m (user_id, PASSWORD, user_name, ROLE, enabled)
VALUES
  (
    'ytaro@xxx.co.jp',
    'p@ss',
    'やまだたろう',
    'ROLE_TOP',
    TRUE
  );

/* ユーザマスタのデータ（一般権限） */
INSERT INTO
  user_m (user_id, PASSWORD, user_name, ROLE, enabled)
VALUES
  (
    'goro@xxx.co.jp',
    'p@ss',
    'ごろう',
    'ROLE_GENERAL',
    TRUE
  );

DELETE FROM task_t;

/* タスクテーブルのデータ */
INSERT INTO
  task_t (id, user_id, title, limitday, priority, complete)
VALUES
  (
    1,
    'admin',
    'このレコードは消さないこと',
    '2022-11-11',
    'HIGH',
    TRUE
  );

INSERT INTO
  task_t (id, user_id, title, limitday, priority, complete)
VALUES
  (
    2,
    'goro@xxx.co.jp',
    '買い物リストを作る',
    '2023-06-24 12:00 ',
    'MIDDLE',
    FALSE
  );

INSERT INTO
  task_t (id, user_id, title, limitday, priority, complete)
VALUES
  (
    3,
    'hanako@xxx.co.jp',
    '明日の焼肉の予約',
    '2023-07-11 12:00',
    'MIDDLE',
    FALSE
  );

INSERT INTO
  task_t (id, user_id, title, limitday, priority, complete)
VALUES
  (
    4,
    'taro@xxx.co.jp',
    'ジムに行く',
    '2024-02-27 12:00',
    'MIDDLE',
    FALSE
  );
