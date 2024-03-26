package com.j0suetm.jgallery.services.bucket;

import com.j0suetm.jgallery.models.ResultModel;

public interface BucketService {
  public ResultModel put(Object o, String key);
  public ResultModel get(String key);
  public ResultModel delete(String key);
}
