package uiInterfaces;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.mysql.jdbc.Connection;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import com.toedter.calendar.JCalendar;

import entities.Absenta;
import entities.Elev;
import entities.Nota;
import entities.Profesor;
import utilities.UserFrame;

public class TeacherFrame extends UserFrame{

	private Connection connection;
	private Profesor profesor;
	private JComboBox<String> selectorElevi;
	private JComboBox<String> selectorClasa;
	private JComboBox<String> selectorMaterie;
	private JComboBox<String> selectorNota;
	private List list;
	private ArrayList<Elev> elevi;
	private JButton btnAdaugaAbsenta;
	private JButton btnAdaugaNota;
	private JCalendar calendar;
	private JButton btnMotiveazaAbsenta;
	private JButton buttonGetAbsente;
	private JButton buttonGetNote;
	private lista continutLista;
	public TeacherFrame(String id,Connection connection) {
		profesor=new Profesor(id);
		this.connection=connection;
		initialize();
	}
	protected void initialize() {
		frame = new JFrame();
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		frame.getContentPane().add(tglbtnNewToggleButton, BorderLayout.WEST);
		frame.getContentPane().setLayout(null);
		
		
		frame.setBounds(100, 100, 647, 514);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNote = new JLabel("Alege materia:");
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNote.setBounds(12, 43, 194, 24);
		frame.getContentPane().add(lblNote);
		addDatePicker();
		addButtonGetAbsente();
		addButtonGetNote();
		addSelectorMaterie();
		addSelectorClasa();
		addSelectorElev();
		addListaRezultat();
		addSelecotrNota();
		addButtonAdaugaAbsenta();
		addButtonAdaugaNota();
		addButtonMotivareAbsenta();
	}
	private void addButtonMotivareAbsenta() {
		btnMotiveazaAbsenta = new JButton("Motiveaza Absenta");
		btnMotiveazaAbsenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Elev.absente.get(list.getSelectedIndex()).motiveazaAbsenta(connection);
				buttonGetAbsente.doClick();
			}
		});
		btnMotiveazaAbsenta.setBounds(157, 312, 130, 23);
		btnMotiveazaAbsenta.setEnabled(false);
		frame.getContentPane().add(btnMotiveazaAbsenta);
	}
	private void addButtonAdaugaNota() {
		btnAdaugaNota = new JButton("AdaugaNota");
		btnAdaugaNota .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nota.adaugaNota(connection, elevi.get(selectorElevi.getSelectedIndex()).getId(), (String) selectorNota.getSelectedItem(),calendar.getDate(), (String) selectorMaterie.getSelectedItem());
				buttonGetNote.doClick();
			}
		});
		btnAdaugaNota .setBounds(440, 241, 103, 24);
		frame.getContentPane().add(btnAdaugaNota );
		btnAdaugaNota.setEnabled(false);
	
		
	}
	private void addButtonAdaugaAbsenta() {
		btnAdaugaAbsenta = new JButton("AdaugaAbsenta");
		btnAdaugaAbsenta.setBounds(412, 276, 131, 23);
		frame.getContentPane().add(btnAdaugaAbsenta);
		btnAdaugaAbsenta.setEnabled(false);
		btnAdaugaAbsenta .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Absenta.AdaugaAbsenta(connection, elevi.get(selectorElevi.getSelectedIndex()).getId(), calendar.getDate(),(String) selectorMaterie.getSelectedItem());
				buttonGetAbsente.doClick();
			}
		});
		
	}
	private void addSelecotrNota() {
		selectorNota = new JComboBox<>();
		for(int i=1;i<=10;i++)
			selectorNota.addItem(Integer.toString(i));
		selectorNota.setBounds(402, 242, 38, 22);
		frame.getContentPane().add(selectorNota);
		
	}
	private void addDatePicker() {
		calendar = new JCalendar();
		calendar.setBounds(378, 78, 198, 153);
		frame.getContentPane().add(calendar);
		
		
	}
	private void addListaRezultat()
	{
		list = new List();
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(verificaContinutLista(continutLista)=="absente")
				btnMotiveazaAbsenta.setEnabled(true);
			}
		});
		list.setFont(new Font("Dialog", Font.BOLD, 16));
		list.setBounds(12, 141, 275, 165);
		frame.getContentPane().add(list);
	}
	private void addButtonGetAbsente()
	{
		buttonGetAbsente = new JButton("Afiseaza Absente");
		buttonGetAbsente.setBounds(157, 111, 130, 24);
		frame.getContentPane().add(buttonGetAbsente);
		
		buttonGetAbsente.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
        		btnMotiveazaAbsenta.setEnabled(false);
            	if(selectorElevi.getSelectedIndex()>=0) {
            	Elev.absente=(elevi.get(selectorElevi.getSelectedIndex())).getAbsente((elevi.get(selectorElevi.getSelectedIndex())).getId(),(String) selectorMaterie.getSelectedItem(),connection);
            	list.removeAll();
            	for(Absenta x:Elev.absente)
				list.add(x.toString());
            	continutLista=lista.absente;
            	}
			}
		});
	}
	private void addButtonGetNote()
	{
		buttonGetNote = new JButton("Afiseaza Note");
		buttonGetNote.setBounds(12, 111, 130, 24);
		frame.getContentPane().add(buttonGetNote);
		
		buttonGetNote.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
        		btnMotiveazaAbsenta.setEnabled(false);
            	if(selectorElevi.getSelectedIndex()>=0) {
            	Elev.note=(elevi.get(selectorElevi.getSelectedIndex())).getNote((elevi.get(selectorElevi.getSelectedIndex())).getId(),(String) selectorMaterie.getSelectedItem(),connection);
            	list.removeAll();
            	for(Nota x:Elev.note)
				list.add(x.toString());
            	continutLista=lista.note;
				}
			}
		});
	}
	private void addSelectorMaterie()
	{
		selectorMaterie = new JComboBox<String>();
		ArrayList<String> materii = profesor.getMaterii(profesor.getId(),connection);
		for(String x:materii)
			selectorMaterie.addItem(x);
		selectorMaterie.setBounds(12, 78, 130, 22);
		frame.getContentPane().add(selectorMaterie);
		
		selectorMaterie.addActionListener ((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<String> clase = profesor.getClase(profesor.getId(),connection,(String) selectorMaterie.getSelectedItem());
            	selectorClasa.removeAllItems();
            	for(String x:clase)
            	selectorClasa.addItem(x);
			}});
	}
	
	private void addSelectorClasa() {
	selectorClasa= new JComboBox<String>();
	selectorClasa.setBounds(142, 78, 47, 22);
	frame.getContentPane().add(selectorClasa);
	
	selectorClasa.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent ie){   
			    if (ie.getStateChange() == ItemEvent.SELECTED){
			    	elevi = profesor.getElevi(profesor.getId(),connection,(String) selectorClasa.getSelectedItem());
			    	selectorElevi.removeAllItems();
	            	for(Elev x:elevi)
	            	selectorElevi.addItem(x.getNume()+" "+x.getPrenume());
	            	btnAdaugaAbsenta.setEnabled(true);
	            	btnAdaugaNota.setEnabled(true);
			    }
			  }
	});
    }
	
	private void addSelectorElev() {
		selectorElevi = new JComboBox<String>();
		selectorElevi.setBounds(189, 78, 98, 22);
		frame.getContentPane().add(selectorElevi);
	}
	private String verificaContinutLista(lista l)
	{
		switch(l)
		{
		case note: return "note";
		case absente: return "absente";
		default: return "";
		}
		
	}
}
enum lista{
	note,absente;
}
