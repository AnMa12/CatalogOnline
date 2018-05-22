package uiInterfaces;
import javax.swing.JFrame;
import com.mysql.jdbc.Connection;

import entities.Absenta;
import entities.Elev;
import entities.Nota;
import utilities.UserFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.List;
import javax.swing.JTextField;


public class StudentFrame extends UserFrame{
	private Connection connection;
	private Elev elev;
	private JComboBox<String> selectorMaterie;
	private List list;
	private JTextField medieTF;
	private JTextField nrAbsenteTF;
	private JTextField medieGeneralaTF;
	private JButton btnOverview;
	public StudentFrame(String id,Connection connection) {
		elev=new Elev(id);
		this.connection=connection;
		initialize();
	}
	protected void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 312, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		
		addLables();
		addTextFieldsforRapoarte();
		addSelectorMaterie();
		addButtonGetAbsente();
		addButtonGetNote();
		addListaRezultat();
		addOverview();
		
	}
	private void addOverview() {
		btnOverview = new JButton("Overview");
		btnOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnOverview.setBounds(99, 444, 89, 23);
		frame.getContentPane().add(btnOverview);
		
	}
	private void addTextFieldsforRapoarte() {
		medieTF = new JTextField();
		medieTF.setEditable(false);
		medieTF.setBounds(135, 115, 37, 20);
		frame.getContentPane().add(medieTF);
		medieTF.setColumns(10);
		
		nrAbsenteTF = new JTextField();
		nrAbsenteTF.setEditable(false);
		nrAbsenteTF.setColumns(10);
		nrAbsenteTF.setBounds(135, 155, 37, 20);
		frame.getContentPane().add(nrAbsenteTF);
		
		medieGeneralaTF = new JTextField();
		medieGeneralaTF.setEditable(false);
		medieGeneralaTF.setBounds(175, 416, 51, 18);
		frame.getContentPane().add(medieGeneralaTF);
		medieGeneralaTF.setColumns(10);
		medieGeneralaTF.setText(Float.toString(elev.getMedieGenerala(elev.getId(), connection)));
		
	}
	private void addLables() {
		JLabel lblNote = new JLabel("Alege materia:");
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNote.setBounds(12, 43, 194, 24);
		frame.getContentPane().add(lblNote);
		
		JLabel lblMedie = new JLabel("Medie:");
		lblMedie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMedie.setBounds(12, 115, 66, 20);
		frame.getContentPane().add(lblMedie);
		
		JLabel lblNumarAbsente = new JLabel("Numar absente:");
		lblNumarAbsente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumarAbsente.setBounds(10, 158, 115, 14);
		frame.getContentPane().add(lblNumarAbsente);
		
		JLabel lblMediaGenerala = new JLabel("Media generala :");
		lblMediaGenerala.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMediaGenerala.setBounds(35, 416, 130, 17);
		frame.getContentPane().add(lblMediaGenerala);
	}
	private void addListaRezultat()
	{
		list = new List();
		list.setFont(new Font("Dialog", Font.BOLD, 16));
		list.setBounds(12, 216, 275, 165);
		frame.getContentPane().add(list);
		
	}
	private void addButtonGetNote()
	{
		JButton buttonGetNote = new JButton("Afiseaza Note");
		buttonGetNote.setBounds(12, 186, 130, 24);
		frame.getContentPane().add(buttonGetNote);
		
		buttonGetNote.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	Elev.note=elev.getNote(elev.getId(),(String) selectorMaterie.getSelectedItem(),connection);
            	list.removeAll();
            	for(Nota x:Elev.note)
				{
				list.add(x.toString());
				}
			}
		});
	}
	private void addSelectorMaterie()
	{
		selectorMaterie = new JComboBox<String>();
		ArrayList<String> materii = elev.getMaterii(elev.getId(),connection);
		for(String x:materii)
		selectorMaterie.addItem(x);
		selectorMaterie.setBounds(12, 78, 130, 22);
		frame.getContentPane().add(selectorMaterie);
		
		selectorMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					medieTF.setText(Float.toString(elev.getMedie(elev.getId(), connection, (String) selectorMaterie.getSelectedItem())));
					nrAbsenteTF.setText(Integer.toString(elev.getNrAbsente(elev.getId(), (String) selectorMaterie.getSelectedItem(), connection)));
				}
			
		});
	}
	private void addButtonGetAbsente()
	{
		JButton buttonGetAbsente = new JButton("Afiseaza Absente");
		buttonGetAbsente.setBounds(152, 186, 130, 24);
		frame.getContentPane().add(buttonGetAbsente);
		
		buttonGetAbsente.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList <Absenta> absente=new ArrayList<>();
            	absente=elev.getAbsente(elev.getId(),(String) selectorMaterie.getSelectedItem(),connection);
            	list.removeAll();
            	for(Absenta x:absente)
				{
				list.add(x.toString());
				
				}
			}
		});
	}
}
