package com.j0suetm.ums.models;

import com.j0suetm.ums.models.Model;

public class ResultModel
  extends Model
{
  public String level;
  public boolean isSuccess;
  public String message;
  public Object obj;

  public ResultModel(
    boolean isSuccess,
    String level,
    String message,
    Object obj
  ) {
    this.isSuccess = isSuccess;
    this.level = level;
    this.message = message;
    this.obj = obj;
  }
}
