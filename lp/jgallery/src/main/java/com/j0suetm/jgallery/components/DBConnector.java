package com.j0suetm.jgallery.components;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DBConnector {
  private static final Logger logger =
    Logger.getLogger(DBConnector.class.getName());

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

    logger.log(Level.FINE, "connected to db");
  }

  public static Connection getConnection() {
    Connection conn;
    try {
      conn = dataSource.getConnection();
    } catch (SQLException ex) {
      logger.log(
        Level.SEVERE,
        "failed to get db connection -- {0}",
        ex.getMessage()
      );

      return null;
    }

    return conn;
  }
}
