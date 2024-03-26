package com.j0suetm.jgallery;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class WebApp
  extends JFrame
{
  private EngineOptions engOpts;
  private Engine engine;
  private Browser browser;
  private BrowserView browserView;

  public WebApp() {
    SwingUtilities.invokeLater(() -> {
      this.setTitle("JGallery");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(800, 600);

      this.engOpts = EngineOptions
        .newBuilder()
        .build();

      this.engine = Engine.newInstance(this.engOpts);
      this.browser = this.engine.newBrowser();

      this.browserView = BrowserView.newInstance(this.browser);

      this.browser
        .navigation()
        .loadUrl(
          WebApp.class
          .getClassLoader()
          .getResource("public/index.html")
          .toString()
        );

      this.getContentPane().add(
        this.browserView,
        BorderLayout.CENTER
      );

      this.setVisible(true);
    });
  }
}
