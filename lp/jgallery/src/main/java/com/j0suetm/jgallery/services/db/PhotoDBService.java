package com.j0suetm.jgallery.services.db;

import com.j0suetm.jgallery.models.ResultModel;
import com.j0suetm.jgallery.models.PhotoModel;
import com.j0suetm.jgallery.components.DBConnector;

import java.lang.Record;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.logging.Level;

public class PhotoDBService
  implements DBService
{
  private static final Logger logger =
    Logger.getLogger(PhotoDBService.class.getName());

  @Override
  public ResultModel create(Record r)
    throws IllegalArgumentException
  {
    if (!(r instanceof PhotoModel)) {
      throw new IllegalArgumentException("expected instance of PhotoModel");
    }
    PhotoModel photo = (PhotoModel)r;

    Connection conn = DBConnector.getConnection();
    if (conn == null) {
      return new ResultModel(
        "ERROR",
        "db connection unavailable",
        null
      );
    }

    String query =
      "INSERT INTO photos (" +
      "  id," +
      "  description"+
      ") VALUES (" +
      "  ?," +
      "  ?" +
      ");";

    try {
      PreparedStatement pstmt = conn.prepareStatement(query);

      int iota = 1;
      pstmt.setObject(iota++, photo.id());
      pstmt.setString(iota++, photo.description());

      pstmt.execute();
    } catch (SQLException ex) {
      logger.log(
        Level.SEVERE,
        "failed to create user on db -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to create user",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "created photo succesfully",
      null
    );
  }

  @Override
  public ResultModel getById(UUID id) {
    return new ResultModel("ERROR", "NOT IMPLEMENTED", null);
  }

  @Override
  public ResultModel updateById(UUID id, Record r) {
    return new ResultModel("ERROR", "NOT IMPLEMENTED", null);
  }

  @Override
  public ResultModel deleteById(UUID id) {
    return new ResultModel("ERROR", "NOT IMPLEMENTED", null);
  }
}
