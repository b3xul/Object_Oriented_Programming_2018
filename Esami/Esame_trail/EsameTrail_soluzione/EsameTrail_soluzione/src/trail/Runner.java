package trail;

public class Runner {
	private int pettorale;
	private String name;
	private String surname;
	private Passage lastPassage;
    
    public Runner(int i, String name, String surname) {
		pettorale = i;
		this.name =name;
		this.surname = surname;
	}

	public int getBibNumber(){
        return pettorale;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

	void addPassage(Passage passage) {
		lastPassage = passage;
	}
	
	int lastNum(){
		return lastPassage.getLocation().getOrderNum();
	}
	
	long lastTime(){
		return lastPassage.getTime();
	}
	
	
	Location lastLocation(){
		return lastPassage.getLocation();
	}

	Passage lastPassage(){
		return lastPassage;
	}

}
