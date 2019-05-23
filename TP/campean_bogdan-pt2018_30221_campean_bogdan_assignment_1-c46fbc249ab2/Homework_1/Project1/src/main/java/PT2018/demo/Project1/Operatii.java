package PT2018.demo.Project1;

import java.util.Iterator;
import java.util.LinkedList;

public class Operatii {
	
	//suma
	public Polinom getSum(Polinom pol1, Polinom pol2) {
		
		for (Monom mon2 : pol2.getPolinom()) {
			int ok = 0;
			
			for (Monom mon1 : pol1.getPolinom()) {
				if (mon1.getIndex().equals(mon2.getIndex())) {
					mon1.setCoef(mon1.getCoef() + mon2.getCoef());
					ok = 1;
				}
			}
			
			if (ok == 0) pol1.addElement(new Monom(mon2.getCoef(), mon2.getIndex()));
		}
		
		return pol1;
	}
	
	
	public Polinom getDiff(Polinom pol1, Polinom pol2) {
		
		for (Monom mon2 : pol2.getPolinom()) {
			int ok = 0;
			
			for (Monom mon1 : pol1.getPolinom()) {
				if (mon1.getIndex().equals(mon2.getIndex())) {
					mon1.setCoef(mon1.getCoef() - mon2.getCoef());
					ok = 1;
				}
			}
			
			if (ok == 0) pol1.addElement(new Monom((-1) * mon2.getCoef(), mon2.getIndex()));
		}
		
		return pol1;
	}
	
	
	public Polinom getDer(Polinom pol) {
		
		for (Monom mon : pol.getPolinom()) {
			if (mon.getIndex().equals(0)) pol.removeElement(mon);//pol.getPolinom().remove(mon);
			else {
				mon.setCoef(mon.getCoef() * mon.getIndex());
				mon.setIndex(mon.getIndex() - 1);
			}
		}
		
		return pol;
	}
	
	
	public Polinom getInteg(Polinom pol) {
		
		for (Monom mon : pol.getPolinom()) {
			mon.setIndex(mon.getIndex() + 1);
			mon.setCoef(mon.getCoef() / mon.getIndex());
		}
		
		return pol;
	}
	
	
	public Polinom getMultiply(Polinom pol1, Polinom pol2) {
		Polinom pAux1 = new Polinom();
		Polinom pAux2 = new Polinom();
		
		
		for (Monom mon1 : pol1.getPolinom()) 
			for (Monom mon2 : pol2.getPolinom())
					pAux1.addElement(new Monom(mon1.getCoef() * mon2.getCoef(), mon2.getIndex() + mon1.getIndex()));
		
		int i = 0;
		
		for (Monom mon : pAux1.getPolinom()) pAux2.addElement(new Monom(mon.getCoef(), mon.getIndex()));
		
		
		for (Monom mon : pAux1.getPolinom()) System.out.print(mon.getCoef() + "^" + mon.getIndex() + " ");
		System.out.println();
		
		Monom mon2 = new Monom();
		Monom mon1 = new Monom();
		Monom mon3 = new Monom();
		Iterator<Monom> polIt1 = pAux1.getPolinom().iterator();
		Iterator<Monom> polIt2, polIt3;
		Polinom pAux3 = new Polinom();
		
		
		while (polIt1.hasNext()) {
			mon3 = mon1 = polIt1.next();
			
			polIt2 = pAux2.getPolinom().iterator();
			
			for (int j = 0; j < i+1; j++) {
				mon2 = polIt2.next();
			}
			
			while (polIt2.hasNext()) {
				mon2 = polIt2.next();
								
				if (mon1.getIndex().equals(mon2.getIndex())) {
					mon1.setCoef(mon1.getCoef() + mon2.getCoef());
					
					int ok = 1;
					polIt3 = polIt1;
					
					while (ok == 1 && polIt3.hasNext()) {
						mon3 = polIt3.next();
						
						if (mon1.getIndex().equals(mon3.getIndex())) {
							pAux1.removeElement(mon3);
							ok = 0;
						}
					}
					
					if (polIt1.hasNext()) {
						pAux1.removeElement(mon3);
					}
					
					pAux2.removeElement(mon2);
				}
			}
			
			i++;
		}
		
		return pAux1;
	}
	
	
	public Polinom getMult(Polinom pol1, Monom mon2) {
		Polinom paux = new Polinom();
		
		for (Monom mon : pol1.getPolinom()) {
			paux.addElement(new Monom(mon.getCoef() * mon2.getCoef(), mon.getIndex() + mon2.getIndex()));
		}
		
		return paux;
	}
	
	
	public Polinom getDiv(Polinom imp, Polinom dimp) {
		Polinom cat = new Polinom();
		Operatii op = new Operatii();
		
		while (imp.getMaxGrad() < dimp.getMaxGrad()) {
			Monom mon1 = dimp.getMaxMonom();
			Monom mon2 = imp.getMaxMonom();
			Monom mon3 = mon1.getDiv(mon2);
			
			cat.addElement(mon3);
			
			Polinom paux = op.getMult(imp, mon3);
			
			dimp = op.getDiff(dimp, paux);
		}
		
		return cat;
	}

}
