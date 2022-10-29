package it.polito.po;

public class Quiz {
	final static public String[] questions = {
	"Cosa puo' dimostrare un test?",
	"In seguito a quale operazione SVN incrementa il numero di revision?",
	"A cosa serve il diamond operator '<>' ?"
	};
	final static public String[][] options = {
	{
		"La correttezza del programma",
		"La presenza di difetti nel programma",
		"L'assenza di difetti nel programma",
		"L'usabilita' del programma",
		"La qualita' del programma"	},
	{
		"Delete",
		"Commit",
		"Add",
		"Check-out",
		"Update"	},
	{
		"A indicare una co-varianza di tipo",
		"A indicare uno scambio di valori atomico",
		"A confrontare due valori differenti",
		"A indicare un'inferenza di tipo per una classe generica",
		"A indicare una riferimento ad un metodo"	}
	};
	
	/**
	 * Return the index of the right answer(s) for the given question 
	 */
	public static int[] answer(int question){
		// TODO: answer the question
		
		switch(question){
			case 0:int[] a = {1}; return a; // replace with your answers
			case 1: int[] b = {1}; return b; // replace with your answers
			case 2: return new int[] {3}; // replace with your answers
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

