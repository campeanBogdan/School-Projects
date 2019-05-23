import static org.junit.Assert.*;

import org.junit.*;

public class TestClass {

	
	@Test
	public void personClassTest() {
		Bank bank = new Bank();
		Person person = new Person("Bogdan");
		Account savingAccount = new SavingAccount(100.0);
		Account spendingAccount = new SpendingAccount(100.0);
		
		
	// OPERATII BANCA
		bank.addPerson(person);
		bank.addNewAccount(person, savingAccount);
		bank.addNewAccount(person, spendingAccount);
		try {
			bank.depositMoney(person, 50.0, savingAccount.getId());
			bank.depositMoney(person, 50.0, spendingAccount.getId());
			bank.withdrawMoney(person, 100.0, savingAccount.getId());
			bank.withdrawMoney(person, 200.0, spendingAccount.getId());
		} catch (Exception e) {
		}
		
		
	// OPERATII PERSOANA
		person.setId(2);
		person.getId();
		person.getName();
		Person.setStatidId(1);
		Person.getStaticId();
		
	// OPERATII ACCOUNT
		Account.setStaticId(2);
		Account.getStaticId();
	}
}
