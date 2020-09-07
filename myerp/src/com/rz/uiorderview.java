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
 * Servlet implementation class uiorderview
 */
@WebServlet("/uiorderview")
public class uiorderview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uiorderview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> currentmember=(Map<String,Object>)request.getSession().getAttribute("currentmember");
		if(currentmember==null)
		{
			response.sendRedirect("uilogin.jsp");
			return;
		}
		String memberid=currentmember.get("id").toString();
		
		String orderid=request.getParameter("orderid"); 
		 DBHelper Dal=new DBHelper();
		 String strSql1=" select * from tborderhead where orderid=? and memberid=? "; 
		 Map<String, Object> objbyid = null;
		 List<Object> params = new ArrayList<Object>();
		 params.add(orderid);
		 params.add(memberid);
		 objbyid=Dal.getSingleObject(strSql1, params);
		 
		 String strSql2=" select * from tborderitems where orderid=? "; 
		 List<Object> params2 = new ArrayList<Object>();
		 params2.add(orderid);
		 List<Map<String,Object>> listitems=new ArrayList<Map<String, Object>>();
		 try {
			 listitems=Dal.executeQuery(strSql2, params2);
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 request.setAttribute("orderhead", objbyid);
		 request.setAttribute("orderitems", listitems);
		 request.getRequestDispatcher("/uiorderview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
