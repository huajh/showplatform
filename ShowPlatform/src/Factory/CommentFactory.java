package Factory;

import JavaBeans.Components.Comment;

public class CommentFactory implements ICommentFactory {

	@Override
	public Comment createComment() {
		// TODO Auto-generated method stub
		return new Comment();
	}
	

}
