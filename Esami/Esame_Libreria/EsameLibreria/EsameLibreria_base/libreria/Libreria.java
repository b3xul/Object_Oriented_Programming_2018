package libreria;

import java.util.Collection;

public class Libreria {

    public Editore creaEditore(String nome, int tempoConsegna, String email){
        return null;
    }

    public Editore getEditore(String nome){
        return null;
    }

    public Collection getEditori(){
        return null;
    }

    public Libro creaLibro(String titolo, String autore, int anno, double prezzo, String nomeEditore)
    			throws EditoreInesistente {
        return null;
    }
    
    public Libro getLibro(String autore, String titolo){
        return null;
    }
    
    public Collection getClassificaSettimana(final int settimana){
        return null;
    }
    
    public Collection getClassificaMese(final int mese){
        return null;
    }
    
    public Collection getOrdini(){
        return null;
    }
    
    public void ordineRicevuto(int numOrdine){
    }
    
    public void leggi(String file){
    }
}
