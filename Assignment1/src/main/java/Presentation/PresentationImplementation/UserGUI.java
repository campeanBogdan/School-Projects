package Presentation.PresentationImplementation;

import javax.swing.*;

public class UserGUI extends JFrame {


    private JPanel panel1;
    private JButton saveClientButton;
    private JButton updateClientButton;
    private JButton deleteClientButton;
    private JButton viewAllClientsButton;
    private JButton goToAccountsButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton exitButton;


    public UserGUI() {
        this.add(panel1);
        this.setSize(600, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setVisible() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JButton getSaveClientButton() {
        return saveClientButton;
    }

    public JButton getUpdateClientButton() {
        return updateClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getViewAllClientsButton() {
        return viewAllClientsButton;
    }

    public JButton getGoToAccountsButton() {
        return goToAccountsButton;
    }

    public JButton getExitButton() { return exitButton; }

// Save button
    public String getTextField1() { return this.textField1.getText(); }

    public String getTextField2() {
        return textField2.getText();
    }

    public String getTextField3() {
        return textField3.getText();
    }

    public String getTextField4() {
        return textField4.getText();
    }

    public String getTextField5() {
        return textField5.getText();
    }

// Update button
    public String getTextField6() { return this.textField6.getText(); }
    public String getTextField7() { return this.textField7.getText(); }
    public String getTextField8() { return this.textField8.getText(); }
    public String getTextField9() { return this.textField9.getText(); }
    public String getTextField10() { return this.textField10.getText(); }

// Delete button
    public String getTextField11() { return this.textField11.getText(); }

// Exit button
}
