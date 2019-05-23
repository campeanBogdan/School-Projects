package bll;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Product;
import dal.ProductRepository;
import dal.GenericRepository;


public class ProductBLL {

	private GenericRepository<Product> prodRep = new ProductRepository();
	
	
	public void updateProduct(String name, String value) {
		Product product = prodRep.selectByName(name);
		
		prodRep.updateObject(product.getId(), value);
	}
	
	
// returnez o lista cu toate produsele
	
	public LinkedList<Product> getAllProducts() {
		int i = 1;
		LinkedList<Product> list = new LinkedList<Product>();
		
		while (prodRep.selectById(i) != null) {
			list.add((Product) prodRep.selectById(i));
			i++;
		}
		
		return list;	
	}
	
	
// returnez o lista cu toate produsele cu nume
	
	public LinkedList<Product> getNameProduct(String name) {
		LinkedList<Product> list = new LinkedList<Product>();
		int i = 1;
		
		while (prodRep.selectById(i) != null) {
			Product pr = prodRep.selectById(i);
			
			if (pr.getName().equals(name))
				list.add(pr);
			i++;
		}
		
		return list;
	}
	
	
	public LinkedList<Product> getAProduct(Integer id) {
		LinkedList<Product> list = new LinkedList<Product>();
		
		Product pr = prodRep.selectById(id);
		list.add(pr);
		
		return list;
	}
	
	
//returenz o lista cu toate produsele care au pretul mai mic decat 'price'
	
	public LinkedList<Product> getPriceProducts(int price) {
		LinkedList<Product> list = new LinkedList<Product>();
		int i = 1;
		
		while (prodRep.selectById(i) != null) {
			Product pr = prodRep.selectById(i);
			
			if (pr.getPrice() <= price) list.add(pr);
			i++;
		}
		
		return list;
	}
	

// adaug un produs nou + cantitatea
	
	public void addNewProduct(Product product) {
		prodRep.insertObject(product);
	}
}
