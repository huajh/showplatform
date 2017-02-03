package Factory;

import JavaBeans.People.Guest;
import JavaBeans.People.People;

public class GuestFactory implements IPeopleFactory {


	@Override
	public People createPeople() {
		// TODO Auto-generated method stub
		return new Guest();
	}
	
	public Guest createGuest(){
		
		return new Guest();
	}

}
