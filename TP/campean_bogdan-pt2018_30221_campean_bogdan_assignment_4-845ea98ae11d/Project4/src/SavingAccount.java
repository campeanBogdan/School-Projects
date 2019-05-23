
public class SavingAccount extends Account {
	private static final long serialVersionUID = 1L;

	
	public SavingAccount(Double money) {
		super(money);
	}
	
	@Override
	public void depositMoney(Double money) {
		this.setMoney(this.getMoney() + money);
		this.setChanged();
		this.notifyObservers("S-au depozitat " + money.toString() + " lei in contul " + this.getId().toString() + ". Saving Account.");
	}
	
	@Override
	public void withdrawMoney(Double money) throws Exception {
		if (this.getMoney() - money < 0) throw (new Exception("Fonduri insuficiente"));
		
		this.setMoney(this.getMoney() - money);
		this.setChanged();
		this.notifyObservers("S-au retras " + money.toString() + " lei din contul " + this.getId().toString() + ". Saving Account.");
	}
	
	@Override
	public void newAccount(Person person) {
		this.setChanged();
		this.notifyObservers("A fost creat un cont (SavingAccount) pe numele: " + person.getName() + " cu suma initiala " + super.getMoney() + " lei si id-ul: " + super.getId());
	}
	
}
