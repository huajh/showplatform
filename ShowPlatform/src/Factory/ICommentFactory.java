package Factory;

import JavaBeans.Components.Comment;

public interface ICommentFactory extends IFactory {
	
	Comment createComment();

}
