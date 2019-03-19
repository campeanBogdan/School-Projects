package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.Stock;

public class Table {
	
	
//	private TableView table = new TableView();

	
	protected void createTable(LinkedList<Object> obj) {

		String[] col = new String[(int) obj.size()];
		int i = 0;
		
	// iau campurile din cls Product
		while(!(obj.get(i) instanceof Product)) {
			i++;
		}
		
	// Product
		int j = 0;
		
		for (Field field : obj.get(i).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
//				col[j] = field.get(obj.get(i)).toString();
				col[j] = field.getName();
				j++;
			} catch (Exception e) {
			}
		}
		
// iau campurile din calasa Stock		
		while (obj.get(i) instanceof Product) {
			i++;
		}
		
	// Stock
		for (Field field : obj.get(i).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			try {
				col[j] = field.getName();
				j++;
			} catch (Exception e) {
			}
		}
		
// iau valorile campurilor
//		Object[] data = new Object[obj.size()];
		DefaultTableModel model = new DefaultTableModel();
		Vector<Object> vector = new Vector<Object>();
		
		int row = 0, st = 0, pr = 0;
		i = 0;
		
		while (i < obj.size()) {
		
			vector.removeAllElements();
			
			if (obj.get(i) instanceof Product) {
				for (Field field : obj.get(i).getClass().getDeclaredFields()) {
					field.setAccessible(true);
			
						try {	
							vector.add(field.get(obj.get(i)));
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
			}
			
			if (obj.get(i) instanceof Stock) {
				for (Field field : obj.get(i).getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					try {
						vector.add(field.get(obj.get(i)));
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			System.out.println(vector);
			i++;
		}
		

		model.addRow(vector);
		
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
	        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
	    Object columnNames[] = { "Column One", "Column Two", "Column Three" };
	    JTable table = new JTable(rowData, columnNames);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(300, 150);
	    frame.setVisible(true);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		
//		Scene scene = new Scene(new Group());
//		primaryStage.setTitle("PULA");
//		primaryStage.setWidth(600);
//		primaryStage.setHeight(600);
//		
//		Label label = new Label("Adresa");
//		
//		TableColumn first = new TableColumn("First Name");
//		TableColumn second = new TableColumn("Second Name");
//		TableColumn thirs = new TableColumn("Third Name");
//		
//		first.setMinWidth(200);
////		first.setCellValueFactory<String, String>();
//		
//		table.getColumns().addAll(first);
//		table.getColumns().addAll(second);
//		
//		final VBox vbox = new VBox();
//		vbox.setSpacing(5);
////		vbox.setPadding(new Insets(10, 0, 0, 10));
////		vbox.getChildren().add(label, table);
//		vbox.getChildren().add(table);
//		
//		((Group) scene.getRoot()).getChildren().addAll(vbox);
//		
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		
//	}
}
