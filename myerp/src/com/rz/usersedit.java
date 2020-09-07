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
 * Servlet implementation class usersedit
 */
@WebServlet("/usersedit")
public class usersedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public usersedit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//修改的思路：第一步就是把旧的数据加载出来，是在doget方法里面写的查询。
		String id=request.getParameter("id");
		String strSql="select * from tbusers where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		
		DBHelper Dal=new DBHelper();
		Map<String, Object> objbyid=Dal.getSingleObject(strSql, params);
		request.setAttribute("objbyid",objbyid);
		request.getRequestDispatcher("admin/usersedit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String truename=request.getParameter("truename");	
		String tel=request.getParameter("tel");
		String memo=request.getParameter("memo");
		String id=request.getParameter("id");		
		
		String strSql="update tbusers set username=?,password=?,tel=?,memo=?,truename=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(password);
		params.add(tel);
		params.add(memo);
		params.add(truename);
		params.add(id);
		DBHelper Dal=new DBHelper();		
		Dal.excuteSql(strSql, params);			
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<font color='green'>保存成功！</font>");
		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/usersedit?id="+id);	
	}

}
