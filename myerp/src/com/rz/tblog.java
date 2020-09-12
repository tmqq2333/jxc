package com.rz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class tblog {
	
	public static void addmsg(int typeid,String msg,HttpServletRequest request)
	{
		Date t=new Date();
	    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String ctime=df1.format(t);		  
	    Object currentmember=request.getSession().getAttribute("currentuser");
	    Map<String, Object> obj=(Map<String,Object>)request.getSession().getAttribute("currentuser");	
		String memberid="0";
		String truename="未知";
		if(!(currentmember == null||currentmember == ""||currentmember.equals(""))){
			memberid=obj.get("id").toString();
			truename=obj.get("truename").toString();
		}
		String strSql=" insert into tblog (typeid,userid,username,msg,ctime) values (?,?,?,?,?)";
		//定义参数对象
		List<Object> params = new ArrayList<Object>();
		params.add(typeid);
		params.add(memberid);
		params.add(truename);
		params.add(msg);
		params.add(ctime);
		DBHelper Dal=new DBHelper();		
		Dal.excuteSql(strSql, params);		
	}
	
	
}

