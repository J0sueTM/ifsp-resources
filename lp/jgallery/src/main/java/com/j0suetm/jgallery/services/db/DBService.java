package com.j0suetm.jgallery.services.db;

import com.j0suetm.jgallery.models.ResultModel;

import java.lang.Record;
import java.util.UUID;

public interface DBService {
  public ResultModel create(Record r);
  public ResultModel getById(UUID id);
  public ResultModel updateById(UUID id, Record r);
  public ResultModel deleteById(UUID id);
}
