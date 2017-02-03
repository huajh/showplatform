package Factory;

import JavaBeans.People.People;

public interface IPeopleFactory extends IFactory {
	
	People createPeople();

}
