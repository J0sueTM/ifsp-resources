package com.j0suetm.jgallery.test.services.db;

import com.j0suetm.jgallery.services.db.PhotoDBService;
import com.j0suetm.jgallery.components.DBConnector;
import com.j0suetm.jgallery.components.Migrator;
import com.j0suetm.jgallery.models.ResultModel;
import com.j0suetm.jgallery.models.PhotoModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

class PhotoDBServiceTest {
  static PostgreSQLContainer<?> pg =
    new PostgreSQLContainer<>("postgres:latest");

  @BeforeAll
  static void beforeAll() {
    pg.start();
  }

  @AfterAll
  static void afterAll() {
    pg.stop();
  }

  @BeforeEach
  void setUp() {
    Migrator.migrate(
      pg.getJdbcUrl(),
      pg.getUsername(),
      pg.getPassword()
    );

    DBConnector.connect(
      pg.getJdbcUrl(),
      pg.getUsername(),
      pg.getPassword()
    );
  }

  @Test
  void shouldCreatePhoto() {
    PhotoDBService pdbSrvc = new PhotoDBService();
    ResultModel res = pdbSrvc.create(
      new PhotoModel(
        UUID.randomUUID(),
        "a group of 5 friends enjoying a trip to alaska"
      )
    );
    assertEquals(res.level(), "INFO");
    assertEquals(res.message(), "created photo succesfully");
  }

  @Test
  void shouldRetrievePhoto() {
    PhotoDBService pdbSrvc = new PhotoDBService();

    UUID id = UUID.randomUUID();
    PhotoModel photo = new PhotoModel(id, "a southern brazilian landscape");

    pdbSrvc.create(photo);

    ResultModel res = pdbSrvc.getById(id);
    assertEquals(res.level(), "INFO");

    PhotoModel retrievedPhoto = (PhotoModel)res.data();
    assertEquals(photo, retrievedPhoto);
  }
}
