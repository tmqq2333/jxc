package com.rz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class guestbookadd
 */
@WebServlet("/uiguestbookadd")
public class uiguestbookadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uiguestbookadd() {
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
		 request.setCharacterEncoding("utf-8"); 
		 String username=request.getParameter("username"); 
		 String tel=request.getParameter("tel"); 
		 String contents=request.getParameter("contents"); 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String createtime=df.format(new Date());	
		 
		 DBHelper Dal=new DBHelper();
		 String strSql=" insert into tbguestbook (username,tel,contents,createtime,isshow) values (?,?,?,?,?) "; 
		 List<Object> params = new ArrayList<Object>();
		 params.add(username);
		 params.add(tel);
		 params.add(contents);
		 params.add(createtime);
		 params.add("1");
		
		 Dal.excuteSql(strSql, params);
		 response.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset=utf-8");
		 response.getWriter().write("<font color='green'>操作已经成功，即将跳转</font>");
         response.setHeader("Refresh", "5;URL="+request.getContextPath()+"/guestbookadd.jsp");
	}

}
