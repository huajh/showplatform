package Log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

public class Log {
	
	private PrintWriter log;
	
	/**
	* 将文本信息写入日志文件
	*/
	public Log(String logFileName)
	{
		InputStream iStream = getClass().getResourceAsStream("/db.properties");
		Properties dbProps = new Properties();
		try {
			dbProps.load(iStream);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("不能读取属性文件. " + 
			"请确保db.properties在CLASSPATH指定的路径中");
			return; 
		}		 
		String logfile = dbProps.getProperty(logFileName,"DBConnectionManager.log");

		System.out.println(logfile);
		try {
			log = new PrintWriter(new FileWriter(logfile,true),true); 
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("无法打开日志文件: " + logfile);
			log = new PrintWriter(System.err);			
		}
	}
	
	public void Write(String msg) {
	log.println(new Date() + ": " + msg);
	}

	/**
	* 将文本信息与异常写入日志文件
	*/
	
	public void Write(Throwable e, String msg) {
	log.println(new Date() + ": " + msg);
	e.printStackTrace(log);
	}
	

}
