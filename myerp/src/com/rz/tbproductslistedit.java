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
 * Servlet implementation class tbproductslistedit
 */
@WebServlet("/tbproductslistedit")
public class tbproductslistedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Object Tel = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tbproductslistedit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//修改的思路：第一步就是把旧的数据加载出来，是在doget方法里面写的查询。
		String id=request.getParameter("id");
		String strSql="select * from tbproducts where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		
		DBHelper Dal=new DBHelper();
		Map<String, Object> objbyid=Dal.getSingleObject(strSql, params);
		request.setAttribute("objbyid",objbyid);
		request.getRequestDispatcher("admin/tbproductslistedit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收参数
		String production=request.getParameter("production");
		String Attendant=request.getParameter("Attendant");
		String Tel=request.getParameter("Tel");
		String id=request.getParameter("id");		
		
		String strSql="update tbproducts set production=?,Attendant=?,Tel=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(production);
		params.add(Attendant);
		params.add(Tel);
		params.add(id);
		DBHelper Dal=new DBHelper();		
		Dal.excuteSql(strSql, params);			
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<font color='green'>保存成功！</font>");
		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/tbproductslistedit?id="+id);	
	}

}