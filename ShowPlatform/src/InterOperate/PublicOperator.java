package InterOperate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import sun.rmi.runtime.NewThreadAction;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xpath.internal.operations.And;

import DBOperator.DBOperator;
import JavaBeans.Components.News;
import JavaBeans.Contest.Contest;
import JavaBeans.People.Member;
import JavaBeans.Works.DesignWorks;
import JavaBeans.Works.LiteratureWorks;
import JavaBeans.Works.PhotographyWorks;
import JavaBeans.Works.Works;

public final class PublicOperator {
		
	
	static public Collection getAllNews()
	{		
		String sql = "select newsID from News"
		+ " order by lastEditTime desc ";
		DBOperator dbOperator = DBOperator.getInstance();		
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				News news = new News(rst.getInt("NewsID"));
				news.load();
				c.add(news);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * 返回新闻
	 * 按更新时间排序
	 * @param newsType
	 * @return
	 */
	
	static public Collection getAllNews(String newsType)
	{
		if(!newsType.equalsIgnoreCase(News.IndexNews) || !newsType.equalsIgnoreCase(News.ContestNews))
			return null;		
		String sql = "select newsID from News where newsType = '"+newsType
		+ "' order by lastEditTime desc ";
		DBOperator dbOperator = DBOperator.getInstance();		
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				News news = new News(rst.getInt("NewsID"));
				news.load();
				c.add(news);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param newsType 新闻类型
	 * @param page  第几页
	 * @param pageSize 每页几行
	 * @return
	 */
	static public Collection getNews(String newsType,int page,int pageSize)
	{
		System.out.println("getNews : "+newsType);
		if(!newsType.equalsIgnoreCase(News.IndexNews) && !newsType.equalsIgnoreCase(News.ContestNews))
		{
			return null;
		}
		String sql = 
			" select top "+pageSize+" NewsID " +
			" from  News " +
			" where NewsID not in" +
				  " ( select top "+pageSize*(page-1)+" NewsID " +
				  	" from News " +
				  	" order by lastEditTime desc  " +
				  " )" +
			" order by lastEditTime desc ";
		System.out.println(sql);		
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				News news = new News(rst.getInt("NewsID"));
				news.load();
				c.add(news);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}	
	/**
	 * 	根据ContestStatus返回Top works
	 *  只能是Start_Score ,Finished两个状态
	 * @param ContestStatus
	 * @param topCount
	 * @return
	 */
	static public Collection getTopWorks(int ContestStatus,int topCount) //
	{
		if(ContestStatus != Contest.Finished && ContestStatus != Contest.Start_Score)
			return null;
		String sql = " select top "+topCount+" WorksID ,WorksType" +
				" from Works " +
				" where Works.isAllow = "+Works.Allow+" and Works.ContestID in( " +
					" select ContestID " +
					" from Contest " +
					" where ContestStatus >= "+ ContestStatus+ ")" +
				" order by avgScore desc ,ScoreTimes desc,CommentTimes desc ";		
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
	
	static public Collection getTopWorks(int ContestStatus,int page,int pageSize) //
	{
		if(ContestStatus != Contest.Finished || ContestStatus != Contest.Start_Score)
			return null;
		
		String sql =
				" select top "+pageSize+" WorkID from " +
				" Works" +
				" where Works.isAllow = 1 and Works.ContestID in" +
				" ( " +
					" select ContestID " +
					" from Contest " +
					" where ContestStatus = "+ ContestStatus+ 
				" )" +	
				" and WorksID not in" +
				" ("+ 
					" select top "+pageSize*(page-1)+" WorksID " +
					" from Works " +
					" where Works.isAllow = 1 and Works.ContestID in" +
					" ( " +
						" select ContestID " +
						" from Contest " +
						" where ContestStatus = "+ ContestStatus+ 
					" )" +									
					" order by avgScore desc ,ScoreTimes desc,CommentTimes desc " +
				" )" +
				" order by avgScore desc ,ScoreTimes desc,CommentTimes desc ";
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				String worksType = rst.getString("WorksType").trim();;
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
	
	/**
	 * 
	 * 作品类别为worksType的Top works
	 * @param worksType
	 * @param topCount
	 * @return
	 */
	static public Collection getTopWorks(String worksType,int topCount)
	{
		String sql = " select top "+topCount+" WorkID" +
					" from Works " +
					" where Works.isAllow = 1 and WorksType = '" +worksType+
					"' order by avgScore desc ,ScoreTimes desc,CommentTimes desc";
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
	/**
	 *  分页
	 */
	static public Collection getTopWorks(String worksType,int page,int pageSize)
	{
		String sql = " select top "+pageSize+" WorksID "+
					" from Works " +
					" where WorksID not in" +
					" ("+	
						" select top "+pageSize*(page-1)+" WorksID " +
						" from Works " +
						" where Works.isAllow = 1 and WorksType = '" +worksType+
						"' order by avgScore desc ,ScoreTimes desc,CommentTimes desc" +
					" )" +
					" and Works.isAllow = 1 and WorksType = '" +worksType+
					"' order by avgScore desc ,ScoreTimes desc,CommentTimes desc";
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
	
	/**
	 * 一个contest比赛中的Top Works
	 * @param contest
	 * @param topCount
	 * @return
	 */	
	static public Collection getContestWorks(int contestID,int topCount)
	{
		System.out.println("getContestWorks");
		String sql = "select top "+topCount+" WorksID " +
		" from Works " +
		" where Works.isAllow = "+Works.Allow+" and Works.contestID = "+contestID+
		" order by avgScore desc ,ScoreTimes desc,VisitTimes desc,CommentTimes desc";		
		Contest contest =new Contest(contestID);
		contest.load();		
		String worksType = contest.getWorksType().trim();
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		
		System.out.println(sql);
		
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
	
	static public Collection getContestWorks(int contestID,String worksType,int page,int pageSize,int sortType)
	{
		String sort = null;
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
		}else {
			return null;
		}
		String sql = 
			" select top "+pageSize+" WorksID" +
			" from Works "+	
			" where Works.isAllow = 1 and Works.contestID = "+contestID+
			" and WorksID not in("+
				" select top "+pageSize*(page-1)+" WorksID " +
				" from Works" +
				" where Works.isAllow = 1 and Works.contestID = "+contestID+
				sort+
				")" +
				sort;
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
	static public Collection getContestWorks(int contestID)
	{
		String sql = "select * from Works where ContestID = "+contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		Works works=null;
		try {
			while(rst.next())
			{
				String worksType = rst.getString("WorksType").trim();
				if(worksType.equalsIgnoreCase(Works.DesignWorksType))
				{
					works = new DesignWorks(rst.getInt("WorksID"));				
					works.load();
					c.add(works);
					
				}else if(worksType.equalsIgnoreCase(Works.LiteratureWorksType)) {
					
					works = new LiteratureWorks(rst.getInt("WorksID"));
					works.load();
					c.add(works);
					
				}else if(worksType.equalsIgnoreCase(Works.PhotographyWorksType))
				{
					works = new PhotographyWorks(rst.getInt("WorksID"));
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
	static public Collection getUnallowedWorks(int contestID)
	{
		String sql = "select * "+
		" from Works " +
		" where Works.isAllow = 1 and Works.contestID = "+contestID;
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		Works works = null;		
		try {
			while(rst.next())
			{
				String worksType =rst.getString("WorksType");
				if(worksType.equalsIgnoreCase(Works.DesignWorksType))
				{
					works = new DesignWorks(rst.getInt("WorksID"));				
					works.load();
					c.add(works);
					
				}else if(worksType.equalsIgnoreCase(Works.LiteratureWorksType)) {
					
					works = new LiteratureWorks(rst.getInt("WorksID"));
					works.load();
					c.add(works);
					
				}else if(worksType.equalsIgnoreCase(Works.PhotographyWorksType))
				{
					works = new PhotographyWorks(rst.getInt("WorksID"));
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
			
	/**
	 *  针对所有的member,返回Top member
	 * 	按其所有作品的平均评分，平均浏览次数由高到低排序
	 * @param topCount
	 * @return
	 */
	static public Collection getTopMember(int topCount)
	{
		String sql = "select top "+topCount+" m.MemberID " +
				" from Member m,Works w " +
				" where m.MemberID = w.MemberID " +
				" group by m.MemberID order by avg(w.avgScore) desc ,avg(w.VisitTimes) desc ";
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				Member member = new Member(rst.getString("MemberID").trim());
				member.load();
				c.add(member);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	static public Collection getRequestMember()
	{
		String sql = "select * from Member where CertifiedStatus = 2";
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{

				Member member = new Member(rst.getString("MemberID"));
				member.load();
				c.add(member);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	static public Collection getRequestHost()
	{
		String sql = "select * from Member where CertifiedStatus = 4";
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{

				Member member = new Member(rst.getString("MemberID"));
				member.load();
				c.add(member);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	static public Collection getTopMember(int page,int pageSize)
	{
		String sql =
			" select top "+ pageSize+" m.MemberID"+
			" from Member m,Works w,Member_Works mw " +
			" where m.MemberID = mw.MemberID and w.WorksID = mw.WorksID  and m.WorksID not in" +
				"(" +
					" select top "+pageSize*(page-1)+" w.WorksID " +
					" from Member m,Works w,Member_Works mw " +
					" where m.MemberID = mw.MemberID and w.WorksID = mw.WorksID  " +
					" group by m.MemberID " +
					"order by avg(w.avgScore) desc ,avg(w.VisitTimes) desc " +
				")" +
			" group by m.MemberID " +
			" order by avg(w.avgScore) desc ,avg(w.VisitTimes) desc ";
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{

				Member member = new Member(rst.getString("MemberID"));
				member.load();
				c.add(member);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 一个contest比赛中的Top Member
	 *  按其比赛作品的评分得分，评分次数和评论次数排序
	 * @param contest
	 * @param topCount
	 * @return
	 */	
	static public Collection getContestMember(int contestID,int topCount)
	{
		String sql = " select top "+topCount+"  m.MemberID " +
				" from Member m,Works w,Member_Works mw " +
				" where m.MemberID = mw.MemberID and w.WorksID = mw.WorksID and w.contestID = "+contestID+
				" order by w.avgScore desc ,w.ScoreTimes desc,w.CommentTimes desc";

		DBOperator dbOperator = DBOperator.getInstance();
		
		ResultSet rst =  dbOperator.Query(sql);		
		try {
			Collection c = new ArrayList();
			int i=0;
			while(rst.next())
			{
				System.out.println(i++);
				Member member = new Member(rst.getString("MemberID").trim());				
				member.load();
				c.add(member);
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	static public Collection getContestMember(int contestID,int page,int pageSize)
	{
		String sql =
			" select top "+pageSize+"  m.MemberID " +
			" from Member m,Works w,Member_Works mw " +
			" where m.MemberID = mw.MemberID and w.WorksID = mw.WorksID and w.contestID = "+contestID+
			" and m.MemberID not in " +
			"("+
				" select top "+pageSize*(page-1)+" m.MemberID " +
				" from Member m,Works w,Member_Works mw " +
				" where m.MemberID = mw.MemberID and w.WorksID = mw.WorksID and w.contestID = "+contestID+
				" order by w.avgScore desc ,w.ScoreTimes desc,w.CommentTimes desc"+
			")" +
			" order by w.avgScore desc ,w.ScoreTimes desc,w.CommentTimes desc";
				
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{

				Member member = new Member(rst.getString("MemberID"));
				member.load();
				c.add(member);				
			}
			rst.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	static public Collection getContest(String contestType)
	{
		String sql = "select ContestID " +
		" from Contest " +
		" where  ContestStatus >= "+Contest.Access+" and ContestType = '"+contestType+"'" +
		" order by BeignTime desc ";
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
	
	static public Collection getContest(String contestType,int page,int pageSize)
	{
		String sql = 
			" select  top "+ pageSize+" ContestID "+
			" from Contest " +
			" where  ContestStatus >= "+Contest.Access+" and ContestType = '"+contestType+"'"+
			" and ContestID not in " +
			" ("+
				" select top "+pageSize*(page-1)+" ContestID " +
				" from Contest " +
				" where  ContestStatus >= "+Contest.Access+" and ContestType = '"+contestType+"'" +
				" order by BeignTime desc " +
			" )" +
			" order by BeignTime desc ";		
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
	
	static public Collection getContest(int page,int pageSize)
	{
		String sql = 
			" select  top "+ pageSize+" ContestID "+
			" from Contest " +
			" where  ContestStatus >= "+Contest.Access+
			" and ContestID not in " +
			" ("+
				" select top "+pageSize*(page-1)+" ContestID " +
				" from Contest " +
				" where  ContestStatus >= "+Contest.Access+
				" order by BeignTime desc "+
			" )" +
			" order by BeignTime desc ";
		DBOperator dbOperator = DBOperator.getInstance();		
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{
				System.out.println("Contest has next ");
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
	
	static public Collection getRequestContest()
	{
		String sql = "select ContestID " +
		" from Contest " +
		" where  ContestStatus = "+Contest.requestAccess+
		" order by ContestType desc ";
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
	
	static public Collection getUnstartContest()
	{
		String sql = "select ContestID " +
		" from Contest " +
		" where  ContestStatus = "+Contest.Access+
		" order by ContestType desc ";
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
	static public Collection getAllContest()
	{
		String sql = "select* " +
		" from Contest " +
		" order by ContestType";
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
	
	static public Collection getUploadingContest()
	{
		String sql = "select ContestID " +
		" from Contest " +
		" where  ContestStatus = "+Contest.Start_Upload+
		" order by ContestType desc ";
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
	
	static public Collection getScoringContest()
	{
		String sql = "select ContestID " +
		" from Contest " +
		" where  ContestStatus = "+Contest.Start_Score+
		" order by ContestType desc ";
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
	
	static public Collection getFinishedContest()
	{
		String sql = "select ContestID " +
		" from Contest " +
		" where  ContestStatus = "+Contest.Finished+
		" order by ContestType desc ";
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
	
	
}
