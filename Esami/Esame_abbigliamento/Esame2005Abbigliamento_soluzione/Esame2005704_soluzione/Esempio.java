import abbigliamento.*;

import java.util.Collection;
import java.util.Iterator;

public class Esempio {
  public static void main(String args[]) throws Exception {

    // modello con nome top, 5.0 euro di costo fisso, richiede 0.2 mq di materiale
	Modello top = new Modello("top", 5.0 , 0.2);
	Modello tshirt = new Modello("t-shirt", 6.0, 1.0);

    // materiale cotone, costo 0.50 euro / mq 
	Materiale cotone = new Materiale("cotone",0.50);
	Materiale lino = new Materiale("lino",1.0);

	Colore rosa = new Colore("rosa");
	Colore bianco = new Colore("bianco");

	Capo c1 = new Capo(top,cotone,rosa);
	Capo c2 = new Capo(tshirt,lino,bianco);
	Capo c3 = new Capo(tshirt,cotone,bianco);

	System.out.println("Capo: " + c1 + "Capo: " + c1.prezzo());

	Collezione estate = new Collezione();
	estate.add(c1);
	estate.add(c2);
	estate.add(c3);

	Collection capiBianchi = estate.trova(bianco);
	System.out.println("Capi bianchi: " + capiBianchi);		
  }
}