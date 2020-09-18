package com.rz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tbproductslist
 */
@WebServlet("/tbproductslist")
public class tbproductslist extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public tbproductslist() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> currentuser=(Map<String,Object>)request.getSession().getAttribute("currentuser");
		if(currentuser==null)
		{
			try{	
        		HttpServletResponse res = (HttpServletResponse) response;
			    response.reset();   
			    request.setCharacterEncoding("UTF-8");
			    response.setContentType("text/html; charset=UTF-8");
			    ServletOutputStream servletOutputStream  = res.getOutputStream();
			    servletOutputStream.println("<script language=javascript>window.parent.location.href=\"./admin/login.jsp\"</script>");
			
			  }catch (Exception e) {
			}
        	return;
		}
	
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
		
		String strSql=" select id from tbproducts order by id desc "; 
		List<Map<String, Object>> listall = null;
		List<Object> params = new ArrayList<Object>();

		try {
			listall=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Pager pageobj=new Pager();
		pageobj.allrecordcount=listall.size();
		pageobj.pagesize=10;
		pageobj.currentpage=currentpage;
		pageobj.urlname="";
		
		int startindex=pageobj.pagesize*(pageobj.currentpage-1);
		String strSqlpager=" select * from tbproducts order by id desc limit "+startindex+","+pageobj.pagesize+""; 
		List<Map<String, Object>> listpage = null;
		try {
			listpage=Dal.executeQuery(strSqlpager, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String pagestr=pageobj.GetPageInfo();
		request.setAttribute("pagestr", pagestr);
		request.setAttribute("list", listpage);
		request.getRequestDispatcher("./admin/tbproductslist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

