package JavaBeans.People;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import DBOperator.DBOperator;
import Factory.CommentFactory;
import Factory.ContestFactory;
import Factory.DesignWorksFactory;

import Factory.IWorksFactory;
import Factory.LiteratureWorksFactory;

import Factory.PhotographyWorksFactory;
import InterOperate.LoginType;
import InterOperate.SortType;
import JavaBeans.Components.Comment;
import JavaBeans.Components.News;
import JavaBeans.Components.Score;
import JavaBeans.Contest.Contest;
import JavaBeans.Works.DesignWorks;
import JavaBeans.Works.LiteratureWorks;
import JavaBeans.Works.PhotographyWorks;
import JavaBeans.Works.Works;

public class Member extends User {
	
	static final public int unRegister = 0;      // 未注册
	static final public int Primary = 1;         // 可评分，可评论
	static final public int requestMedium = 2;   // 申请获得上传作品的权限
	static final public int Medium = 3;          // 可上传作品
	static final public int requestAdvanced = 4;  // 申请获得主办比赛的权限
	static final public int Advanced = 5;        // 可申请主办比赛
	
	private String MemberID;	
	private String Constellation;
	private String LiveProvince;
	private String LiveCity;
	private String GraduateSchool;
	private String Profession;	
	private String RegisterEmail;
	private String MemberPath;
	
	private Date Birthday;
	
	private String Introduction;
	private String msg;
	
	private int CertifiedStatus;
	
	private int Credit;
	
	private Date RegisterTime;
	private Date LastVisitTime;
	
	private IWorksFactory Works_Factory;
	private final CommentFactory Comment_Factory = new CommentFactory();
	private final ContestFactory Contest_Factory = new ContestFactory();
	
	public Member()
	{	
		
	}
	
	public Member(String MemberID)
	{
		this.MemberID = MemberID;
	}
	
	public void visit()
	{
		this.Credit++;
	}
	
	public boolean canHostContest()
	{
		if(getCertifiedStatus()==Member.Advanced)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPostWorks()
	{
		if(getCertifiedStatus()>=Member.Medium)
		{
			return true;
		}
		return false;
	}
	
	public boolean isRegistered()
	{
		if(getCertifiedStatus()>=Member.Primary)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isExist() {
		return (DBOperator.getInstance()).isExist(this, "MemberID", getMemberID());
	}
	
	public Contest loginContest(String username,String pwd)
	{
		DBOperator dbOperator = DBOperator.getInstance();
		if(!dbOperator.checkLogin( LoginType.CONTESTTYPE, username, pwd))
			return null;
		else 
		{	 
			return Contest_Factory.createContest();
		}
	}
	
	public Contest createContest()
	{
		return Contest_Factory.createContest();
	}
	
	
	public boolean PostContest(Contest contest)
	{
		if(this.getCertifiedStatus()==Member.Advanced)
		{
			contest.insert();
			return true;
		}
		return false;
	}
	
	public boolean EditContestPwd(int ContestID,String pwd)
	{
		Contest contest = new Contest(ContestID);
		if(contest.isExist())
		{
			
		}
		return false;
	}
	
	public boolean PostWorks(Works works)
	{
		if(this.getCertifiedStatus()>=Member.Medium)
		{
			works.insert();
			return true;
		}
		return false;
	}
	
	public DesignWorks createDesignWorks()
	{
		Works_Factory = new DesignWorksFactory();
		return (DesignWorks)Works_Factory.createWorks();
	}
	
	public LiteratureWorks createLiteratureWorks()
	{
		Works_Factory = new LiteratureWorksFactory();
		return (LiteratureWorks)Works_Factory.createWorks();
	}
	
	public PhotographyWorks createPhotographyWorks()
	{
		Works_Factory = new PhotographyWorksFactory();
		return (PhotographyWorks)Works_Factory.createWorks();
	}
	
	
	public void doComment(int worksID,String commentContent,String replyMemberID)
	{		
		System.out.println("doComment");
		Comment comment = Comment_Factory.createComment();
		comment.setCommentContent(commentContent);
		comment.setWorksID(worksID);
		comment.setMemberID(this.getMemberID());
		comment.setReplyMemberID(replyMemberID);
		comment.setCommentTime(new Date());
		comment.insert();
	}
	public boolean doScore(Works works,int value)
	{		
		System.out.println("doScore");
		if(works == null)
		{
			return false;
		}
		Score score= new Score();		
		score.setMemberID(this.getMemberID());
		score.setValue(value);
		score.setWorksID(works.getWorksID());
		score.insert();		
		float avg = (works.getScoreTimes()*works.getAvgScore()+value)/(works.getScoreTimes()+1);
		works.Scored();
		works.setAvgScore(avg);
		works.Save();	
		return true;
	}
	
	public boolean haveMsg()
	{
		if(msg.equals(""))
			return false;
		return true;
	}
	
	public boolean ApplyAccessUpLoad()
	{
		if(this.getCertifiedStatus() == Primary)
		{
			this.setCertifiedStatus(requestMedium);
			this.Save();
			return true;
		}	
		return false;
	}
	
	public boolean ApplyAccessHostContest()
	{
		if(this.getCertifiedStatus() == Medium)
		{
			this.setCertifiedStatus(requestAdvanced);
			this.Save();
			return true;
		}
		return false;
	}
	
	
	public Collection getMyHostContest()
	{
		if(this.getCertifiedStatus() == Advanced)
		{
			String sql = "select * from Contest where Contest.OrganizerID = '"+this.getMemberID()+"'" +
					" order by ContestStatus desc";
			DBOperator dbOperator = DBOperator.getInstance();
			System.out.println(sql);
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
		return null;
	}
			
	
	//
	//
	public Collection getMyContestWorks(int contestID)
	{
		if(this.getCertifiedStatus()>=Medium)
		{
			String sql = "select WorksID,WorksType from Works" +
					" where Works.MemberID = '"+this.getMemberID()+"'" +
							"and contestID = "+contestID;			
			DBOperator dbOperator = DBOperator.getInstance();
			ResultSet rst =  dbOperator.Query(sql);
			Collection c = new ArrayList();
			try {
				while(rst.next())
				{
					String worksType = rst.getString("WorksType").trim();
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
		return null;
	}

	///
	public Collection getMyWorksBySort(int sortType)//xiaojie
	{
		if(this.getCertifiedStatus()>=Medium)
		{
			String sort = "";
			if(sortType == SortType.OrderByAvgScore)
			{
				sort = " order by avgScore desc ,ScoreTimes desc,VisitTimes desc ,CommentTimes desc ";
				
			}else if(sortType == SortType.OrderByScoreTimes)
			{
				sort = " order by ScoreTimes desc,avgScore desc ,VisitTimes desc ,CommentTimes desc ";
				
			}else if(sortType == SortType.OrderByVisitTimes)
			{
				sort = " order by VisitTimes desc,avgScore desc ,ScoreTimes desc ,CommentTimes desc ";
				
			}else if(sortType == SortType.OrderByCommentTimes)
			{
				sort = " order by CommentTimes desc,avgScore desc ,VisitTimes desc ,ScoreTimes desc ";
			}
			String sql = "select WorksID ,WorksType from Works" +
					" where Works.MemberID = '"+this.getMemberID()+"'"+sort;			
			DBOperator dbOperator = DBOperator.getInstance();
			ResultSet rst =  dbOperator.Query(sql);
			Collection c = new ArrayList();
			try {
				while(rst.next())
				{
					String worksType = rst.getString("WorksType").trim();
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
		return null;
	}
	
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		if(getMemberID()==null)
		{
			return;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst = dbOperator.Load(this, "MemberID", getMemberID());
		if(rst==null)
		{
			System.out.println("load fail");
			return;
		}
		try {
			while (rst.next())
			{							
				this.setAge(rst.getInt("Age"));											
				this.setConstellation(rst.getString("Constellation"));				
				this.setGraduateSchool(rst.getString("GraduateSchool"));
				this.setIntroduction(rst.getString("Introduction"));			
				this.setLiveCity(rst.getString("LiveCity"));
				this.setLiveProvince(rst.getString("LiveProvince"));
				this.setMemberPath(rst.getString("MemberPath"));
				this.setProfession(rst.getString("Profession"));
				this.setPwd(rst.getString("Pwd"));
				this.setRealName(rst.getString("Realname"));
				this.setRegisterEmail(rst.getString("RegisterEmail"));				
				this.setSex(rst.getString("Sex"));							
				this.setCertifiedStatus(rst.getInt("CertifiedStatus"));
				this.setCredit(rst.getInt("Credit"));				
				this.setBirthday(rst.getDate("Birthday"));	
				this.setLastVisitTime(rst.getDate("LastVisitTime"));
				this.setRegisterTime(rst.getDate("RegisterTime"));				
				this.setMsg(rst.getString("msg"));	
				
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
		if(getMemberID()==null)			
		{
			return;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		this.setLastVisitTime(new Date(new java.util.Date().getTime()));
		this.RefreshValueMap();
		dbOperator.Save(this, ValueMap, "MemberID", getMemberID());
		
	}
	
	@Override
	public void delete()
	{
		if(getMemberID()== null)
		{
			return ;
		}
		DBOperator dbOperator = DBOperator.getInstance();
		dbOperator.delete(this,"MemberID",String.valueOf(getMemberID()));		
	}
	
	
	@SuppressWarnings("unchecked")
	protected void RefreshValueMap()
	{		
		super.RefreshValueMap();
		ValueMap.put("MemberID", getMemberID());
		ValueMap.put("Constellation", getConstellation());
		ValueMap.put("GraduateSchool", getGraduateSchool());
		ValueMap.put("Introduction", getIntroduction());
		ValueMap.put("LiveCity", getLiveCity());
		ValueMap.put("LiveProvince", getLiveProvince());			
		ValueMap.put("MemberPath", getMemberPath());
		ValueMap.put("Profession", getProfession());			
		ValueMap.put("RegisterEmail", getRegisterEmail());			
		ValueMap.put("Birthday", getBirthday());
		DateFormat dateFormat = DateFormat.getDateTimeInstance(); 
		if(getBirthday()!=null)
		{
			String date_str = dateFormat.format(getBirthday());
			ValueMap.put("Birthday", date_str);
		}
		if(getRegisterTime()!=null)
		{			
			String date_str = dateFormat.format(getRegisterTime());
			ValueMap.put("RegisterTime", date_str);
		}
		if(getLastVisitTime()!=null)
		{
			String date_str = dateFormat.format(getLastVisitTime());
			ValueMap.put("LastVisitTime", date_str);
		}
		ValueMap.put("CertifiedStatus",getCertifiedStatus());
		ValueMap.put("Credit", getCredit());	
	}
	
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	
	public String getMemberID() {
		return MemberID;
	}
	
	public void setConstellation(String constellation) {
		Constellation = constellation;
	}
	public String getConstellation() {
		return Constellation;
	}
	public void setLiveCity(String liveCity) {
		LiveCity = liveCity;
	}
	public String getLiveCity() {
		return LiveCity;
	}
	public void setLiveProvince(String liveProvince) {
		LiveProvince = liveProvince;
	}
	public String getLiveProvince() {
		return LiveProvince;
	}
	public void setGraduateSchool(String graduateSchool) {
		GraduateSchool = graduateSchool;
	}
	public String getGraduateSchool() {
		return GraduateSchool;
	}
	public void setProfession(String profession) {
		Profession = profession;
	}
	public String getProfession() {
		return Profession;
	}
	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}
	public String getIntroduction() {
		return Introduction;
	}

	public void setCertifiedStatus(int certifiedStatus) {
		CertifiedStatus = certifiedStatus;
	}
	public int getCertifiedStatus() {
		return CertifiedStatus;
	}
	public void setCredit(int credit) {
		Credit = credit;
	}
	public int getCredit() {
		return Credit;
	}
	public void setRegisterTime(Date registerTime) {
		RegisterTime = registerTime;
	}
	
	public Date getRegisterTime() {
		return RegisterTime;
	}
	
	public void setLastVisitTime(Date lastVisitTime) {
		LastVisitTime = lastVisitTime;
	}
	public Date getLastVisitTime() {
		return LastVisitTime;
	}
	
	public void setRegisterEmail(String registerEmail) {
		RegisterEmail = registerEmail;
	}
	public String getRegisterEmail() {
		return RegisterEmail;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMemberPath(String memberPath) {
		MemberPath = memberPath;
	}

	public String getMemberPath() {
		return MemberPath;
	}
	
	
	public void ViewProperty()
	{
		System.out.println(getMemberID());
		System.out.println(getAge());
		System.out.println(getPwd());
		System.out.println(getRealName());
		System.out.println(getSex());		
		System.out.println(getBirthday());
		System.out.println(getCertifiedStatus());
		System.out.println(getConstellation());
		System.out.println(getCredit());
		System.out.println(getGraduateSchool());
		System.out.println(getIntroduction());
		System.out.println(getLiveCity());
		System.out.println(getLiveProvince());
		System.out.println(getMemberPath());
		System.out.println(getProfession());
		System.out.println(getRegisterEmail());		
		System.out.println(getLastVisitTime());
		System.out.println(getRegisterTime());	
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public Date getBirthday() {
		return Birthday;
	}


	
}
