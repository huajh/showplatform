package Factory;

import JavaBeans.Contest.Contest;

public class ContestFactory implements IContestFactory {

	@Override
	public Contest createContest() {
		// TODO Auto-generated method stub
		return new Contest();
	}

}
