package JavaBeans.Contest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import DBOperator.DBOperator;
import Factory.INewsFactory;

public class Contest {

	static final public int Refuse = -1;
	static final public int none = 0;
	static final public int requestAccess = 1;
	static final public int Access = 2;
	static final public int Start_Upload = 3;
	static final public int Start_Score = 4;
	static final public int Finished = 5;
	
	// contestType
	
	static final public String DesignType = "Design";
	static final public String LiteratureType = "Literature";
	static final public String PhotographyType = "Photography";
	
	private int ContestID;
	
	private String Title;
	private String OrganizerID;
	private String ContestContent;
	private String ContestType;
	private String ContestPath;
	private int ContestLevel;
	private int ContestStatus;
	private String WorksType;
	private String AccessPwd;
	
	private Date BeignTime;
	private Date EndTime;
	
	private INewsFactory NewsCreator;

	private HashMap ValueMap = new HashMap(); 
	
	public Contest()
	{
		
	}
	public Contest(int ContestID)
	{
		this.ContestID = ContestID;
	}
	
	public boolean isExist() {
		return (DBOperator.getInstance()).isExist(this, "ContestID", String.valueOf(getContestID()));
	}

	public void insert()
	{
		System.out.println("insert");
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();
		dbOperator.Insert(this, ValueMap);
	}
	
	public void delete()
	{
		if(this.ContestID == 0)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"ContestID",String.valueOf(getContestID()));		
	}
	
	public void Save()
	{ 
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();
		dbOperator.Save(this, ValueMap, "ContestID", String.valueOf(getContestID()));
	}
	
	public void load()
	{
		if(this.ContestID == 0)
		{
			return ;
		}		
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst = dbOperator.Load(this, "ContestID",String.valueOf(getContestID()));
		try {
			while (rst.next())
			{			
				this.setTitle(rst.getString("Title"));				
				this.setOrganizerID(rst.getString("OrganizerID"));				
				this.setContestType(rst.getString("ContestType"));
				this.setContestPath(rst.getString("ContestPath"));
				this.setWorksType(rst.getString("WorksType"));
				this.setAccessPwd(rst.getString("AccessPwd"));
				this.setContestContent(rst.getString("ContestContent"));
				this.setContestLevel(rst.getInt("ContestLevel"));
				this.setContestStatus(rst.getInt("ContestStatus"));	
				
				this.setBeignTime(rst.getDate("BeignTime"));
				this.setEndTime(rst.getDate("EndTime"));							
								
			}
			rst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setContestID(int contestID) {
		ContestID = contestID;
	}

	public int getContestID() {
		return ContestID;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getTitle() {
		return Title;
	}

	public void setOrganizerID(String organizerID) {
		OrganizerID = organizerID;
	}

	public String getOrganizerID() {
		return OrganizerID;
	}

	public void setContestType(String contestType) {
		ContestType = contestType;
	}

	public String getContestType() {
		return ContestType;
	}

	public void setContestLevel(int contestLevel) {
		ContestLevel = contestLevel;
	}

	public int  getContestLevel() {
		return ContestLevel;
	}

	public void setContestStatus(int contestStatus) {
		ContestStatus = contestStatus;
	}

	public int getContestStatus() {
		return ContestStatus;
	}

	public void setWorksType(String worksType) {
		WorksType = worksType;
	}

	public String getWorksType() {
		return WorksType;
	}

	public void setAccessPwd(String accessPwd) {
		this.AccessPwd = accessPwd;
	}

	public String getAccessPwd() {
		return AccessPwd;
	}

	public void setBeignTime(Date beignTime) {
		BeignTime = beignTime;
	}

	public Date getBeignTime() {
		return BeignTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public Date getEndTime() {
		return EndTime;
	}
	

	public void setContestContent(String contestContent) {
		ContestContent = contestContent;
	}

	public String getContestContent() {
		return ContestContent;
	}
	
	protected void RefreshValueMap()
	{			
	//	ValueMap.put("ContestID",getContestID());	
		
		ValueMap.put("Title",getTitle());	
		ValueMap.put("OrganizerID",getOrganizerID());
		ValueMap.put("ContestType",getContestType());
		ValueMap.put("ContestContent",getContestContent());
		ValueMap.put("ContestPath",getContestPath());
		ValueMap.put("ContestLevel",getContestLevel());	
		ValueMap.put("ContestStatus",getContestStatus());	
		
		ValueMap.put("WorksType",getWorksType());	
		ValueMap.put("accessPwd",getAccessPwd());
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(); 	
		if(getBeignTime()!=null)
		{			
			String date_str = dateFormat.format(getBeignTime());
			ValueMap.put("BeignTime", date_str);
		}
		if(getEndTime()!=null)
		{			
			String date_str = dateFormat.format(getEndTime());
			ValueMap.put("EndTime", date_str);
		}
		
	}
	public void setContestPath(String contestPath) {
		ContestPath = contestPath;
	}
	public String getContestPath() {
		return ContestPath;
	}

	

}
