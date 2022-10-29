package gestioneTaxi;

public class MainClass {


	public static void main(String[] args) {
        TaxiCompany company = new TaxiCompany();
        Luogo luogo1 = new Luogo("Via Roma 10" , "centro");
        Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
        Luogo luogo3 = new Luogo("Corso Duca Abruzzi 24", "crocetta");
        Passeggero passeggero1 = new Passeggero(luogo1);
        Passeggero passeggero2 = new Passeggero(luogo1);
        Passeggero passeggero3 = new Passeggero(luogo3);
        try{
        	company.addTaxi("Taxi1");
        	company.addTaxi("Taxi2");
        	company.addTaxi("Taxi1");
        }
        catch (InvalidTaxiName itn){
        	System.out.println(itn);
        }
        Taxi t = company.chiamataTaxi(passeggero1);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi1
        }
        else
        	System.out.println("Richiesta  rifiutata");
        t.inizioCorsa(luogo2);
        System.out.println( t + ": partito da " + passeggero1.getPosizione() + ", destinazione " + luogo2);
        																// Taxi1: partito da Via Roma 10, destinazione Corso Francia 105
        t.fineCorsa();
        System.out.println(t + ": arrivato in " + passeggero1.getPosizione());	// Taxi1: arrivato in Corso Francia 105
        
        t = company.chiamataTaxi(passeggero2);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi2
        }
        else
        	System.out.println("Richiesta  rifiutata");
        
        t.inizioCorsa(luogo3);
        System.out.println( t + ": partito da " + passeggero2.getPosizione() + ", destinazione " + luogo3);
        																// Taxi2: partito da Via Roma 10, destinazione Corso Duca Abruzzi 24
        t.fineCorsa();
        System.out.println(t + ": arrivato in " + passeggero2.getPosizione());	// Taxi2: arrivato in Corso Duca Abruzzi 24
        
        t = company.chiamataTaxi(passeggero1);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi1
        }
        else
        	System.out.println("Richiesta  rifiutata");
        t.inizioCorsa(luogo3);
        System.out.println( t + ": partito da " + passeggero1.getPosizione() + ", destinazione " + luogo3);
        t.fineCorsa();
        System.out.println(t + ": arrivato in " + passeggero1.getPosizione());
        
        t = company.chiamataTaxi(passeggero1);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi2
        }
        else
        	System.out.println("Richiesta  rifiutata");
        
        t = company.chiamataTaxi(passeggero2);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi1
        }
        else
        	System.out.println("Richiesta  rifiutata");
        
        t = company.chiamataTaxi(passeggero3);
        if (t != null){
        	System.out.println("Assegnato " + t);
        }
        else
        	System.out.println("Richiesta  rifiutata");					// Richiesta  rifiutata
        
        System.out.println("Corse effettuate da Taxi1");
        try{
        for(Corsa c : company.getCorse("Taxi1"))
        	System.out.println("  " + c);								//   Via Roma 10,Corso Francia 105
        }																//   Corso Francia 105,Corso Duca Abruzzi 24
        catch (InvalidTaxiName itn){
        	System.out.println(itn);									
        }
                
        System.out.println("Corse effettuate da Taxi2");
        try{
        for(Corsa c : company.getCorse("Taxi2"))
        	System.out.println("  " + c);								//   Via Roma 10,Corso Duca Abruzzi 24
        }
        catch (InvalidTaxiName itn){
        	System.out.println(itn);									
        } 
        System.out.println("Corse perse: " + company.getCorsePerse());	// Corse perse: 1
   	
        System.out.println("Statistiche Taxi:");
        for(InfoI info : company.statisticheTaxi())
        	System.out.println(info.getId() + " " + info.getValore());	//  Taxi1 2
        																//  Taxi2 1
        
        System.out.println("Statistiche Quartieri:");
        for(InfoI info : company.statisticheQuartieri())
        	System.out.println(info.getId() + " " + info.getValore());	//  crocetta 2
																		//  cit turin 1
	}
} 
