package com.j0suetm.jgallery.components;

import org.flywaydb.core.Flyway;

public class Migrator {
  public static void migrate(
    String url,
    String username,
    String password
  ) {
    Flyway
      .configure()
      .dataSource(url, username, password)
      .load()
      .migrate();
  }
}
