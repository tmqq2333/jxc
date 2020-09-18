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
 * Servlet implementation class tbproducts
 */
@WebServlet("/tbproducts")
public class tbproducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public tbproducts() {
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
		//1、先接受前端传递过来的参数
		request.setCharacterEncoding("utf-8");
		String production=request.getParameter("production");
		String Attendant=request.getParameter("Attendant");
		String Tel=request.getParameter("Tel");		
		
		
		//2、把接受到的参数添加到数据库
		
		//定义sql语句
		String strSql=" insert into tbproducts (production,Attendant,Tel) values (?,?,?)";
		//定义参数对象
		List<Object> params = new ArrayList<Object>();
		params.add(production);
		params.add(Attendant);	
		params.add(Tel);
			
		DBHelper Dal=new DBHelper();		
		Dal.excuteSql(strSql, params);		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<font color='green'>保存成功！</font>");
		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/admin/tbproducts.jsp");	
	}

}
