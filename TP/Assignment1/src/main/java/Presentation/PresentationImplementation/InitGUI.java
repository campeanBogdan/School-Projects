package Presentation.PresentationImplementation;

import javax.swing.*;

public class InitGUI extends JFrame {


    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton exitButton;

    public InitGUI() {
        this.add(panel1);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void close() {
        this.setVisible(false);
    }

    public JButton getLoginButton() { return this.button1; }

    public JButton getExitButton() { return this.exitButton; }

    public String getUsername() { return this.textField1.getText(); }

    public String getPassword() { return this.passwordField1.getText(); }

}
