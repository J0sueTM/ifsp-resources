package com.j0suetm.jgallery;

import com.j0suetm.jgallery.components.PropsLoader;
import com.j0suetm.jgallery.components.Migrator;
import com.j0suetm.jgallery.components.BucketConnector;
import com.j0suetm.jgallery.components.DBConnector;
import com.j0suetm.jgallery.WebApp;

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

    WebApp.start();
  }
}
