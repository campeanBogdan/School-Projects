package Presentation.PresentationImplementation;

import javax.swing.*;

public class AccountGUI extends JFrame {
    private JButton saveAccountButton;
    private JPanel panel1;
    private JButton deleteAccountButton;
    private JButton updateAccountButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton exitButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton viewAllAccountsButton;
    private JTextField textField7;
    private JButton transferButton;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;

    public AccountGUI() {
        this.add(panel1);
        this.setSize(700, 600);
    }

    public void setVisible() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JButton getSaveAccountButton() { return this.saveAccountButton; }
    public JButton getDeleteAccountButton() { return this.deleteAccountButton; }
    public JButton getUpdateAccountButton() { return this.updateAccountButton; }
    public JButton getTransferButton() { return this.transferButton; }
    public JButton getViewAllAccountsButton() { return this.viewAllAccountsButton; }
    public JButton getExitButton() { return this.exitButton; }

// save account
    public String getTextField1() { return this.textField1.getText(); }
    public String getTextField2() { return this.textField2.getText(); }
    public String getTextField3() { return this.textField3.getText(); }

// delete account
    public String getTextField5() { return this.textField5.getText(); }

// update account
    public String getTextField6() { return this.textField6.getText(); }
    public String getTextField7() { return this.textField7.getText(); }

// transfer account
    public String getTextField8() { return this.textField8.getText(); }
    public String getTextField9() { return this.textField9.getText(); }
    public String getTextField10() { return this.textField10.getText(); }

}
