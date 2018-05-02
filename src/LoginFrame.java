import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JButton button;
	private LoginDatabase loginDatabase;
	
    
	public LoginFrame() {
		initialize();
	}
	public JFrame getFrame()
	{
		return frame;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 425, 292);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		button = new JButton("Login");
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Arial", Font.BOLD, 22));
		button.setBounds(158, 168, 115, 35);
		frame.getContentPane().add(button);
		
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(124, 64, 176, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField.setBounds(124, 116, 176, 41);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 17));
		lblUsername.setBounds(23, 67, 115, 28);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 17));
		lblPassword.setBounds(23, 119, 115, 28);
		frame.getContentPane().add(lblPassword);
		
		button.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	loginDatabase=LoginDatabase.getAccess(textField.getText(),passwordField.getPassword());
                if(loginDatabase.getConnection()==null)
            		JOptionPane.showMessageDialog(null, "Userul sau parola este gresita!");
                else
                	{
                	frame.dispose();
                	MainActivity.window=null;
                	MainActivity.initializeUserFrame(loginDatabase);
                	}
            }
				
			}
		);
	}
}
