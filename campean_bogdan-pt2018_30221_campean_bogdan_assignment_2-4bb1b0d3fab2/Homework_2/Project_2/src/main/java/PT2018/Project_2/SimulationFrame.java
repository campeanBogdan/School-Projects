package PT2018.Project_2;

import java.awt.*;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.*;

public class SimulationFrame extends JFrame {

		private JFrame frame = new JFrame("Simulation");
		private JPanel panel = new JPanel();
		public JLabel[] label = new JLabel[10];
		private JLabel[] colorLabel = new JLabel[20];
		public JLabel[][] queueLabel = new JLabel[50][50];
		private JTextField[] textField = new JTextField[10];
		private JButton[] btn = new JButton[10];
		private LinkedList<Label> labelList;
		public Integer timpScurs = new Integer(0);
		private String time = new String();
		public int noOfServers = 0;
		private String[][] labelMatrix = new String[50][50];
		private int decServerQueue = 0;
		public int index = 0;
		
		
		
		public void setStrategy(SelectionPolicy sp) {
			
			label[5].setText("Strategie:   " + sp.toString());
		}
		
		
		public void refreshMatrix() {
			
			for (Integer i = 0; i < 50; i++)
				for (Integer j = 0; j < 50; j++)
					queueLabel[j][i].setText(this.labelMatrix[i][j]);
		}
		
		
		public void removeServerFromFrame() {
			
			for (int i = 0; i < 50; i++)
				labelMatrix[i][index-1] = "-";
			
			colorLabel[index-1].setText("");
		}
		
		public void removeTaskFromFrame(int row) {
			
			for (int i = 0; i < 49; i++) {
				labelMatrix[i][row] = labelMatrix[i+1][row];
			}
			
			labelMatrix[19][row] = "-";
			
			decServerQueue = row;
		}
		
		
		public int getDecrementServerQueue() {
			return decServerQueue;
		}
		
		
		public void substractFromLabels() {
			Integer nr = new Integer(0);
			
			for (int i = 0; i < 15; i++)
				for (int j = 0; j < 20; j++) {
					if (!labelMatrix[j][i].equals("-")) {
						nr = Integer.parseInt(labelMatrix[j][i]);
					
						if (nr.equals(0)) {
							removeTaskFromFrame(i);
						}
						else {
							nr--;
							labelMatrix[j][i] = nr.toString();
						}
					}
				}
		}
		
		
		public void addTaskOnFrame(int row, String arg) {
			int i = 0;
			
			while (i < 50 && !labelMatrix[i][row].equals("-"))
				i++;
			
			labelMatrix[i][row] = arg;
		}
		
		
		public void setLabelTrue() {
			Font font = new Font("TimeRoman", Font.BOLD, 20);
			
			for (int i = 0; i < 50; i++)
				for (int j = 0; j < 50; j++) {
					queueLabel[j][i].setLocation(700 + i * 70, 100 * j);
				}
			
			for (int i = 0; i < index; i++) {
				colorLabel[i].setVisible(true);
			}
			
			for (int i = index; i < 20; i++) {//				
				colorLabel[i].setVisible(false);
			}
		}
		
		
		public String toHour(Integer time) {
			
			String hourS = new String("00");
			String minutesS = new String("00");
			String secondsS = new String("00");
			String finalTime = new String();
			
			Integer hourI = new Integer(0);
			Integer minutesI = new Integer(0);
			Integer secondsI = new Integer(0);		
			
			minutesI = time / 60;
			secondsI = time % 60;
				
			minutesS = minutesI.toString();
			secondsS = secondsI.toString();
				
			if (hourI < 10) finalTime = hourS;
			else finalTime = hourS;
				
			if (minutesI < 10) finalTime = finalTime + ":0" + minutesS;
			else finalTime = finalTime + ":" + minutesS;
				
			if (secondsI < 10) finalTime = finalTime + ":0" + secondsS;
			else finalTime = finalTime + ":" + secondsS;
		
			return finalTime;
		}
		
		
		public void setPeakHour(String peakHour) {
			label[7].setText("Ora de varf: " + peakHour);
		}
		
		public void setAverageWaitingTime(String averageTime) {
			label[6].setText("Timp mediu asteptare: " + averageTime);
		}
		
		
		public void setTimeFrame(Integer timpScurs) {
			label[0].setText("<html><u>Timp scurs</u>:   " + toHour(timpScurs) + "</html>");
		}
		
		
		public void createSimulationFrame() {
			Font font = new Font("TimeRoman", Font.BOLD, 30);
			Font font2 = new Font("TimesRoman", Font.BOLD, 30);
			Font font3 = new Font("TimesRoman", Font.PLAIN, 12);
			
			
			panel.setLayout(null);
			
			for (int i = 0; i < 50; i++) 
				for (int j = 0; j < 50; j++)
					labelMatrix[j][i] = new String("-");
			
			for (int i = 0; i < 20; i++) {
				colorLabel[i] = new JLabel("Open");
				colorLabel[i].setSize(30, 30);
				colorLabel[i].setFont(font3);
				colorLabel[i].setLocation(600, 100 * i);
				panel.add(colorLabel[i]);
			}
			
			labelList = new LinkedList<Label>();
			
			frame.setExtendedState(MAXIMIZED_BOTH);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setContentPane(panel);
			
			
			for (int i = 0; i < 10; i++) {
				label[i] = new JLabel();
				label[i].setSize(450, 50);
				label[i].setFont(font);
				panel.add(label[i]);
			}
			
			for (Integer i = 0; i < 50; i++) 
				for (Integer j = 0; j < 50; j++) {
					queueLabel[j][i] = new JLabel("-");
					queueLabel[j][i].setSize(70, 70);
					queueLabel[j][i].setFont(font2);
					panel.add(queueLabel[j][i]);
				}
			
			for (int i = 0; i < 10; i++) {
				btn[i] = new JButton();
				btn[i].setSize(150, 50);
			}
			
		// timp scurs
			label[0].setLocation(10, 10);
			label[0].setText("<html><u>Timp scurs</u>:   " + toHour(timpScurs) + "</html>");
			
			this.revalidate();
		//adaugare date
		//timp limita
			label[1].setLocation(10, 200);
			label[1].setText("Timp limita:");
			
		//timp min procesare
			label[2].setLocation(10, 250);
			label[2].setText("Timp min. procesare:");
			
		//timp max procesare
			label[3].setLocation(10, 300);
			label[3].setText("Timp max. procesare:");
			
		//nr servere
			label[4].setLocation(10, 350);
			label[4].setText("Numar servere:");
			
		// strategie
			label[5].setLocation(10, 400);
			label[5].setText("Strategie:   " + toHour(timpScurs));
			
		// timp mediu asteptare
			label[6].setLocation(10, 450);
			label[6].setText("Timp mediu asteptare: ");
			label[6].setForeground(Color.BLUE);
			
		// ora de varf
			label[7].setLocation(10, 500);
			label[7].setText("Ora de varf: ");
			label[7].setForeground(Color.BLUE);
			label[7].setSize(700, 50);
			
		//////////////////////////////////////////////////
			for (int i = 0; i < 10; i++) {
				textField[i] = new JTextField("");
				textField[i].setSize(50, 20);
			}
			
		// textfield0
			textField[0].setLocation(400, 200);
			panel.add(textField[0]);
			
		//
			textField[1].setLocation(400, 250);
			panel.add(textField[1]);
			
		//
			textField[2].setLocation(400, 300);
			panel.add(textField[2]);
			
		//
			textField[3].setLocation(400, 350);
			panel.add(textField[3]);
			
		//////////////////////////////////////////////
		//buton adaugare date
			btn[0].setLocation(50, 700);
			btn[0].setText("Adaugtati Datele");
			panel.add(btn[0]);
			
		// buton adaugare task
			btn[1].setLocation(250, 700);
			btn[1].setText("Adaugati Task");
			panel.add(btn[1]);
			
		// buton incepere simulare
			btn[2].setLocation(50, 800);
			btn[2].setText("Start Simulare");
			btn[2].setForeground(Color.BLUE);
			panel.add(btn[2]);
			
		// buton pt schimbarea strategiei
			btn[3].setLocation(250, 800);
			btn[3].setText("Strategie");
			panel.add(btn[3]);
			
		// buton pt oprirea simularii
			btn[4].setLocation(50, 900);
			btn[4].setText("Stop Simulare");
			btn[4].setForeground(Color.RED);
			panel.add(btn[4]);
			
		// buton pt adaugarea unui server nou
			btn[5].setLocation(250, 900);
			btn[5].setText("Adaugati Server");
			panel.add(btn[5]);
			
		// buton pt stergerea ultimlui server
			btn[6].setLocation(50, 600);
			btn[6].setText("Stergeti server");
			panel.add(btn[6]);
		}
		
		
		public JButton getBtn0() {
			return btn[0];
		}
		
		public JButton getBtn1() {
			return btn[1];
		}
		
		public JButton getBtn2() {
			return btn[2];
		}
		
		public JButton getBtn3() {
			return btn[3];
		}
		
		public JButton getBtn4() {
			return btn[4];
		}
		
		public JButton getBtn5() {
			return btn[5];
		}
		
		public JButton getBtn6() {
			return btn[6];
		}
		
		public JTextField getTextField0() {
			return textField[0];
		}
		
		public JTextField getTextField1() {
			return textField[1];
		}
		
		public JTextField getTextField2() {
			return textField[2];
		}
		
		public JTextField getTextField3() {
			return textField[3];
		}
		
		public void resetTextFields() {
			for (int i = 0; i < 4; i++)
				textField[i].setText("");
		}
		
}
