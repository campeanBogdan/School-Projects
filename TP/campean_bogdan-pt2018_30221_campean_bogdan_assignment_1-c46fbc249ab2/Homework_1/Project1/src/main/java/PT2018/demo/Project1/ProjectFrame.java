package PT2018.demo.Project1;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

public class ProjectFrame extends JFrame {
	private JFrame frame = new JFrame("Operatii Polinoame");
	public JTextField textField1 = new JTextField();
	private JTextField textField2 = new JTextField();
	private JButton[] opBtn = new JButton[6];
	private JButton resetBtn = new JButton("Reset");
	private JLabel resultLabel = new JLabel();
	private int displayNumber = 0;
	
	
	public void createFrame() {
		JPanel mainPanel = new JPanel();
		JLabel label = new JLabel("Polinoamele se introduc: An Xn An-1 Xn-1 ... A0");
	
		resultLabel.setBounds(10, 10, 460, 100);										//ecranul cu rezultatul polinoamelor
		resultLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		resultLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
//		resultLabel.setText(resultText);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);		//fereastra
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		
		mainPanel.setLayout(null);
		
		mainPanel.add(label);
		
		label.setSize(300, 40);
		label.setLocation(110, 130);
		
		textField1.setSize(300, 25);
		textField1.setLocation(100, 170);
		textField2.setSize(300, 25);
		textField2.setLocation(100, 210);
		
		for (int i = 0; i < 4; i++) {
			opBtn[i] = new JButton();					//butoanele cu operatiile
			opBtn[i].setSize(100, 50);
			opBtn[i].setLocation(127*i, 300);
			mainPanel.add(opBtn[i]);
		}
		
		for (int i = 4; i < 6; i++) {
			opBtn[i] = new JButton();
			opBtn[i].setSize(100, 50);
			mainPanel.add(opBtn[i]);
		}
		
		opBtn[0].setLocation(10, 300);
		opBtn[4].setLocation(10, 400);
		opBtn[5].setLocation(127, 400);
		
		opBtn[0].setText("Suma");
		opBtn[1].setText("Diferenta");
		opBtn[2].setText("Inmultire");			//textul butoanelor
		opBtn[3].setText("Impartire");
		opBtn[4].setText("Derivare");
		opBtn[5].setText("Integrare");
		
		resetBtn.setSize(100, 50);
		resetBtn.setLocation(381, 400);
		resetBtn.setForeground(Color.RED);
		mainPanel.add(textField1);
		mainPanel.add(textField2);
		mainPanel.add(resetBtn);
		mainPanel.add(resultLabel);	
	}
	
	//iau rezultatul si il aduc la forma initiala pentru a-l folosi in alte calcule
	public String getResult() {
		String str = new String();
		
		str = resultLabel.getText().replace("<html>", "");
		str = str.replace("</html>", "");
		str = str.replaceAll("<sup>", "");
		str = str.replaceAll("</sup>", "");
		
		if (str.charAt(0) == 'x') str = str.replaceFirst("x", "1 ");
		
		str = str.replaceAll("\\+x", "1 ");
		str = str.replaceAll("\\-x", "1 ");
		str = str.replaceAll("x", " ");
		str = str.replaceAll("\\+", "");
		str = str.replaceAll("  ", " ");
		str = str.substring(0, str.length() - 1);
		
		return str;
	}
	
	public void setResultText(String str) {
		this.resultLabel.setText(str);
	}
	
	public void resetResultText() {
		this.resultLabel.setText("");
	}
	
	public void setDisplayNumber() {
		displayNumber++;
	}
	
	public int getDisplayNumber() {
		return displayNumber;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public int validTextField(JTextField textField) {										//verificare daca textul citit este format din litere si spatii
		if (textField.getText().length() == 0) return 0;
		
		for (int i = 0; i < textField.getText().length(); i++) {
			if (textField.getText().charAt(i) != ' ')
				if ((textField.getText().charAt(i) < '0' || textField.getText().charAt(i) > '9') && textField.getText().charAt(i) != '-' && textField.getText().charAt(i) != '.') return 1;
		}
		
		return 2;
	}
	
	public JTextField getTextField1() throws Exception {
		if (validTextField(textField1) == 0) throw (new Exception("Null"));
		if (validTextField(textField1) == 1) throw (new Exception("Litere1"));
		return textField1;
	}
	
	public JTextField getTextField2() throws Exception {
		if (validTextField(textField2) == 0) throw (new Exception("Null"));
		if (validTextField(textField2) == 1) throw (new Exception("Litere2"));
		return textField2;
	}
	
	public void setTextField1(String str) {
		textField1.setText(str);
	}
	
	public JButton getResetButton() {
		return resetBtn;
	}
	
	public JButton getSumButton() {
		return opBtn[0];
	}
	
	public JButton getDifferenceButton() {
		return opBtn[1];
	}
	
	public JButton getMultiplyButton() {
		return opBtn[2];
	}
	
	public JButton getDivisionButton() {
		return opBtn[3];
	}
	
	public JButton getDerivateButton() {
		return opBtn[4];
	}
	
	public JButton getIntergateButton() {
		return opBtn[5];
	}
	
	public void resetTextField1() {
		textField1.setText("");
	}
	
	public void resetTextField2() {
		textField2.setText("");
	}
	
}