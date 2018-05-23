package uiInterfaces;


import javax.swing.JFrame;

import com.mysql.jdbc.Connection;

import entities.Absenta;
import entities.Elev;
import entities.Nota;
import entities.Parinte;
import utilities.UserFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ParentFrame extends UserFrame{

	private Parinte parinte;
	private JComboBox<String> selectorMaterie;
	private JComboBox<String> selectorElev;
	private List listaRezultat;
	private Connection connection;
	public ParentFrame(String id,String nume,String prenume,Connection connection) {
		parinte = new Parinte(id,nume,prenume,connection);
		this.connection = connection;
		initialize();
	}

	protected void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 394, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		addSelectorElev();
		addSelectorMaterie();
		addButtonGetNote();
		addButtonGetAbsente();
		addListaRezultat();
		
		
	}
	private void addButtonGetNote()
	{
		JButton buttonGetNote = new JButton("Afiseaza Note");
		buttonGetNote.setBounds(36, 196, 140, 24);
		frame.getContentPane().add(buttonGetNote);
		
		buttonGetNote.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	Elev elevSelectat = parinte.getElevi().get(selectorElev.getSelectedIndex());
            	Elev.note=elevSelectat.getNote(elevSelectat.getId(),(String) selectorMaterie.getSelectedItem(),connection);
            	listaRezultat.removeAll();
            	for(Nota x:Elev.note) {
            		listaRezultat.add(x.toString());
            	}
			}
		});
	}
	private void addButtonGetAbsente()
	{
		JButton buttonGetAbsente = new JButton("Afiseaza Absente");
		buttonGetAbsente.setBounds(190, 196, 140, 24);
		frame.getContentPane().add(buttonGetAbsente);
		
		buttonGetAbsente.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	Elev elevSelectat = parinte.getElevi().get(selectorElev.getSelectedIndex());
            	Elev.absente=elevSelectat.getAbsente(elevSelectat.getId(),(String) selectorMaterie.getSelectedItem(),connection);
            	listaRezultat.removeAll();
            	for(Absenta x:Elev.absente)
				{
            		listaRezultat.add(x.toString());
				}
			}
		});
	}
	private void addSelectorMaterie() {
		JLabel lblAlegeMaterie = new JLabel("Alege materie:");
		lblAlegeMaterie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlegeMaterie.setBounds(36, 103, 113, 22);
		frame.getContentPane().add(lblAlegeMaterie);
		
		selectorMaterie = new JComboBox<String>();
		selectorMaterie.setBounds(36, 136, 140, 22);
		frame.getContentPane().add(selectorMaterie);
		
	}

	private void addSelectorElev() {
		JLabel lblAlegeElev = new JLabel("Alege elev:");
		lblAlegeElev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlegeElev.setBounds(36, 31, 93, 28);
		frame.getContentPane().add(lblAlegeElev);
		
		selectorElev = new JComboBox<String>();
		selectorElev.setBounds(36, 70, 140, 22);
		frame.getContentPane().add(selectorElev);
		
		for(Elev x:parinte.getElevi())
		selectorElev.addItem(x.getNume() +" "+ x.getPrenume());
		
		selectorElev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Elev elevSelectat =  parinte.getElevi().get(selectorElev.getSelectedIndex());
					ArrayList<String> materii = new ArrayList<>();
					materii = elevSelectat.getMaterii(elevSelectat.getId(), connection);
					for(String x: materii) {
						selectorMaterie.addItem(x);
					}
				}
		});
	}

	private void addListaRezultat() {
		listaRezultat = new List();
		listaRezultat.setFont(new Font("Dialog", Font.BOLD, 16));
		listaRezultat.setBounds(36, 233, 294, 163);
		frame.getContentPane().add(listaRezultat);
		
	}
}
