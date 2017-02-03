package Factory;

import JavaBeans.People.Administor;
import JavaBeans.People.People;
import JavaBeans.People.User;

public class AdminFactory implements IUserFactory {

	@Override
	public People createPeople() {
		// TODO Auto-generated method stub
		return new Administor();
	}
	
	@Override
	public User createUser() {
		// TODO Auto-generated method stub
		return new Administor();
	}
	
	@Override
	public User createUser(String account) {
		// TODO Auto-generated method stub
		return new Administor(account);
	}
	
	public Administor createAdministor()
	{
		return new Administor();
	}
	
	public Administor createAdministor(String account)
	{
		return new Administor(account);
	}
	
	public Administor createAdministor(String account,String pwd)
	{
		return new Administor(account,pwd);
	}


}
