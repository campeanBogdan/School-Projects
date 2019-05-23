package bll;
import java.util.LinkedList; 

import dal.GenericRepository;
import dal.StockRepository;
import dal.ProductRepository;
import model.Product;
import model.Stock;


public class StockBLL {

	private GenericRepository<Stock> stockRep = new StockRepository();
	private GenericRepository<Product> productRep = new ProductRepository();
	
	
//	iau un stock
	
	public Stock getStock(int id) {
		return stockRep.selectById(id);
	}

//	toate stocurile
	
	public LinkedList<Stock> getAllStock() {
		int i = 1;
		LinkedList<Stock> list = new LinkedList<Stock>();
		
		while (stockRep.selectById(i) != null) {
			list.add((Stock) stockRep.selectById(i));
			i++;
		} 
		
		return list;
	}
	
	
//	stocurile care au pretul <= price
	
	public LinkedList<Stock> getPriceStock(int price) {
		LinkedList<Stock> list = new LinkedList<Stock>();
		int i = 1;
		
		while (stockRep.selectById(i) != null) {
			Product product = productRep.selectById(i);
			
			if (product.getPrice() <= price) list.add((Stock) stockRep.selectById(i));
			i++;
		}
		
		return list;
	}
	
	
	public LinkedList<Stock> getAStock(Integer id) {
		LinkedList<Stock> list = new LinkedList<Stock>();
		
		Stock stock = stockRep.selectById(id);
		list.add(stock);
		
		return list;
	}
	
	
	public void updateStock(Integer id, String value) {
		Stock stock = stockRep.selectById(id);
		
		stockRep.updateObject(id, value);
	}
	
	
// stourile cu un nume
	
	public LinkedList<Stock> getNameStock(String name) {
		LinkedList<Stock> list = new LinkedList<Stock>();
		int i = 1;
		
		while (productRep.selectById(i) != null) {
			Product product = productRep.selectById(i);
			
			if (product.getName().equals(name)) {
				list.add((Stock) stockRep.selectById(i));
			}
			i++;
		}
		
		return list;
	}
	
	
	public void addNewStock(Stock stock) {
		stockRep.insertObject(stock);
	}
}
