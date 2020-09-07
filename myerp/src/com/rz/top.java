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
 * Servlet implementation class top
 */
@WebServlet("/top")
public class top extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public top() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Object currentmember=request.getSession().getAttribute("currentuser");
        if(currentmember == null||currentmember == ""||currentmember.equals("")){
        	try{	
        		HttpServletResponse res = (HttpServletResponse) response;
			    response.reset();   
			    request.setCharacterEncoding("UTF-8");
			    response.setContentType("text/html; charset=UTF-8");
			    ServletOutputStream servletOutputStream  = res.getOutputStream();
			    servletOutputStream.println("<script language=javascript>window.prevent.location.href=\"./admin/login.jsp\"</script>");
			
			  }catch (Exception e) {
			}
        	return;
        }
		DBHelper Dal=new DBHelper();		
		String strSql="select * from tbmenubar where tbmenubar.id in (select menuid from tbrolehasmenu where tbrolehasmenu.roleid in (select roleid from tbuserhasrole where userid=?)) and tbmenubar.Visible=1 and tbmenubar.ParentID=0 order by OrderList"; 
		List<Map<String, Object>> list = null;
		List<Object> params = new ArrayList<Object>();		
		String currentuserid=((Map<String,Object>)request.getSession().getAttribute("currentuser")).get("id").toString(); 
		params.add(currentuserid);
		try {
			list=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/top.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
