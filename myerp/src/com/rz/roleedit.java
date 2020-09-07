package com.rz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class roleedit
 */
@WebServlet("/roleedit")
public class roleedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public roleedit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String strSql="select * from tbroles where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);	
		DBHelper Dal=new DBHelper();
		Map<String, Object> objbyid=Dal.getSingleObject(strSql, params);
		request.setAttribute("objbyid",objbyid);
		request.getRequestDispatcher("admin/roleedit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收参数
		String rolename=request.getParameter("rolename");
		String memo=request.getParameter("memo");
		String id=request.getParameter("id");		
		String strSql="update tbroles set rolename=?,memo=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(rolename);
		params.add(memo);
		params.add(id);
		DBHelper Dal=new DBHelper();		
		Dal.excuteSql(strSql, params);			
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<font color='green'>保存成功！</font>");
		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/roleedit?id="+id);	
	}

}
