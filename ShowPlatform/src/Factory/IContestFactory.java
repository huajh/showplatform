package Factory;

import JavaBeans.Contest.Contest;

public interface IContestFactory extends IFactory {
	
	Contest createContest();

}
