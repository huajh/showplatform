package Path;

public class PathInfo 
{
	
	
	public static final String ContestRootPath = "/Root/Contest";
	public static final String NewsRootPath = "/Root/News";
	public static final String UsersRootPath = "/Root/Users";	
	
	public static final String MemberServlet = "servlet/Member";//Member Servlet
	public static final String AdminServlet = "servlet/Admin";//Admin Servlet
	
	/***
	 *  HomePage
	 * 
	 */
	
	public static final String HOMEPAGE = "/HomePage/Index.jsp";//首页
	
	public static final String LOGIN = "/HomePage/Login.jsp";//普通用户登录
	public static final String REGISTER = "/HomePage/Register.jsp";//注册
	
	public static final String IndexNews = "/HomePage/News.jsp";//新闻
	public static final String IndexNewsList = "/HomePage/NewsList.jsp";//新闻列表
	
	/**
	 *  个人中心
	 *  
	 */
	public static final String InfoCenter ="/HomePage/InfoCenter.jsp";//普通用户个人主页
	public static final String EditInfo = "/HomePage/EditInfo.jsp";//修改个人信息页面
	public static final String EditPwd = "/HomePage/EditPwd.jsp";//修改密码	
	public static final String EditContestPwd = "/HomePage/EditContestPwd.jsp";//修改登陆比赛密码
	
	public static final String ApplyUpload = "/HomePage/ApplyUpload.jsp";//申请参加比赛的权限
	public static final String ApplyHostContest = "/HomePage/ApplyHostContest.jsp";//申请主办比赛的权限
	
	public static final String MyWorksList = "/HomePage/MyWorksList.jsp"; //我的作品
	public static final String MyHostContest = "/HomePage/MyHostContest.jsp";//我主办的比赛	
	
	/**
	 *  申请主办比赛
	 * 
	 */
	public static final String PostContest= "/HomePage/PostContest.jsp";//参赛者上传主办的比赛信息
	public static final String EditMyContestInfo= "/HomePage/EditMycontestInfo.jsp";//参赛者上传主办的比赛信息
	
	/**
	 *  比赛模块  
	 * 
	 */
	public static final String ContestList = "/HomePage/ContestList.jsp";//各种比赛入口
	public static final String ContestMain = "/HomePage/ContestMain.jsp";//比赛主页
	public static final String ContestNewsDetail = "/HomePage/ContestNewsDetail.jsp";//未开始和正在进行的比赛（允许上传作品）通知
	public static final String PostWorks= "/HomePage/PostWorks.jsp";//参赛者上传作品页面
		
	/***
	 *  作品展示，三种类别
	 * 
	 */
	
	public static final String DesignWorksList = "/HomePage/DesignWorksList.jsp";//设计类作品展示页面列表
	public static final String DesignworksDetail = "/HomePage/DesignWorksDetail.jsp";//单个设计类作品展示页面
	
	public static final String LitetatureworksList = "/HomePage/LitetatureworksList.jsp";//作品展示页面列表
	public static final String litetatureworksDetail = "/HomePage/LitetatureWorksDetail.jsp";//单个文字作品展示页面
	
	public static final String PhotographyWorksList = "/HomePage/PhotographyWorksList.jsp";//图片作品展示页面列表
	public static final String PhotographyWorksDetail = "/HomePage/PhotographyWorksDetail.jsp";//单个图片作品展示页面
	
	/***
	 * 
	 *  参赛选手展示
	 * 
	 */	
	
	public static final String DesignersList = "/HomePage/DesignersList.jsp";//参赛选手(设计师)列表
	public static final String DesignerDetail = "/HomePage/DesignerDetail.jsp";//单个选手信息		
	
	public static final String successPage = "/HomePage/success.jsp";//success
	public static final String failedPage = "/HomePage/failed.jsp";//fail
	/**
	 * 
	 *   BackGround
	 *   
	 */
	
	public static final String AdminLogin = "/BackGround/Login.jsp";//管理员登录
	public static final String AdminIndex = "/BackGround/Index.jsp";//后台主页
	
	public static final String AdminEditInfo = "/BackGround/EditInfo.jsp";//管理员修改个人信息页面
	public static final String AdminEditPwd = "/BackGround/EditPwd.jsp";//管理员修改登陆密码
	public static final String AddAdmin = "/BackGround/AddAmdin.jsp";//添加管理员
		
	
	//信息审核
	public static final String ExamineMemberList = "/BackGround/ExamineMemberList.jsp";//管理员审核个人信息页面
	public static final String ExamineHost = "/BackGround/ExamineHost.jsp";//管理员审核主办方页面
	public static final String MemberDetail = "/BackGround/MemberDetail.jsp";//个人信息页面详情		
	public static final String ExamineWorksList = "/BackGround/ExamineWorksList.jsp";//管理员审核作品页面
	public static final String ExamineWorksDetail = "/BackGround/ExamineWorksDetail.jsp";//管理员审核作品页面
	
	//新闻管理
	public static final String NewsList = "/BackGround/NewsList.jsp";//管理员所有新闻页面
	public static final String AddNews = "/BackGround/AddNews.jsp";//管理员发布新闻页面
	public static final String EditNews = "/BackGround/EditNews.jsp";//管理员修改新闻信息	
	
	//作品管理
	public static final String WorksManage = "/BackGround/WorksManage.jsp";//管理员和主办方下载作品页面
	public static final String WorksDetail = "/BackGround/WorksDetail.jsp";//作品详情
	//应该支持批量下载
	
	//比赛管理
	public static final String ContestManage = "/BackGround/ContestManage.jsp";//比赛管理页面
	public static final String AddContest = "/BackGround/AddContest.jsp";//主办方上传比赛信息页面
	public static final String ExamineContestList = "/BackGround/ExamineContestList.jsp";//管理员审核主办比赛页面
	public static final String UnstartContest = "/BackGround/UnstartContest.jsp";//已通过审核未开始的比赛
	public static final String UploadingContest = "/BackGround/UploadingContest.jsp";//处于上传作品阶段的比赛
	public static final String ScoringContest = "/BackGround/ScoringContest.jsp";//处于评分阶段的比赛
	public static final String FinishedContest = "/BackGround/FinishedContest.jsp";//已经结束的比赛
	public static final String ExamineContestDetail = "/BackGround/ExamineContestDetail.jsp";//主办比赛详情
	
	
	
}
