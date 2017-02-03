package JavaBeans.Components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import DBOperator.DBOperator;
import JavaBeans.People.Member;

public class Score {
	
	private int Value;
	private int WorksID;
	private String MemberID;
	
	private HashMap ValueMap = new HashMap();
	
	private void RefreshValueMap()
	{		
		ValueMap.put("Value",getValue());	
		ValueMap.put("WorksID",getWorksID());	
		ValueMap.put("MemberID",getMemberID());		
	}
	
	public static Integer getScore(String memberID,int WorksID)
	{
		String sql = "select value from Score where memberID = '"+memberID+"' and WorksID = "+WorksID;
		DBOperator dbOperator = DBOperator.getInstance();
		ResultSet rst =  dbOperator.Query(sql);
		Collection c = new ArrayList();
		try {
			while(rst.next())
			{		
				Integer value = rst.getInt("Value");
				return value;
			}
			rst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public void insert()
	{
		DBOperator dbOperator = DBOperator.getInstance();
		this.RefreshValueMap();
		dbOperator.Insert(this, ValueMap);
	}
	
	public void setValue(int value) {
		Value = value;
	}
	public int getValue() {
		return Value;
	}
	public void setWorksID(int worksID) {
		WorksID = worksID;
	}
	public int getWorksID() {
		return WorksID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getMemberID() {
		return MemberID;
	}
	
	

}
