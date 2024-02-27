package com.j0suetm.ums.services;

import com.j0suetm.ums.db.ConnectionManager;

import java.util.UUID;
import java.sql.Connection;
import java.sql.SQLException;

public class Service {
  private Connection conn;

  public Service() {
    try {
      this.conn = ConnectionManager.getConnection();
    } catch (SQLException e) {
      System.out.println("failed to connect -- " + e.getMessage());
    }
  }
}
