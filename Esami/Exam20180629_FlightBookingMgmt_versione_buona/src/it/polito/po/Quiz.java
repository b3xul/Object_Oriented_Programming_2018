package it.polito.po;

public class Quiz {
	final static public String[] questions = {
	"Quali di queste affermazioni sono valide per un comparatore? / Which among the following statements are correct for a comparator?",
	"Quali di queste affermazioni sono valide per lo sviluppo agile? / Which among the following statements are correct for agile development?",
	"Quali di queste affermazioni sono valide? / Which among the following statements are correct?"
	};
	final static public String[][] options = {
	{
		"Un comparatore deve contenere solo metodi statici / A comparator must contain static methods only",
		"Un comparatore implementa il metodo compare() / A comparator implements method compare()",
		"Un comparatore e' un oggetto che implementa l'interfaccia Comparator<T> / A comparator is an object implementing interface Comparator<T>",
		"Un comparatore contiene dei metodi astratti / A comparator contains abstract methods",
		"Un comparatore si pu?? passare al costruttore di un HashSet / A comparator can be passed as an argument to the constructor of HashSet"	},
	{
		"durante un'iterazione di tengono in considerazione quelle future / during an iteration all the future ones are considered",
		"e' facile adattarsi al cambiamento dei requisiti / it is easy to adapt to changing requirements",
		"e' essenziale il coinvolgimento stretto del committente / close involvement of the customer is essential",
		"e' necessario che i requisiti siano stabili / stable requirements are required",
		"e' importante produrre una documentazione completa / producing a complete documentation is important"	},
	{
		"Il pair programming pu?? facilitare il training on the job dei neoassunti. / Pair programming may ease training on the job for new employees",
		"La disponibilit?? (availability) e l'affidabilit?? (reliability) sono attributi di qualit?? interni. / Availability and reliability are internal quality attributes",
		"Il CMM ha l'obiettivo di rivoluzionare il processo di sviluppo del software. / CMM has the goal of revolutionizing software development",
		"La propriet?? collettiva e' un obiettivo della programmazione estrema. / Collective ownership is an extreme programming goal",
		"Nella programmazione estrema il refactoring si riferisce alla pianificazione incrementale dello sviluppo del software. / In extreme programming, refactoring refers to the incremental planning of software development"	}
	};
	
	/**
	 * Return the index of the right answer(s) for the given question 
	 */
	public static int[] answer(int question){
		// TODO: answer the question
		
		switch(question){
			case 0: return null; // replace with your answers
			case 1: return null; // replace with your answers
			case 2: return null; // replace with your answers
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

