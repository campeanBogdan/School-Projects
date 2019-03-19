import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Presentation extends JFrame {
	
	private static final long serialVersionUID = 1L;
//	private JFrame frame = new JFrame("Banca/Client");
	private JPanel cards;
    private JButton button1, button2;
    private JButton[] btn = new JButton[10];
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table = new JTable(model);
    private JTextField[] textField = new JTextField[10];
    private String[] str = {"Saving Account", "Spending Account"};
    private JComboBox<Object> cb = new JComboBox<Object>(str);

    
    	public Presentation() {

    		setLayout(new FlowLayout());
    		setSize(1000,600);
    		setTitle("Client/Banca");
    		setResizable(false);

    		button1 = new JButton("<html><b>Client");
    		button2 = new JButton("<html><b>Banca");
    		
    		button1.setForeground(Color.RED);
    		button2.setForeground(Color.BLUE);
        
        
    		JPanel card1 = new JPanel();
    		JPanel card2 = new JPanel();
    		
    		
    		card1.setPreferredSize(new Dimension(600, 500));
    		card2.setPreferredSize(new Dimension(750, 500));
    		
    		card1.add(button1);
    		card2.add(button2);
    		
    		JScrollPane scrollPane = new JScrollPane(table);
    		JLabel[] label = new JLabel[10];
    		
    		table.setFillsViewportHeight(true);
    		
    		for (int i = 0; i < 2; i++) {
    			btn[i] = new JButton();
    			label[i] = new JLabel();
    			textField[i] = new JTextField();
    			textField[i].setColumns(10);
    		}
    		
    		label[0].setText("Nume: ");
    		label[1].setText("Suma: ");
    		label[2] = new JLabel("Nr. Cont: ");
    		textField[2] = new JTextField();
    		textField[2].setColumns(10);
    		
    	// nume
    		card1.add(label[0]);
    		card1.add(textField[0]);
    		card1.add(label[1]);
    		card1.add(textField[1]);
    		card1.add(label[2]);
    		card1.add(textField[2]);
    		card1.add(btn[0]);
    		card1.add(btn[1]);
    		
    		for (int i = 2; i < 6; i++) {
    			btn[i] = new JButton();
    		}
    		
//    		card1.add(scrollPane, new BorderLayout());
//    		card2.add(scrollPane);
    		
    		
    	// client
    		btn[0].setText("Adaugati Suma");
    		btn[1].setText("Retragere Numerar");
    		
    	// banca
    		btn[2].setText("Deschidere Cont");
    		btn[3].setText("Stergere Cont");
    		btn[4].setText("Adaugati Client");
    		btn[5].setText("Stergeti Client");
    		
    		
    		for (int i = 3; i < 6; i++) {
    			textField[i] = new JTextField();
    			textField[i].setColumns(10);
    			label[i] = new JLabel();
    		}
    		
    		textField[6] = new JTextField();
    		textField[6].setColumns(5);
    		label[6] = new JLabel("ID: ");
    		
    		label[3].setText("Nume: ");
    		label[4].setText("Suma: ");
    		label[5].setText("Nr. Cont: ");
    		
    		card2.add(label[3]);
    		card2.add(textField[3]);
    		card2.add(label[4]);
    		card2.add(textField[4]);
    		card2.add(label[5]);
    		card2.add(textField[5]);
    		card2.add(label[6]);
    		card2.add(textField[6]);
    		
    		
    		card2.add(cb);
    		card2.add(btn[3]);
    		card2.add(btn[4]);
    		card2.add(btn[5]);
    		card2.add(btn[2]);
    		card2.add(scrollPane);

        
    		cards = new JPanel(new CardLayout());
        
    		CardLayout cl = (CardLayout) cards.getLayout();
    		cl.show(cards, "D");

    		cards.add(card1);
    		cards.add(card2);
        

    	// schimb ferestrele
    		button1.addActionListener(e-> {
    			cl.next(cards);
    		});
        
    		button2.addActionListener(e-> {
    			cl.next(cards);
    		});
        
        
    		getContentPane().add(cards); 
    		setVisible(true);
    	}
    	
    	
    	private Integer getNumberOfAccounts(HashMap<Person, LinkedList<Account>> map) {
    		Integer counter = new Integer(0);
    		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
    		
    		while (hashMapIterator.hasNext()) {
    			Map.Entry pair = (Map.Entry)hashMapIterator.next();
    			
    			Person person = (Person)pair.getKey();
    			LinkedList<Account> personAccounts = (LinkedList<Account>) pair.getValue();
    			
    			Iterator<Account> accountIterator = personAccounts.iterator();
    			
    			if (personAccounts.isEmpty()) counter++;
    			
    			while (accountIterator.hasNext()) {
    				Account account = accountIterator.next();
    				counter++;
    			}
    		}
    		
    		return counter;
    	}
    	
    	
    	public void createTable(HashMap<Person, LinkedList<Account>> hashMap) {
    		String[] col = {"ID", "Nume", "Nr. Cont", "Tip Cont", "Suma"};
    		Object[][] data = new Object[this.getNumberOfAccounts(hashMap)][5];
    		int i = 0;
    		
    		
    		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = hashMap.entrySet().iterator();
    		
    		while (hashMapIterator.hasNext()) {
    			Map.Entry pair = (Map.Entry)hashMapIterator.next();
    			
    			Person person = (Person)pair.getKey();
    			LinkedList<Account> personAccounts = (LinkedList<Account>) pair.getValue();
    			
    			Iterator<Account> accountIterator = personAccounts.iterator();
    			
    			data[i][0] = person.getId();
    			data[i][1] = person.getName();
    			
    			if (personAccounts.isEmpty()) i++;
    			
    			while (accountIterator.hasNext()) {
    				Account account = accountIterator.next();
    				data[i][2] = account.getId();
    				
    				if (account instanceof SavingAccount) data[i][3] = "Saving Account";
    				if (account instanceof SpendingAccount) data[i][3] = "Spending Account";
    				
    				data[i][4] = account.getMoney();
    				i++;
    			}
    		}
    		
    		model.setDataVector(data, col);
    		
    		
    		table.getColumnModel().getColumn(0).setPreferredWidth(1);
    		table.getColumnModel().getColumn(2).setPreferredWidth(5);
    		table.getColumnModel().getColumn(4).setPreferredWidth(5);
    }
    	
    public Integer getComboBox() {
    	return this.cb.getSelectedIndex();
    }
    	
    public void resetText() {
    	for (int i = 0; i < 7; i++)
    		textField[i].setText("");
    }
    	
    	
    public JButton getBtn0() {
    	return this.btn[0];
    }
    
    public JButton getBtn1() {
    	return this.btn[1];
    }
    
    public JButton getBtn2() {
    	return this.btn[2];
    }
    
    public JButton getBtn3() {
    	return this.btn[3];
    }
    
    public JButton getBtn4() {
    	return this.btn[4];
    }
    
    public JButton getBtn5() {
    	return this.btn[5];
    }
    
    public JButton getBtn6() {
    	return this.btn[6];
    }
    
    public JTextField getTextField0() {
    	return this.textField[0];
    }
    
    public JTextField getTextField1() {
    	return this.textField[1];
    }
    
    public JTextField getTextField2() {
    	return this.textField[2];
    }
    
    public JTextField getTextField3() {
    	return this.textField[3];
    }
    
    public JTextField getTextField4() {
    	return this.textField[4];
    }
    
    public JTextField getTextField5() {
    	return this.textField[5];
    }
    
    public JTextField getTextField6() {
    	return this.textField[6];
    }
}
