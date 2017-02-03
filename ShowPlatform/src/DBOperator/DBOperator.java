package DBOperator;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.lang.reflect.Field;


import Connection.DBConnectionManager;
import InterOperate.LoginType;
import Log.Log;


public class DBOperator {
	
	static private DBOperator Instance;
	private Log log;
	static private int Clients ;
	static private DBConnectionManager connMgr;
	static private String poolName = "access";
	
	/**
	* ����Ψһʵ��.����ǵ�һ�ε��ô˷���,�򴴽�ʵ��
	*
	* @return getInstance Ψһʵ��
	*/
	static synchronized public DBOperator getInstance()
	{
		System.out.println("start DBOperator getInstance ");
		if(Instance == null){
			Instance = new DBOperator();
		}
		Clients ++;
		return Instance;
	}
	
	private DBOperator()
	{
		connMgr = DBConnectionManager.getInstance();
		log = new Log("DBOperatorlog");
	}
	
	/** 
	 * ִ��sql��䣬�޷���ֵ
	 * 
	 * @param sql
	 */
	public void Execute(String sql)
	{
		Connection conn = connMgr.getConnection(poolName);
		if(conn==null)
		{
			String msg ="���ܻ�ȡ���ݿ�����";
			System.out.println(msg);
			log.Write(msg);
			return ;
		}
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String msg = "ִ��sql������";
			e.printStackTrace();
			log.Write(e, msg);
		}
		connMgr.freeConnection(poolName, conn);
		
	}
	/**
	 *  ִ��sql��ѯ���
	 * @param sql
	 * @return ResultSet
	 */
	private ResultSet ExecuteQuery(String sql)
	{
		System.out.println("ExecuteQuery");
		Connection conn = connMgr.getConnection(poolName);			
		Statement stmt;
		ResultSet rst=null;		
		try {			
			stmt = conn.createStatement();		
			rst = stmt.executeQuery(sql);
		//	stmt.close();
		//	conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		connMgr.freeConnection(poolName, conn);		
		return rst;
	}
	
	/**
	 * 
	 * ����һ����¼
	 * 
	 * @param obj
	 * @param hashMap
	 */
	
	
	public void Insert(Object obj,HashMap hashMap)
	{
		
		String tableName = obj.getClass().getSimpleName();
		if(tableName.equalsIgnoreCase("DesignWorks") ||
				tableName.equalsIgnoreCase("LiteratureWorks") ||
				tableName.equalsIgnoreCase("PhotographyWorks") )
		{
			tableName = "Works";
		}
		String sql = "insert into "+tableName;
		System.out.println(sql);
		StringBuffer Parameters = new StringBuffer("("), Values = new StringBuffer("(");						
		Class classReflect = obj.getClass();
		while(classReflect!=Object.class)
		{			
			Field[] field = classReflect.getDeclaredFields();			
			for(int i=0;i<field.length;i++)
			{				
				if(hashMap.get(field[i].getName())!=null)
				{
					Parameters.append(field[i].getName()+",");					
					Values.append("'"+hashMap.get(field[i].getName()).toString()+"',");
				}	
			}			
			classReflect = classReflect.getSuperclass();
		}
		
		Parameters.replace(Parameters.length()-1, Parameters.length(), ")");		
		Values.replace(Values.length()-1, Values.length(), ")");	
		sql+=Parameters+" values"+Values+";";
		System.out.println(sql);
		
		this.Execute(sql);											
	}
	
	
	/**
	 * �ʺ�ֻ��һ����������λProperty��ֵΪvalue
	 * @param obj
	 * @param Property
	 * @param value
	 * @return
	 */
	
	public ResultSet Load(Object obj,String Property,String value)
	{		
		String tableName = obj.getClass().getSimpleName();
		if(tableName.equalsIgnoreCase("DesignWorks") ||
				tableName.equalsIgnoreCase("LiteratureWorks") ||
				tableName.equalsIgnoreCase("PhotographyWorks") )
		{
			tableName = "Works";
		}
		String sql = "SELECT * FROM "+tableName+" WHERE "+Property+" = '"+value+"'";
		System.out.println(sql);
		ResultSet rst = this.ExecuteQuery(sql);		
		return rst;
	}
	
	/**
	 * 
	 * select��ѯ���
	 * @param sql
	 * @return
	 */
	public ResultSet Query(String sql)
	{
		return this.ExecuteQuery(sql);
	}
	
	/**
	 * 
	 * @param obj javaBean�ж�Ӧ�Ķ���
	 * @param hashMap ��Ҫ�޸ĵ��ֶ�key�Լ�value
	 * @param Property ����
	 * @param value	ֵ
	 */
	
	public void Save(Object obj,HashMap hashMap,String Property,String value)
	{
		System.out.println(" begin Save  ");
		String tableName = obj.getClass().getSimpleName();
		if(tableName.equalsIgnoreCase("DesignWorks") ||
				tableName.equalsIgnoreCase("LiteratureWorks") ||
				tableName.equalsIgnoreCase("PhotographyWorks") )
		{
			tableName = "Works";
		}
		StringBuffer sql = new StringBuffer(" update "+tableName+" set ");		
		Class classReflect = obj.getClass();				
		while(classReflect!= Object.class)
		{			
			Field[] field = classReflect.getDeclaredFields();			
			for(int i=0;i<field.length;i++)
			{
				if(hashMap.get(field[i].getName())!=null)
				{
					if(field[i].getName()== Property)
					{
					//	System.out.println("�����޸�����ֵ��"+field[i].getName() );
					//	log.Write("�����޸�����ֵ��");
					//	return;
						continue;
					}
					if(field[i].getType()==String.class || field[i].getType() == Date.class)
					{
						sql.append(field[i].getName()+" = '"+hashMap.get(field[i].getName()).toString().trim() +"',");
					}		
					else
					{
						sql.append(field[i].getName()+" = "+hashMap.get(field[i].getName()).toString().trim() +",");
					}
				}						
			}
			classReflect = classReflect.getSuperclass();
		}		
		sql.replace(sql.length()-1, sql.length(), " ");		
		sql.append(" where "+Property+" = '"+value+"'");
		System.out.println(sql);
		this.Execute(sql.toString());
		
		System.out.println(" Save end ");
	}
	
	public void delete(Object obj,String Property,String value)
	{	
		String tableName = obj.getClass().getSimpleName();
		if(tableName.equalsIgnoreCase("DesignWorks") ||
				tableName.equalsIgnoreCase("LiteratureWorks") ||
				tableName.equalsIgnoreCase("PhotographyWorks") )
		{
			tableName = "Works";
		}
		String sql = "delete from "+tableName+" where "+Property+" = '"+value+"'";
		this.Execute(sql);			
	}
	
	public boolean isExist(Object obj,String Property,String value)
	{
		String tableName = obj.getClass().getSimpleName();
		if(tableName.equalsIgnoreCase("DesignWorks") ||
				tableName.equalsIgnoreCase("LiteratureWorks") ||
				tableName.equalsIgnoreCase("PhotographyWorks") )
		{
			tableName = "Works";
		}
		String sql = "select * from "+tableName+" where "+Property+" = '"+value+"'";
		ResultSet rst = this.ExecuteQuery(sql);
		try {
			if(rst.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean checkLogin(String usertype,String username,String pwd)
	{
		String sql;
		if(usertype == LoginType.ADMINTYPE)
		{
			sql ="select * from Administor where Account = '"+username+"' and pwd = '"+pwd+"'";
		}else if(usertype == LoginType.MEMBERTYPE)
		{
			sql ="select * from Member where MemberID  = '"+username+"' and pwd = '"+pwd+"'";
		}else if(usertype == LoginType.CONTESTTYPE)
		{
			sql ="select * from Contest where ContestID   = '"+username+"' and AccessPwd = '"+pwd+"'";
		}
		else {
			return false;
		}	
		System.out.println(sql);
		ResultSet rst = this.ExecuteQuery(sql);
		if(rst == null)
			return false;
		try {
			if(rst.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
