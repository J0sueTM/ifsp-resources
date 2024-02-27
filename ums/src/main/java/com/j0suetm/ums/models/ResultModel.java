package com.j0suetm.ums.models;

import com.j0suetm.ums.models.Model;

public class ResultModel
  extends Model
{
  public String level;
  public String message;

  public ResultModel(String level, String message) {
    this.level = level;
    this.message = message;
  }
}
