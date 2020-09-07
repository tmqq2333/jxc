package com.rz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		String id=request.getParameter("id");
		String countvalue=request.getParameter("countvalue");
		String sessionid=request.getSession().getId();    
		String strSql="update tbshoppingcar set procount=? where sessionid=? and proid=? ";
		DBHelper db=new DBHelper();
		List<Object> params = new ArrayList<Object>();
		params.add(countvalue);
		params.add(sessionid);
		params.add(id);
		db.excuteSql(strSql, params);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
	}

}
