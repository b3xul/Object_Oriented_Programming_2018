package test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import abbigliamento.Abbigliamento;
import abbigliamento.Capo;
import abbigliamento.Colore;
import abbigliamento.Materiale;
import abbigliamento.Modello;

import junit.framework.TestCase;

public class TestR5_File extends TestCase {

	  private static void writeFile(String fileName,String content) {
	  	try{
	  		FileOutputStream fos = new FileOutputStream(fileName);
	  		fos.write(content.getBytes());
	  		fos.close();
		}catch(IOException ioe){
			throw new RuntimeException(ioe.getMessage());
		}
	  }
	  
	  private String file = "abbigliamento.txt";

	  private Abbigliamento abb;
	  
	  public void setUp() throws Exception {
	  	String content = 
	  		"MOD;pantalone;5.0;2.0\n"
	  		+"MOD;camicia;9.0;1.5\n"
	  		+"COL;blu\n"
	  		+"COL;bianco\n"
	  		+"COL;rosso\n"
			+"MAT;cotone;10.0\n"
			+"MAT;lino;15.0\n"
			+"CAP;c1;pantalone;cotone;blu\n"
			+"CAP;c2;camicia;lino;bianco\n"
			+"CAP;c3;camicia;cotone;rosso\n"
			+"COL;estate;c1;c2;c3\n"
		;	
	  	writeFile(file,content);
	  	
	  	abb = new Abbigliamento();
	  	abb.leggiFile(file);
	  }
	  
	  public void tearDown() {
	  	File f = new File(file);
	  	f.delete();
	  }
	  
	  /* --------------------------------------- */
	  public void testLeggiModello() throws Exception {
	  	 Modello m = abb.getModello("pantalone");
	  	 
	  	 assertNotNull(m);
	  	 assertEquals("pantalone",m.getNome());
	  	 assertEquals(5.0,m.getCosto(),0.001);
	  	 assertEquals(2.0,m.getQuantita(),0.001);
	  }

	  public void testLeggiColore() throws Exception {
	  	 Colore c = abb.getColore("rosso");
	  	 
	  	 assertNotNull(c);
	  	 assertEquals("rosso",c.getNome());
	  }

	  public void testLeggiMateriale() throws Exception {
	  	 Materiale m = abb.getMateriale("cotone");
	  	 
	  	 assertNotNull(m);
	  	 assertEquals("cotone",m.getNome());
	  	 assertEquals(10.0,m.getCosto(),0.001);
	  }

	  public void testLeggiModelloErr() throws Exception {
	  	Modello m = null;
	  	m = abb.getModello("pantalone"); 
	  	assertNotNull(m);
	  	try{
	  	  m = abb.getModello("p");
	  	}catch(Exception e){
	  	  m = null;
	  	}
	  	assertNull(m);
	  }

	  public void testLeggiColoreErr() throws Exception {
	  	Colore c = null;
	  	c = abb.getColore("rosso"); 
	  	assertNotNull(c);
	  	try{
	  	  c = abb.getColore("p");
	  	}catch(Exception e){
	  	  c = null;
	  	}
	  	assertNull(c);
	  }

	  public void testLeggiMaterialeErr() throws Exception {
	  	Materiale m = null;
	  	m = abb.getMateriale("cotone"); 
	  	assertNotNull(m);
	  	try{
	  	  m = abb.getMateriale("p");
	  	}catch(Exception e){
	  	  m = null;
	  	}
	  	assertNull(m);
	  }

	  public void testLeggiCapo() throws Exception {
	  	 Capo c = abb.getCapo("c1");
	  	 
	  	 assertNotNull(c);
	  	 assertEquals(25.0,c.prezzo(),0.001);
	  }

//	  public void testLeggiCollezione() throws Exception {
//		// Il testo era ambiguo, nessun test su questa parte	  	
//	  }
}
