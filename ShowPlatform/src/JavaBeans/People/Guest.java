package JavaBeans.People;

import DBOperator.DBOperator;
import Factory.AdminFactory;
import Factory.MemberFactory;
import InterOperate.LoginType;

public class Guest extends People {
	
	public static final String GuestType = "Guest";
	
	private static final MemberFactory Member_FACTORY = new MemberFactory();
	private static final AdminFactory Admin_FACTORY = new AdminFactory();
	

	
	static public Object Login(String UserType,String username,String pwd)
	{
		DBOperator dbOperator = DBOperator.getInstance();
		if(!dbOperator.checkLogin(UserType, username, pwd))
			return null;
		else 
		{	 
			if(UserType.equals(LoginType.ADMINTYPE))
			{
				return Admin_FACTORY.createAdministor(username);
			}else if(UserType.equals(LoginType.MEMBERTYPE)){
				return Member_FACTORY.createMember(username);
			}			
			return null;		
		}
	}

}
