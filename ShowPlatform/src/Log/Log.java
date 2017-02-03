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
	* ���ı���Ϣд����־�ļ�
	*/
	public Log(String logFileName)
	{
		InputStream iStream = getClass().getResourceAsStream("/db.properties");
		Properties dbProps = new Properties();
		try {
			dbProps.load(iStream);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("���ܶ�ȡ�����ļ�. " + 
			"��ȷ��db.properties��CLASSPATHָ����·����");
			return; 
		}		 
		String logfile = dbProps.getProperty(logFileName,"DBConnectionManager.log");

		System.out.println(logfile);
		try {
			log = new PrintWriter(new FileWriter(logfile,true),true); 
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("�޷�����־�ļ�: " + logfile);
			log = new PrintWriter(System.err);			
		}
	}
	
	public void Write(String msg) {
	log.println(new Date() + ": " + msg);
	}

	/**
	* ���ı���Ϣ���쳣д����־�ļ�
	*/
	
	public void Write(Throwable e, String msg) {
	log.println(new Date() + ": " + msg);
	e.printStackTrace(log);
	}
	

}
