package Factory;

import JavaBeans.Works.PhotographyWorks;
import JavaBeans.Works.Works;

public class PhotographyWorksFactory implements IWorksFactory {

	@Override
	public Works createWorks() {
		// TODO Auto-generated method stub
		return new PhotographyWorks();
	}

	public PhotographyWorks createPhotographyWorks()
	{
		
		return new PhotographyWorks();
	}
}
