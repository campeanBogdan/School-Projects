package bll;

import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Customer;
import model.Order;
import model.Product;
import model.Stock;


public class PDFDocument {

//	private static String FILE = "F:/POLI/Tehnici de Programare/PT2018/PT2018_30221_Campean_Bogdan/Homework_3/Project3/Comanda nr. ".pdf";

	
	public PDFDocument() {
		
	}
	
	
	public void createPDF(Customer customer, LinkedList<Product> productList, LinkedList<Stock> stockList, Order order) {
		
		String FILE = "F:/POLI/Tehnici de Programare/PT2018/PT2018_30221_Campean_Bogdan/Homework_3/Project3/Comanda nr. " + order.getId() + ".pdf";
		Document document = new Document();
		int suma = 0;

		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			
			document.open();
			document.addTitle("Comanda nr. " + order.getId());
			
			document.addAuthor("Producator");
			document.add(new Paragraph("Comanda numarul: " + customer.getId()));
			document.add(new Paragraph());
			document.add(new Paragraph("Nume: " + customer.getName()));
			document.add(new Paragraph("Adresa: " + customer.getAddress()));
			document.add(new Paragraph("Numar telefon: " + customer.getTelNo()));
			document.add(new Paragraph("Produse: "));
			
			Iterator<Product> itP = productList.iterator();
			Iterator<Stock> itS = stockList.iterator();
			
			while (itP.hasNext() && itS.hasNext()) {
				Product pr = itP.next();
				Stock st = itS.next();
				suma += pr.getPrice() * st.getStock();
				document.add(new Paragraph("Nume produs: " + pr.getName() + " | Pret: " + pr.getPrice() + " | Stoc: " + st.getStock()));
			}
			
			document.add(new Paragraph("Suma totala: " + suma));
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
