package it.polito.po;

public class Quiz {
	final static public String[] questions = {
	"Cosa si trova nella sezione inferiore di una classe UML? / What is specified in the bottom section of a UML class?",
	"Che operazione crea una copia locale di un repository SVN? What operation makes a local copy of an SVN repository?",
	"Cosa puo' dimostrare un test?"
	};
	final static public String[][] options = {
	{
		"Lista degli attributi / List of attributes",
		"Dettagli implementativi / Implementation details",
		"Lista dei metodo / List of methods",
		"Nome della classe / Name of the class",
		"Lista dei metodi statici / List of static methods"	},
	{
		"Check-out",
		"Lock",
		"Unlock",
		"Update",
		"Merge"	},
	{
		"La correttezza del programma",
		"La qualita' del programma",
		"La presenza di difetti nel programma",
		"La manutenibilita' del programma",
		"L'assenza di difetti nel programma"	}
	};
	
	/**
	 * Return the index of the right answer(s) for the given question 
	 */
	public static int[] answer(int question){
		// TODO: answer the question
		
		switch(question){
			case 0: return new int[]{2}; // replace with your answers
			case 1: return new int[]{2}; // replace with your answers
			case 2: return new int[]{2}; // replace with your answers
		}
		return null; // means: "No answer"
	}

	/**
	 * When executed will show the answers you selected
	 */
	public static void main(String[] args){
		for(int q=0; q<questions.length; ++q){
			System.out.println("Question: " + questions[q]);
			int[] a = answer(q);
			if(a==null || a.length==0){
				System.out.println("<undefined>");
				continue;
			}
			System.out.println("Answer" + (a.length>1?"s":"") + ":" );
			for(int i=0; i<a.length; ++i){
				System.out.println(a[i] + " - " + options[q][a[i]]);
			}
		}
	}
}

