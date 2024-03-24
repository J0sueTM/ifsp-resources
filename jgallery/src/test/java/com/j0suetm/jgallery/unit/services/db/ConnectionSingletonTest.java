package com.jsuetm.jgallery.unit.services.db.ConnectionSingleton;

import com.j0suetm.jgallery.services.db.ConnectionSingleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSingletonTest {
  @Test
  public void getConnection() {
    ConnectionSingleton connSing = new ConnectionSingleton();
    try {
      Connection conn = connSing.getConnection();
      assertNotNull(conn);
    } catch (SQLException ex) {
      fail(ex.getMessage());
    }
  }
}
