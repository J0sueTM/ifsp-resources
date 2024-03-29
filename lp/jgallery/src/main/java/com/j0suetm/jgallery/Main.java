package com.j0suetm.jgallery;

import com.j0suetm.jgallery.components.PropsLoader;

import software.amazon.awssdk.services.s3.S3Client;

import com.j0suetm.jgallery.components.Migrator;
import com.j0suetm.jgallery.components.BucketConnector;
import com.j0suetm.jgallery.components.DBConnector;

import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    Properties props = PropsLoader.load(
      "application.prod.properties"
    );
    if (props == null) {
      return;
    }

    Migrator.migrate(
      props.getProperty("db.url"),
      props.getProperty("db.username"),
      props.getProperty("db.password")
    );

    DBConnector.connect(
      props.getProperty("db.url"),
      props.getProperty("db.username"),
      props.getProperty("db.password")
    );

    BucketConnector s3Connr = new BucketConnector(
      props.getProperty("s3.bucketName")
    );

    BucketConnector.connect(
      props.getProperty("s3.endpoint"),
      props.getProperty("s3.accessKey"),
      props.getProperty("s3.secretKey"),
      props.getProperty("s3.region")
    );

    s3Connr.setup();

    S3Client s3c = BucketConnector.getClient();
    if (s3c == null) {
      return;
    }

    s3c.close();
  }
}
