package com.j0suetm.jgallery.services.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.j0suetm.jgallery.components.DBConnector;
import com.j0suetm.jgallery.models.PhotoModel;
import com.j0suetm.jgallery.models.ResultModel;

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
        "failed to create photo on db -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to create photo on db",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "created photo succesfully",
      null
    );
  }

  public ResultModel count() {
    Connection conn = DBConnector.getConnection();
    if (conn == null) {
      return new ResultModel(
        "ERROR",
        "db connection unavailable",
        null
      );
    }

    int photoCount = 0;
    String query = "SELECT COUNT(*) FROM photos;";
    try {
      PreparedStatement pstmt = conn.prepareStatement(query);
      ResultSet rs = pstmt.executeQuery();
      boolean hasFirst = rs.next();
      if (hasFirst) {
        photoCount = rs.getInt("count");
      }
    } catch (SQLException ex) {
      return new ResultModel(
        "ERROR",
        "failed to retrieve photo count",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "retrieved photo count",
      photoCount
    );
  } 

  @Override
  public ResultModel getByID(UUID id) {
    Connection conn = DBConnector.getConnection();
    if (conn == null) {
      return new ResultModel(
        "ERROR",
        "db connection unavailable",
        null
      );
    }

    PhotoModel photo;
    String query = "SELECT id, description FROM photos WHERE id = ?;";
    try {
      PreparedStatement pstmt = conn.prepareStatement(query);

      pstmt.setObject(1, id);

      ResultSet rs = pstmt.executeQuery();
      boolean hasFirst = rs.next();
      if (!hasFirst) {
        return new ResultModel(
          "WARNING",
          "photo with given id does not exist",
          null
        );
      }

      photo = new PhotoModel(
        (UUID)rs.getObject("id"),
        rs.getString("description")
      );
    } catch (SQLException ex) {
      logger.log(
        Level.SEVERE,
        "failed to retrieve photo from db -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to retrieve photo from db",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "retrieved photo successfully",
      photo
    );
  }

  public ResultModel getAll(int limit, int offset) {
    Connection conn = DBConnector.getConnection();
    if (conn == null) {
      return new ResultModel(
        "ERROR",
        "db connection unavailable",
        null
      );
    }

    List<PhotoModel> photos = new ArrayList<PhotoModel>();
    String query = "SELECT id, description FROM photos LIMIT ? OFFSET ?;";
    try {
      PreparedStatement pstmt = conn.prepareStatement(query);

      int iota = 1;
      pstmt.setInt(iota++, limit);
      pstmt.setInt(iota++, offset);

      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        photos.add(
          new PhotoModel(
            (UUID)rs.getObject("id"),
            rs.getString("description")
          )
        );
      }
    } catch (SQLException ex) {
      logger.log(
        Level.SEVERE,
        "failed to retrieve photos from db -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to retrieve photos from db",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "retrieved photos succesfully",
      photos
    );
  }

  @Override
  public ResultModel updateByID(UUID id, Record r)
    throws IllegalArgumentException
  {
    if (!(r instanceof PhotoModel)) {
      throw new IllegalArgumentException("expected instance of PhotoModel");
    }
    PhotoModel newPhoto = (PhotoModel)r;

    Connection conn = DBConnector.getConnection();
    if (conn == null) {
      return new ResultModel(
        "ERROR",
        "db connection unavailable",
        null
      );
    }

    PhotoModel updatedPhoto;
    String query =
      "UPDATE photos SET" +
      "  description = ? " +
      "WHERE id=? " +
      "RETURNING *;";
    try {
      PreparedStatement pstmt = conn.prepareStatement(query);

      int iota = 1;
      pstmt.setString(iota++, newPhoto.description());
      pstmt.setObject(iota++, id);

      ResultSet rs = pstmt.executeQuery();
      boolean hasFirst = rs.next();
      if (!hasFirst) {
        return new ResultModel(
          "ERROR",
          "photo may have been updated, but was not returned",
          null
        );
      }

      updatedPhoto = new PhotoModel(
        (UUID)rs.getObject("id"),
        rs.getString("description")
      );
    } catch (SQLException ex) {
      logger.log(
        Level.SEVERE,
        "failed to update photo on db -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to update photo on db",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "updated photo succesfully",
      updatedPhoto
    );
  }

  @Override
  public ResultModel deleteByID(UUID id) {
    Connection conn = DBConnector.getConnection();
    if (conn == null) {
      return new ResultModel(
        "ERROR",
        "db connection unavailable",
        null
      );
    }

    String query = "DELETE FROM photos WHERE id = ?;";
    try {
      PreparedStatement pstmt = conn.prepareStatement(query);

      pstmt.setObject(1, id);

      pstmt.execute();
    } catch (SQLException ex) {
      logger.log(
        Level.SEVERE,
        "failed to delete photo from db -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to delete photo from db",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "deleted photo succesfully",
      null
    );
  }
}
