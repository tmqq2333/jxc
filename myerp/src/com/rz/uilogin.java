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
 * Servlet implementation class uilogin
 */
@WebServlet("/uilogin")
public class uilogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uilogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username"); 
		String password=request.getParameter("password"); 
		DBHelper Dal=new DBHelper();
		String strSql=" select * from tbmember where loginname='"+username+"' and password='"+password+"'"; 
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> memlist = null;
		try {
			memlist=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(memlist.size()>0)
		{
			request.getSession().setAttribute("currentmember", memlist.get(0));
			response.sendRedirect("index");
		}
		else
		{
			request.setAttribute("msg", "用户名或者密码错误");
			request.getRequestDispatcher("/uilogin.jsp").forward(request, response);
		}
	}

}
