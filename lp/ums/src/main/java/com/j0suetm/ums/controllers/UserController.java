package com.j0suetm.ums.controllers;

import com.j0suetm.ums.controllers.CRUDController;
import com.j0suetm.ums.models.Model;
import com.j0suetm.ums.models.ResultModel;
import com.j0suetm.ums.models.UserModel;
import com.j0suetm.ums.services.db.UserDBService;

public class UserController
  implements CRUDController
{
  @Override
  public ResultModel create(Model m)
    throws IllegalArgumentException
  {
    UserModel user;
    if (!(m instanceof UserModel)) {
      throw new IllegalArgumentException("Expected instance of UserModel");
    }
    user = (UserModel)m;

    UserDBService udbs = new UserDBService();
    ResultModel res = udbs.create(user);

    return res;
  }

  @Override
  public ResultModel get(Model m) {
    return new ResultModel(false, "ERROR", "NOT IMPLEMENTED", null);
  }

  @Override
  public ResultModel update(Model m) {
    return new ResultModel(false, "ERROR", "NOT IMPLEMENTED", null);
  }

  @Override
  public ResultModel delete(Model m) {
    return new ResultModel(false, "ERROR", "NOT IMPLEMENTED", null);
  }
}
