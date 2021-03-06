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
 * Servlet implementation class left
 */
@WebServlet("/left")
public class left extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public left() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object currentmember=request.getSession().getAttribute("currentuser");
//		用户信息
        if(currentmember == null||currentmember == ""||currentmember.equals("")){
        	try{	
        		HttpServletResponse res = (HttpServletResponse) response;
			    response.reset();  //重置
			    request.setCharacterEncoding("UTF-8");
			    response.setContentType("text/html; charset=UTF-8");
    		    ServletOutputStream servletOutputStream  = res.getOutputStream();//获取输出流
			    servletOutputStream.println("<script language=javascript>window.parent.location.href=\"./admin/login.jsp\"</script>");
//			如果没有获得用户数据，则返回登陆。js返回父元素main全局跳转，没有跳回父级，则局部显示
			  }catch (Exception e) {
			}
        	return;
        }
        
		String parentid=request.getParameter("id"); 
		DBHelper Dal=new DBHelper();		
		String strSql="select * from tbmenubar where tbmenubar.id in (select menuid from tbrolehasmenu where tbrolehasmenu.roleid in (select roleid from tbuserhasrole where userid=?)) and tbmenubar.Visible=1 and tbmenubar.ParentID=? order by OrderList"; 
		String currentuserid=((Map<String,Object>)request.getSession().getAttribute("currentuser")).get("id").toString(); 
		List<Map<String, Object>> list = null;
		List<Object> params = new ArrayList<Object>();
		params.add(currentuserid);
		params.add(parentid);
		try {
			list=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("list", list);
		request.getRequestDispatcher("admin/left.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
