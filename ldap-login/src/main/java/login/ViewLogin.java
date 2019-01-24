package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ViewLogin  extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11;
	
	private		JFrame				frmLogin			=	null;
	private		JPanel				panel				=   null;
	
	private		JLabel				lblTSof				=   null;
	private		JLabel				lblTitle			=   null;
	
	
	private   	JLabel       		lblUserName      	=   null; //user
	private   	JTextField  	 	txtUserName      	=   null;
	
	private   	JLabel      	 	lblPassword      	=   null; //password
	private 	JPasswordField	 	txtPasswordField	=   null;	

	private   	JLabel      	 	lblDomainTitle	   	=   null; //domain
	private		JLabel				lblDomainName		= 	null;
	
		
	private   	JLabel     	 	 	lblSubmitStatus		=   null;
	private   	JButton				btnUser         	=   null; //user-get-id
	private   	JButton				btnLogin         	=   null; //Submit
	private   	JButton   		  	btnclean        	=   null; //Clean Fields from text/data.
	private   	JButton    		 	btnExit         	=   null; //exit program



	public void frmVisible (boolean status)
	{
		frmLogin.setVisible(status);
	}
	
	private String stUser = "";
	private String stDomainName = "";
	private String stPassword = "";
	private String stStatus = "";
	private boolean loginWindowsVisible = true;
	
	
	


	public boolean isLoginWindowsVisible() {
		return loginWindowsVisible;
	}


	public void setLoginWindowsVisible(boolean loginWindowsVisible) {
		frmLogin.setVisible(loginWindowsVisible);
		this.loginWindowsVisible = loginWindowsVisible;
	}


	/***
	 * Domain
	 */
	public String getStDomainName() {
		lblDomainName.getText().toString();
		return stDomainName;
	}


	public void setStDomainName(String stDomainName) {
		lblDomainName.setText(stDomainName);
		this.stDomainName = stDomainName;
	}


	/**
	 * user
	 */
	public String getStUser() {
		stUser = txtUserName.getText().toString();
		return stUser;
	}


	public void setStUser(String stUser) {
		txtUserName.setText(stUser);
		this.stUser = stUser;
	}

	
	/*
	 * password
	 */
	public String getStPassword() {
		return stPassword;
	}


	public void setStPassword() {
		
		String stPassword = new StringBuilder().append("").append(txtPasswordField.getPassword()).toString();
		
		this.stPassword = stPassword.toString();
	}

	

	/**
	 * status
	 */
	public String getStStatus() {
		return stStatus;
	}


	public void setStStatus(String stStatus) {
		lblSubmitStatus.setText(stStatus);
		this.stStatus = stStatus;
	}

	
	
	
	
/***
 * view - constructor 	
 */
	public ViewLogin() 
	{
		
		
	
		
		frmLogin = new JFrame("Login");
		//frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(ViewLogin.class.getResource("/media/lockGray.jpg")));
		
		frmLogin.setResizable(false);

        //create panel
		panel = new JPanel();
		panel.setForeground(Color.CYAN);
		panel.setBackground(Color.LIGHT_GRAY);

		//create bound/s
		panel.setLayout(null);
		frmLogin.getContentPane().add(panel);


		//logo
		lblTSof = new JLabel("");
		//lblTSof.setIcon(new ImageIcon(ViewLogin.class.getResource("../media/lockGray.jpg")));
		lblTSof.setBounds(310, 10, 48, 66);
		panel.add(lblTSof);
		
		
	
//		//logo
		lblTitle = new JLabel("Login");
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 32));
		lblTitle.setText("LOGIN");
		lblTitle.setBounds(15, 25, 160, 52);
		panel.add(lblTitle);
		
		
		
		// user
		lblUserName = new JLabel("User Name");

		lblUserName.setForeground(Color.BLUE);
		lblUserName.setBounds(20, 100, 110, 24);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUserName.setToolTipText("please select the string that you wish to search the file for.");
		panel.add(lblUserName);

		
		
		txtUserName = new JTextField(); 
		txtUserName.setBounds(130, 102, 188, 24); 
		txtUserName.setFont(new Font("Dialog", Font.BOLD, 12));
		txtUserName.setToolTipText("please select a source file, for the processing data job - manually.");
		txtUserName.setForeground(Color.blue);
		txtUserName.setBackground(Color.lightGray);
//		txtFileName1.setHorizontalAlignment(0);
		panel.add(txtUserName);
		

		
		//Password
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setBounds(20, 142, 110, 22);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setToolTipText("please select the string that you wish to search the file for.");
		panel.add(lblPassword);
		
		
		
		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(130, 142, 224, 24);
		txtPasswordField.setForeground(Color.blue);
		txtPasswordField.setBackground(Color.lightGray);
		panel.add(txtPasswordField);
		
		
				
		//domain
		lblDomainTitle = new JLabel("Domain");
		lblDomainTitle.setForeground(Color.BLUE);
		lblDomainTitle.setBounds(20, 182, 110, 22);
		lblDomainTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDomainTitle.setToolTipText("please select the string that you wish to search the file for.");
		panel.add(lblDomainTitle);
		
		
		
		//Domain Name
		lblDomainName = new JLabel();
		lblDomainName.setForeground(Color.BLUE);
		lblDomainName.setBounds(130, 182, 200, 22);
		lblDomainName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDomainName.setToolTipText("please select the string that you wish to search the file for.");
//		lblDomainName.setHorizontalAlignment(0);
		panel.add(lblDomainName);
		
		
		
		//getUser
		btnUser = new JButton("...");	
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnUser.setForeground(Color.BLUE);
		btnUser.setBounds(322, 102, 30, 23); //399, 260, 117, 25
		btnUser.setToolTipText("");
		btnUser.setBackground(Color.lightGray);
		panel.add(btnUser);
		
		
		
		// Button - btnLogin //		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setBounds(15, 225, 100, 30); //399, 260, 117, 25
		btnLogin.setToolTipText("");
		btnLogin.setBackground(Color.lightGray);
		panel.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			}
			
		});


		
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);  //alt + enter
			
			KeyListener keyListener = new KeyListener()
			{
				public void keyPressed(KeyEvent keyEvent)	{ 	}

				public void keyReleased(KeyEvent keyEvent)
				{
					int keyCode = keyEvent.getKeyCode();
					
					if (keyCode == 10)
					{
						System.out.println("10");
						btnLogin.doClick();
					}

				}
				public void keyTyped(KeyEvent keyEvent)	{ }
			};
			
			frmLogin.addKeyListener(keyListener);
			btnLogin.addKeyListener(keyListener);
			txtPasswordField.addKeyListener(keyListener);
		
		
		//clean
		btnclean = new JButton("Clean");
		btnclean.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnclean.setForeground(Color.BLUE);
		btnclean.setBounds(150, 225, 100, 30); //399, 260, 117, 25
		btnclean.setToolTipText("");
		btnclean.setBackground(Color.lightGray);
		panel.add(btnclean);
		
		
		//Exit
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setForeground(Color.BLUE);
		btnExit.setBounds(255, 225, 100, 30); //399, 260, 117, 25
		btnExit.setToolTipText("");
		btnExit.setBackground(Color.lightGray);
		panel.add(btnExit);
		
		
		//SubmitStatus
		lblSubmitStatus = new JLabel("...");
		lblSubmitStatus.setForeground(Color.red);
		lblSubmitStatus.setBounds(20, 262, 334, 22);
		lblSubmitStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSubmitStatus.setToolTipText("please select the string that you wish to search the file for.");
		lblSubmitStatus.setHorizontalAlignment(0);
		panel.add(lblSubmitStatus);
		
		
		
		frmLogin.setSize(380, 320); //x,y
		frmLogin.setVisible(true);

		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
		btnExit.setMnemonic(KeyEvent.VK_E); //alt+e = exit	
	
		//focus
		txtPasswordField.requestFocus();
	
		
	} //constructor

	


	public void clean()
	{
		txtUserName.setText("");
		txtPasswordField.setText("");
		lblSubmitStatus.setText("...");
	}
	
	
	
	//??model??
	public void ExitProc()
	{
		int n = JOptionPane.showConfirmDialog(
				frmLogin ,
			    "EXIT Program  ???",
			    "--Exit--",
			    JOptionPane.YES_NO_OPTION);
		
		//user pressed result.
		switch (n) 
		{
		case 0:  //yes

			frmLogin.dispose();
			System.exit(0); // Exit Program
			
			break;
		case 1:  //no
			break;
		}
		
	}
	
	

	
	/***
	 * user
	 */
	void addUserListener (ActionListener listenForBtnUser) 
	{
		btnUser.addActionListener(listenForBtnUser); //login Listener.		
	}
	

	
	/***
	 * login 
	 */
	void addCalculateListener (ActionListener listenForCalcButton) 
	{
		btnLogin.addActionListener(listenForCalcButton); //login Listener.		
	}
	

	
	/***
	 * clean - imput/output field/s
	 */
	void addcleanListener (ActionListener listenForCalcButton)
	{
		btnclean.addActionListener(listenForCalcButton); //clear Listener
	}
	
	
	
	/***
	 * exit
	 */
	void addExitListener (ActionListener listenForExitButton)
	{
		btnExit.addActionListener(listenForExitButton);
	}


	public void setTxtUserName(String currentUserId) {
		// TODO Auto-generated method stub
		
	}
	

	
}//class
