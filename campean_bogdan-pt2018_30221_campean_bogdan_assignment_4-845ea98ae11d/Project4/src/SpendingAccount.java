
public class SpendingAccount extends Account {
	
	private static final long serialVersionUID = 1L;

	public SpendingAccount(Double money) {
		super(money);
	}

	@Override
	public void depositMoney(Double money) {
		this.setMoney(this.getMoney() + money);
		this.setChanged();
		this.notifyObservers("S-au depozitat " + money.toString() + " in contul " + this.getId().toString() + ". Spending Account.");
	}
	
	@Override
	public void withdrawMoney(Double money) throws Exception {
		if (this.getMoney() - money < 0) throw (new Exception("Fonduri insuficiente"));
		
		this.setMoney(this.getMoney() - money);
		this.setChanged();
		this.notifyObservers("S-au retras " + money.toString() + " din contul " + this.getId().toString() + ". Spending Account.");
	}
	
	@Override
	public void newAccount(Person person) {
		this.setChanged();
		this.notifyObservers("A fost creat un cont (SpendingAccount) pe numele " + person.getName() + "  cu suma initiala " + super.getMoney() + " lei si id-ul: " + super.getId());
	}
	
}
