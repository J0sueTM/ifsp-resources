package com.j0suetm.jgallery.models;

import java.lang.Object;

public record ResultModel(
  String level,
  String message,
  Object data
) {}
