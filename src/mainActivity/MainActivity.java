package mainActivity;
import java.awt.EventQueue;
import com.mysql.jdbc.Connection;

import loginDatabase.LoginDatabase;
import uiInterfaces.*;
import utilities.UserFrame;

public class MainActivity {
	static UserFrame userFrame;
	public static LoginFrame window;
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
					if(ld.getStatut().equals("3"))
						userFrame =new ParentFrame(ld.getId(),ld.getNume(),ld.getPrenume(),(Connection) ld.getConnection());
					else
						if(ld.getStatut().equals("2"))
							userFrame =new StudentFrame(ld.getId(),ld.getNume(),ld.getPrenume(),(Connection) ld.getConnection());
						 
							else 
							if(ld.getStatut().equals("1"))
								userFrame =new TeacherFrame(ld.getId(),ld.getNume(),ld.getPrenume(),(Connection) ld.getConnection());
							else 
								if(ld.getStatut().equals("0"))
									userFrame =new DirectorFrame(ld.getId(),ld.getNume(),ld.getPrenume(),(Connection) ld.getConnection());
					userFrame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
