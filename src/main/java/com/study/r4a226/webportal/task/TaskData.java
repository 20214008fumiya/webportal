package com.study.r4a226.webportal.task;

import java.sql.Timestamp;

public class TaskData {

  private int id;

  private String userId;

  private String title;

  private String priority;

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  private Timestamp limitDay;

  private boolean isComplete;

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Timestamp getLimitDay() {
    return limitDay;
  }

  public void setLimitDay(Timestamp limitDay) {
    this.limitDay = limitDay;
  }

}
