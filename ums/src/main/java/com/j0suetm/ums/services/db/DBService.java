package com.j0suetm.ums.services.db;

import com.j0suetm.ums.services.db.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DBService {
  private Connection conn;

  public DBService() {
    try {
      this.conn = ConnectionManager.getConnection();
    } catch (SQLException e) {
      System.out.println("failed to connect -- " + e.getMessage());
    }
  }
}
