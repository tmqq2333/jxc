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
 * Servlet implementation class tbtypeedit
 */
@WebServlet("/tbtypeedit")
public class tbtypeedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tbtypeedit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String strSql="select * from tbtype where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);	
		DBHelper Dal=new DBHelper();
		Map<String, Object> objbyid=Dal.getSingleObject(strSql, params);
		request.setAttribute("objbyid",objbyid);
		request.getRequestDispatcher("admin/tbtypeedit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收参数
		String typename=request.getParameter("typename");
		String id=request.getParameter("id");		
		
		String strSql="update tbtype set typename=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(typename);
		params.add(id);
		DBHelper Dal=new DBHelper();		
		Dal.excuteSql(strSql, params);			
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<font color='green'>保存成功！</font>");
		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/tbtypeedit?id="+id);	
	}

}
