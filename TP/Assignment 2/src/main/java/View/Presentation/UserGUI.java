package View.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserGUI extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton insertClientButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton deleteClientButton;
    private JButton updateClientButton;
    private JTextField textField5;
    private JTextField textField6;
    private JButton buyBookButton;
    private JTextField textField7;
    private JButton deleteBookFromClientButton;


    public UserGUI() {
        this.add(panel1);
        this.setSize(new Dimension(900, 500));
        this.setLocation(1000, 1200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public JButton getDeleteBookFromClientButton() {
        return deleteBookFromClientButton;
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    public void addInsertClientActionListener(ActionListener listener) {
        this.insertClientButton.addActionListener(listener);
    }

    public void addDeleteClientActionListener(ActionListener listener) {
        this.deleteClientButton.addActionListener(listener);
    }

    public void addUpdateClientActionListener(ActionListener listener) {
        this.updateClientButton.addActionListener(listener);
    }

    public void addDeleteBookFromClientActionListener(ActionListener listener) {
        this.deleteBookFromClientButton.addActionListener(listener);
    }



    public void setClientText(String fullName) {
        this.textField6.setText(fullName);
    }

    public void setBookText(String title) {
        this.textField7.setText(title);
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getInsertClientButton() {
        return insertClientButton;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getUpdateClientButton() {
        return updateClientButton;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JButton getBuyBookButton() {
        return buyBookButton;
    }

    public JTextField getTextField7() {
        return textField7;
    }
}
