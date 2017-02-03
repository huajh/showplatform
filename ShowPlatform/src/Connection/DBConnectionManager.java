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
	* 返回唯一实例.如果是第一次调用此方法,则创建实例
	*
	* @return DBConnectionManager 唯一实例
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
	* 构造函数私有以防止其它对象创建本类实例
	*/
	private DBConnectionManager()
	{
		System.out.println(" create DBConnectionManager");
		Init();
	}
	
	/**
	* 将连接对象返回给由名字指定的连接池
	*
	* @param name 在属性文件中定义的连接池名字
	* @param conn 连接对象
	*/
	
	public void freeConnection(String name,Connection conn)
	{ 
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if(pool !=null){
			pool.freeConnection(conn);
		}
	}
	
	/**
	* 获得一个可用的(空闲的)连接.如果没有可用连接,且已有连接数小于最大连接数
	* 限制,则创建并返回新连接
	*
	* @param name 在属性文件中定义的连接池名字
	* @return Connection 可用连接或null
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
	* 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制,
	* 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
	*
	* @param name 连接池名字
	* @param time 以毫秒计的等待时间
	* @return Connection 可用连接或null
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
	* 关闭所有连接,撤销驱动程序的注册
	*/
	public synchronized void Release()
	{
		// 等待直到最后一个客户程序调用
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
				log.Write("撤销JDBC驱动程序 " + driver.getClass().getName()+"的注册");
			} catch (SQLException e) {
				// TODO: handle exception
				log.Write(e, "无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
			}
		}
		
	}
	
	private void Init() {
		// TODO Auto-generated method stub
		Properties dbProps = new Properties();
		InputStream iStream = getClass().getResourceAsStream("/db.properties");
		try {
			dbProps.load(iStream);
			System.out.println("读取数据成功!");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("不能读取属性文件. " +
			"请确保db.properties在CLASSPATH指定的路径中");
			return; 
		}
		log = new Log("connlog");
		loadDrivers(dbProps);
		createPools(dbProps);
	}
	
	/**
	* 装载和注册所有JDBC驱动程序
	*
	* @param props 属性
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
				
				System.out.println("成功注册JDBC驱动程序" + driverClassName);
				
				log.Write("成功注册JDBC驱动程序" + driverClassName);
				
			} catch (Exception e) { 
				// TODO: handle exception
				
				System.out.println("无法注册JDBC驱动程序: " + driverClassName);
				
				log.Write("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
			}
		}		
	}
	
	
	/**
	* 根据指定属性创建连接池实例.
	*
	* @param props 连接池属性
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
					log.Write("没有为连接池" + poolName + "指定URL");
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
					log.Write("错误的最大连接数限制: " + maxconn + " .连接池: " + poolName);
					max = 0;
				}
				
				DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
				log.Write("成功创建连接池" + poolName);
			}			
		}
	}
	
	
	/**
	* 此内部类定义了一个连接池.它能够根据要求创建新连接,直到预定的最
	* 大连接数为止.在返回连接给客户程序之前,它能够验证连接的有效性.
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
		* 创建新的连接池
		*
		* @param name 连接池名字
		* @param URL 数据库的JDBC URL
		* @param user 数据库帐号,或 null
		* @param password 密码,或 null
		* @param maxConn 此连接池允许建立的最大连接数
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
		* 将不再使用的连接返回给连接池
		*
		* @param con 客户程序释放的连接
		*/
		
		public synchronized void freeConnection(Connection conn)
		{
			freeConnections.addElement(conn);
			checkedOut--;
			notifyAll();
		}
		
		/**
		* 从连接池获得一个可用连接.如没有空闲的连接且当前连接数小于最大连接
		* 数限制,则创建新连接.如原来登记为可用的连接不再有效,则从向量删除之,
		* 然后递归调用自己以尝试新的可用连接. 
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
						log.Write("从连接池" + name+"删除一个无效连接");
						// 递归调用自己,尝试再次获取可用连接
						conn = getConnection();
					}
				} catch (SQLException e) {					
					// TODO: handle exception
					log.Write("从连接池" + name+"删除一个无效连接");
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
		* 从连接池获取可用连接.可以指定客户程序能够等待的最长时间
		* 参见前一个getConnection()方法.
		*
		* @param timeout 以毫秒计的等待时间限制
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
					//wait()返回的原因是超时
					return null;
				}
			}
			return connection;
		}
				
		/**
		* 关闭所有连接
		*/
	
		public synchronized void Release()
		{
			Enumeration allConnections = freeConnections.elements();
			while(allConnections.hasMoreElements())
			{
				Connection connection = (Connection)allConnections.nextElement();
				try {
					connection.close();
					log.Write("关闭连接池" +name+"中的一个连接");
				} catch (SQLException e) {
					log.Write(e,"无法关闭连接池" + name+"中的连接");				
				}
			}
			freeConnections.removeAllElements();
		}
		
		/**
		* 创建新的连接
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
				System.out.println("连接池" + name+"创建一个新的连接");
				log.Write("连接池" + name+"创建一个新的连接");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("无法创建下列URL的连接: " + URL);
				log.Write(e, "无法创建下列URL的连接: " + URL);
				return null;			
			}
			return connection;
		}
		
	}
	
	
}

