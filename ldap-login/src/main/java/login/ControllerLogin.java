
package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerLogin
{
	String stGroup = "il.group.name";//change for your group
	
	private ModelLogin cModel;
	private ViewLogin cView;

	
	
	private LogInLog l = new LogInLog();
	
	
	public ControllerLogin(ModelLogin model, ViewLogin view)
	{
		//pointer / 
		this.cModel = model;
		this.cView = view;
	
		//group locate
		cModel.setStFindGroup(stGroup);	
		
		//onload values
		cView.setStUser(cModel.getCurrentUserId().toString());
		
		cModel.setDomain();
		cView.setStDomainName(cModel.getDomain().toString());
		System.out.println("---Domain--- " + cModel.getDomain());
		
		
		
		// Listener/s		
		this.cView.addCalculateListener(new SubmitListener());
		this.cView.addcleanListener(new SubmitClean());
		this.cView.addExitListener(new SubmitExit());
		this.cView.addUserListener(new SubmitUser());
	}
	
	
	class SubmitUser implements ActionListener 
	{
		
		public void actionPerformed (ActionEvent e) 
		{
			System.out.println("user = " + cModel.getCurrentUserId());
//			cView.setTxtUserName(txtUserName, "test");
			cView.setStUser(cModel.getCurrentUserId().toString());
			
		}
			
	}//SubmitUser
	
	
	
	class SubmitListener implements ActionListener 
	{
		
		public void actionPerformed (ActionEvent e) 
		{
			//System.out.println("Login button pressed");
			
			
			cModel.setUserId(cView.getStUser().trim());
			//System.out.println("UseName is == " + cModel.getUserId() );
			
			
			
			cView.setStPassword(); //user enter password
			cModel.setPassword(cView.getStPassword());
			//System.out.println("password is == " + cModel.getPassword());
			

			cModel.Login();
			cView.setStStatus(cModel.getStLoginMessage());
			
			//System.out.println("12321 >>> " + cModel.getLoginStatus());
			
			/***
			 * connection to main menu
			 * i think it not needed
			 */
//			//TOSO
			if (cModel.getLoginStatus() == true)
			{
			//	MainMenu m = new MainMenu();
				
				//cView.frmVisible(false);
				
				System.out.println("controller: password is ok");
		
//				if (cModel.isMemberOfADGroup(ctx, dnADGroup, dnADUser))
			
				/****
				 * Scan
				 */				
		//		Scan s = new Scan();
				System.out.println("controller: login done");
//				Money m = new Money();
				
				
				
//				cView.setLoginWindowsVisible(false);
			}
			
		}
			
	}//SubmitListener
	
	
	
	class SubmitClean implements ActionListener 
	{
		
		public void actionPerformed (ActionEvent e) 
		{

			System.out.println("Clean");
			cView.clean();
		}
				
	}//SubmitClean
	

	
	class SubmitExit implements ActionListener 
	{
		
		public void actionPerformed (ActionEvent e) 
		{
			System.out.println("Exit");
			cView.ExitProc();
		}
				
	}//SubmitExit

	
	
	
	
}//class ControllerLogin


