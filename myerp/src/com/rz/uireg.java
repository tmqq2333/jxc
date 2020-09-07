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
 * Servlet implementation class uireg
 */
@WebServlet("/uireg")
public class uireg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uireg() {
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
		String loginname=request.getParameter("loginname");
		String password=request.getParameter("password");
		String truename=request.getParameter("truename");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String strSql="insert into tbmember (loginname,password,truename,tel,address) values (?,?,?,?,?)";
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		params.add(loginname);
		params.add(password);
		params.add(truename);
		params.add(tel);
		params.add(address);
		db.excuteSql(strSql, params);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
	}

}
