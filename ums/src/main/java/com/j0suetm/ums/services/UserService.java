package com.j0suetm.ums.services;

import com.j0suetm.ums.models.UserModel;
import com.j0suetm.ums.models.Model;
import com.j0suetm.ums.services.Service;
import com.j0suetm.ums.models.ResultModel;
import java.util.UUID;

public class UserService
  extends Service
  implements IService
{
  @Override
  public ResultModel create(Model m) {
    return new ResultModel("ERROR", "NOT IMPLEMENTED YET");
  }

  @Override
  public Model getById(UUID id) {
    UserModel m = new UserModel();

    return m;
  }

  @Override
  public ResultModel update(Model m) {
    return new ResultModel("ERROR", "NOT IMPLEMENTED YET");
  }

  @Override
  public ResultModel delete(Model m) {
    return new ResultModel("ERROR", "NOT IMPLEMENTED YET");
  }
}
