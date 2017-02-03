package JavaBeans.Works;

import java.sql.ResultSet;
import java.sql.SQLException;

import DBOperator.DBOperator;

public class DesignWorks extends Works {
	
	public String getWorksType() {
		return Works.DesignWorksType;
	}
	
	protected void RefreshValueMap()
	{	
		ValueMap.put("WorksType", getWorksType());
		super.RefreshValueMap();
		
	}
	public DesignWorks()
	{
	}
	
	public DesignWorks(int worksID)
	{
		this.setWorksID(worksID);
	}
	
	public void insert()
	{		
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();
		dbOperator.Insert(this, ValueMap);
	}
	@Override
	public void Save() {
		System.out.println("save designworks");
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
		// TODO Auto-generated method stub
		
		System.out.println("load");
		
		if(this.getWorksID() == 0)
		{
			return;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst = dbOperator.Load(this, "WorksID", String.valueOf(getWorksID()));
		try {
			while (rst.next())
			{
				System.out.println("while load");
				
				
				this.setContestID(rst.getInt("ContestID"));
				this.setMemberID(rst.getString("MemberID"));
				this.setName(rst.getString("Name"));				
				this.setAwards(rst.getString("Awards"));				
				this.setSummary(rst.getString("Summary"));
				this.setCreativePlace(rst.getString("CreativePlace"));	
				this.setWorksType(rst.getString("WorksType"));
				this.setVisitTimes(rst.getInt("VisitTimes"));
				this.setCommentTimes(rst.getInt("CommentTimes"));
				this.setScoreTimes(rst.getInt("ScoreTimes"));
				this.setAvgScore(rst.getFloat("AvgScore"));				
				this.setIsAllow(rst.getInt("IsAllow"));
				this.setWorksPath(rst.getString("WorksPath"));
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
