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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		String usernam=request.getParameter("username"); 
		String passwor=request.getParameter("password"); 
		String username=comm.sqlValidate(usernam); 
		String password=comm.sqlValidate(passwor); 
		
		DBHelper Dal=new DBHelper();
		String strSql=" select * from tbusers where username='"+username+"' and password='"+password+"'"; 
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> userlist = null;
		try {
			userlist=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(userlist.size()>0)
		{
			//currentmember
			request.getSession().setAttribute("currentuser", userlist.get(0));
			tblog.addmsg(1, "正常登陆系统,用户名为:"+username, request);
			response.sendRedirect("/myerp/admin/default.jsp");
			
		}
		else
		{
			request.setAttribute("msg", "用户名或密码错误");
			String msg="登录系统失败，用户名为:"+username+"密码为:"+password;
			tblog.addmsg(3, msg, request);
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		}
	}

}
