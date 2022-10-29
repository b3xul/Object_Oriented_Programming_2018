package warehouse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Supplier {

	private String code;
	private String name;
	private Map<String,Product> products=new HashMap<>();
	
	public Supplier(String code, String name) {
		this.code=code;
		this.name=name;
	}
	public String getCodice() {
		return code;
	}
	public String getNome() {
		return name;
	}
	
	public void newSupply(Product product){
		product.addSupplier(this);
		products.put(product.getCode(), product);
		
	}
	
	public List<Product> supplies(){
		return products.values().stream()
				.sorted(Comparator.comparing(Product::getDescription))
				.collect(Collectors.toList());
	}
}
