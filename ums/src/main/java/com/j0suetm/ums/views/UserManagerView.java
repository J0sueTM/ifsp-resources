package com.j0suetm.ums.views;

import com.j0suetm.ums.views.components.UserListComponent;
import com.j0suetm.ums.models.UserModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UserManagerView
  extends JPanel
{
  private UserListComponent ulCmp;

  private JPanel headerPnl;
  private JLabel userCountLbl;
  private JButton newUserBtn;

  public UserManagerView() {
    BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(bl);

    this.headerPnl = new JPanel(new FlowLayout());

    this.userCountLbl = new JLabel("2 users");
    this.newUserBtn = new JButton("Register new user");

    this.headerPnl.add(this.userCountLbl);
    this.headerPnl.add(this.newUserBtn);

    this.add(this.headerPnl);

    UserModel[] users = new UserModel[4];
    users[0] = new UserModel();
    users[0].name = "josue teodoro";
    users[0].cpf = "467.795.808-48";
    users[1] = new UserModel();
    users[1].name = "gustavo bizo";
    users[1].cpf = "467.795.808-58";
    users[2] = new UserModel();
    users[2].name = "fabrincio cingarro";
    users[2].cpf = "467.795.808-58";
    users[3] = new UserModel();
    users[3].name = "guto cabeca de prego";
    users[3].cpf = "467.795.808-58";
    
    this.ulCmp = new UserListComponent(users);
    this.add(this.ulCmp);
  }
}
