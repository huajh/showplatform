package Factory;

import JavaBeans.Components.News;

public class NewsFactory implements INewsFactory {

	@Override
	public News createNews() {
		// TODO Auto-generated method stub
		return new News();
	}

}
