package Factory;

import JavaBeans.Works.DesignWorks;
import JavaBeans.Works.Works;

public class DesignWorksFactory implements IWorksFactory {

	@Override
	public Works createWorks() {
		// TODO Auto-generated method stub
		return new DesignWorks();
	}
	
	public DesignWorks createDesignWorks()
	{
		return new DesignWorks();
	}
}
