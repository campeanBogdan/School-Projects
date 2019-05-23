package Presentation.PresentationImplementation;

import javax.swing.*;

public class AdminGUI extends JFrame {


    private JPanel panel1;
    private JButton saveUserButton;
    private JButton updateUserButton;
    private JButton deleteUserButton;
    private JButton viewAllUsersButton;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox isAdminSaveCheckBox;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox isAdminUpdateCheckBox;
    private JTextField textField5;
    private JButton exitButton;
    private JTextField textField6;
    private JTextField textField7;
    private JButton viewActivitiesButton;


    public AdminGUI() {
        this.add(panel1);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setVisible() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void close() {
        this.dispose();
    }

// save user
    public String getUsername1() { return this.textField1.getText(); }
    public String getPassword1() { return this.textField2.getText(); }
    public boolean getIsAdmin1() { return this.isAdminSaveCheckBox.isSelected(); }

// update user
    public String getUsername2() { return this.textField3.getText(); }
    public boolean getIsAdmin2() { return this.isAdminUpdateCheckBox.isSelected(); }
    public String getUsername3() { return this.textField6.getText(); }
    public String getPassword3() { return this.textField7.getText(); }

// delete user
    public String getUsername5() { return this.textField5.getText(); }

    public JButton getViewActivitiesButton() { return this.viewActivitiesButton; }

    public JButton getExitButton() { return this.exitButton; }

    public JButton getUpdateUserButton() { return updateUserButton; }

    public JButton getDeleteUserButton() { return deleteUserButton; }

    public JButton getSaveUserButton() { return this.saveUserButton; }

    public JButton getViewAllUsersButton() { return viewAllUsersButton; }
}
