package com.j0suetm.ums.views.components;

import com.j0suetm.ums.models.UserModel;
import com.j0suetm.ums.views.components.UserRowComponent;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class UserListComponent
  extends JPanel
{
  private JPanel userListPnl;
  private JScrollPane scroller;

  public UserListComponent(UserModel[] users) {
    this.userListPnl = new JPanel(new GridLayout(users.length, 1));
    for (UserModel user : users) {
      userListPnl.add(new UserRowComponent(user));
    }

    this.scroller = new JScrollPane(this.userListPnl);
    this.add(this.scroller);
  }
}
