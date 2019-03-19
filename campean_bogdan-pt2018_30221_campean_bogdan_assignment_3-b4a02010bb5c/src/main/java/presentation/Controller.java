package presentation;

import connection.ConnectionFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.*;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bll.*;
import dal.*;

import presentation.*;
import model.*;

public class Controller {

	private static ConnectionFactory conn = new ConnectionFactory();
	private static GenericRepository<Product> pr = new ProductRepository();
	private static GenericRepository<Stock> sr = new StockRepository();
	private static ProductBLL productBLL = new ProductBLL();
	private static StockBLL stockBLL = new StockBLL();
	private static OrderBLL orderBLL = new OrderBLL();
	private static CustomerBLL customerBLL = new CustomerBLL();
	private static GUI gui = new GUI();
	private static Integer cantitate = new Integer(0);
	private static Customer customer;
	private static JTable table = new JTable();
	private static Table tbl = new Table();
	static LinkedList<Order> orderList = new LinkedList<Order>();
	static LinkedList<Customer> customerList = new LinkedList<Customer>();
	static LinkedList<Product> productList = new LinkedList<Product>();
	static LinkedList<Stock> stockList = new LinkedList<Stock>();
	static LinkedList<Object> objectList = new LinkedList<Object>();
	
	
	public static void main(String[] args) {
		
		
		
		gui.createClientFrame();
		
		
		
//	afisati toate produsele
//	TOATE PRODUSELE
		gui.getBtn1().addActionListener(e-> {
			productList = null;
			stockList = null;
			productList = productBLL.getAllProducts();
			stockList = stockBLL.getAllStock();
			objectList.addAll(productList);
			objectList.addAll(stockList);
//			tbl.createTable(objectList);
//			gui.createTable(objectList);
//			Application.launch(args);
			
			
			gui.createTable(objectList);
			
			gui.resetTextFields();
		
	//selectez o linie pentru a adauga in cos produsele dorite
			gui.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {		     
		        	
		        	if (!event.getValueIsAdjusting()) {
		        	gui.createFrame2();
		        	
		        	
		        //	OK TABEL
		        //	adaug cantitatea aleasa 
		        	gui.getBtn3().addActionListener(e1 -> {
		        		if (gui.getTextField3().getText().isEmpty() || gui.getTextField1().getText().isEmpty() || gui.getTextField2().getText().isEmpty()) JOptionPane.showMessageDialog(gui, "Introduceti datele dvs!");
		        		else {
		        			customer = new Customer(gui.getTextField1().getText(), gui.getTextField2().getText(), gui.getTextField3().getText());
		        			customerBLL.addNewCustomer(customer);
		        			
		        		stockList = stockBLL.getAStock(gui.getTable().getSelectedRow() + 1);
		        		
		        		cantitate = stockList.getFirst().getStock() - Integer.parseInt(gui.getTextField8().getText());
		        		System.out.println(cantitate);
		        		
		        		if (cantitate < 0) JOptionPane.showMessageDialog(gui, "Ati ales prea multe produse");
		        		else {
		        			productList = productBLL.getAProduct(gui.getTable().getSelectedRow() + 1);
		        			stockBLL.updateStock(gui.getTable().getSelectedRow() + 1, cantitate.toString());
		        			
		        			System.out.println("Adaugat la comanda: " + productList.get(0).getName() + " " + gui.getTextField8().getText());
		        		}
		        		gui.getFrame2().setVisible(false);
		        		}
		        	});
		        	
		        	
		        // trimit comanda
		        	gui.getBtn7().addActionListener(e-> {
		        		Order order = new Order();
		        		
		        		System.out.println(productList.isEmpty());
		        		
		        		orderBLL.addNewOrder(order);
	        			orderBLL.makeNewOrder(productList, stockList, customer, order);
		        	});
		        	}
		        }
		    });
		});
		
		
// afisez un produs in functie de nume sau pret
// INTEROGARE
		gui.getBtn0().addActionListener(e-> {
			productList = null;
			stockList = null;
			
			if (gui.valid() == -1) JOptionPane.showMessageDialog(gui, "Introduceti un pret sau nume");
			else {
			if (gui.valid() == 0) {
				productList = productBLL.getNameProduct(gui.getNameTextField().getText());
				stockList = stockBLL.getNameStock(gui.getNameTextField().getText());
				objectList.addAll(productList);
				objectList.addAll(stockList);
			
				gui.createTable(objectList);
			}
			if (gui.valid() == 1) {
				productList = productBLL.getPriceProducts(gui.getPriceTextField());
				stockList = stockBLL.getPriceStock(gui.getPriceTextField());
				objectList.addAll(productList);
				objectList.addAll(stockList);
				
				gui.createTable(objectList);
			}
			
			gui.resetTextFields();
			
			//selectez o linie pentru a adauga in cos produsele dorite
					gui.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				        public void valueChanged(ListSelectionEvent event) {		     
				        	
				        	if (!event.getValueIsAdjusting()) {
				        	gui.createFrame2();
				        	
				        	
				        //	OK TABEL
				        //	adaug cantitatea aleasa 
				        	gui.getBtn3().addActionListener(e1 -> {
				        		if (gui.getTextField3().getText().isEmpty() || gui.getTextField1().getText().isEmpty() || gui.getTextField2().getText().isEmpty()) JOptionPane.showMessageDialog(gui, "Introduceti datele dvs!");
				        		else {
				        			customer = new Customer(gui.getTextField1().getText(), gui.getTextField2().getText(), gui.getTextField3().getText());
				        			customerBLL.addNewCustomer(customer);
				        			
				        		stockList = stockBLL.getAStock(gui.getTable().getSelectedRow() + 1);
				        		
				        		cantitate = stockList.getFirst().getStock() - Integer.parseInt(gui.getTextField8().getText());
				        		System.out.println(cantitate);
				        		
				        		if (cantitate < 0) JOptionPane.showMessageDialog(gui, "Ati ales prea multe produse");
				        		else {
				        			productList = productBLL.getAProduct(gui.getTable().getSelectedRow() + 1);
				        			stockBLL.updateStock(gui.getTable().getSelectedRow() + 1, cantitate.toString());
				        			
				        			System.out.println("Adaugat la comanda: " + productList.get(0).getName() + " " + gui.getTextField8().getText());
				        		}
				        		gui.getFrame2().setVisible(false);
				        		}
				        	});
				        	
				        	
				        // trimit comanda
				        	gui.getBtn7().addActionListener(e-> {
				        		Order order = new Order();
				        		
				        		System.out.println(productList.isEmpty());
				        		
				        		orderBLL.addNewOrder(order);
			        			orderBLL.makeNewOrder(productList, stockList, customer, order);
				        	});
				        	}
				        }
				    });
						
			
			}
			gui.resetTextFields();
		});
		
		
	gui.getBtn2().addActionListener(e-> {
		Product newProduct = new Product();
		Stock newStock = new Stock();
		
		try {
			newProduct.setName(gui.getTextfield5().getText());
			newProduct.setPrice(Integer.parseInt(gui.getTextfield6().getText()));
			newStock.setStock(Integer.parseInt(gui.getTextfield7().getText()));
		
			productBLL.addNewProduct(newProduct);
			stockBLL.addNewStock(newStock);
			
			JOptionPane.showMessageDialog(null, "Produsul a fost adaugat cu succes!");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(gui, "Campuri invalide");
		}
		
		gui.resetTextFields();
	});
	
	
// UPDATE
	gui.getBtn4().addActionListener(e-> {
		productList = null;
		stockList = null;
		productList = productBLL.getAllProducts();
		stockList = stockBLL.getAllStock();
		
		productBLL.updateProduct(gui.getTextfield5().getText(), gui.getTextfield6().getText());
		

		gui.resetTextFields();

	});
	
	
	// CLIENTI
		gui.getBtn5().addActionListener(e-> {
			customerList = customerBLL.getAllCustomers();
		});
		
	}
}
