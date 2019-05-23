import java.util.Observable;
import java.io.*;


public abstract class Account extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Integer id = 0;
	private Integer idAccount;
	private Double money;
	
	
	public Account(Double money) {
		idAccount = new Integer(Account.id++);
		this.money = money;
	}
	
	
	public static void setStaticId(Integer id) {
		Account.id = id;
	}
	
	public static Integer getStaticId() {
		return Account.id;
	}
	
	public Double getMoney() {
		return this.money;
	}
	
	public Integer getId() {
		return this.idAccount;
	}
	
	public void setMoney(Double money) {
		this.money = money;
	}
	
	public void incraseMoney(double d) {
		this.money = d + this.money;
	}
	
	public abstract void depositMoney(Double money);
	public abstract void withdrawMoney(Double money) throws Exception;
	public abstract void newAccount(Person person);
}
