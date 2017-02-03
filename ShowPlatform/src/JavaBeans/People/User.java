package JavaBeans.People;

import java.util.HashMap;

import DBOperator.DBOperator;
import Factory.NewsFactory;

public abstract class User extends People {
	
	public abstract void load();
	
	public abstract void insert();
	
	public abstract void Save();
	
	public abstract void delete();
	
	public abstract boolean isExist();

	public void setRealName(String realname) {
		RealName = realname;
	}
	public String getRealName() {
		return RealName;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSex() {
		return Sex;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getAge() {
		return Age;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getPwd() {
		return Pwd;
	}
	

	protected void RefreshValueMap()
	{	
		ValueMap.put("Age",getAge());
		ValueMap.put("Pwd",getPwd());
		ValueMap.put("RealName",getRealName());
		ValueMap.put("Sex",getSex());	
	}
	
	private String RealName;
	private String Sex;
	private int Age;
	private String Pwd;
	
	protected HashMap ValueMap = new HashMap();
	
}
