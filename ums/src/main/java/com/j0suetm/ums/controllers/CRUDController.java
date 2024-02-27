package com.j0suetm.ums.controllers;

import com.j0suetm.ums.models.Model;
import com.j0suetm.ums.models.ResultModel;

public interface CRUDController {
  public ResultModel create(Model m);
  public ResultModel get(Model m);
  public ResultModel update(Model m);
  public ResultModel delete(Model m);
}
