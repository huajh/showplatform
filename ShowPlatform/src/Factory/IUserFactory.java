package Factory;

import JavaBeans.People.User;

public interface IUserFactory extends IPeopleFactory {
	
	User createUser(String id);
	
	User createUser();

}
