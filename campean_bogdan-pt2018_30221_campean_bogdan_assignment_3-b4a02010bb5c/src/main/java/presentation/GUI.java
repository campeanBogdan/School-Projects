package presentation;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import model.Product;
import model.Stock;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JFrame frame = new JFrame("Client/Depozit");
	private JFrame frame2 = new JFrame("Comanda");
	private JTextField[] textField = new JTextField[20];
	private JButton[] btn = new JButton[20];
	private Object[][] data = new Object[100][6];
	private String[] col = new String[6];
	private JPanel panel = new JPanel();
	JFrame fr = new JFrame();	    
    JTable table;
    JScrollPane scrollPane;
	
	
	protected void createClientFrame() {
		JLabel[] label = new JLabel[20];
		Font font = new Font("TimeNewRoman", Font.PLAIN, 20);
		
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setContentPane(panel);
		panel.setLayout(null);
		
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		
		
	//	client part
		
		for (int i = 0; i < 10; i++) {
			textField[i] = new JTextField();
			textField[i].setSize(150, 30);
			btn[i] = new JButton();
			btn[i].setSize(200, 50);
			label[i] = new JLabel();
			label[i].setFont(font);
			label[i].setSize(100, 50);
			panel.add(label[i]);
		}
		
		label[0].setLocation(900, 0);
		label[0].setText("<html><u><b>Client/Depozit");
		
		label[1].setLocation(50, 100);
		label[1].setText("<html><u>Pret:");
		
		label[2].setLocation(50, 200);
		label[2].setText("<html><u>Nume:");
		
		label[3].setLocation(350, 200);
		label[3].setText("<html><u>Adresa:");
		
		label[4].setLocation(700, 200);
		label[4].setText("<html><u>Numar telefon:");
		
		label[5].setLocation(500, 100);
		label[5].setText("<html><u>Nume");
		
		
		textField[0].setLocation(150, 110);
		panel.add(textField[0]);
		 
		textField[1].setLocation(150, 210);
		panel.add(textField[1]);
		
		textField[2].setLocation(450, 210);
		panel.add(textField[2]);
		
		textField[3].setLocation(800, 210);
		panel.add(textField[3]);
		
		textField[4].setLocation(600, 110);
		panel.add(textField[4]);
		
		
		btn[0].setLocation(1100, 100);
		btn[0].setText("Interogare");
		panel.add(btn[0]);
		 
		btn[1].setLocation(1100, 200);
		btn[1].setText("Toate Produsele");
		panel.add(btn[1]);
		
		
	// depozit
		
		label[6].setLocation(50, 500);
		label[6].setText("<html><u>Nume:");
		
		label[7].setLocation(400, 500);
		label[7].setText("<html><u>Pret:");
		
		label[8].setLocation(700, 500);
		label[8].setText("<html><u>Cantitate:");
		
		
		textField[5].setLocation(120, 510);
		panel.add(textField[5]);
		
		textField[6].setLocation(480, 510);
		panel.add(textField[6]);
		
		textField[7].setLocation(820, 510);
		panel.add(textField[7]);
		
		
		btn[2].setLocation(1100, 500);
		btn[2].setText("Introduceti");
		panel.add(btn[2]);
		
		
		btn[4].setLocation(1100, 600);
		btn[4].setText("Update");
		panel.add(btn[4]);
		
		btn[5].setLocation(1100, 700);
		btn[5].setText("Clienti");
		panel.add(btn[5]);
		
		btn[6].setLocation(1100, 800);
		btn[6].setText("Comenzi");
//		panel.add(btn[6]);
	}
	
	protected JTable getTable() {
		return this.table;
	}
	
	

	
	protected void createTable(LinkedList<Object> obj) {

		String[] col = new String[5];
		int i = 0;
		
	// iau campurile din cls Product
		while(!(obj.get(i) instanceof Product)) {
			i++;
		}
		
	// Product
		int j = 0;
		
		for (Field field : obj.get(i).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				col[j] = field.getName();
				j++;
			} catch (Exception e) {
			}
		}
		
// iau campurile din calasa Stock		
		while (obj.get(i) instanceof Product) {
			i++;
		}
		
	// Stock
		for (Field field : obj.get(i).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			try {
				col[j] = field.getName();
				j++;
			} catch (Exception e) {
			}
		}
		
// iau valorile campurilor
		Object[][] data = new Object[obj.size()][6];
		Vector<Object> vector = new Vector<Object>();
		
		for (int ii = 0; ii < obj.size(); ii++) {
			for (int jj = 0; jj < 5; jj++) {
				data[ii][jj] = new Object();
				data[ii][jj] = (String) "";
			}
		}

		
		int row = 0, st = 0, pr = 0;
		i = 0;	j = 0;
		int k = 0;
		
		while (obj.get(j) instanceof Product) j++;
		
		
		while (i < obj.size() / 2 + 1 && j < obj.size()) {
			k = 0;
			
			if (obj.get(i) instanceof Product) {
				
					for (Field field : obj.get(i).getClass().getDeclaredFields()) {
						field.setAccessible(true);
				
						try {
							data[i][k] = field.get(obj.get(i));
							k++;
					
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}
			
			if (obj.get(j) instanceof Stock) {
				
				for (Field field : obj.get(j).getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					try {
						data[i][k] = field.get(obj.get(j));
						k++;
						
					} catch (Exception e) {
					}
				}
			}
			
			i++;
			j++;
		}
		
		
		DefaultTableModel model = new DefaultTableModel(data, col);
		table = new JTable(model);
	    scrollPane = new JScrollPane(table);

	    
	    btn[7] = new JButton();
	    btn[7].setText("Trimiteti comanda");
	    fr.add(btn[7], BorderLayout.SOUTH);
	    fr.add(scrollPane, BorderLayout.CENTER);
	    fr.setSize(600, 300);
	    fr.setVisible(true);
	    
	    
	    for (i = 0; i < obj.size(); i++) 
	    	obj.removeFirst();
		
	}
	
	protected JButton getBtn7() {
		return this.btn[7];
	}
	
	
	protected void createFrame2() {
//		comanda
			JPanel panel2 = new JPanel();
			
			frame2.setExtendedState(MAXIMIZED_BOTH);
			frame2.setVisible(true);
			frame2.setContentPane(panel2);
			panel2.setLayout(null);
			frame2.setSize(200, 200);
			
			textField[8].setLocation(10, 10);
			textField[8].setSize(75, 25);
			panel2.add(textField[8]);
			
			
			btn[3].setSize(75, 75);
			btn[3].setText("OK");
			btn[3].setLocation(100, 10);
			panel2.add(btn[3]);
			
	}
	
	
	
	protected int valid() {
		if (textField[4].getText().equals("") && textField[0].getText().equals("")) return -1;
		if (!textField[4].getText().equals("")) return 0;		// nume
		if (!textField[0].getText().equals("")) return 1;		// pret
		
		return -2;
	}
	
	protected JButton getBtn5() {
		return this.btn[5];
	}
	
	protected JButton getBtn6() {
		return this.btn[6];
	}
	
	protected Integer getPriceTextField() {
		String str = textField[0].getText();
		
		return Integer.parseInt(str);
	}
	
	protected JTextField getTextField0() {
		return this.textField[0];
	}
	
	protected JTextField getTextField1() {
		return this.textField[1];
	}
	
	protected JTextField getTextField2() {
		return this.textField[2];
	}
	
	protected JTextField getTextField3() {
		return this.textField[3];
	}
	
	protected JFrame getFrame2() {
		return this.frame2;
	}
	
	protected JTextField getNameTextField() {
		return textField[4];
	}
	
	protected JTextField getTextfield5() {
		return textField[5];
	}
	
	protected JTextField getTextfield6() {
		return textField[6];
	}
	
	protected JFrame getFrame() {
		return this.frame;
	}
	
	protected JTextField getTextfield7() {
		return textField[7];
	}
	
	protected JTextField getTextField8() {
		return textField[8];
	}
	
	protected JButton getBtn0() {
		return this.btn[0];
	}
	
	protected JButton getBtn1() {
		return this.btn[1];
	}
	
	protected JButton getBtn2() {
		return this.btn[2];
	}
	
	protected JButton getBtn3() {
		return this.btn[3];
	}
	
	protected JButton getBtn4() {
		return this.btn[4];
	}
	
	protected void resetTextFields() {
		for (int i = 0; i < 8; i++)
			textField[i].setText("");
	}

}
