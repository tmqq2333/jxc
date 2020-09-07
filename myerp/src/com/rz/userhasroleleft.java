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
 * Servlet implementation class userhasroleleft
 */
@WebServlet("/userhasroleleft")
public class userhasroleleft extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userhasroleleft() {
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
		String userid = request.getParameter("userid");
		DBHelper Dal = new DBHelper();		
		String strSql = " SELECT id from tbroles where id not in (SELECT roleid from tbuserhasrole where userid=?) ";
		List<Map<String, Object>> listall = null;
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
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
		pageobj.w="userid="+userid;
		int startindex = pageobj.pagesize * (pageobj.currentpage - 1);
		String strSqlpager = " SELECT * from tbroles where id not in (SELECT roleid from tbuserhasrole where userid=?) order by id desc limit "
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
		request.getRequestDispatcher("/admin/userhasroleleft.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
