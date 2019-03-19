import java.util.Iterator; 
import java.util.*;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class Controller {
	
	public static final double DOBANDA = 4;
	
	public static Presentation frame = new Presentation();
	public Integer sec = new Integer(0);
	
	
	public static void main(String[] args) {
		Bank bank = new Bank();
		HashMap<Person, LinkedList<Account>> map = new HashMap<Person, LinkedList<Account>>();
		Thread thread = new Thread();
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int sec = 0;
			
			public void run() {
				System.out.println(sec);
				sec++;
				bank.setMoney(DOBANDA);
				frame.createTable(bank.getHashMap());
				bank.writeAccounts();
			}
		}, new Date(), 1000);
		
		
		
	// adaugati client
		frame.getBtn4().addActionListener(e-> {
			if (frame.getTextField3().getText().equals("")) JOptionPane.showMessageDialog(frame, "Adaugati un NUME valid!", "WARNING!", JOptionPane.WARNING_MESSAGE);
			else if (!frame.getTextField3().getText().equals("")) {
				bank.addPerson(new Person(frame.getTextField3().getText()));
				frame.resetText();
				bank.writeAccounts();
				frame.createTable(bank.getHashMap());
			}
		});
		
		
	// creati cont
		frame.getBtn2().addActionListener(e-> {
			if (frame.getTextField4().getText().equals("") || frame.getTextField6().getText().equals("")) JOptionPane.showMessageDialog(frame, "Adaugati un NUME, o SUMA si un ID CLIENT valid!", "WARNING!", JOptionPane.WARNING_MESSAGE);
			else if (!frame.getTextField4().getText().equals("") && !frame.getTextField6().getText().equals("")) {
				if (frame.getComboBox().equals(0)) {
					Account account = new SavingAccount(Double.parseDouble(frame.getTextField4().getText()));
					Person person = new Person(frame.getTextField3().getText());
					person.setId(Integer.parseInt(frame.getTextField6().getText()));
					bank.addNewAccount(person, account);
				}
				else {
					Account account = new SpendingAccount(Double.parseDouble(frame.getTextField4().getText()));
					Person person = new Person(frame.getTextField3().getText());
					person.setId(Integer.parseInt(frame.getTextField6().getText()));
					bank.addNewAccount(person, account);
				}
				
				frame.createTable(bank.getHashMap());
				bank.writeAccounts();
				frame.resetText();
			}
		});
		
		
	// stergeti cont
		frame.getBtn3().addActionListener(e-> {
			if (frame.getTextField3().getText().equals("") || frame.getTextField6().getText().equals("") || frame.getTextField5().getText().equals("")) JOptionPane.showMessageDialog(frame, "Adaugati un NUME, un ID CLIENT si un NR. CONT valid!", "WARNING!", JOptionPane.WARNING_MESSAGE);
			else if (!frame.getTextField3().getText().equals("") && !frame.getTextField6().getText().equals("") && !frame.getTextField5().getText().equals("")) {
				Person person = new Person(frame.getTextField3().getText());
				person.setId(Integer.parseInt(frame.getTextField6().getText()));
				
				bank.removeAccount(person, Integer.parseInt(frame.getTextField5().getText()));
				bank.writeAccounts();
				frame.resetText();
				frame.createTable(bank.getHashMap());
			}
		});
		
		
	// stergeti client
		frame.getBtn5().addActionListener(e-> {
			if (frame.getTextField3().getText().equals("") || frame.getTextField6().getText().equals("")) JOptionPane.showMessageDialog(frame, "Adaugati un NUME si un ID CLIENT valid!", "WARNING!", JOptionPane.WARNING_MESSAGE);
			else if (!frame.getTextField3().getText().equals("") && !frame.getTextField6().getText().equals("")) {
				Person person = new Person(frame.getTextField3().getText());
				person.setId(Integer.parseInt(frame.getTextField6().getText()));
				
				bank.removePerson(person);
				bank.writeAccounts();
				bank.readAccounts();
				frame.resetText();
				frame.createTable(bank.getHashMap());
			}
		});
		
		
	// adaugati suma
		frame.getBtn0().addActionListener(e-> {
			if (frame.getTextField0().getText().equals("") || frame.getTextField1().getText().equals("") || frame.getTextField2().getText().equals("")) JOptionPane.showMessageDialog(frame, "Adaugati un NUME, un NR. CONT si SUMA valide!", "WARNING!", JOptionPane.WARNING_MESSAGE);
			else if (!frame.getTextField0().getText().equals("") && !frame.getTextField1().getText().equals("") && !frame.getTextField2().getText().equals("")) {
				Person person = new Person(frame.getTextField0().getText());
				
				try {
					bank.depositMoney(person, Double.parseDouble(frame.getTextField1().getText()), Integer.parseInt(frame.getTextField2().getText()));
					bank.writeAccounts();
					frame.createTable(bank.getHashMap());
				} catch (NumberFormatException e1) {
				} catch (Exception e1) {
					e1.printStackTrace();
					if (e1.toString().equals("java.lang.Exception: Cont inexistent")) JOptionPane.showMessageDialog(frame, "Cont inexistent!", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				
				frame.resetText();
			}
		});
		
		
	// retragere numerar
		frame.getBtn1().addActionListener(e-> {
			if (frame.getTextField0().getText().equals("") || frame.getTextField1().getText().equals("") || frame.getTextField2().getText().equals("")) JOptionPane.showMessageDialog(frame, "Adaugati un NUME, un NR. CONT si SUMA valide!", "WARNING!", JOptionPane.WARNING_MESSAGE);
			else if (!frame.getTextField0().getText().equals("") && !frame.getTextField1().getText().equals("") && !frame.getTextField2().getText().equals("")) {
				Person person = new Person(frame.getTextField0().getText());
				
				try {
					bank.withdrawMoney(person, Double.parseDouble(frame.getTextField1().getText()), Integer.parseInt(frame.getTextField2().getText()));
					bank.writeAccounts();
					frame.resetText();
					frame.createTable(bank.getHashMap());
				} catch (NumberFormatException e1) {
				} catch (Exception e1) {
					e1.printStackTrace();
					if (e1.toString().equals("java.lang.Exception: Cont inexistent")) JOptionPane.showMessageDialog(frame, "Cont inexistent!", "WARNING", JOptionPane.WARNING_MESSAGE);
					if (e1.toString().equals("java.lang.Exception: Fonduri insuficiente")) JOptionPane.showMessageDialog(frame, "Fonduri insuficiente!", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		frame.createTable(bank.getHashMap());
	}
	
}
