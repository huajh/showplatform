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
	
	public static final String HOMEPAGE = "/HomePage/Index.jsp";//��ҳ
	
	public static final String LOGIN = "/HomePage/Login.jsp";//��ͨ�û���¼
	public static final String REGISTER = "/HomePage/Register.jsp";//ע��
	
	public static final String IndexNews = "/HomePage/News.jsp";//����
	public static final String IndexNewsList = "/HomePage/NewsList.jsp";//�����б�
	
	/**
	 *  ��������
	 *  
	 */
	public static final String InfoCenter ="/HomePage/InfoCenter.jsp";//��ͨ�û�������ҳ
	public static final String EditInfo = "/HomePage/EditInfo.jsp";//�޸ĸ�����Ϣҳ��
	public static final String EditPwd = "/HomePage/EditPwd.jsp";//�޸�����	
	public static final String EditContestPwd = "/HomePage/EditContestPwd.jsp";//�޸ĵ�½��������
	
	public static final String ApplyUpload = "/HomePage/ApplyUpload.jsp";//����μӱ�����Ȩ��
	public static final String ApplyHostContest = "/HomePage/ApplyHostContest.jsp";//�������������Ȩ��
	
	public static final String MyWorksList = "/HomePage/MyWorksList.jsp"; //�ҵ���Ʒ
	public static final String MyHostContest = "/HomePage/MyHostContest.jsp";//������ı���	
	
	/**
	 *  �����������
	 * 
	 */
	public static final String PostContest= "/HomePage/PostContest.jsp";//�������ϴ�����ı�����Ϣ
	public static final String EditMyContestInfo= "/HomePage/EditMycontestInfo.jsp";//�������ϴ�����ı�����Ϣ
	
	/**
	 *  ����ģ��  
	 * 
	 */
	public static final String ContestList = "/HomePage/ContestList.jsp";//���ֱ������
	public static final String ContestMain = "/HomePage/ContestMain.jsp";//������ҳ
	public static final String ContestNewsDetail = "/HomePage/ContestNewsDetail.jsp";//δ��ʼ�����ڽ��еı����������ϴ���Ʒ��֪ͨ
	public static final String PostWorks= "/HomePage/PostWorks.jsp";//�������ϴ���Ʒҳ��
		
	/***
	 *  ��Ʒչʾ���������
	 * 
	 */
	
	public static final String DesignWorksList = "/HomePage/DesignWorksList.jsp";//�������Ʒչʾҳ���б�
	public static final String DesignworksDetail = "/HomePage/DesignWorksDetail.jsp";//�����������Ʒչʾҳ��
	
	public static final String LitetatureworksList = "/HomePage/LitetatureworksList.jsp";//��Ʒչʾҳ���б�
	public static final String litetatureworksDetail = "/HomePage/LitetatureWorksDetail.jsp";//����������Ʒչʾҳ��
	
	public static final String PhotographyWorksList = "/HomePage/PhotographyWorksList.jsp";//ͼƬ��Ʒչʾҳ���б�
	public static final String PhotographyWorksDetail = "/HomePage/PhotographyWorksDetail.jsp";//����ͼƬ��Ʒչʾҳ��
	
	/***
	 * 
	 *  ����ѡ��չʾ
	 * 
	 */	
	
	public static final String DesignersList = "/HomePage/DesignersList.jsp";//����ѡ��(���ʦ)�б�
	public static final String DesignerDetail = "/HomePage/DesignerDetail.jsp";//����ѡ����Ϣ		
	
	public static final String successPage = "/HomePage/success.jsp";//success
	public static final String failedPage = "/HomePage/failed.jsp";//fail
	/**
	 * 
	 *   BackGround
	 *   
	 */
	
	public static final String AdminLogin = "/BackGround/Login.jsp";//����Ա��¼
	public static final String AdminIndex = "/BackGround/Index.jsp";//��̨��ҳ
	
	public static final String AdminEditInfo = "/BackGround/EditInfo.jsp";//����Ա�޸ĸ�����Ϣҳ��
	public static final String AdminEditPwd = "/BackGround/EditPwd.jsp";//����Ա�޸ĵ�½����
	public static final String AddAdmin = "/BackGround/AddAmdin.jsp";//��ӹ���Ա
		
	
	//��Ϣ���
	public static final String ExamineMemberList = "/BackGround/ExamineMemberList.jsp";//����Ա��˸�����Ϣҳ��
	public static final String ExamineHost = "/BackGround/ExamineHost.jsp";//����Ա������췽ҳ��
	public static final String MemberDetail = "/BackGround/MemberDetail.jsp";//������Ϣҳ������		
	public static final String ExamineWorksList = "/BackGround/ExamineWorksList.jsp";//����Ա�����Ʒҳ��
	public static final String ExamineWorksDetail = "/BackGround/ExamineWorksDetail.jsp";//����Ա�����Ʒҳ��
	
	//���Ź���
	public static final String NewsList = "/BackGround/NewsList.jsp";//����Ա��������ҳ��
	public static final String AddNews = "/BackGround/AddNews.jsp";//����Ա��������ҳ��
	public static final String EditNews = "/BackGround/EditNews.jsp";//����Ա�޸�������Ϣ	
	
	//��Ʒ����
	public static final String WorksManage = "/BackGround/WorksManage.jsp";//����Ա�����췽������Ʒҳ��
	public static final String WorksDetail = "/BackGround/WorksDetail.jsp";//��Ʒ����
	//Ӧ��֧����������
	
	//��������
	public static final String ContestManage = "/BackGround/ContestManage.jsp";//��������ҳ��
	public static final String AddContest = "/BackGround/AddContest.jsp";//���췽�ϴ�������Ϣҳ��
	public static final String ExamineContestList = "/BackGround/ExamineContestList.jsp";//����Ա����������ҳ��
	public static final String UnstartContest = "/BackGround/UnstartContest.jsp";//��ͨ�����δ��ʼ�ı���
	public static final String UploadingContest = "/BackGround/UploadingContest.jsp";//�����ϴ���Ʒ�׶εı���
	public static final String ScoringContest = "/BackGround/ScoringContest.jsp";//�������ֽ׶εı���
	public static final String FinishedContest = "/BackGround/FinishedContest.jsp";//�Ѿ������ı���
	public static final String ExamineContestDetail = "/BackGround/ExamineContestDetail.jsp";//�����������
	
	
	
}
