import javax.swing.JFrame;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.List;

public class BaseUserFrame extends UserFrame{
	
	private Connection connection;
	private Elev elev;
	private JComboBox<String> comboBox;
	private List list;
	public BaseUserFrame(String id,Connection connection) {
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
			
		JLabel lblNote = new JLabel("Alege materia:");
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNote.setBounds(12, 43, 194, 24);
		frame.getContentPane().add(lblNote);
		
		addSelectorMaterie();
		addButtonGetAbsente();
		addButtonGetNote();
		addListaRezultat();
		
	}
	private void addListaRezultat()
	{
		list = new List();
		list.setFont(new Font("Dialog", Font.BOLD, 16));
		list.setBounds(12, 147, 275, 165);
		frame.getContentPane().add(list);
	}
	private void addButtonGetNote()
	{
		JButton buttonGetNote = new JButton("Afiseaza Note");
		buttonGetNote.setBounds(12, 111, 130, 24);
		frame.getContentPane().add(buttonGetNote);
		
		buttonGetNote.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	Elev.note=elev.getNote(elev.getId(),(String) comboBox.getSelectedItem(),connection);
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
		comboBox = new JComboBox<String>();
		ArrayList<String> materii = elev.getMaterii(elev.getId(),connection);
		for(String x:materii)
		comboBox.addItem(x);
		comboBox.setBounds(12, 78, 130, 22);
		frame.getContentPane().add(comboBox);
	}
	private void addButtonGetAbsente()
	{
		JButton buttonGetAbsente = new JButton("Afiseaza Absente");
		buttonGetAbsente.setBounds(157, 111, 130, 24);
		frame.getContentPane().add(buttonGetAbsente);
		
		buttonGetAbsente.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList <Absenta> absente=new ArrayList<>();
            	absente=elev.getAbsente(elev.getId(),(String) comboBox.getSelectedItem(),connection);
            	list.removeAll();
            	for(Absenta x:absente)
				{
				list.add(x.toString());
				
				}
			}
		});
	}
}
