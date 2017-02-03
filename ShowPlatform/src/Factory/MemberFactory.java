package Factory;

import JavaBeans.People.Member;
import JavaBeans.People.People;
import JavaBeans.People.User;

public class MemberFactory implements IUserFactory {

	@Override
	public People createPeople() {
		// TODO Auto-generated method stub
		return new Member();
	}

	@Override
	public User createUser() {
		// TODO Auto-generated method stub
		return new Member();
	}
	
	@Override
	public User createUser(String id) {
		// TODO Auto-generated method stub
		return new Member(id);
	}

	public Member createMember()
	{
		return new Member();
	}
	public Member createMember(String id)
	{
		return new Member(id);
	}


}
