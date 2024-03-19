package com.j0suetm.ums;

import com.formdev.flatlaf.FlatLightLaf;
import com.j0suetm.ums.views.UserManagerView;
import com.j0suetm.ums.views.View;

public class Ums {
  public static View baseView = new View(
    "J0sueTM's User Management System",
    600, 400
  );

  public static void main(String[] args) {
    FlatLightLaf.setup();

    setView("user-list");
    baseView.render()
  }

  public static void setPanel(String viewName) {
    JPanel newPanel;
    switch (viewName) {
    case "user-creation"
      newPanel = new UserCreationView();

      break;
    case "user-list":
    default:
      newPanel = new UserManagerView();
      
      break;
    }

    baseView.setCurrentPanel(newView);
  }
}
