package com.rz;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class uiupdatecarcount
 */
@WebServlet("/uiupdatecarcount")
public class uiupdatecarcount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uiupdatecarcount() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String idstr=request.getParameter("paras");
	   JSONArray jsonArray = JSONArray.fromObject(paras);//解析json
	   String[] idarray = idstr.split(",");//放入数组，隔开
	    for(String s:idarray)//循环插入数据
       {
       
       		addtocarsinge(request,response,s);
//       		获取数据
       	
       }		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
		
	}
   
   
	
	protected void addtocarsinge(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException {
		DBHelper db=new DBHelper();
		String sessionid=request.getSession().getId();
		Boolean flag=false;
	   	String StrSqlexist="update tbshoppingcar set procount=?, price=? where sessionid=? and proid=?  ";
	   	List<Map<String, Object>> carrecordlist = null;
	   	List<Object> paramsexist = new ArrayList<Object>();
	   	paramsexist.add(sessionid);
	   	paramsexist.add(id);
	   	try {
				carrecordlist=db.executeQuery(StrSqlexist, paramsexist);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   	if(carrecordlist.size()>0)
	   	{
	   		flag=true;
	   	}
			if(flag)
			{
				//修改个数
				String strSql1="update tbshoppingcar set procount=procount+1 where sessionid=? and proid=? ";
				List<Object> paramsupdate = new ArrayList<Object>();
				paramsupdate.add(sessionid);
				paramsupdate.add(id);
				db.excuteSql(strSql1, paramsupdate);
			}
			else
			{
				//根据id把商品信息查询出来。
				List<Object> params = new ArrayList<Object>();
				String StrSqlpro="select * from tbproduct where id="+id;
				Map<String, Object> obj=db.getSingleObject(StrSqlpro, params);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String createtime=df.format(new Date());
				//新增
				String strSql2="insert into tbshoppingcar (sessionid,proname,proid,procount,ctime,imgurl) values(?,?,?,?,?,?) ";
				params.add(sessionid);
				params.add(obj.get("proname"));
				params.add(id);
				params.add(1);
				params.add(createtime);
				params.add(obj.get("imgurl"));
				/*params.add(obj.get("price"));*/
				db.excuteSql(strSql2, params);
				
			}
		}
}
	   
	   
	   
	   
	   
	   //		String id=request.getParameter("id");
//	    String newprice=request.getParameter("newprice");// newvalue:newvalue,
//		String newvalue=request.getParameter("newvalue");
//		String sessionid=request.getSession().getId();    
//		String strSql="update tbshoppingcar set procount=?, price=? where sessionid=? and proid=? ";
//		DBHelper db=new DBHelper();
//		List<Object> params = new ArrayList<Object>();
//		params.add(newvalue);
//		params.add(newprice);
//		params.add(sessionid);
//		params.add(id);
//		db.excuteSql(strSql, params);
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/json;charset=utf-8");
//		response.getWriter().write("{\"msg\":\"ok\"}");
	


