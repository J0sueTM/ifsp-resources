package com.j0suetm.jgallery.test.services.bucket;

import com.j0suetm.jgallery.components.BucketConnector;
import com.j0suetm.jgallery.services.bucket.PhotoBucketService;
import com.j0suetm.jgallery.models.ResultModel;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.UUID;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PhotoBucketServiceTest {
  static LocalStackContainer localstack =
    new LocalStackContainer(
      DockerImageName.parse("localstack/localstack:latest")
    ).withServices(Service.S3);
  static String currentBucketName;

  @BeforeAll
  static void beforeAll() {
    localstack.start();
  }

  @AfterAll
  static void afterAll() {
    localstack.stop();
  }

  @BeforeEach
  void setUp() {
    currentBucketName = "jgallery-" + UUID.randomUUID().toString();
    BucketConnector s3Connr = new BucketConnector(currentBucketName);
    BucketConnector.connect(
      localstack.getEndpoint().toString(),
      localstack.getAccessKey(),
      localstack.getSecretKey(),
      localstack.getRegion()
    );

    s3Connr.setup();
  }

  private BufferedImage readSampleImage() {
    BufferedImage imgBuf;
    try {
      imgBuf = ImageIO.read(
        PhotoBucketServiceTest.class.getResourceAsStream(
          "/images/sample-image.jpg"
        )
      );
    } catch (IOException ex) {
      ex.printStackTrace();
      
      return null;
    }

    return imgBuf;
  }

  @Test
  void shouldUploadPhoto() {
    BufferedImage imgBuf = readSampleImage();
    if (imgBuf == null) {
      return;
    }
    
    PhotoBucketService pbSrvc = new PhotoBucketService();

    String imgKey = UUID.randomUUID().toString() + ".jpg";
    ResultModel res = pbSrvc.put(imgBuf, imgKey);
    assertEquals(res.level(), "INFO");

    String uploadedImgKey = (String)res.data();
    assertEquals(uploadedImgKey, imgKey);
  }

  @Test
  void shouldGetPhoto() {
    BufferedImage imgBuf = readSampleImage();
    if (imgBuf == null) {
      return;
    }

    PhotoBucketService pbSrvc = new PhotoBucketService();
    String imgKey = UUID.randomUUID().toString() + ".jpg";
    pbSrvc.put(imgBuf, imgKey);

    ResultModel res = pbSrvc.get(imgKey);
    assertEquals(res.level(), "INFO");

    BufferedImage retrievedImg = (BufferedImage)res.data();
    assertNotNull(retrievedImg);
  }
}
