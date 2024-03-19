package com.j0suetm.jgallery.services.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSingleton {
  private static final HikariDataSource dataSource;
  static {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://localhost:8000/jgallery-db");
    config.setUsername("j0suetm");
    config.setPassword("password");

    dataSource = new HikariDataSource(config);
  }

  public static Connection getConnection()
    throws SQLException
  {
    return dataSource.getConnection();
  }
}
