package Connection;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import Log.Log;

public class DBConnectionManager {
	
	static private DBConnectionManager Instance;
	
	static private int Clients;	
	private Vector Drivers = new Vector();	
	private Log log;	
	private Hashtable pools = new Hashtable();
	
	/**
	* ����Ψһʵ��.����ǵ�һ�ε��ô˷���,�򴴽�ʵ��
	*
	* @return DBConnectionManager Ψһʵ��
	*/
	
	static synchronized public DBConnectionManager getInstance()
	{
		System.out.println("start getInstance ");		
		if(Instance == null){
			Instance = new DBConnectionManager();
			System.out.println("new Instance successful");
		}
		Clients ++;
		return Instance;
	}
	/**
	* ���캯��˽���Է�ֹ�������󴴽�����ʵ��
	*/
	private DBConnectionManager()
	{
		System.out.println(" create DBConnectionManager");
		Init();
	}
	
	/**
	* �����Ӷ��󷵻ظ�������ָ�������ӳ�
	*
	* @param name �������ļ��ж�������ӳ�����
	* @param conn ���Ӷ���
	*/
	
	public void freeConnection(String name,Connection conn)
	{ 
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if(pool !=null){
			pool.freeConnection(conn);
		}
	}
	
	/**
	* ���һ�����õ�(���е�)����.���û�п�������,������������С�����������
	* ����,�򴴽�������������
	*
	* @param name �������ļ��ж�������ӳ�����
	* @return Connection �������ӻ�null
	*/
	
	public Connection getConnection(String name)
	{
		System.out.println("getConnection "+ name);
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if(pool != null)
		{
			return pool.getConnection();
		}
		return null;		
	}
	
	/**
	* ���һ����������.��û�п�������,������������С���������������,
	* �򴴽�������������.����,��ָ����ʱ���ڵȴ������߳��ͷ�����.
	*
	* @param name ���ӳ�����
	* @param time �Ժ���Ƶĵȴ�ʱ��
	* @return Connection �������ӻ�null
	*/

	public Connection getConnection(String name,long time){
		DBConnectionPool pool = (DBConnectionPool)pools.get(name);
		if(pool!=null){
			return pool.getConnection(time);
		}
		return null;
	}
	
	public int getClient()
	{
		return Clients;
	}
	
	/**
	* �ر���������,�������������ע��
	*/
	public synchronized void Release()
	{
		// �ȴ�ֱ�����һ���ͻ��������
		if(--Clients!=0)
			return ;
		
		Enumeration allPools = pools.elements();
		while(allPools.hasMoreElements())
		{
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.Release();
		}
		
		Enumeration allDrivers = Drivers.elements();
		while(allDrivers.hasMoreElements())
		{
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				log.Write("����JDBC�������� " + driver.getClass().getName()+"��ע��");
			} catch (SQLException e) {
				// TODO: handle exception
				log.Write(e, "�޷���������JDBC���������ע��: " + driver.getClass().getName());
			}
		}
		
	}
	
	private void Init() {
		// TODO Auto-generated method stub
		Properties dbProps = new Properties();
		InputStream iStream = getClass().getResourceAsStream("/db.properties");
		try {
			dbProps.load(iStream);
			System.out.println("��ȡ���ݳɹ�!");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("���ܶ�ȡ�����ļ�. " +
			"��ȷ��db.properties��CLASSPATHָ����·����");
			return; 
		}
		log = new Log("connlog");
		loadDrivers(dbProps);
		createPools(dbProps);
	}
	
	/**
	* װ�غ�ע������JDBC��������
	*
	* @param props ����
	*/
	
	private void loadDrivers(Properties Props) {
		
		System.out.println("begin LoadDrivers"); 
		
		// TODO Auto-generated method stub
		String driverClasses = Props.getProperty("drivers");

		StringTokenizer st = new StringTokenizer(driverClasses);
		
		while(st.hasMoreElements())
		{
			String driverClassName = st.nextToken().trim();

			try {
				
				Driver driver = (Driver) Class.forName(driverClassName).newInstance();
				
				System.out.println(" begin registerDriver");
				
				DriverManager.registerDriver(driver);
				
				Drivers.addElement(driver);
				
				System.out.println("�ɹ�ע��JDBC��������" + driverClassName);
				
				log.Write("�ɹ�ע��JDBC��������" + driverClassName);
				
			} catch (Exception e) { 
				// TODO: handle exception
				
				System.out.println("�޷�ע��JDBC��������: " + driverClassName);
				
				log.Write("�޷�ע��JDBC��������: " + driverClassName + ", ����: " + e);
			}
		}		
	}
	
	
	/**
	* ����ָ�����Դ������ӳ�ʵ��.
	*
	* @param props ���ӳ�����
	*/
	
	private void createPools(Properties props) {
		
		System.out.println("begin createPools");
		
		Enumeration propNames = props.propertyNames();
		while(propNames.hasMoreElements())
		{
			String name = (String)propNames.nextElement();
			if(name.endsWith(".url"))
			{
				String poolName = name.substring(0,name.lastIndexOf("."));
				String url = props.getProperty(poolName+".url");
				if(url ==null)
				{
					log.Write("û��Ϊ���ӳ�" + poolName + "ָ��URL");
					continue;  
				}
				String user = props.getProperty(poolName+".user");
				String password = props.getProperty(poolName+".password");
				String maxconn = props.getProperty(poolName+".maxconn","0");
				
				int max ;
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					// TODO: handle exception
					log.Write("������������������: " + maxconn + " .���ӳ�: " + poolName);
					max = 0;
				}
				
				DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
				log.Write("�ɹ��������ӳ�" + poolName);
			}			
		}
	}
	
	
	/**
	* ���ڲ��ඨ����һ�����ӳ�.���ܹ�����Ҫ�󴴽�������,ֱ��Ԥ������
	* ��������Ϊֹ.�ڷ������Ӹ��ͻ�����֮ǰ,���ܹ���֤���ӵ���Ч��.
	*/

	class DBConnectionPool
	{
		private int checkedOut;
		private Vector freeConnections = new Vector();
		private int maxConn;
		private String name;
		private String password;
		private String URL;
		private String user;

		/**
		* �����µ����ӳ�
		*
		* @param name ���ӳ�����
		* @param URL ���ݿ��JDBC URL
		* @param user ���ݿ��ʺ�,�� null
		* @param password ����,�� null
		* @param maxConn �����ӳ������������������
		*/
		public DBConnectionPool(String name, String URL, String user, String password,
				int maxConn) {
				this.name = name;
				this.URL = URL;
				this.user = user;
				this.password = password;
				this.maxConn = maxConn;
				}
		
		/**
		* ������ʹ�õ����ӷ��ظ����ӳ�
		*
		* @param con �ͻ������ͷŵ�����
		*/
		
		public synchronized void freeConnection(Connection conn)
		{
			freeConnections.addElement(conn);
			checkedOut--;
			notifyAll();
		}
		
		/**
		* �����ӳػ��һ����������.��û�п��е������ҵ�ǰ������С���������
		* ������,�򴴽�������.��ԭ���Ǽ�Ϊ���õ����Ӳ�����Ч,�������ɾ��֮,
		* Ȼ��ݹ�����Լ��Գ����µĿ�������. 
		*/
		
		public synchronized Connection getConnection()
		{
			Connection conn = null;
			if(freeConnections.size()>0)
			{
				conn = (Connection)freeConnections.firstElement();
				freeConnections.removeElementAt(0);
				try {
					if(conn.isClosed())
					{
						log.Write("�����ӳ�" + name+"ɾ��һ����Ч����");
						// �ݹ�����Լ�,�����ٴλ�ȡ��������
						conn = getConnection();
					}
				} catch (SQLException e) {					
					// TODO: handle exception
					log.Write("�����ӳ�" + name+"ɾ��һ����Ч����");
					conn = getConnection();				
				}
			}else if(maxConn == 0 || checkedOut < maxConn )
			{
				conn = NewConnection();
			}
			if(conn != null)
			{
				checkedOut ++;
			}
			return conn;
		}

		/**
		* �����ӳػ�ȡ��������.����ָ���ͻ������ܹ��ȴ����ʱ��
		* �μ�ǰһ��getConnection()����.
		*
		* @param timeout �Ժ���Ƶĵȴ�ʱ������
		*/

		public synchronized Connection getConnection(long timeout)
		{
			long startTime = new Date().getTime();
			Connection connection;
			while((connection = getConnection())==null)
			{
				try {
					wait(timeout);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				if((new Date().getTime() - startTime)>=timeout)
				{
					//wait()���ص�ԭ���ǳ�ʱ
					return null;
				}
			}
			return connection;
		}
				
		/**
		* �ر���������
		*/
	
		public synchronized void Release()
		{
			Enumeration allConnections = freeConnections.elements();
			while(allConnections.hasMoreElements())
			{
				Connection connection = (Connection)allConnections.nextElement();
				try {
					connection.close();
					log.Write("�ر����ӳ�" +name+"�е�һ������");
				} catch (SQLException e) {
					log.Write(e,"�޷��ر����ӳ�" + name+"�е�����");				
				}
			}
			freeConnections.removeAllElements();
		}
		
		/**
		* �����µ�����
		*/

		private Connection NewConnection() {
			
			System.out.println("Create NewConnection");
			
			// TODO Auto-generated method stub
			
			System.out.println("user: "+user);	
			System.out.println("password: "+password);
			System.out.println("url: "+URL);
			
			Connection connection = null;
			try {
				if(user == null)
				{
					connection = DriverManager.getConnection(URL);
					
				}else 
				{
					connection = DriverManager.getConnection(URL,user,password);
				}
				System.out.println("���ӳ�" + name+"����һ���µ�����");
				log.Write("���ӳ�" + name+"����һ���µ�����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޷���������URL������: " + URL);
				log.Write(e, "�޷���������URL������: " + URL);
				return null;			
			}
			return connection;
		}
		
	}
	
	
}

