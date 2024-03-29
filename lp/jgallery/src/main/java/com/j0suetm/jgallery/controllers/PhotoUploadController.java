package com.j0suetm.jgallery.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.j0suetm.jgallery.Main;
import com.j0suetm.jgallery.models.PhotoModel;
import com.j0suetm.jgallery.models.ResultModel;
import com.j0suetm.jgallery.services.bucket.PhotoBucketService;
import com.j0suetm.jgallery.services.db.PhotoDBService;
import com.j0suetm.jgallery.utils.ImageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PhotoUploadController {
  private final FileChooser fc = new FileChooser();
  private BufferedImage photoBuf;

  @FXML
  private ImageView thumbnail;

  @FXML
  private TextArea description;

  public void initialize() {
    this.fc.setTitle("Select photo to upload");
  }

  @FXML
  protected void loadPhotoFile(ActionEvent evt) {
    File file = this.fc.showOpenDialog(
      Main.getGlobalStage()
    );
    if (file == null) {
      Alert alert = new Alert(
        AlertType.ERROR,
        "failed to load photo -- null",
        ButtonType.OK
      );
      alert.showAndWait();

      return;
    }

    try {
      this.photoBuf = ImageIO.read(file);
      this.thumbnail.setImage(
        ImageUtils.imgBufToImg(photoBuf)
      );
    } catch (Exception ex) {
      ex.printStackTrace();

      Alert alert = new Alert(
        AlertType.ERROR,
        "failed to load photo",
        ButtonType.OK
      );
      alert.showAndWait();
    }
  } 

  @FXML
  protected void uploadPhoto(ActionEvent evt) {
    UUID photoID = UUID.randomUUID();

    PhotoBucketService pbSrvc = new PhotoBucketService();
    ResultModel res = pbSrvc.put(this.photoBuf, photoID.toString());
    if (res.level() != "INFO") {
      new Alert(
        AlertType.ERROR,
        res.message(),
        ButtonType.OK
      ).showAndWait();

      return;
    }

    PhotoDBService pdbSrvc = new PhotoDBService();
    res = pdbSrvc.create(
      new PhotoModel(
        photoID,
        this.description.getText()
      )
    );
    if (res.level() != "INFO") {
      new Alert(
        AlertType.ERROR,
        res.message(),
        ButtonType.OK
      ).showAndWait();

      return;
    }

    new Alert(
      AlertType.INFORMATION,
      res.message(),
      ButtonType.OK
    ).showAndWait();

    Main.switchScene("photo-list");
  }

  @FXML
  protected void goToPhotoList(ActionEvent evt) {
    Main.switchScene("photo-list");
  }
}
