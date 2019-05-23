import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.JOptionPane;


public class Bank extends Observable implements BankProc, Observer, Serializable {
	private HashMap<Person, LinkedList<Account>> map;
	private static Integer id;
	
	
	public Bank() {
		this.map = this.readAccounts();
		this.readAccounts();
		this.setStaticId();
		id = new Integer(0);
	}
	
	
	public HashMap<Person, LinkedList<Account>> getHashMap() {
		return this.map;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
//		JOptionPane.showMessageDialog(null, arg1, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public void addPerson(Person person) {
		
// assert
		assert person != null : "person == null";
		
		int preSize = map.size();
		
		map.put(person, new LinkedList<Account>());
		this.setChanged();
		this.notifyObservers("Clientul " + person.getName() + " cu ID-ul " + person.getId() + " a fost adaugat!");
		
		int postSize = map.size();

// assert
		assert preSize != 1 + postSize : "preSize() == postSize()";
		assert this.map.containsKey(person) : person.getName() + " exista";
	}
	
	
// setMoney
	public void setMoney(double dobanda) {
		LinkedList<Account> accountList = new LinkedList<Account>();
		
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext()) {
			Map.Entry pair = (Map.Entry)hashMapIterator.next();
			
			Person person = (Person)pair.getKey();
			LinkedList<Account> personAccounts = (LinkedList<Account>) pair.getValue();
			
			Iterator<Account> accountIterator = personAccounts.iterator();
			
			while (accountIterator.hasNext()) {
				Account account = accountIterator.next();
				
				if (account instanceof SavingAccount)
					account.incraseMoney((dobanda / 100) * account.getMoney());
			}
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public void removePerson(Person person) {
// assert
		assert person != null : "person == null";
		
		int preSize = this.map.size();
		int ok = 1;
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext() && ok == 1) {
			Map.Entry pair = (Map.Entry)hashMapIterator.next();
			Person auxPerson = (Person)pair.getKey();
			
			if (auxPerson.getId().equals(person.getId())) {
				ok = 0;
				map.remove(auxPerson);
			}
		}
		
		int postSize = map.size();
		
// assert
		assert preSize != postSize - 1 : "preSize() == postSize()";
		assert !this.map.containsKey(person) : person.getName() + " exista";
	}
	
	
// addAccounts
	@SuppressWarnings("unlikely-arg-type")
	public void addNewAccount(Person person, Account account) {
//assert
		assert person != null : "person == null";
		assert account != null : "account == null";
		
		int personPreSize = map.size();
		int accountPreSize = 0, accountPostSize = 0;
		
		int ok = 1;
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext() && ok == 1) {
			Map.Entry pair = (Map.Entry)hashMapIterator.next();
			Person auxPerson = (Person)pair.getKey();
			
			if (auxPerson.getId().equals(person.getId())) {
				accountPreSize = this.getPersonAccounts(auxPerson).size();
				
				ok = 0;
				account.addObserver(auxPerson);
				account.newAccount(auxPerson);
				map.get(auxPerson).add(account);
				
				accountPostSize = this.getPersonAccounts(auxPerson).size();
			}
		}
		
		int personPostSize = map.size();

//assert
		assert accountPreSize != accountPostSize + 1 : "Account.preSize() == Account.postSize()";
		assert personPreSize == personPostSize : "Person.preSize() != Person.postSize()";
		assert !map.containsValue(account) : account.getId() + " nu s-a adaugat";
	}
	
	
// depositMoney
	public void depositMoney(Person person, Double money, Integer id) throws Exception {
// assert
		assert money != null : "person == null";
		assert money > 0 : "money < 0";
		assert person != null : "person == null";
		assert id != null : "id == null";
		
		double preMoney = money , postMoney = 0;
		
		LinkedList<Account> accountList = new LinkedList<Account>();
		int ok = 0;
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) hashMapIterator.next();
			Person auxPerson = (Person) pair.getKey();
		
			accountList = map.get(auxPerson);
			
			Iterator<Account> it = accountList.iterator();
			while (it.hasNext() && ok == 0) {
				Account account = it.next();
				if (account.getId().equals(id)) {
					ok = 1;
					account.depositMoney(money);
					
					postMoney = account.getMoney();
				}
			}
		}
		
		if (ok == 0) {
			throw (new Exception("Cont inexistent"));
		}
		else {
			this.setChanged();
			this.notifyObservers();
		}
		
// assert
		assert postMoney != preMoney + money : "money@pre != money@post + money";
		assert postMoney > 0 : "money@post < 0";
	}
	
// withdrawMoney
	public void withdrawMoney(Person person, Double money, Integer id) throws Exception {
// assert
		assert person != null : "person == null";
		assert money != null : "money == null";
		assert id != null : "id == null";
		assert money > 0 : "money < 0";
		
		double preMoney = money, postMoney = 0;
		
		LinkedList<Account> accountList = new LinkedList<Account>();
		int ok = 0;
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) hashMapIterator.next();
			Person auxPerson = (Person) pair.getKey();
		
			accountList = map.get(auxPerson);
			
			Iterator<Account> it = accountList.iterator();
			while (it.hasNext() && ok == 0) {
				Account account = it.next();
				if (account.getId().equals(id)) {
					ok = 1;
					if (account.getMoney() - money < 0) throw (new Exception("Fonduri insuficiente"));
					else
						account.withdrawMoney(money);
					
						postMoney = account.getMoney();
				}
			}
		}
		
		if (ok == 0) throw (new Exception("Cont inexistent"));
		
// assert
		assert postMoney != preMoney - money : "money@post != money@pre - money";
		assert postMoney > 0 : "money@post < 0";
	}
	
	
// getPersonAccounts
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList<Account> getPersonAccounts(Person person) {
		LinkedList<Account> accountList = new LinkedList<Account>();
		Integer id = person.getId();
		int ok = 1;
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext() && ok == 1) {
			Map.Entry pair = (Map.Entry)hashMapIterator.next();
			Person auxPerson = (Person)pair.getKey();
			
			if (person.getId().equals(auxPerson.getId()))
				accountList = (LinkedList<Account>) pair.getValue();
		}
		
		return accountList;
	}
	
	
// getAllAccounts
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList<Account> getAllAccounts() {
		LinkedList<Account> accountList = new LinkedList<Account>();
		
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		
		while (hashMapIterator.hasNext()) {
			Map.Entry pair = (Map.Entry)hashMapIterator.next();
			
			Person person = (Person)pair.getKey();
			LinkedList<Account> personAccounts = (LinkedList<Account>) pair.getValue();
			
			Iterator<Account> accountIterator = personAccounts.iterator();
			
			while (accountIterator.hasNext()) {
				Account account = accountIterator.next();
				accountList.add(account);
			}
		}
		
		
		return accountList;
	}
	
	
// setStaticId
	@SuppressWarnings("unchecked")
	public void setStaticId() {
		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		Integer max = new Integer(0);
		Integer maxP = new Integer(0);
		
		while (hashMapIterator.hasNext()) {
			Map.Entry pair = (Map.Entry)hashMapIterator.next();
			
			Person person = (Person)pair.getKey();
			LinkedList<Account> personAccounts = (LinkedList<Account>) pair.getValue();
			
			if (person.getId() > maxP) maxP = person.getId();
			
			Iterator<Account> accountIterator = personAccounts.iterator();
			
			while (accountIterator.hasNext()) {
				Account account = accountIterator.next();
				if (account.getId() > max) max = account.getId();
			}
		}
		
		Account.setStaticId(max);
		Person.setStatidId(maxP);
	}
	
	
// removeAccount
	public void removeAccount(Person person, Integer id) {
//asssert
		assert id != null : "id == null";
		assert person != null : "person == null";
		
		int accountPreSize = 0, accountPostSize = 0;
		int personPreSize = map.size();

		Iterator<Entry<Person, LinkedList<Account>>> hashMapIterator = map.entrySet().iterator();
		Account account = null;
		int ok = 1;
		
		while (ok == 1 && hashMapIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) hashMapIterator.next(); 
			Person auxPerson = (Person) pair.getKey();
			LinkedList<Account> accountList = (LinkedList<Account>) pair.getValue();
			Iterator<Account> accountIterator = accountList.iterator();
			
			while (accountIterator.hasNext() && ok == 1) {
				account = accountIterator.next();
				
				if (account.getId().equals(id)) {
					accountPreSize = map.get(auxPerson).size();
					
					ok = 0;
					accountList.remove(account);
					
					accountPostSize = map.get(auxPerson).size();
				}
			}
		}
		
		int personPostSize = map.size();
		
// assert
		assert accountPreSize != accountPostSize - 1 : "account.preSize() == account.postSize()";
		assert personPreSize == personPostSize : "person.preSize() != person.postSize()";
		assert !this.map.containsKey(person) : person.getName() + " nu exista";
		assert !this.map.containsValue(account) : account.getId() + " nu s-a sters";
	}
	
	
// readAccounts
	@SuppressWarnings({ "unchecked", "resource" })
	public HashMap<Person, LinkedList<Account>> readAccounts() {
		FileInputStream fileIn = null;
		ObjectInputStream obj = null;
		HashMap<Person, LinkedList<Account>> auxMap = new HashMap<Person, LinkedList<Account>>();
		
		
		try {
			fileIn = new FileInputStream("C:\\Users\\Bogdan\\eclipse-workspace\\Project4\\file.ser");
			obj = new ObjectInputStream(fileIn);
			
			auxMap = (HashMap<Person, LinkedList<Account>>) obj.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
				obj.close();
			} catch (Exception e) {
			}
		}
		
//		this.setStaticId();
		
		return auxMap;
	}
	
	
// writeAccounts
	@SuppressWarnings("resource")
	public void writeAccounts() {
		FileOutputStream fileOut = null;
		ObjectOutputStream obj = null;
				
		try {
			fileOut = new FileOutputStream("C:\\Users\\Bogdan\\eclipse-workspace\\Project4\\file.ser");
			obj = new ObjectOutputStream(fileOut);
			
			obj.writeObject(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.close();
				obj.close();
			} catch (Exception e) {
			}
		}
	}


}
