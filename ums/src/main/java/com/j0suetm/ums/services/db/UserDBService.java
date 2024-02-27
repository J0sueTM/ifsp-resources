package com.j0suetm.ums.services.db;

import com.j0suetm.ums.models.UserModel;
import com.j0suetm.ums.models.Model;
import com.j0suetm.ums.models.ResultModel;

import com.j0suetm.ums.services.db.DBService;
import com.j0suetm.ums.services.db.ModelDBService;

import java.util.UUID;

public class UserDBService
  extends DBService
  implements ModelDBService
{
  @Override
  public ResultModel create(Model user) {
    return new ResultModel(false, "ERROR", "NOT IMPLEMENTED YET", null);
  }

  @Override
  public Model getById(UUID id) {
    UserModel m = new UserModel();

    return m;
  }

  @Override
  public ResultModel update(Model user) {
    return new ResultModel(false, "ERROR", "NOT IMPLEMENTED YET", null);
  }

  @Override
  public ResultModel delete(Model user) {
    return new ResultModel(false, "ERROR", "NOT IMPLEMENTED YET", null);
  }
}
