package View.Presentation.Admin;

import Model.Model.Book;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class AdminBookGUI extends JFrame implements Observer {
    private JPanel panel1;
    private JTextField textField1;
    private JButton insertBookButton;
    private JButton deleteBookButton;
    private JButton updateBookButton;
    private JButton insertUserButton;
    private JButton deleteUserButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JCheckBox checkBox1;
    private JButton exportReportButton;
    private JComboBox comboBox1;


    public AdminBookGUI() {
        this.add(panel1);
        this.setSize(new Dimension(900, 300));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        comboBox1.addItem("PDF");
        comboBox1.addItem("TXT");
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    public boolean getCheckBox1() {
        return checkBox1.isSelected();
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Insert Book"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Delete Book"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Update Book"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Insert User"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Delete User"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Login User"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Login Admin"))
            JOptionPane.showMessageDialog(null, arg, "Observer:AdminGUI", JOptionPane.INFORMATION_MESSAGE);

        if (arg.equals("String null"))
            JOptionPane.showMessageDialog(null, arg, "Observer: AdminGUI", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getComboValue() {
        Object item = comboBox1.getSelectedItem();
        return (String) item;
    }

    // listeners
    public void addExportReportListener(ActionListener listener) { this.exportReportButton.addActionListener(listener);}

    public void addInsertActionListener(ActionListener listener) {
        this.insertBookButton.addActionListener(listener);
    }

    public void addDeleteActionListener(ActionListener listener) {
        this.deleteBookButton.addActionListener(listener);
    }

    public void addUpdateActionListener(ActionListener listener) {
        this.updateBookButton.addActionListener(listener);
    }

    public void addInsertUserActionListener(ActionListener listener) {
        this.insertUserButton.addActionListener(listener);
    }

    public void addDeleteUserActionListener(ActionListener listener) {
        this.deleteUserButton.addActionListener(listener);
    }

    public JButton getInsertUserButton() {
        return insertUserButton;
    }

    public void setInsertUserButton(JButton insertUserButton) {
        this.insertUserButton = insertUserButton;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public void setDeleteUserButton(JButton deleteUserButton) {
        this.deleteUserButton = deleteUserButton;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public void setTextField4(JTextField textField4) {
        this.textField4 = textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public void setTextField5(JTextField textField5) {
        this.textField5 = textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public void setTextField6(JTextField textField6) {
        this.textField6 = textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public void setTextField7(JTextField textField7) {
        this.textField7 = textField7;
    }

    public JTextField getTextField8() {
        return textField8;
    }

    public void setTextField8(JTextField textField8) {
        this.textField8 = textField8;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getInsertBookButton() {
        return insertBookButton;
    }

    public void setInsertBookButton(JButton insertBookButton) {
        this.insertBookButton = insertBookButton;
    }

    public JButton getDeleteBookButton() {
        return deleteBookButton;
    }

    public void setDeleteBookButton(JButton deleteBookButton) {
        this.deleteBookButton = deleteBookButton;
    }

    public JButton getUpdateBookButton() {
        return updateBookButton;
    }

    public void setUpdateBookButton(JButton updateBookButton) {
        this.updateBookButton = updateBookButton;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
