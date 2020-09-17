package com.rz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class orderheadlist
 */
@WebServlet("/orderheadlist")
public class orderheadlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderheadlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentpage=1;
		try
		{
			String p=request.getParameter("p"); 
			currentpage = Integer.valueOf(p);
		}
		catch(Exception e){
			currentpage=1;
		}
		
		DBHelper Dal=new DBHelper();
		
		String strSql=" select id from tborderhead order by id desc "; 
		List<Map<String, Object>> listall = null;
		List<Object> params = new ArrayList<Object>();
		try {
			listall=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//视图数据放进材料表里 
		 String strSqp=" select * from v_orderitems"; 
		 List<Map<String,Object>> listitemsp=new ArrayList<Map<String, Object>>();
		 List<Object> paramsp = new ArrayList<Object>();
		 try {
			 listitemsp=Dal.executeQuery(strSqp, paramsp);
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 for (Object item : listitemsp) {
		 	Map<String, Object> temp=(Map<String, Object>)item;
			String proname=temp.get("proname").toString();
//			Float sumprocount=temp.get("sumprocount").Float.valueOf();
			double sumprocount=(double)temp.get("sumprocount");
			double sumprice=(double)temp.get("sumprice");
			String  strSqlt="update tbcliaozong set zprocount=?,zprice=?  where proname=?";
			
			List<Object> params9 = new ArrayList<Object>();
			params9.add(sumprocount);
			params9.add(sumprice);	
			params9.add(proname);
			Dal.excuteSql(strSqlt, params9);
			
			
		 }		
	
		//取出id对比，如果有，执行修改数量，金额。无则添加
		//数据库取出同一产品的数量，添加
		//
		
		/*listall2.proid
		if()*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//分页
		Pager pageobj=new Pager();
		pageobj.allrecordcount=listall.size();
		pageobj.pagesize=10;
		pageobj.currentpage=currentpage;
		pageobj.urlname="";
		
		int startindex=pageobj.pagesize*(pageobj.currentpage-1);
		String strSqlpager=" select * from tborderhead  order by id desc limit "+startindex+","+pageobj.pagesize+""; 
		List<Map<String, Object>> listpage = null;
		try {
			listpage=Dal.executeQuery(strSqlpager, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String pagestr=pageobj.GetPageInfo();
		request.setAttribute("pagestr", pagestr);
		request.setAttribute("list", listpage);
		request.getRequestDispatcher("./admin/orderheadlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
