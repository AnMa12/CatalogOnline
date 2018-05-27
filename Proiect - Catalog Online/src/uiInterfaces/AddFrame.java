package uiInterfaces;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AddFrame {
	
	protected JFrame frame;
	private JTextField numeTF;
	private JTextField prenumeTF;
	private JTextField clasaTF;
	private Connection connection;
	private JButton btnSave;
	private JButton btnCancel;
	
	protected AddFrame(Connection connection)
	{	
		this.connection=connection;
		initialize();
		saveButton();
	}
	protected void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 271, 220);
		
		numeTF = new JTextField();
		numeTF.setBounds(149, 65, 96, 19);
		frame.getContentPane().add(numeTF);
		numeTF.setColumns(10);
		
		prenumeTF = new JTextField();
		prenumeTF.setBounds(149, 91, 96, 19);
		frame.getContentPane().add(prenumeTF);
		prenumeTF.setColumns(10);
		
		clasaTF = new JTextField();
		clasaTF.setBounds(149, 120, 96, 19);
		frame.getContentPane().add(clasaTF);
		clasaTF.setColumns(10);
	
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(149, 149, 96, 21);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	frame.dispose();
		    }
		});
		
		
		JLabel lblNume = new JLabel("Nume:");
		lblNume.setBounds(27, 68, 45, 13);
		frame.getContentPane().add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume:");
		lblPrenume.setBounds(27, 94, 96, 13);
		frame.getContentPane().add(lblPrenume);
		
		JLabel lblClasa = new JLabel("Clasa:");
		lblClasa.setBounds(27, 126, 45, 13);
		frame.getContentPane().add(lblClasa);
}
	
	private void saveButton()
	{	btnSave = new JButton("Save");
		btnSave.setBounds(27, 149, 90, 21);
		
		frame.getContentPane().add(btnSave);
		btnSave.setEnabled(true);
		btnSave.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {	String id="";
		    	PreparedStatement  stmt=null;
		        try {
		        	stmt = connection.prepareStatement("insert into LoginData(username,password,statut) values(?,SHA1('password'),2)");
		        	stmt.setString(1, (prenumeTF.getText()+'.'+numeTF.getText()).toLowerCase());
		        stmt.executeUpdate();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		        
		        try {
		    		
		        	stmt = connection.prepareStatement("select id from LoginData where username=?");
		        	stmt.setString(1, (prenumeTF.getText()+'.'+numeTF.getText()).toLowerCase());
		        	ResultSet rs=stmt.executeQuery();
		        	rs.next();
		            id=rs.getString(1);
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		        
		        try {
		        	stmt = connection.prepareStatement("insert into Elev values(?,?,?,?);");
		        	stmt.setString(1,id);
		        	stmt.setString(2,numeTF.getText());
		        	stmt.setString(3,prenumeTF.getText());
		        	stmt.setString(4,clasaTF.getText());
		        	stmt.executeUpdate();
		        	frame.dispose();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		       
		    }
		});
		
	}
}