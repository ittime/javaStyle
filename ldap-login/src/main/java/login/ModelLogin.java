package login;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;



public class ModelLogin 
{
	private String osName = "";	
	private String userId = "";
	private	static String UserIdFull = "";
	private String password = "";
	private String domain = "";
	private String ldapURL = "";
	
	public String getLdapURL() {
		return ldapURL;
	}

	public void setLdapURL(String ldapURL) {
		this.ldapURL = ldapURL;
	}



	private Boolean LoginStatus = false; //ldap;
	private String stLoginMessage = ""; 
	private String stFindGroup = "";
	

	public String getStFindGroup() {
		return stFindGroup;
	}

	public void setStFindGroup(String stFindGroup) {
		this.stFindGroup = stFindGroup;
	}



	private static LogInLog l = new LogInLog(); //logs


	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	
	
	public String getUserId() {
		return userId;
	}

	
	
	public String getUserIdFull() {
		return UserIdFull;
	}

	public static void setUserIdFull(String userIdFull) {
		UserIdFull = userIdFull;
	}


	
	public String getCurrentUserId() {
		return 	System.getProperty( "user.name" );
	}


	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	
	
	public Boolean getLoginStatus() {
		return LoginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		LoginStatus = loginStatus;
	}
	
	

	public String getStLoginMessage() {
		return stLoginMessage;
	}

	public void setStLoginMessage(String stLoginMessage) {
		this.stLoginMessage = stLoginMessage;
	}
	
	
	
	/**
	 * Get/Set Domain
	 */
	public String getDomain() {
		return domain;
	}

	
	public void setDomain() 
	{
		
		String domainName = "";
		
		try{
            String fqdn = java.net.InetAddress.getLocalHost().getCanonicalHostName();
            if (fqdn.split("\\.").length>1) 
            {
            	domainName = fqdn.substring(fqdn.indexOf(".")+1);
            	this.domain = domainName;
            }
        }
        catch(java.net.UnknownHostException e){
        	l.LogIt("error", "<<<set domain>>>", " " + e.getMessage());
        }
		
//		this.domain = "";
	}
	
	
	
	public void setInformation() 
	{
 		
		String osName = System.getProperty( "os.name" ).toLowerCase();
		String className = null;
		String methodName = "getUsername";
//		String fullName = System.getProperty("os.getSimpleName");

		
		setDomain();
		
		
		if( osName.contains( "windows" ) ){
		    className = "com.sun.security.auth.module.NTSystem";
		    methodName = "getName";
		    
		}
		else if( osName.contains( "linux" ) ){
		    className = "com.sun.security.auth.module.UnixSystem";
		}
		else if( osName.contains( "solaris" ) || osName.contains( "sunos" ) ){
		    className = "com.sun.security.auth.module.SolarisSystem";
		}

		if( className != null )
		{
			
		    try {
				Class<?> c = Class.forName( className );
				Method method = c.getDeclaredMethod( methodName );
				Object o = c.newInstance();
				//System.out.println( method.invoke( o ) );
				
				String StCurrentUser =  (String) method.invoke( o );
				//
				//attributes.
				setOsName(osName);
				setUserId(StCurrentUser);

				
			} catch (ClassNotFoundException e) {
				// notnow Auto-generated catch block
				e.printStackTrace();
	        	l.LogIt("error", "<<<set Information>>>", " " + e.getMessage());
			} catch (NoSuchMethodException e) {
				// notnow Auto-generated catch block
				e.printStackTrace();
				l.LogIt("error", "<<<set Information>>>", " " + e.getMessage());
			} catch (SecurityException e) {
				// notnow Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// notnow Auto-generated catch block
				l.LogIt("error", "<<<set Information>>>", " " + e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// notnow Auto-generated catch block
				e.printStackTrace();
				l.LogIt("error", "<<<set Information>>>", " " + e.getMessage());
			} catch (IllegalArgumentException e) {
				// notnow Auto-generated catch block
				l.LogIt("error", "<<<set Information>>>", " " + e.getMessage());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// notnow Auto-generated catch block
				e.printStackTrace();
				l.LogIt("error", "<<<set Information>>>", " " + e.getMessage());
			}			    
		}
		
		
	}	
	

	
    public static LdapContext getConnection(String username, String password, String domainName, String serverName) throws NamingException {
    	 
        if (domainName==null){
            try{
                String fqdn = java.net.InetAddress.getLocalHost().getCanonicalHostName();
                if (fqdn.split("\\.").length>1) domainName = fqdn.substring(fqdn.indexOf(".")+1);
            }
            catch(java.net.UnknownHostException e){
            	l.LogIt("error", "<<<LdapContext>>>", " " + e.getMessage());
            }
        }
        
        
        setUserIdFull(username + "@" + domainName);
        //System.out.println("Authenticating " + username + "@" + domainName + " through " + serverName);
        System.out.println("modellogin: Authenticating " + username + "@" + domainName);
        
      
        
        if (password!=null){
            password = password.trim();
            if (password.length()==0) password = null;
        }
 
        //bind by using the specified username/password
        Hashtable<String, String> props = new Hashtable<String, String>();
        String principalName = username + "@" + domainName;
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        if (password!=null) props.put(Context.SECURITY_CREDENTIALS, password);
 
 
        String ldapURL = "ldap://" + ((serverName==null)? domainName : serverName + "." + domainName) + '/';
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, ldapURL);
        
       // System.out.println("ldapURL " + ldapURL);
        
        
        try{
            return new InitialLdapContext(props, null);
        }
        catch(javax.naming.CommunicationException e){
//            throw new NamingException("Failed to connect to " + domainName + ((serverName==null)? "" : " through " + serverName));
        	System.out.println("<<CommunicationException, Password not ok>>");
        	l.LogIt("error", "<<<Password>>>", "Password not ok" + e.getMessage());
        }
        catch(NamingException e){
            //throw new NamingException("Failed to authenticate " + username + "@" + domainName + ((serverName==null)? "" : " through " + serverName));
        	System.out.println("<<NamingException, Password not ok>>  " + e.getMessage());
        	l.LogIt("error", "<<<Password>>>", "Password not ok" + e.getMessage());
        }
		return null;
    }
 
	

    
	public static boolean isMemberOfADGroup(LdapContext ctx, String dnADGroup, String dnADUser) {
		

		  try {
				
		   DirContext lookedContext = (DirContext) (ctx.lookup(dnADGroup));
		   Attribute attrs = lookedContext.getAttributes("").get("member");
		  		   
		   for (int i = 0; i < attrs.size(); i++) {
		    String foundMember = (String) attrs.get(i);
		    if(foundMember.equals(dnADUser)) {
		     return true;
		    }
		   }
		  } catch (NamingException ex) {
		   String msg = "There has been an error trying to determin a group membership for AD user with distinguishedName: "+dnADUser;
		   System.out.println(msg);
		   ex.printStackTrace();
		  }
		  return false;
		 }
		
    

    /****
     * OSI - 
     */
    private void OSI() 
    {
 			
		System.out.println("os - user.name: " + System.getProperty( "user.name" ).toLowerCase() );
		System.out.println("os - name: " + System.getProperty( "os.name" ).toLowerCase() );		
		System.out.println("os - user.dir: " + System.getProperty( "user.dir" ).toLowerCase() );
		System.out.println("os - user.home: " + System.getProperty( "user.home" ).toLowerCase() );    	
    }
    

    

	
public boolean Login() {
	//Active Directory check //
  	try
  	{
  		
  		if ((getUserId() == "" ) || (getUserId() == null) || (getUserId().length() <= 1))
  		{
  			setStLoginMessage("please Enter a user id");
  			System.out.println("please Enter a user id");
  			return false;
  		}
  		
  		if ((getPassword() == "" ) || (getPassword() == null) || (getPassword().length() <= 1))
  		{
  			System.out.println("please Enter a password");
  			setStLoginMessage("please Enter a password");  			
  			return false;
  		}
  		
  		
  		/****
  		 * 
  		 */
//  		System.out.println(getUserId() + "  " + getPassword());
	    LdapContext ctx = getConnection(getUserId(), getPassword(), null, null);
  		//LdapContext ctx = getConnection(getUserId(), "", null, null);

    	if (ctx == null) {
    		System.out.println("Password NOT-OK");
    		setStLoginMessage("Password NOT-OK");
    		setLoginStatus(false);
    	}
    	else 
    	{
    		System.out.println("Password OK");
    		setStLoginMessage("Password OK");
    		setLoginStatus(true);	    
    		
    	}
    	
	    System.out.println("\n\n model Information:");
    	System.out.println("\nctx==="+ctx);
    	System.out.println("group===" + getStFindGroup());
    	System.out.println("user==="+getUserId() + " full user ===" + getUserIdFull());
	    	
    //    System.out.println(isMemberOfADGroup (ctx, getStFindGroup() ,getUserIdFull() ) );
    	
    	/***
    	 * 
    	 */
//    	
//    	try {
//			System.out.println("\nmodel login - check group ");
//			LdapGroupAuthenticator l = new LdapGroupAuthenticator();
//			System.out.println("*** login status >>> " + l.authenticate(getDomain(),getUserId() ,getPassword(), getStFindGroup()) );
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Group problem ") ;
//			e.printStackTrace();
//		}

    	
    	/***
    	 *  test
    	 */
//    	Memberof m = new Memberof();
//    	System.out.println("model >>> Memberof " +  m.fnMemberof(getUserIdFull(), getStFindGroup()) );
    	
    	
    	//boolean bl = isMemberOfADGroup(ctx,getStFindGroup(),getUserIdFull());
    	//System.out.println("~~~ gropu to fined: " + getStFindGroup() + "   user id == " + getUserIdFull() ); //getUserId //getUserIdFull
    	
//    	System.out.println("!!!!" + "ldap://" + getDomain() + ":389");
 //   	Memberof m = new Memberof();
  //  	String test = m.fnMemberof(getUserId(), getPassword(),getDomain(),getStFindGroup());// "ldap://" + getDomain() + ":389");
 
    	
    	
//    	/*****
//    	 * 
//    	 */
//    	LdapTwo lt = new LdapTwo();
//    	lt.authenticate(getDomain(), getUserId(), getPassword());
//    	

    	LdapPrimary lp = new LdapPrimary();
    	
    	
		ctx.close();
			
	}
	catch(Exception e)
  	{
	    //e.printStackTrace();
		System.out.println(""); //error
		l.LogIt("error", "Login", " " + e.getMessage());
		
	}
  	
  	if ((getLoginStatus() == true) || (getLoginStatus() == false))
  		return getLoginStatus();
  	else
  		return false;

}
	


public ModelLogin() 
{
	OSI(); //Print OS Information to console
	
	setInformation();
	
	
} //constructor


} //class
