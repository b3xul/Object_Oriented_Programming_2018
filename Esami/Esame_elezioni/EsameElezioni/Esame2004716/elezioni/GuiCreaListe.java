package elezioni;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuiCreaListe extends JFrame {

  public JList cittadini;
  public JList liste;
  public JButton addCapoLista;
  public JButton addCandidato;
  public JTextField nome;
  public JTextArea motto;
  public JButton registraLista;
  public GuiCreaListe(Elezione elezione) {
    super("Elezioni - Creazione Liste");
    Container cp = this.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    cp.setLayout(gbl);

    GridBagConstraints c = new GridBagConstraints();

    // lista cittadini
    c.gridx = 0;
    c.gridy = 0;
    c.gridheight = 2;
    c.weighty = 1;
    c.weightx = 1;
    c.fill = GridBagConstraints.BOTH;
    cittadini = new JList();
    JScrollPane sp = new JScrollPane(cittadini);
    gbl.setConstraints(sp, c);
    cp.add(sp);

    // lista liste
    c.gridx = 2;
    c.gridy = 0;
    c.gridheight = 2;
    c.weighty = 1;
    c.weightx = 1;
    c.fill = GridBagConstraints.BOTH;
    liste = new JList();
    sp = new JScrollPane(liste);
    gbl.setConstraints(sp, c);
    cp.add(sp);

    // pulsante aggiungi capo lista
    c.gridx = 1;
    c.gridy = 0;
    c.gridheight = 1;
    c.weighty = 1;
    c.weightx = 1;
    c.fill = GridBagConstraints.NONE;
    addCapoLista = new JButton("Capo Lista");
    gbl.setConstraints(addCapoLista, c);
    cp.add(addCapoLista);

    // pulsante aggiungi candidato
    c.gridx = 1;
    c.gridy = 1;
    c.gridheight = 1;
    c.weighty = 1;
    c.weightx = 1;
    c.fill = GridBagConstraints.NONE;
    addCandidato = new JButton("Candidato");
    gbl.setConstraints(addCandidato, c);
    cp.add(addCandidato);

    // label nome
    c.gridx = 0;
    c.gridy = 2;
    c.gridheight = 1;
    c.weighty = 0;
    c.weightx = 0;
    c.fill = GridBagConstraints.NONE;
    JLabel lbl = new JLabel("Nome:");
    gbl.setConstraints(lbl, c);
    cp.add(lbl);

    // label motto
    c.gridx = 1;
    c.gridy = 2;
    c.gridheight = 1;
    c.weighty = 0;
    c.weightx = 0;
    c.fill = GridBagConstraints.NONE;
    lbl = new JLabel("Motto:");
    gbl.setConstraints(lbl, c);
    cp.add(lbl);

    // text nome
    c.gridx = 0;
    c.gridy = 3;
    c.weighty = 0;
    c.weightx = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    nome = new JTextField();
    gbl.setConstraints(nome, c);
    cp.add(nome);

    // pulsante registra lista
    c.gridx = 0;
    c.gridy = 4;
    c.weighty = 1;
    c.weightx = 0;
    c.fill = GridBagConstraints.NONE;
    c.anchor = GridBagConstraints.NORTH;
    registraLista = new JButton("Registra");
    gbl.setConstraints(registraLista, c);
    cp.add(registraLista);

    // text motto
    c.gridx = 1;
    c.gridy = 3;
    c.weighty = 1;
    c.weightx = 1;
    c.gridheight = 2;
    c.gridwidth = 2;
    c.fill = GridBagConstraints.BOTH;
    motto = new JTextArea();
    sp = new JScrollPane(motto);
    gbl.setConstraints(sp, c);
    cp.add(sp);

    setSize(500, 300);

    //--------------------- DATI ----------
    // TODO aggiugnere codice per popolare le liste
    
    // il seguente codice è un esempio di come utilizzare le JList
	cittadini.setListData(new String[] {
				"Giovanni Bianchi", "Mario Rossi",
				"Giuseppe Verdi", "Romano Prodi", "Silvio Berlusconi"});
	liste.setListData(new String[] { "Ulivo", "Casa delle Liberta'" });

    //--------------------- EVENTI ---------- 
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    addCandidato.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doAddCandidato();
      }
    });
    addCapoLista.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doAddCapoLista();
      }
    });
    registraLista.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doRegistraLista();
      }
    });
  }

  void doAddCapoLista() {
    // TODO aggiungere gestione pulsante addCapoLista
  }

  void doAddCandidato() {
    // TODO aggiungere gestione pulsante addCandidato
  }

  void doRegistraLista() {
    // TODO aggiungere gestione pulsante registraLista
  }

}
