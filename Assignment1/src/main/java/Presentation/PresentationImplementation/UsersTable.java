package Presentation.PresentationImplementation;

import Model.Account;
import Model.Activity;
import Model.Client;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsersTable extends JFrame {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTable table1;
    private JFrame frame;
    private DefaultTableModel dtm;


    public UsersTable() {
    }

    public void activities(List<Activity> activityList) {
        frame = new JFrame("Activity List");
        panel1 = new JPanel();
        frame.add(panel1);
        frame.setSize(1300, 750);
        frame.setLocationRelativeTo(frame);
        frame.setVisible(true);

        String[] cols = {"Actiune", "Data", "Ora"};
        String[][] data = new String[activityList.size()][3];

        for (int i = 0; i < activityList.size(); i++) {
            String o1 = activityList.get(i).getName();
            String o2 = activityList.get(i).getDate();
            String[] s = o2.split(" ");
            data[i][0] = o1;
            data[i][1] = s[0];
            data[i][2] = s[1];
        }

        table1 = new JTable(data, cols);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(1200, 600));
        panel1.add(scrollPane);
    }

    public void users(List<User> userList) {
        frame = new JFrame("Users List");
        panel1 = new JPanel();
        frame.add(panel1);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] cols = {"Username", "Password", "Admin"};
        String[][] data = new String[userList.size()][3];

        for (int i = 0; i < userList.size(); i++) {
            String o1 = userList.get(i).getUsername();
            String o2 = userList.get(i).getPassword();
            String o3;
            boolean admin = userList.get(i).isAdmin();
            if (admin)
                o3 = "1";
            else
                o3 = "0";
            data[i][0] = o1;
            data[i][1] = o2;
            data[i][2] = o3;
        }

        table1 = new JTable(data, cols);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(900, 600));
        panel1.add(scrollPane);
    }

    public void clients(List<Client> clientList) {
        frame = new JFrame("Clients List");
        panel1 = new JPanel();
        frame.add(panel1);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] cols = {"First Name", "Last Name", "Card Number", "CNP", "Address"};
        String[][] data = new String[clientList.size()][5];

        for (int i = 0; i < clientList.size(); i++) {
            String o1 = clientList.get(i).getFirstName();
            String o2 = clientList.get(i).getLastName();
            String o3 = clientList.get(i).getCardId();
            String o4 = clientList.get(i).getCNP();
            String o5 = clientList.get(i).getAddress();
            data[i][0] = o1;
            data[i][1] = o2;
            data[i][2] = o3;
            data[i][3] = o4;
            data[i][4] = o5;
        }

        table1 = new JTable(data, cols);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(1150, 600));
        panel1.add(scrollPane);
    }

    public void accounts(List<Account> accountList, List<Client> clientList) {
        frame = new JFrame("Accounts List");
        panel1 = new JPanel();
        frame.add(panel1);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] cols = {"Account Number", "Account Type", "Money", "Creation Date", "Client CNP"};
        String[][] data = new String[accountList.size()][5];

        for (int i = 0; i < accountList.size(); i++) {
            String o1 = accountList.get(i).getId().toString();
            String o2 = accountList.get(i).getType();
            String o3 = accountList.get(i).getMoney().toString();
            String o4 = accountList.get(i).getCreationDate();
            //String o5 = accountList.get(i).getClientId().toString();
            String o5 = null;

            for (int j = 0; j < clientList.size(); j++)
                if (clientList.get(j).getId().equals(accountList.get(i).getClientId()))
                    o5 = clientList.get(j).getCNP();

            data[i][0] = o1;
            data[i][1] = o2;
            data[i][2] = o3;
            data[i][3] = o4;
            data[i][4] = o5;
        }

        table1 = new JTable(data, cols);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(900, 600));
        panel1.add(scrollPane);
    }
}
