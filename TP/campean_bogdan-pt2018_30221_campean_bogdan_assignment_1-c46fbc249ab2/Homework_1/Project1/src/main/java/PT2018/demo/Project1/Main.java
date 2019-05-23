package PT2018.demo.Project1;

// integrarea si derivarea se face asupra casutei 1
// butonul 'rezultat' aduce rezultatul la forma initiala si il insereaza in casuta 1


import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		ProjectFrame f = new ProjectFrame();
		Polinom pol1 = new Polinom();
		Polinom pol2 = new Polinom();
		Operatii op = new Operatii();
		
	//	MainTest test = new MainTest();
		
//		test.testMain();
		
		f.createFrame();
		
		f.getSumButton().addActionListener(e-> {				//butonul "Suma"
			try {
				pol1.makePolinom(f.getTextField1());
				pol2.makePolinom(f.getTextField2());
				
				pol1.setPol(op.getSum(pol1, pol2));
				
				f.setResultText(pol1.convertToString());
				
				pol1.resetPolinom();
				pol2.resetPolinom();
				f.resetTextField1();
				f.resetTextField2();
				f.setTextField1(f.getResult());
				
			} catch (Exception e1) {
				if (e1.toString().equals("java.lang.Exception: Null")) JOptionPane.showMessageDialog(f, "Introduceti cel putin un numar!");
				if (e1.toString().equals("java.lang.Exception: Litere1")) {
					f.resetTextField1();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Litere2")) {
					f.resetTextField2();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Putere negativa")) JOptionPane.showMessageDialog(f, "Polinomul nu are puteri negative!");
			}
		});
		
		
		f.getDifferenceButton().addActionListener(e-> {					//buton "Diferenta"
			try {
				pol1.makePolinom(f.getTextField1());
				pol2.makePolinom(f.getTextField2());
				
				pol1.setPol(op.getDiff(pol1, pol2));
				
				f.setResultText(pol1.convertToString());
				
				pol1.resetPolinom();
				pol2.resetPolinom();
				f.resetTextField1();
				f.resetTextField2();
				f.setTextField1(f.getResult());
				
			} catch (Exception e1) {
				if (e1.toString().equals("java.lang.Exception: Null")) JOptionPane.showMessageDialog(f, "Introduceti cel putin un numar!");
				if (e1.toString().equals("java.lang.Exception: Litere1")) {
					f.resetTextField1();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Litere2")) {
					f.resetTextField2();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
			}
		});
		
		
		f.getMultiplyButton().addActionListener(e-> {					//buton "Inmultire"
			try {
				pol1.makePolinom(f.getTextField1());
				pol2.makePolinom(f.getTextField2());
				
				pol1.setPol(op.getMultiply(pol1, pol2));
				
				f.setResultText(pol1.convertToString());
				
				pol1.resetPolinom();
				pol2.resetPolinom();
				f.resetTextField1();
				f.resetTextField2();
				f.setTextField1(f.getResult());
				
			} catch (Exception e1) {
				if (e1.toString().equals("java.lang.Exception: Null")) JOptionPane.showMessageDialog(f, "Introduceti cel putin un numar!");
				if (e1.toString().equals("java.lang.Exception: Litere1")) {
					f.resetTextField1();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Litere2")) {
					f.resetTextField2();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
			}
		});
		
		
		f.getDivisionButton().addActionListener(e-> {				//buton "Impartire"
			try {
				pol1.makePolinom(f.getTextField1());
				pol2.makePolinom(f.getTextField2());
				
				pol1.setPol(op.getDiv(pol1, pol2));
				
				f.setResultText(pol1.convertToString());
				
				pol1.resetPolinom();
				pol2.resetPolinom();
				f.resetTextField1();
				f.resetTextField2();
				f.setTextField1(f.getResult());
				
				
			} catch (Exception e1) {
				if (e1.toString().equals("java.lang.Exception: Null")) JOptionPane.showMessageDialog(f, "Introduceti cel putin un numar!");
				if (e1.toString().equals("java.lang.Exception: Litere1")) {
					f.resetTextField1();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Litere2")) {
					f.resetTextField2();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
			}
		});
		
		
		f.getDerivateButton().addActionListener(e-> {				//buton "Derivare"
			try {
				pol1.makePolinom(f.getTextField1());
				
				pol1.setPol(op.getDer(pol1));
				
				f.setResultText(pol1.convertToString());
				
				pol1.resetPolinom();
				pol2.resetPolinom();
				f.resetTextField1();
				f.resetTextField2();
				f.setTextField1(f.getResult());
				
			} catch (Exception e1) {
				if (e1.toString().equals("java.lang.Exception: Null")) JOptionPane.showMessageDialog(f, "Introduceti cel putin un numar!");
				if (e1.toString().equals("java.lang.Exception: Litere1")) {
					f.resetTextField1();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Litere2")) {
					f.resetTextField2();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
			}
		});
		
		
		f.getIntergateButton().addActionListener(e-> {				//buton "Integrare"
			try {
				pol1.makePolinom(f.getTextField1());
				
				pol1.setPol(op.getInteg(pol1));
			
				f.setResultText(pol1.convertToString());
				
				pol1.resetPolinom();
				pol2.resetPolinom();
				f.resetTextField1();
				f.resetTextField2();
				f.setTextField1(f.getResult());
				
			} catch (Exception e1) {
				if (e1.toString().equals("java.lang.Exception: Null")) JOptionPane.showMessageDialog(f, "Introduceti cel putin un numar!");
				if (e1.toString().equals("java.lang.Exception: Litere1")) {
					f.resetTextField1();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
				if (e1.toString().equals("java.lang.Exception: Litere2")) {
					f.resetTextField2();
					JOptionPane.showMessageDialog(f, "Introduceti doar NUMERE si SPATII!");
				}
			}
		});
		
		
		f.getResetButton().addActionListener(e-> {					//buton "Reset"
			f.resetTextField1();
			f.resetTextField2();  
			pol1.resetPolinom();
			pol2.resetPolinom();
			f.resetResultText();
		});
	}

}
