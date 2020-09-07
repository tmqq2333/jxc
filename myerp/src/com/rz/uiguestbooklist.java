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
 * Servlet implementation class guestbooklist
 */
@WebServlet("/uiguestbooklist")
public class uiguestbooklist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uiguestbooklist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentpage = 1;
		try {
			String p = request.getParameter("p");
			currentpage = Integer.valueOf(p);
		} catch (Exception e) {
			currentpage = 1;
		}

		DBHelper Dal = new DBHelper();

		
		String strSql = " select id from tbguestbook order by id desc ";
		List<Map<String, Object>> listall = null;
		List<Object> params = new ArrayList<Object>();
		try {
			listall = Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		Pager pageobj = new Pager();
		pageobj.allrecordcount = listall.size();
		pageobj.pagesize = 10;
		pageobj.currentpage = currentpage;
		pageobj.urlname = "";

		
		int startindex = pageobj.pagesize * (pageobj.currentpage - 1);
		String strSqlpager = " select * from tbguestbook order by id desc limit "
				+ startindex + "," + pageobj.pagesize + "";
		List<Map<String, Object>> listpage = null;
		try {
			listpage = Dal.executeQuery(strSqlpager, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String pagestr = pageobj.GetPageInfo();

		request.setAttribute("pagestr", pagestr);
		request.setAttribute("list", listpage);
		request.getRequestDispatcher("/guestbooklist.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
