package com.j0suetm.jgallery;

import javafx.beans.value.ChangeListener;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import static javafx.concurrent.Worker.State;

public class WebApp
  extends Application
{
  @Override
  public void start(final Stage stage) {
    WebView view = new WebView();
    final WebEngine engine = view.getEngine();

    engine.load("localhost:3345");
    engine
      .getLoadWorker()
      .stateProperty()
      .addListener(new ChangeListener<State>() {
        public void changed(
          ObservableValue<? extends State> ov,
          State oldState,
          State newState
        ) {
          if (newState != State.SUCCEEDED) {
            return;
          }

          stage.setTitle(engine.getTitle());
        }
      }
    );

    VBox root = new VBox();
    root.getChildren().add(view);

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public static void start() {
    Application.launch();
  }
}
