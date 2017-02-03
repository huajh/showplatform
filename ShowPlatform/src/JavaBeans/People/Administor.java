package JavaBeans.People;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import DBOperator.DBOperator;
import Factory.AdminFactory;
import Factory.INewsFactory;
import Factory.NewsFactory;
import JavaBeans.Components.News;
import JavaBeans.Contest.Contest;
import JavaBeans.Works.*;
import Path.FileUtil;

public class Administor extends User {
	
	private String Account;
	
	private static final AdminFactory Admin_FACTORY = new AdminFactory();
	private static final INewsFactory News_FACTORY = new NewsFactory();		

	public Administor()
	{		
		
	}
	
	public Administor(String account)
	{
		this.setAccount(account);
	}	
	
	public Administor(String account,String pwd)
	{
		this.setAccount(account);
		this.setPwd(pwd);
	}
	
	@Override
	public boolean isExist() {
		// TODO Auto-generated method stub
		return (DBOperator.getInstance()).isExist(this, "Account", getAccount());
	}
	
	protected void RefreshValueMap()
	{
		ValueMap.put("Account", getAccount());
		super.RefreshValueMap();
	}
		
	public News createNews()
	{
		return News_FACTORY.createNews();
	}	
	
	public void addNews(News news)
	{
		news.insert();
	}
	
	public void deleteNews(News news)
	{
		news.delete();
	}
	public void deleteNews(int newsID)
	{
		String sql = "delete from News where NewsID = " +newsID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}

	public void editNews(News news)
	{
		news.Save();
	}	
	
	public void addAdministor(String account,String pwd)
	{		
		Administor admin = Admin_FACTORY.createAdministor(account,pwd);
		admin.insert();
	}
	
	/**
	 * 
	 * 
	 * @param contestID
	 * @return
	 */
	public Collection getRequestWorks(int contestID)
	{
		Contest contest = new Contest(contestID);
		contest.load();
		String worksType = contest.getWorksType();
		String sql = "select * " +
		" from Works " +
		" where  isAllow = "+Works.request+" and ContestID = "+contestID+
		" order by WorksID desc ";
		DBOperator dbOperator = DBOperator.getInstance();		
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				if(worksType.equalsIgnoreCase(Works.DesignWorksType))
				{
					Works works = new DesignWorks(rst.getInt("WorksID"));				
					works.load();
					c.add(works);
					
				}else if(worksType.equalsIgnoreCase(Works.LiteratureWorksType)) {
					
					Works works = new LiteratureWorks(rst.getInt("WorksID"));
					works.load();
					c.add(works);
					
				}else if(worksType.equalsIgnoreCase(Works.PhotographyWorksType))
				{
					Works works = new PhotographyWorks(rst.getInt("WorksID"));
					works.load();
					c.add(works);
				}				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	
	public void AccessWorks(int worksID,String url) throws SQLException
	{
		File file = new File(url);
		if (!file.mkdirs()){
			System.out.println("mkdirs failed");
		}
		
		String msql = "select WorksPath from Works where WorksID = "+worksID;
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(msql);
		rst.next();
		File newFile = new File("ShowTest" + rst.getString("WorksPath"));
		if (!newFile.exists()) {
			System.out.println(newFile.getAbsolutePath());
		}
		
		File dest = new File(url + newFile.getName());
		
		System.out.println(newFile.renameTo(dest));
		//FileUtil.MoveFile("/ShowTest" + rst.getString("WorksPath"), url);
		String sql = " update Works " +
				" set isAllow = "+Works.Allow+", WorksPath = '"+url+"'" +
				" where Works.workID = "+worksID+";";		
		dbOperator.Execute(sql);
	}
	
	public void AccessWorks(int[] worksIDs)
	{
		if(worksIDs.length==0)
			return;
		String sql="";
		for(int i=0;i<worksIDs.length;i++)
		{
			sql +=" update Works set Works.isAllow = "+Works.Allow+" where Works.workID = "+worksIDs[i]+"; ";
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);		
	}
	
	public void refuseWorks(int worksID)
	{
		String sql = "update Works set Works.isAllow = "+Works.Refuse+" where Works.workID = "+worksID+";";
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);			
	}
	
	public void refuseWorks(int[] worksIDs)
	{
		if(worksIDs.length==0)
			return;
		String sql="";
		for(int i=0;i<worksIDs.length;i++)
		{
			sql +=" update Works set Works.isAllow = "+Works.Refuse+" where Works.workID = "+worksIDs[i]+"; ";
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);				
	}
	
	public void deleteWorks(int worksID)
	{
		String sql = "delete from News where worksID =" +worksID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}

	
	public Collection getRequestContest()
	{		
		String sql = "select * " +
		" from Contest " +
		" where  ContestStatus = "+Contest.requestAccess+
		" order by ContestID desc ";
		DBOperator dbOperator = DBOperator.getInstance();		
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{

				Contest contest = new Contest(rst.getInt("ContestID"));
				contest.load();
				c.add(contest);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void AccessContest(int contestID)
	{
		String sql = " update Contest set ContestStatus = "+Contest.Access+" where contestID = " + contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);				
	}
	
	public void AccessContest(int[] contestIDs)
	{		
		if(contestIDs.length==0)
			return;
		String sql="";
		for(int i=0;i<contestIDs.length;i++)
		{
			sql+= "update Contest set ContestStatus = "+Contest.Access+" where contestID = " + contestIDs[i]+"; ";
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);						
	}	
		
	public void RefuseContest(int contestID)
	{
		String sql="update Contest set ContestStatus = "+Contest.Refuse +
		" where ContestID = "+contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}
	
	public void RefuseContest(int[] contestIDs)
	{
		if(contestIDs.length==0)
			return;
		String sql="";
		for(int i=0;i<contestIDs.length;i++)
		{
			sql+= "update Contest set ContestStatus = "+Contest.Refuse+" where contestID = " + contestIDs[i]+"; ";
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}
	
	
	
	public void AccessMember(String memberID,int accessType)
	{
		String sql="";
			 sql += "update Member" +
					" set CertifiedStatus = "+accessType+
					" where MemberID = '"+memberID+"'; ";			
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}
	/**
	 * 审核用户权限，审核通过后用户才能上传作品
	 * @param memberIDs
	 * @param accessType  权限类型
	 */	
	public void AccessMember(String[] memberIDs,int accessType)
	{
		if(memberIDs.length==0)
			return;
		String sql="";
		for(int i= 0; i<memberIDs.length;i++)
		{
			 sql += "update Member" +
					" set CertifiedStatus ="+accessType+
					" where MemberID ='"+memberIDs[i]+"'; ";
			
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}
	public void AcceptMember(String memberID,int accessType)
	{
		String sql= "update Member" +
					" set CertifiedStatus ="+accessType+
					" where MemberID ='"+memberID+"'; ";
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
	}
	/**
	 * 设置比赛状态，分别为开始上传作品、开始投票、比赛结束
	 * @param contestID
	 */
	public void StartUploadContest(int contestID) //xiaojie
	{
		String sql="update Contest set ContestStatus = "+Contest.Start_Upload +
				" where ContestID = "+contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
		
	}
	
	public void StartScoreContest(int contestID)  //xiaojie
	{
		String sql="update Contest set ContestStatus = "+Contest.Start_Score +
				" where ContestID = "+contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);
		
	}
	 
	public void FinishContest(int contestID)  //xiaojie
	{
		String sql="update Contest set ContestStatus = "+Contest.Finished +
				" where ContestID = "+contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.Execute(sql);		
	}
	
	public void load()
	{
		if(getAccount()==null)
		{
			return;
		}
		
		DBOperator dbOperator = DBOperator.getInstance();		
		
		ResultSet rst = dbOperator.Load(this, "Account", getAccount());
		try {
			while (rst.next())
			{			
				this.setAge(rst.getInt("Age"));
				this.setRealName(rst.getString("RealName"));
				this.setPwd(rst.getString("Pwd"));
				this.setSex(rst.getString("Sex"));					
			}
			rst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(getAccount()==null)
		{
			return;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();	
		dbOperator.Save(this, ValueMap, "Account", getAccount());
		
	}
	
	@Override
	public void delete()
	{
		if(this.Account == null)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"Account",getAccount());		
	}

	public void delete(String account)
	{
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"Account",account);		
	}
	public void setAccount(String account) {
		Account = account;		
	}

	public String getAccount() {
		return Account;			
	}
	


}
