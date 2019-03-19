import java.util.*;

public interface BankProc {

	/*
	 *	@pre person != null
	 *
	 *	@post HashMap.size() == HashMap@pre.size() + 1
	 *	@post HashMap.contains(person)
	 */
	public void addPerson(Person person);
	
	/*
	 *	@pre person != null
	 *
	 *	@post HashMap.size() == HashMap@pre.size() - 1
	 *	@post !HashMap.contains(person)	
	 */
	public void removePerson(Person person);
	
	/*
	 * 	@pre person != null
	 *  @pre account != null
	 *  
	 *  @post HashMap.size() == HashMap@pre.size()
	 *  @post HashMap.Account.contains(account)
	 *  @post HashMap.Account.size() == HashMap.Account@pre.size() + 1
	 */
	public void addNewAccount(Person person, Account account);
	
	/*
	 *  @pre person != null
	 *  @pre id != null
	 *  
	 *  @post HashMap.size() == HashMap@preSize()
	 *  @post HashMap.Account.size() == HashMap.Account@preSize() - 1
	 *  @post !HashMap.Account.contains(account)
	 */
	public void removeAccount(Person person, Integer id);
	
	/*
	 *  @pre person != null
	 *  @pre money != null
	 *  @pre id != null
	 *  
	 *  @inv money > 0
	 *  
	 *  @post money@post == money@pre + money
	 */
	public void depositMoney(Person person, Double money, Integer id) throws Exception;
	
	/*
	 *  @pre person != null
	 *  @pre money != null
	 *  @pre id != null
	 *  
	 *  @inv money > 0
	 *  
	 *  @post money@post == money@pre - money
	 */
	public void withdrawMoney(Person person, Double money, Integer id) throws Exception;
	
	public HashMap<Person, LinkedList<Account>> readAccounts();
	public void writeAccounts();
	
}
