package PT2018.demo.Project1;

public class Monom {
	private Float coef = new Float(0);
	private Integer index;
	
	public Monom() {
		this.index = 0;
	}
	
	public Monom(Float coef, int index) {
		this.coef = coef;
		this.index = index;
	}
	
	public Float getCoef() {
		return coef;
	}
	
	public Integer getIndex() {
		return index;
	}
	
	public void setCoef(Float d) {
		this.coef = d;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public Monom getDiv(Monom mon) {
		Monom aux = new Monom();
		
		aux.setCoef(this.coef / mon.getCoef());
		aux.setIndex(this.index - mon.getIndex());
		
		return aux;
	}
}
