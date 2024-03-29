package com.j0suetm.jgallery;

import com.j0suetm.jgallery.components.PropsLoader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.j0suetm.jgallery.components.Migrator;
import com.j0suetm.jgallery.components.BucketConnector;
import com.j0suetm.jgallery.components.DBConnector;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
  extends Application
{
  private static final Logger logger =
    Logger.getLogger(Main.class.getName());

  private static Stage globalStage;

  public static void main(String[] args) {
    setupComponents();
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    globalStage = stage;

    boolean isSuccess = switchScene("photo-list");
    if (!isSuccess) {
      System.exit(1);;
    }

    stage.setTitle("Jgallery");
    stage.show();
  }

  public static Stage getGlobalStage() {
    return globalStage;
  }

  public static boolean switchScene(String sceneName) {
    String rsrcPath;
    switch (sceneName) {
      case "photo-upload":
        rsrcPath = "views/photo-upload.fxml";
        break;
      case "photo-list":
      default:
        rsrcPath = "views/photo-list.fxml";
        break;
    }

    URL viewURL = Main.class
      .getClassLoader()
      .getResource(rsrcPath);
    Parent root;
    try {
      root = FXMLLoader.load(viewURL);
    } catch (Exception ex) {
      logger.log(
        Level.WARNING,
        "failed to switch scene"
      );

      ex.printStackTrace();

      return false;
    }

    Scene scene = new Scene(root, 600, 450);
    globalStage.setScene(scene);

    return true;
  }

  private static void setupComponents() {
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
  }
}
