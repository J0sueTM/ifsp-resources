package com.j0suetm.ums.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class View
  extends JFrame
{
  protected JPanel currentPanel;

  public View(String title, int width, int height) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    this.setTitle(title);
    this.setSize(width, height);
  }

  public void setCurrentPanel(JPanel newPanel) {
    boolean shouldAdd = this.currentPanel == null;

    this.currentPanel = newPanel;
    if (shouldAdd) {
      this.add(this.currentPanel);
    }

    revalidate();
    repaint();
  }

  public void render() {
    SwingUtilities.invokeLater(() -> {
      this.setVisible(true);
    });
  }
}
