package com.j0suetm.jgallery;

import com.j0suetm.jgallery.components.PropsLoader;
import com.j0suetm.jgallery.components.Migrator;
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
  }
}
