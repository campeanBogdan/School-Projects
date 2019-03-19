import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;


public class Person implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;
	private static Integer idC = 0;
	
	
	public Person(String name) {
		this.name = name;
		this.id = new Integer(idC++);
	}
	
	
	public static void setStatidId(Integer id) {
		Person.idC = id;
	}
	
	public static Integer getStaticId() {
		return Person.idC;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		JOptionPane.showMessageDialog(null, arg1, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(arg1);
	}
}
