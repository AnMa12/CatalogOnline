package uiInterfaces;


import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

import entities.Director;
import entities.Elev;
import utilities.UserFrame;

import javax.swing.JButton;

public class DirectorFrame extends UserFrame {

	//private JFrame frame;
	private JTextField numeTF;
	private JTextField prenumeTF;
	private JTextField numarMatricolTF;
	private JTextField clasaTF;
	private JComboBox<String> classSelector;
	private JComboBox<String> studentSelector;
	private JButton editButton;
	private JButton deleteButton;
	private Connection connection;
	private Director director;
	private ArrayList<String> clase;
	private ArrayList<Elev> elevi;
	private AddFrame addFrame;
	public DirectorFrame(String id,Connection connection) {
		this.connection=connection;
		director =new Director(id);
		initialize();
	}

	protected void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 461, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		addTextFields();

		addClassSelector();
		addStudentSelector();

		addLabels();

		addAddButton();
		addEditButton();
		addDeleteButton();

	}
	private void addDeleteButton() {
		deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		deleteButton.setBounds(298, 312, 111, 34);
		frame.getContentPane().add(deleteButton);
	}

	private void addEditButton() {
		editButton = new JButton("Edit");
		editButton.setEnabled(false);
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		editButton.setBounds(163, 312, 111, 34);
		frame.getContentPane().add(editButton);

	}

	private void addAddButton() {
		JButton btnAdd = new JButton("Add...");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.setBounds(33, 312, 111, 34);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        addFrame=new AddFrame(connection);
		      
		        addFrame.frame.setVisible(true);
		    }
		});
	}

	private void addStudentSelector() {
		studentSelector = new JComboBox<String>();
		studentSelector.setBounds(188, 76, 174, 34);
		frame.getContentPane().add(studentSelector);
		
		studentSelector.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){   
				if (ie.getStateChange() == ItemEvent.SELECTED)
				{
					numeTF.setText(elevi.get(studentSelector.getSelectedIndex()).getNume());
					prenumeTF.setText(elevi.get(studentSelector.getSelectedIndex()).getPrenume());
					numarMatricolTF.setText(elevi.get(studentSelector.getSelectedIndex()).getId());
					clasaTF.setText(classSelector.getSelectedItem().toString());
				}
			}
		});
	}

	private void addLabels() {
		JLabel lblNewLabel = new JLabel("Selecteaza clasa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(31, 41, 129, 24);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Selecteaza elevul:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(188, 44, 129, 19);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nume:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(33, 146, 88, 25);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Prenume:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(33, 182, 127, 24);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNumarMatricol = new JLabel("Numar matricol:");
		lblNumarMatricol.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumarMatricol.setBounds(33, 217, 127, 24);
		frame.getContentPane().add(lblNumarMatricol);

		JLabel lblClasa = new JLabel("Clasa:");
		lblClasa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClasa.setBounds(33, 251, 127, 24);
		frame.getContentPane().add(lblClasa);
	}

	private void addClassSelector() {
		classSelector = new JComboBox<String>();
		classSelector.setBounds(33, 76, 127, 34);
		frame.getContentPane().add(classSelector);
		clase = director.getClase(connection);

		for(String x:clase)
			classSelector.addItem(x);

		classSelector.addActionListener ((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				elevi = director.getElevi(classSelector.getSelectedItem().toString(),connection);
				studentSelector.removeAllItems();
				for(Elev x:elevi)
					studentSelector.addItem(x.getNume()+" "+x.getPrenume());
				editButton.setEnabled(true);
				deleteButton.setEnabled(true);
			}});
	}

	private void addTextFields() {
		numeTF = new JTextField();
		numeTF.setEditable(false);
		numeTF.setBounds(163, 151, 174, 20);
		frame.getContentPane().add(numeTF);
		numeTF.setColumns(10);

		prenumeTF = new JTextField();
		prenumeTF.setEditable(false);
		prenumeTF.setColumns(10);
		prenumeTF.setBounds(163, 187, 174, 20);
		frame.getContentPane().add(prenumeTF);

		numarMatricolTF = new JTextField();
		numarMatricolTF.setEditable(false);
		numarMatricolTF.setColumns(10);
		numarMatricolTF.setBounds(163, 222, 174, 20);
		frame.getContentPane().add(numarMatricolTF);

		clasaTF = new JTextField();
		clasaTF.setEditable(false);
		clasaTF.setColumns(10);
		clasaTF.setBounds(163, 256, 174, 20);
		frame.getContentPane().add(clasaTF);
	}
}
