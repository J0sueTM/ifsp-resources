package com.j0suetm.jgallery.components;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {
  private static HikariDataSource dataSource;

  public static void connect(
    String url,
    String username,
    String password
  ) {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(url);
    config.setUsername(username);
    config.setPassword(password);

    dataSource = new HikariDataSource(config);
  }

  public static Connection getConnection()
    throws SQLException
  {
    return dataSource.getConnection();
  }
}
