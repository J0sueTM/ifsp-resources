package com.j0suetm.jgallery.controllers;

import java.awt.image.BufferedImage;
import java.util.List;

import com.j0suetm.jgallery.Main;
import com.j0suetm.jgallery.models.PhotoModel;
import com.j0suetm.jgallery.models.ResultModel;
import com.j0suetm.jgallery.services.bucket.PhotoBucketService;
import com.j0suetm.jgallery.services.db.PhotoDBService;
import com.j0suetm.jgallery.utils.ImageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class PhotoListController {
  private final PhotoBucketService pbSrvc = new PhotoBucketService();
  private final PhotoDBService pdbSrvc = new PhotoDBService();

  @FXML
  private Pagination pagination;

  public void initialize() {
    ResultModel res = pdbSrvc.count();
    if (res.level() != "INFO") {
      new Alert(
        AlertType.ERROR,
        res.message(),
        ButtonType.OK
      ).showAndWait();

      return;
    }
    int photoCount = (int)res.data();
    int pageCount = photoCount / 3;

    this.pagination.setPageCount(pageCount);
    this.pagination.setPageFactory(new Callback<Integer,Node>() {
      @Override
      public Node call(Integer pageIndex) {
        return createPhotoListPage(pageIndex);
      }
    });
  }

  private HBox createPhotoListPage(int pageIndex) {
    HBox root = new HBox(10);

    ResultModel res = pdbSrvc.getAll(3, pageIndex * 3);
    if (res.level() != "INFO") {
      new Alert(
        AlertType.ERROR,
        res.message(),
        ButtonType.OK
      ).showAndWait();

      return root;
    }

    @SuppressWarnings("unchecked")
    List<PhotoModel> photos = (List<PhotoModel>)res.data();
    for (PhotoModel photo : photos) {
      VBox card = new VBox();
      card.getChildren().add(new Text(photo.description()));

      res = pbSrvc.get(photo.id().toString());
      if (res.level() != "INFO") {
        new Alert(
          AlertType.ERROR,
          res.message(),
          ButtonType.OK
        ).showAndWait();

        return root;
      }

      BufferedImage imgBuf = (BufferedImage)res.data();
      ImageView imgView = new ImageView(
        ImageUtils.imgBufToImg(imgBuf)
      );
      imgView.setFitWidth(150);
      imgView.setFitHeight(250);
      card.getChildren().add(imgView);

      root.getChildren().add(card);
    }

    return root;
  }

  @FXML
  protected void goToUpload(ActionEvent evt) {
    Main.switchScene("photo-upload");
  } 

  @FXML
  protected void exit(ActionEvent evt) {
    Main.getGlobalStage().close();
  }
}
