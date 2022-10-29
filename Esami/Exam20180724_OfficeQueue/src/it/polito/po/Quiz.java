package it.polito.po;

public class Quiz {
	final static public String[] questions = {
	"Quali di queste affermazioni sono valide per una espressione lambda? / Which among the following statements are correct for a lambda expression?",
	"Quali di queste affermazioni sono valide? / Which among the following statements are correct?",
	"Quali di queste affermazioni sono valide? / Which among the following statements are correct?"
	};
	final static public String[][] options = {
	{
		"Una lambda pu?? sostituire qualsiasi method reference / A lambda can replace any method reference",
		"Una lambda implementa sempre una classe astratta / A lambda implements always an abstract class",
		"Una lambda implementa solitamente i metodi di default / A lambda usually implements defaults methods",
		"Una lambda pu?? implementare un iteratore / A lambda can implement an iterator",
		"Una lambda implementa una interfaccia funzionale / A lambda implements a functional interface"	},
	{
		"Il test black box non considera la struttura interna del codice / Black box testing does not consider internal data structure",
		"Un design pattern e'una struttura dati per riconoscere sequenze di caratteri / A design pattern is a data structure to recognize character sequences",
		"La validazione considera se il prodotto e' conforme ai requisiti / Validation is the evaluation of whether the product complies with requirements",
		"L'approccio waterfall manca di flessibilita' / The waterfall model lacks flexibility",
		"L'integrazione continua e' la presa in carico di nuovi requisiti / Continuous integration consists in taking in charge new requirements"	},
	{
		"L'interfaccia Comparator ?? un'interfaccia funzionale. / Interface Comparator is a functional interface.",
		"L'interfaccia Comparator non contiene metodi statici / Interface Comparator does not contain static methods",
		"Il metodo compareTo() si trova nell'interfaccia Comparator / Method compareTo() is in interfaces Comparator",
		"L'interfaccia Comparable non contiene metodi statici / Interface Comparable does not contain static methods",
		"L'interfaccia Comparable ?? usata nell'ordinamento naturale degli oggetti di una classe. / Interface Comparable is used in the natural ordering of the objects of a class"	}
	};
	
	/**
	 * Return the index of the right option(s) for the given question 
	 * If no option is correct return an empty array
	 */
	public static int[] answer(int question){
		// TODO: answer the question
		
		switch(question){
			case 0: return new int[] {4}; // replace with your answers
			case 1: return new int[] {0,3}; // replace with your answers
			case 2: return new int[] {0}; // replace with your answers
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

