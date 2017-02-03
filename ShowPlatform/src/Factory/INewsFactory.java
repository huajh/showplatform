package Factory;

import JavaBeans.Components.News;

public interface INewsFactory extends IFactory {
	
	News createNews();
	
}
