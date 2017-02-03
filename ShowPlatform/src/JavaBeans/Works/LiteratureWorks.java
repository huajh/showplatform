package JavaBeans.Works;

import java.sql.ResultSet;
import java.sql.SQLException;

import DBOperator.DBOperator;

public class LiteratureWorks extends Works {
	
	private String Content; // 
	
	public LiteratureWorks()
	{
	}
	
	public LiteratureWorks(int worksID)
	{
		this.setWorksID(worksID);
	}
	
	public String getWorksType() {
		
		return Works.LiteratureWorksType;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	public String getContent() {
		
		return Content;
		
	}
	protected void RefreshValueMap()
	{
		ValueMap.put("WorksType", getWorksType());
		super.RefreshValueMap();
		ValueMap.put("Content",getContent());	
		
	}
	@Override
	public void insert()
	{
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();
		dbOperator.Insert(this, ValueMap);
	}

	@Override
	public void Save() {
		System.out.println("save LiteratureWorks");
		if(this.getWorksID() == 0)
		{
			return;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();		
		dbOperator.Save(this, ValueMap, "WorksID", String.valueOf(getWorksID()));
	}

	
	@Override
	public void load() {
		
		if(this.getWorksID() == 0)
		{
			return;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst = dbOperator.Load(this, "WorksID", String.valueOf(getWorksID()));
		try {
			while (rst.next())
			{
				this.setContestID(rst.getInt("ContestID"));
				this.setMemberID(rst.getString("MemberID"));
				this.setName(rst.getString("Name"));				
				this.setAwards(rst.getString("Awards"));				
				this.setSummary(rst.getString("Summary"));
				this.setCreativePlace(rst.getString("CreativePlace"));	
				this.setWorksType(rst.getString("WorksType"));
				this.setContent(rst.getString("Content"));	
				this.setWorksPath(rst.getString("WorksPath"));
				this.setVisitTimes(rst.getInt("VisitTimes"));
				this.setCommentTimes(rst.getInt("CommentTimes"));
				this.setScoreTimes(rst.getInt("ScoreTimes"));
				this.setAvgScore(rst.getFloat("AvgScore"));				
				this.setIsAllow(rst.getInt("IsAllow"));
				
				this.setCreativeTime(rst.getDate("CreativeTime"));
				this.setLoadTime(rst.getDate("LoadTime"));	
								
			}
			rst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
