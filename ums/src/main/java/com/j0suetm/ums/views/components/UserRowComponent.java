package com.j0suetm.ums.views.components;

import com.j0suetm.ums.models.UserModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UserRowComponent
  extends JPanel
{
  private JPanel infoPnl;
  private JPanel actionsPnl;

  private JLabel aboutLbl;

  private JButton deleteBtn;
  private JButton updateBtn;

  public UserRowComponent(UserModel user) {
    this.setLayout(new FlowLayout());
    this.setPreferredSize(new Dimension(300, 80));
    this.setMaximumSize(new Dimension(300, 100));

    this.infoPnl = new JPanel(new GridLayout(2, 1));

    this.aboutLbl = new JLabel(user.name + " - " + user.cpf);

    this.infoPnl.add(this.aboutLbl);

    this.actionsPnl = new JPanel(new FlowLayout());

    this.deleteBtn = new JButton("delete");
    this.updateBtn = new JButton("update");

    this.actionsPnl.add(this.deleteBtn);
    this.actionsPnl.add(this.updateBtn);

    this.infoPnl.add(this.actionsPnl);

    this.add(this.infoPnl);
  }
}
