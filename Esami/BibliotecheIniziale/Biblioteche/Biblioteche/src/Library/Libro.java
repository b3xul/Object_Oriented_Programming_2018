package Library;

public class Libro implements Comparable<Libro>{
	private String autori;
	private String titolo;
	private String editore;
	private String isbn;

	public Libro (String a, String t, String e, String i) {
		autori = a;
		titolo = t;
		editore = e;
		isbn = i;
	}
	
	public String getIsbn () {
		return isbn;
	}
	
	public String getAutori () {
		return autori;
	}
	
	public String toString(){
		return autori + ";" + titolo + ";"+ editore + ";"+ isbn;
	}
	
	public int compareTo(Libro l) {
		return autori.compareTo(l.getAutori());
	}
}
