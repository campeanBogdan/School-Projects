package PT2018.demo.Project1;

import java.util.*;
import javax.swing.*;

public class Polinom {
	private LinkedList<Monom> pol = new LinkedList<>();
	
	
	public int getMaxGrad() {
		int max = 0;
		
		for (Monom mon : pol) 
			if (mon.getIndex() > max) max = mon.getIndex();
		
		return max;
	}
	
	
	public Monom getMaxMonom() {
		int maxIndex = 0;
		Monom maxMon = new Monom();
		
		for (Monom mon : pol) 
			if (mon.getIndex() > maxIndex) {
				maxIndex = mon.getIndex();
				maxMon = mon;
			}
		
		return maxMon;
		
	}
	
	
	public void setPol(Polinom pol) {
		this.pol = pol.getPolinom();
	}
	
	
	public void makePolinom(JTextField txt) throws Exception {
		float coef = 0;
		int index, c = 0;
		String s = new String();
		String str = new String();
		
		str = txt.getText();
		
		str = str + " ";
		
	//parcurg Stringul o data: valorile de pe pozitiile pare sunt coeficientii, valorile de pe poz impare sunt puterile
		for (int i = 0; i < str.length(); i++) {
			
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9' || str.charAt(i) == '-' || str.charAt(i) == '.') s = s + str.charAt(i);
			else if (str.charAt(i) == ' ') {
				//c%2 == 0 => coeficientul
				if (c % 2 == 0) coef = Float.parseFloat(s);
				else {
					if (s.charAt(0) == '-') throw (new Exception("Putere negativa"));
					
					index = Integer.parseInt(s);
					
					pol.add(new Monom(coef, index));
				}
				c++;
				s = "";
			}
		}
		
	//daca utilizatorul a uitat sa puna ultima putere in textField il adaug implicit cu puterea 0 
		if (c % 2 != 0) pol.add(new Monom(coef, 0));
	}
	
	
	public void addElement(Monom mon) {
		pol.add(mon);
	}
	
	
	public void resetPolinom() {
		pol.removeAll(pol);
	}
	
	
	public LinkedList<Monom> getPolinom() {
		return pol;
	}
	
	
	public void removeElement(Monom mon) {
		pol.remove(mon);
	}
	
	
	public int getNoPolElements() {
		int i = 0;
		
		for (Monom mon : pol) i++;
		
		return i;
	}
	
	
	// rezultatul => String
	public String convertToString() {
		String str = new String("<html>");
		
		for (Monom mon : pol) {
			Integer in = new Integer(0);
			Float fl = new Float(0);

			if (mon.getCoef() != 0) {
				
				if (mon.getCoef() % 1 == 0) {
					
					in = mon.getCoef().intValue();
					
					if (in > 0) {
						if (str.equals("<html>")) {
							if (in == 1) {
								str = str + "x<sup>" + mon.getIndex() + "</sup> ";
							}
							else str = str + in.toString() + "x<sup>" + mon.getIndex() + "</sup> ";
						}
						else {
							if (in == 1) str = str + "+" + "x<sup>" + mon.getIndex() + "</sup> ";
							else str = str + "+" + in.toString() + "x<sup>" + mon.getIndex() + "</sup> ";
						}
					}
					else	
						str = str + in.toString() + "x<sup>" + mon.getIndex() + "</sup> ";
					
				}
				else {
					fl = mon.getCoef();
					
					if (fl > 0) {
						if (str.equals("<html>")) {
							if (fl == 1) {
								str = str + "x<sup>" + mon.getIndex() + "</sup> ";
							}
							else str = str + fl.toString() + "x<sup>" + mon.getIndex() + "</sup> ";
						}
						else {
							if (fl == 1) str = str + "+" + "x<sup>" + mon.getIndex() + "</sup> ";
							else str = str + "+" + fl.toString() + "x<sup>" + mon.getIndex() + "</sup> ";
						}
					} 
					else	
						str = str + fl.toString() + "x<sup>" + mon.getIndex() + "</sup> ";
					
				}
			}
		}
		
		if (str.equals("<html>")) return "0";
		else return str + "</html>";
	}
	
}