package JavaBeans.Components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.util.NewInstance;

import DBOperator.DBOperator;

public class News {
		
	static final public String IndexNews = "IndexNews";
	static final public String ContestNews = "ContestNews";
	
	private int NewsID;
	
	private Integer ContestID;
	private String Title;
	private String Author;
	private int VisitTimes;
	private String NewsContent;
	private String newsType;
	
	private Date CreateTime;
	private Date lastEditTime;
	
	private HashMap ValueMap = new HashMap(); 
	
	public News() {
		// TODO Auto-generated constructor stub
			
	}
	
	public News(int NewsID)
	{
		this.NewsID = NewsID;
	}
	
	public void visited()
	{
		this.VisitTimes++;
		DBOperator dbOperator = DBOperator.getInstance();
		String sql = "update News set VisitTimes = "+VisitTimes+" where NewsID = "+this.getNewsID();
		dbOperator.Execute(sql);
	}
	
	public void insert()
	{	
		this.RefreshValueMap();
		DBOperator dbOperator = DBOperator.getInstance();		
		dbOperator.Insert(this, ValueMap);
	}
	
	public void Save()
	{
		System.out.println("save");
		if(this.NewsID == 0)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
	//	this.setLastEditTime(new Date());
		this.RefreshValueMap();	
		dbOperator.Save(this, ValueMap, "NewsID",String.valueOf(getNewsID()));
	}
	
	public void delete()
	{
		if(this.NewsID == 0)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"NewsID",String.valueOf(getNewsID()));		
	}
	
	public void load()
	{
		if(this.NewsID == 0)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst = dbOperator.Load(this, "NewsID", String.valueOf(getNewsID()));
		try {
			while (rst.next())
			{
				this.setContestID((Integer)rst.getObject("ContestID"));			
				this.setTitle(rst.getString("Title"));				
				this.setAuthor(rst.getString("Author"));				
				this.setNewsContent(rst.getString("NewsContent"));				
				this.setNewsType(rst.getString("newsType"));	
				
				this.setVisitTimes(rst.getInt("VisitTimes"));	
				this.setCreateTime(rst.getDate("CreateTime"));
				
				this.setLastEditTime(rst.getDate("lastEditTime"));														
			}
			rst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void RefreshValueMap()
	{	
		
	//	ValueMap.put("NewsID",getNewsID());			
		ValueMap.put("ContestID",getContestID());			
		ValueMap.put("Title",getTitle());
		ValueMap.put("Author",getAuthor());
		ValueMap.put("VisitTimes",getVisitTimes());	
		ValueMap.put("NewsContent",getNewsContent());
		ValueMap.put("newsType",getNewsType());
		DateFormat dateFormat = DateFormat.getDateTimeInstance(); 	
		if(getCreateTime()!=null)
		{			
			String date_str = dateFormat.format(getCreateTime());
			ValueMap.put("CreateTime", date_str);
		}	
		if(getLastEditTime()!=null)
		{			
			String date_str = dateFormat.format(getLastEditTime());
			ValueMap.put("lastEditTime", date_str);
		}	
		
	}
	
	public void setNewsID(int newsID) {
		NewsID = newsID;
	}
	public int getNewsID() {
		return NewsID;
	}
	public void setContestID(Integer contestID) {
		ContestID = contestID;
	}
	public Integer getContestID() {
		return ContestID;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getTitle() {
		return Title;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getAuthor() {
		return Author;
	}
	public void setVisitTimes(int visitTimes) {
		VisitTimes = visitTimes;
	}
	public int getVisitTimes() {
		return VisitTimes;
	}
	public void setNewsContent(String newsContent) {
		NewsContent = newsContent;
	}
	public String getNewsContent() {
		return NewsContent;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	
}
