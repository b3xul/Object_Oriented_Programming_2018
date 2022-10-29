package trail;

class Delegate {
	String name;
	String surname;
	String id;

	public Delegate(String name, String surname, String id) {
		this.name = name;
		this.surname = surname;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getId() {
		return id;
	}
	
	public String toString(){
		return surname + ", " + name + ", " + id;
	}

}
