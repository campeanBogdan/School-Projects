package View;

import Model.Model.Book;
import Model.Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TableClass extends JFrame {
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JTable table1;
    private JTable table2;
    private JFrame frame= new JFrame("Books List");
    private JFrame frame2= new JFrame("Books List");
    private DefaultTableModel dtm;
    private static int j = 0;
    private static int jj = 0;

    // user -> client table
    private JTable userClientTable;
    private JFrame userClientFrame;
    private JScrollPane userClientScroll;
    private JPanel userClientPanel;

    // user -> books table
    private JTable userBookTable;
    private JFrame userBookFrame;
    private JScrollPane userBookScroll;
    private JPanel userBookPanel;


    // client table
    public void initializeUserClientTable() {
        userClientTable = new JTable();
        userClientPanel = new JPanel();
        userClientFrame = new JFrame("Clients list");
        userClientScroll = new JScrollPane(userClientTable);
        userClientPanel.add(userClientScroll);
        userClientFrame.add(userClientPanel);
        userClientFrame.setSize(700, 700);
        userClientFrame.setLocation(new Point(100, 50));
        userClientFrame.setVisible(true);
    }

    // book table
    public void initializeUserBookTable() {
        userBookTable = new JTable();
        userBookPanel = new JPanel();
        userBookFrame = new JFrame("Books list");
        userBookScroll = new JScrollPane(userBookTable);
        userBookPanel.add(userBookScroll);
        userBookFrame.add(userBookPanel);
        userBookFrame.setSize(700, 700);
        userBookFrame.setLocation(new Point(1200, 50));
        userBookFrame.setVisible(true);
    }

    public void displayClients(List<Client> clients) {
        DefaultTableModel tableModel = (DefaultTableModel) userClientTable.getModel();
        String[] cols = {"FirstName", "LastName", "Address"};
        String[][] data = new String[clients.size()][3];
        for (int i = 0; i < clients.size(); i++) {
            String o1 = clients.get(i).getFirstName();
            String o2 = clients.get(i).getLastName();
            String o3 = clients.get(i).getAddress();
            data[i][0] = o1;
            data[i][1] = o2;
            data[i][2] = o3;
        }
        tableModel.setDataVector(data, cols);
        tableModel.setColumnIdentifiers(cols);
        tableModel.fireTableStructureChanged();
    }

    public void displayBooks(List<Book> books) {
        DefaultTableModel tableModel = (DefaultTableModel) userBookTable.getModel();
        String[] cols = {"Title", "Genre", "Author Name", "Stock"};
        String[][] data = new String[books.size()][4];
        for (int i = 0; i < books.size(); i++) {
            String o1 = books.get(i).getTitle();
            String o2 = books.get(i).getGenre();
            String o3 = books.get(i).getAuthorName();
            String o4 = books.get(i).getStock().toString();
            data[i][0] = o1;
            data[i][1] = o2;
            data[i][2] = o3;
            data[i][3] = o4;
        }

        tableModel.setDataVector(data, cols);
        tableModel.setColumnIdentifiers(cols);
        tableModel.fireTableStructureChanged();
    }


    public void displayBooksFromClient(List<Book> books) {
        JFrame frame2 = new JFrame();
        JPanel panel2 = new JPanel();
        frame2.add(panel2);
        frame2.setSize(700, 700);
        frame2.setLocationRelativeTo(frame2);
        frame2.setVisible(true);

        String[] cols = {"Title", "Genre", "Author", "Stock"};
        String[][] data = new String[books.size()][4];

        for (int i = 0; i < books.size(); i++) {
            String o1 = books.get(i).getTitle();
            String o2 = books.get(i).getGenre();
            String o3 = books.get(i).getAuthorName();
            String o4 = books.get(i).getStock().toString();
            data[i][0] = o1;
            data[i][1] = o2;
            data[i][2] = o3;
            data[i][3] = o4;
        }

        JTable table2 = new JTable(data, cols);
        DefaultTableModel dtm = new DefaultTableModel(data, cols);

        JScrollPane scrollPane1 = new JScrollPane(table2);
        scrollPane1.setPreferredSize(new Dimension(650, 650));
        panel2.add(scrollPane1);
    }


    public JTable getUserClientTable() {
        return this.userClientTable;
    }

    public JTable getUserBookTable() {
        return this.userBookTable;
    }


    public String[] getSelectedRowBook() {
        String[] rowValue = new String[4];
        int row = userBookTable.getSelectedRow();
        rowValue[0] = userBookTable.getValueAt(row, 0).toString();
        rowValue[1] = userBookTable.getValueAt(row, 1).toString();
        rowValue[2] = userBookTable.getValueAt(row, 2).toString();
        return rowValue;
    }

    public String[] getSelectedRowClient() {
        String[] rowValue = new String[4];
        int row = userClientTable.getSelectedRow();
        rowValue[0] = userClientTable.getValueAt(row, 0).toString();
        rowValue[1] = userClientTable.getValueAt(row, 1).toString();
        rowValue[2] = userClientTable.getValueAt(row, 2).toString();
        return rowValue;
    }
}
