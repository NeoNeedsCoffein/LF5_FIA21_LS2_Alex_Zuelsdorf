package main;

/**
 * @author Alex Zuelsdorf
 * @version 1.0
 */
public class Cargo {
	
	private String name;
	private int amount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * @param name
	 * @param amount
	 */
	public Cargo(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	// Override of toString() method
	@Override
	public String toString() {
		return "Cargo: " + this.getClass() + " Name: " + this.getName() + " Menge: " + this.getAmount();
	}

}
