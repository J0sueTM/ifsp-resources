package com.j0suetm.ums;

import com.formdev.flatlaf.FlatLightLaf;
import com.j0suetm.ums.views.UserManagerView;
import com.j0suetm.ums.views.View;

public class Ums {
  public static void main(String[] args) {
    FlatLightLaf.setup();

    UserManagerView umv = new UserManagerView();
    View baseView = new View(
      "J0sueTM's User Management System",
      600,
      400
    );

    baseView.setCurrentPanel(umv);
    baseView.render();
  }
}
