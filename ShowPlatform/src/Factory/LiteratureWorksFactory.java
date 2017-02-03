package Factory;

import JavaBeans.Works.LiteratureWorks;
import JavaBeans.Works.Works;

public class LiteratureWorksFactory implements IWorksFactory {

	@Override
	public Works createWorks() {
		// TODO Auto-generated method stub
		return new LiteratureWorks();
	}
	
	public LiteratureWorks creaLiteratureWorks()
	{
		return new LiteratureWorks();
	}

}
