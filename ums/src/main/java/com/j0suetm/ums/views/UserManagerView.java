package com.j0suetm.ums.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class UserManagerView
  extends JPanel
{
  private JTextField nameFld;
  private JPasswordField passFld;
  private JButton submitBtn;

  public UserManagerView() {
    this.setLayout(new GridLayout(4, 2));

    this.nameFld = new JTextField();
    this.add(new JLabel("Name:"));
    this.add(this.nameFld);

    this.passFld = new JPasswordField();
    this.add(new JLabel("Password:"));
    this.add(this.passFld);

    this.submitBtn = new JButton("Create User");
    this.add(this.submitBtn);
  }
}
