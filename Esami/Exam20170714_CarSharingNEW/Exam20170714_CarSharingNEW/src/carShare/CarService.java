package carShare;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class CarService {
	
	public void addParking(String name) throws InvalidName{
	}

	public void addCar(String parking, String licencePlate, double minRate, double kmRate) throws InvalidName{
	}
		
	public SortedSet<String> getAvailableCars(String parking) throws InvalidName{
		return null;
	}
	
	public void addSubscriber(String card) throws InvalidName{
	}
	
	public List<String> getSubscribers(){
		return null;
	}
	
	public String reserve(String card, String parking) throws InvalidName{
		return null;
	}
	
	public String release(String card, String plate) throws InvalidName{
		return null;
	}
	
	public Set<String> getReserved(String parking) throws InvalidName{
		return null;
	}
	
	public String useCar(String card, String plate) throws InvalidName{
		return null;
	}
	
	public String terminate(String card, String plate, String parking, int min, int km) throws InvalidName{
		return null;
	}

	public List<String> charges() {
		return null;
	}
		
	public List<String> subscriberCharges(String card) throws InvalidName{
		return null;
	}
	
	public double averageCharge() {
		return -1.0;
	}
	
	public long departuresFrom(String parking) throws InvalidName{
		return -1;
	}
}
