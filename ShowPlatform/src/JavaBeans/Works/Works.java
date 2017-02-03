package JavaBeans.Works;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import DBOperator.DBOperator;
import InterOperate.LoginType;
import JavaBeans.Components.Comment;
import JavaBeans.Components.News;
import JavaBeans.Contest.Contest;

public abstract class Works {
	
	static final public String DesignWorksType = "DesignWorks";
	static final public String LiteratureWorksType = "LiteratureWorks";
	static final public String PhotographyWorksType = "PhotographyWorks";
	
	static final public int Allow = 2;
	static final public int request = 1;
	static final public int none = 0;
	static final public int Refuse = -1;
	
	private int WorksID;
	private Integer ContestID;
	private String MemberID;
	
	private String Name;
	private String Awards;
	private String Summary;
	private String CreativePlace;
	private String WorksType;
	private String WorksPath;
	
	private Date CreativeTime ;
	private Date LoadTime;
	
	private int VisitTimes;
	private int CommentTimes;
	private int ScoreTimes;
	private float avgScore;
	
	private int IsAllow;
	
	protected HashMap ValueMap = new HashMap(); 
	
	abstract public void Save();	
	abstract public void insert();
	abstract public void load();
	
	public boolean isExist() {
		return (DBOperator.getInstance()).isExist(this, "WorksID", String.valueOf(getWorksID()));
	}
	public void delete()
	{
		if(getWorksID()== 0)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"WorksID",String.valueOf(getWorksID()));	
	}
	
	public void visited()
	{
		this.VisitTimes++;
	}
	
	public void commented()
	{
		this.CommentTimes++;
	}
	
	public void Scored()
	{
		this.ScoreTimes++;
	}
	
	public static String getWorksType(int worksID)
	{
		String sql = "select worksType " +
				"from works " +
				"where worksID = "+worksID;
		DBOperator dbOperator = DBOperator.getInstance();	
		ResultSet rst =  dbOperator.Query(sql);
		try {
			while(rst.next())
			{
				String worksType = rst.getString("worksType").trim();
				return worksType;
			}
			rst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	protected void RefreshValueMap()
	{	
	//	ValueMap.put("WorksID", getWorksID());
		ValueMap.put("ContestID", getContestID());
		ValueMap.put("MemberID", getMemberID());
		ValueMap.put("Name",getName());	
		ValueMap.put("Awards",getAwards());
		ValueMap.put("Summary",getSummary());
		ValueMap.put("CreativePlace",getCreativePlace());
		ValueMap.put("WorkType",getWorksType());
		ValueMap.put("WorksPath",getWorksPath());
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(); 	
		if(getCreativeTime()!=null)
		{
			String date_str = dateFormat.format(getCreativeTime());
			ValueMap.put("CreativeTime", date_str);
		}
		if(getLoadTime()!=null)
		{			
			String date_str = dateFormat.format(getLoadTime());
			ValueMap.put("LoadTime", date_str);
		}
		
		ValueMap.put("VisitTimes",getVisitTimes());	
		ValueMap.put("CommentTimes",getCommentTimes());	
		ValueMap.put("ScoreTimes",getScoreTimes());	
		ValueMap.put("AvgScore",getAvgScore());		
		ValueMap.put("IsAllow",getIsAllow());	
	}
	
	
	public Collection getALlComment()
	{	
		String sql = "select * from Comment where WorksID = "+this.getWorksID()
		+ " order by CommentTime desc ";
		DBOperator dbOperator = DBOperator.getInstance();		
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				Comment comment = new Comment();
				comment.setCommentID(rst.getInt("CommentID"));
				comment.setWorksID(rst.getInt("WorksID"));
				comment.setMemberID(rst.getString("MemberID"));
				comment.setReplyMemberID(rst.getString("ReplyMemberID"));
				comment.setCommentContent(rst.getString("CommentContent"));
				comment.setCommentTime(rst.getDate("CommentTime"));
				c.add(comment);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public void setWorksID(int worksID) {
		this.WorksID = worksID;
	}

	public int getWorksID() {
		return WorksID;
	}
	public void setContestID(Integer contestID) {
		this.ContestID = contestID;
	}

	public Integer getContestID() {
		return ContestID;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setAwards(String awards) {
		Awards = awards;
	}

	public String getAwards() {
		return Awards;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public String getSummary() {
		return Summary;
	}

	public void setLoadTime(Date loadTime) {
		LoadTime = loadTime;
	}

	public Date getLoadTime() {
		return LoadTime;
	}

	public void setVisitTimes(int visitTimes) {
		VisitTimes = visitTimes;
	}

	public int getVisitTimes() {
		return VisitTimes;
	}

	public void setCreativePlace(String creativePlace) {
		CreativePlace = creativePlace;
	}

	public String getCreativePlace() {
		return CreativePlace;
	}

	public void setCreativeTime(Date creativeTime) {
		CreativeTime = creativeTime;
	}

	public Date getCreativeTime() {
		return CreativeTime;
	}

	public void setCommentTimes(int commentTimes) {
		CommentTimes = commentTimes;
	}

	public int getCommentTimes() {
		return CommentTimes;
	}

	public void setScoreTimes(int scoreTimes) {
		ScoreTimes = scoreTimes;
	}

	public int getScoreTimes() {
		return ScoreTimes;
	}

	public void setIsAllow(int isAllow) {
		this.IsAllow = isAllow;
	}

	public int getIsAllow() {
		return IsAllow;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}
	public float getAvgScore() {
		return avgScore;
	}
	public void setWorksType(String worksType) {
		WorksType = worksType;
	}
	public String getWorksType() {
		return WorksType;
	}
	public void setWorksPath(String worksPath) {
		WorksPath = worksPath;
	}
	public String getWorksPath() {
		return WorksPath;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getMemberID() {
		return MemberID;
	}
	

}
