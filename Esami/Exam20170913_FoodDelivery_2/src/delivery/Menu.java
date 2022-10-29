package delivery;

public class Menu implements Comparable<Menu>{
	private String description;
	private double price;
	private String category;
	private int prepTime;
	public Menu(String description, double price, String category, int prepTime) {
		super();
		this.description = description;
		this.price = price;
		this.category = category;
		this.prepTime = prepTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	
	public String toString(){
		return "[" + category + "]" + " " + description + " " + ":" + " " + String.format("%.2f", price);
	}
	@Override
	public int compareTo(Menu arg0) {
		      if(  this.description.equals(arg0.description) &&
			    this.price==(arg0.price) &&
			    this.category.equals(arg0.category) &&
			    this.prepTime==arg0.prepTime)
		    	  return 1;
		      else 
		    	  return -1;
		 
	}
		

}
