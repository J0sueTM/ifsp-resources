package com.j0suetm.ums;

import com.j0suetm.ums.db.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Ums {
  public Ums() {}
  
  public void run() {
    Connection conn;
    try {
      conn = ConnectionManager.getConnection();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static void main(String[] args) {
    new Ums().run();
  }
}
