package Validation;

import java.sql.ResultSet;

import DBOperator.DBOperator;
import Log.Log;

public class Validation {
	
	static private Validation Instance;
	static private int Clients ;
	private DBOperator dbOperator;
	private Log log;
	
	static synchronized public Validation getInstance()
	{
		System.out.println("start DBOperator getInstance ");
		if(Instance == null){
			Instance = new Validation();
		}
		Clients ++;
		return Instance;
	}
	
	private Validation()
	{
		dbOperator = DBOperator.getInstance();
		log = new Log("Validationlog");
	}
	/**
	 * 按主键查询是否存在
	 * @param obj
	 * @param Property
	 * @param value
	 * @return
	 */
	public boolean isExsit(Object obj,String Property,String value)
	{
		log.Write("查询表"+obj.getClass().getSimpleName()+"是否有主键"+Property+"的值为"+value);
		
		ResultSet rst = dbOperator.Load(obj,Property,value);
		
		if(rst==null)
		{
			log.Write("结果：NO");
			return false;
		}
		log.Write("结果：YES");
		return true;
	}
	
}
