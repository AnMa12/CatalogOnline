import java.awt.EventQueue;

import com.mysql.jdbc.Connection;

public class MainActivity {
	static UserFrame userFrame;
	static LoginFrame window;
	public static void main(String[] args) {
		initializareLoginFrame();
	}
	
	public static void initializareLoginFrame()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window =new LoginFrame();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void initializeUserFrame(LoginDatabase ld)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(ld.getStatut().equals("2"))
					userFrame =new BaseUserFrame(ld.getId(),(Connection) ld.getConnection());
					else if(ld.getStatut().equals("1"))
						userFrame =new TeacherFrame(ld.getId(),(Connection) ld.getConnection());
						//else if(ld.getStatut().equals("0"))
							//userFrame =new DirectorFrame(ld.getId(),(Connection) ld.getConnection());
					userFrame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
