package JavaBeans.Components;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import DBOperator.DBOperator;

public class Comment {
	
	private int CommentID;
	
	private String memberID;
	private String ReplyMemberID;
	private int WorksID;
	private String CommentContent;
	private Date CommentTime;
	
	private HashMap ValueMap = new HashMap();
	
	private void RefreshValueMap()
	{
	//	ValueMap.put("CommentID",getCommentID());	
		ValueMap.put("memberID",getMemberID());	
		ValueMap.put("ReplyMemberID",getReplyMemberID());
		ValueMap.put("WorksID",getWorksID());
		ValueMap.put("CommentContent",getCommentContent());	
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(); 	
		if(getCommentTime()!=null)
		{			
			String date_str = dateFormat.format(getCommentTime());
			ValueMap.put("CommentTime", date_str);
		}	
	}
	
	public void insert()
	{
		this.RefreshValueMap();	
		DBOperator dbOperator = DBOperator.getInstance();		
		dbOperator.Insert(this, ValueMap);
	}
	
	public void delete()
	{
		if(this.CommentID == 0)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"NewsID",String.valueOf(getCommentID()));		
	}
	
	public void setCommentID(int commentID) {
		CommentID = commentID;
	}
	public int getCommentID() {
		return CommentID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setWorksID(int worksID) {
		WorksID = worksID;
	}
	public int getWorksID() {
		return WorksID;
	}
	public void setReplyMemberID(String replyMemberID) {
		ReplyMemberID = replyMemberID;
	}
	public String getReplyMemberID() {
		return ReplyMemberID;
	}
	public void setCommentContent(String commentContent) {
		CommentContent = commentContent;
	}
	public String getCommentContent() {
		return CommentContent;
	}
	public void setCommentTime(Date commentTime) {
		CommentTime = commentTime;
	}
	public Date getCommentTime() {
		return CommentTime;
	}

	
	

}
