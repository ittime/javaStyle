package login;

public class Login 
{
	public Login() 
	{
		ModelLogin model = new ModelLogin();
		ViewLogin view = new ViewLogin();
		ControllerLogin controller = new ControllerLogin(model, view);	
				
	}

}
