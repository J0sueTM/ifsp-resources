package com.j0suetm.ums.services.db;

import com.j0suetm.ums.models.Model;
import com.j0suetm.ums.models.ResultModel;

import java.util.UUID;

public interface ModelDBService {
  public ResultModel create(Model m);
  public Model getById(UUID id);
  public Model update(Model m);
  public ResultModel delete(Model m);
}
