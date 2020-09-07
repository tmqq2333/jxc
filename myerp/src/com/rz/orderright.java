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
 * Servlet implementation class orderright
 */
@WebServlet("/orderright")
public class orderright extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderright() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionid=request.getSession().getId();
		List<Map<String, Object>> carlistall = null;
		List<Object> params2 = new ArrayList<Object>();
		DBHelper Dal=new DBHelper();
		String strSqlpager=" select  * from tbshoppingcar where sessionid=? "; 
		params2.add(sessionid);
		try {
			carlistall=Dal.executeQuery(strSqlpager, params2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("carlistall", carlistall);		
		request.getRequestDispatcher("/admin/orderright.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id=request.getParameter("id");
//		DBHelper db=new DBHelper();
//		String sessionid=request.getSession().getId();
//		Boolean flag=false;
//    	String StrSqlexist="select * from tbshoppingcar where sessionid=? and proid=? ";
//    	List<Map<String, Object>> carrecordlist = null;
//    	List<Object> paramsexist = new ArrayList<Object>();
//    	paramsexist.add(sessionid);
//    	paramsexist.add(id);
//    	try {
//			carrecordlist=db.executeQuery(StrSqlexist, paramsexist);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    	if(carrecordlist.size()>0)
//    	{
//    		flag=true;
//    	}
//		if(flag)
//		{
//			//修改个数
//			String strSql1="update tbshoppingcar set procount=procount+1 where sessionid=? and proid=? ";
//			List<Object> paramsupdate = new ArrayList<Object>();
//			paramsupdate.add(sessionid);
//			paramsupdate.add(id);
//			db.excuteSql(strSql1, paramsupdate);
//		}
//		else
//		{
//			//根据id把商品信息查询出来。
//			List<Object> params = new ArrayList<Object>();
//			String StrSqlpro="select * from tbproduct where id="+id;
//			Map<String, Object> obj=db.getSingleObject(StrSqlpro, params);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//			String createtime=df.format(new Date());
//			//新增
//			String strSql2="insert into tbshoppingcar (sessionid,proname,proid,procount,ctime,imgurl,price) values(?,?,?,?,?,?,?) ";
//			params.add(sessionid);
//			params.add(obj.get("proname"));
//			params.add(id);
//			params.add(1);
//			params.add(createtime);
//			params.add(obj.get("imgurl"));
//			params.add(obj.get("price"));
//			db.excuteSql(strSql2, params);
//			
//		}
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/json;charset=utf-8");
//		response.getWriter().write("{\"msg\":\"ok\"}");
	}

}
