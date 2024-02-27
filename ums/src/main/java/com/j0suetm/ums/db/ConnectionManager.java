package com.j0suetm.ums.db;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
  private static final HikariDataSource dataSource;
  static {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/ums_db");
    config.setUsername("j0suetm");
    config.setPassword("password");

    dataSource = new HikariDataSource(config);
  }

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
